package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restriccion implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emisiones;
    private List<EmisoraDetalle> emisoraDetalleLista;
    private Boolean enConjunto;
    private Double influencia;
    private Integer numEmisiones;
    private Integer tendencia;
    private Integer condicion;
    private Boolean pesosExactos;
    private Boolean excedeFlotantes;
}
