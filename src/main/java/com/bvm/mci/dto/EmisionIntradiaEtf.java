package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class EmisionIntradiaEtf implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEmision;
    private Long idMuestraETF;
    private Long idDerivadosIpc;
    private Integer numeroTitulos;
    private Double titulosExcluidos;
    private String emision;
    private String serie;
    private Double precioReferencia;
    private String tipoInstrumento;
    private Double precioActual;

    // Constructor específico si es necesario
    public EmisionIntradiaEtf(
            Long idEmision,
            Long idMuestraETF,
            Long idDerivadosIpc,
            Integer numeroTitulos,
            Double titulosExcluidos,
            String emision,
            String serie,
            Double precioReferencia,
            String tipoInstrumento,
            Double precioActual
    ) {
        this.idEmision = idEmision;
        this.idMuestraETF = idMuestraETF;
        this.idDerivadosIpc = idDerivadosIpc;
        this.numeroTitulos = numeroTitulos;
        this.titulosExcluidos = titulosExcluidos;
        this.emision = emision;
        this.serie = serie;
        this.precioReferencia = precioReferencia;
        this.tipoInstrumento = tipoInstrumento;
        this.precioActual = precioActual;
    }
}
