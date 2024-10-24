package com.bvm.mci.security.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.bvm.mci.exception.BusinessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

    @Value("${bmv.mci.api.jwtSecret}")
    private String jwtSecret;

    @Value("${bmv.mci.api.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Log log = LogFactory.getLog(JwtUtils.class);

    public String getJWTToken(String username, String roles, String name) throws BusinessException {
        log.debug("metodo para generar token....");
        String token = null;

        try {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

            token = Jwts.builder().setId("bmvJWT").setSubject(username)
                    .claim("authorities",
                            grantedAuthorities.stream().map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .claim("username", name).setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        } catch (Exception e) {
            log.error("Error al generar el token {}", e);
            throw new BusinessException("Error al iniciar sesion. Consulte con el administrador del sistema");
        }
        return token;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("username").toString();
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        @SuppressWarnings("unchecked")
        List<String> authorities = claims.get("authorities", List.class);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (authorities != null) {
            for (String authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return grantedAuthorities;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: "+ e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: "+ e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: "+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: "+ e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: "+ e.getMessage());
        }
        return false;
    }


}
