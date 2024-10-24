package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSimple implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;
    private Long idPerfil;
    private String claveUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String perfil;
}
