package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorPrecisionPrecAcc implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cveEmision;
    private String cveSerie;
    private Double precioActual;
    private Double ultimoPrecioOperado;
    private Long numeroOperacionesMci;
    private Long numeroOperacionesOds;
}
