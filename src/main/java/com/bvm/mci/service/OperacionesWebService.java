package com.bvm.mci.service;

import com.bvm.mci.dto.PerfilDTO;
import com.bvm.mci.dto.UsuarioDTO;

public interface OperacionesWebService {

    UsuarioDTO getUser(String userName);

    String getClaveCodificada(String idUsuario);

    PerfilDTO getPerfilByuser(String idUsuario);

    Long getPerfil(String usuarioName);
}
