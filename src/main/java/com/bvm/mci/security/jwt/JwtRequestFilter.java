package com.bvm.mci.security.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bvm.mci.exception.BusinessException;
import com.bvm.mci.security.config.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private Log log = LogFactory.getLog(JwtRequestFilter.class);

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException, BusinessException {
        log.info("ejecutando filtro...");
        try {
            String jwt = parseJwt(request);
            log.info(jwt);
            if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String userkey = jwtUtils.getUserNameFromJwtToken(jwt);
                String username = jwtUtils.getUserName(jwt);
                List<GrantedAuthority> authorities = jwtUtils.getAuthorities(jwt);
                log.info("usuario obtenido " + userkey);

                UserDetailsImpl userDetails = new UserDetailsImpl(userkey,null, authorities,username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch(BusinessException e) {
            throw new BusinessException("Failed to get user authentication");
        }
        catch(Exception e) {
            log.error("Failed to get user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
