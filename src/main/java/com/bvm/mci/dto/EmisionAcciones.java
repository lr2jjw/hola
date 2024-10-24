package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionAcciones implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEmision;
    private String nombreEmision;
    private Long accionesMuestra;
    private Long accionesMuestraNueva;
    private Double precioActual;
    private Double precioRT;
    private Long accionesCirculacion;

    // No need to manually implement getters, setters, or constructors
}
