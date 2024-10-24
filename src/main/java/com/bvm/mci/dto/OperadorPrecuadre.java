package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorPrecuadre implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cveIndice;
    private Double valorIndice;
    private Double variacionPorcentual;
    private Double variacionPuntos;
    private Double valorIndiceReferencia;
    private Double valorMaximoIndice;
    private Double valorMinimoIndice;
    private Date horaMaximo;
    private Date horaMinimo;
    private Double sumVmActual;
    private Double sumVmReferencia;
}
