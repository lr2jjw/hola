package com.bvm.mci.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author �l
 * Clase de transformaci&oacute;n para el servicio de GrupoListaService.getGrupos
 * donde
 * identificador: GrupoIndice.id
 * descripcion: GrupoIndice.descripcion
 * 
 */ 


 
@Data
@NoArgsConstructor
public class GruposDisponibles {

    private String identificador;
    private String descripcion;

    public GruposDisponibles(String identificador, String descripcion) {
        this.identificador = identificador;
        this.descripcion = descripcion;
    }
}