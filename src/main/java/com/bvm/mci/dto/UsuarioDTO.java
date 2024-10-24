package com.bvm.mci.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String claveUsuario;

    private String password;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String nombreCompleto;

    private String ip;

    private String system;

    private List<String> institutions;
}
