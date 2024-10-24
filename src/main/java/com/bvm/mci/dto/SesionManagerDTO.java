package com.bvm.mci.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SesionManagerDTO implements Serializable {

    private static final long serialVersionUID = 2469930871089227105L;


    private PerfilDTO profiles;
    private List<ActividadesPerfil> activadesPerfil;

    @JsonIgnore
    private List<String> roles;
    private UsuarioSimple datosUsuario;

    private String token;
}
