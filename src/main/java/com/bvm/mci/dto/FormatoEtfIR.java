package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormatoEtfIR {
    private String formato;
    private String instrumento;
    private Double precioUltimo;
    private Double precioDiaAnterior;
    private String tipoValor;
    private String serie;
    private Long numEmision;
    private Long numeroFeed;
}
