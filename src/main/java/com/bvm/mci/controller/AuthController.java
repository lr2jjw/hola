package com.bvm.mci.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.LoginDTO;
import com.bvm.mci.dto.SesionManagerDTO;
import com.bvm.mci.service.AuthenticationService;
import com.bvm.mci.shared.Constantes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constantes.PATH + "auth")
@RequiredArgsConstructor
public class AuthController {

    private Log log = LogFactory.getLog(AuthController.class);
    private final AuthenticationService authService;

    @PostMapping("/login")
    public SesionManagerDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.loginSession(loginDTO);
    }

    @GetMapping("/actividades")
    public List<ActividadesPerfil> actividades(@RequestHeader(value = "Authorization") String token){
        log.info("Token: " + token);
        return authService.getActividadesPerfil(token);
    }

}
