package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuestraIndice implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emisora;
    private String serie;
    private String tipoValor;
    private Long accionesMuestra;
    private Double ultimoHecho;
    private Double precioCierre;
    private Double influencia;
}
