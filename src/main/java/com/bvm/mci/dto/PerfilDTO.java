package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PerfilDTO implements Serializable {
    private static final long serialVersionUID = -7685631628655827305L;
    private Long idPerfil;
    private String nombrePerfil;
    private String descripcion;
    private List<FuncionDTO> listaFunciones;
}