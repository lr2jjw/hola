package com.bvm.mci.service;

import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.LoginDTO;
import com.bvm.mci.dto.SesionManagerDTO;

import java.util.List;

public interface AuthenticationService {

    SesionManagerDTO loginSession(LoginDTO loginDTO);

    Boolean isFunctionAccesible( java.lang.String idUsuario, java.lang.Long idActividad);

    List<ActividadesPerfil> getActividadesPerfil(String token);
}
