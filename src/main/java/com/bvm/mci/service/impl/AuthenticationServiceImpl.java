package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.LoginDTO;
import com.bvm.mci.dto.SesionManagerDTO;
import com.bvm.mci.exception.BusinessException;
import com.bvm.mci.security.jwt.JwtUtils;
import com.bvm.mci.service.AdmonUsuariosService;
import com.bvm.mci.service.AuthServiceRepository;
import com.bvm.mci.service.AuthenticationService;
import com.bvm.mci.service.OperacionesWebService;
import com.bvm.mci.shared.Constantes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final OperacionesWebService operacionesWebService;

    private final AdmonUsuariosService admonUsuariosService;

    private final AuthServiceRepository authServiceRepository;

    private final PasswordEncoder passwordEncoder;

    private final String preferenteU;

    private final String preferenteP;

    private final JwtUtils jwtUtils;

    private  final List<Long> MENUS = Collections.unmodifiableList(Arrays.asList(Constantes.SECCION_REPROCE_ACCESO, Constantes.SECCION_ETF_ACCESO, Constantes.SECCION_USUARIOS_ACCESO, Constantes.SECCION_ALERTASADMON_ACCESO,
    Constantes.SECCION_INDXEMI_ACCESO, Constantes.SECCION_OPERADOR_ACCESO));

    private Log log = LogFactory.getLog(AuthenticationServiceImpl.class);


    public SesionManagerDTO loginSession(LoginDTO loginDTO) throws BusinessException {
        log.debug("LOGIN => "+ loginDTO);

        SesionManagerDTO sesionManagerDTO = new SesionManagerDTO();

            String storedPass = operacionesWebService.getClaveCodificada(loginDTO.getUserName());
            log.info(" [ storedPass] ["+storedPass+"] Si no es null es usuario de MCI");
            if (storedPass == null)
                throw new UsernameNotFoundException("Usuario y/o contraseña incorrecta");
            // preferenteU : es el usuario que esta en los properties.
            log.info("---- usuario["+loginDTO.getUserName()+"]  usuario preferente["+preferenteU+" ]");
            if(loginDTO.getUserName().equals(preferenteU)){
                log.info("validando si es un usuario preferente");

                // se comenta temporalmente	hasta que tengamos conexion a ldap
                log.info("---- usuario password["+loginDTO.getUserName()+"]  preferente["+preferenteU+" ]");
                log.info("comparando contrasenas :[ "+passwordEncoder.matches(loginDTO.getPassword(), preferenteP) +"]");
                if ( !passwordEncoder.matches(loginDTO.getPassword(), preferenteP))
                    //if (!standardPasswordEncoder.matches(loginDTO.getPassword(), preferenteP))
                    throw new BadCredentialsException(" Usuario y/o contraseña incorrecta");
                else
                    log.warn("Usando usuario bypass ...");
            }else {
                log.info("validando si es un usuario LDAP");
                // se comenta temporalmente	hasta que tengamos conexion a ldap
                //if(!ldapAuthenticationProvider.authenticateActiveDirectory(loginDTO.getUserName(), loginDTO.getPassword(), ldapDomain,
                //		ldapUrl, ldapDn))
                //  throw new BadCredentialsException("Invalid password for user: " + loginDTO.getUserName());
            }
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            sesionManagerDTO.setRoles(roles);
            //Long idPerfil = operacionesWebService.getPerfil(loginDTO.getUserName());
            //sesionManagerDTO.setProfiles( operacionesWebService.getPerfilByuser(loginDTO.getUserName()) );
           // sesionManagerDTO.setActivadesPerfil(getActividadesPerfil(idPerfil, loginDTO.getUserName()));

            String claveUsuario = operacionesWebService.getUser(loginDTO.getUserName()).getClaveUsuario();
            sesionManagerDTO.setToken( jwtUtils.getJWTToken(claveUsuario, sesionManagerDTO.getRoles().toString(), loginDTO.getUserName()));

        return sesionManagerDTO;
    }


    public List<ActividadesPerfil> getActividadesPerfil(String token){
        log.info("Asignando actividades");

        String username = jwtUtils.getUserName(token.split(" ")[1]);
        Long idPerfil = operacionesWebService.getPerfil(username);

        List<ActividadesPerfil> actividadesPermitidas = new ArrayList<>();
        List<ActividadesPerfil> listaActividades = admonUsuariosService.getActividadesPerfil(idPerfil);

        listaActividades.forEach(act -> {
            if(MENUS.contains(act.getIdActividad()))
                actividadesPermitidas.add(act);

        });

        Collections.sort(actividadesPermitidas);

        return actividadesPermitidas;
    }

    @Override
    public Boolean isFunctionAccesible(String idUsuario, Long idActividad) {
        return authServiceRepository.isFunctionAccesible(idUsuario, idActividad);
    }
}
