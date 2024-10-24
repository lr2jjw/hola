package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionCierre implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEmisionMCI;
    private String emisora;
    private String serie;
    private String tipoValor;
    private Long numeroAcciones;
    private Double precio;
    private Double valorMercadoEsperado;
    private Double valorMercadoObtenido;
    private Long idIndice;
    private Long idMuestra;
}
