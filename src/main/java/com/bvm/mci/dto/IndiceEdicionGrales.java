package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceEdicionGrales implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idIndice;
    private Long idMuestra;
    private String claveIndice;
    private String nombreCorto;
    private String nombreLargo;
    private String descripcion;
    private Double valorReferencia;
    private Double sumatoriaReferencia;
    private Double tope;
    private Double valorAnualAnterior;
    private Short clasificacionN1;
    private Short clasificacionN2;
    private Short clasificacionN3;
    private Short clasificacionN4;
}