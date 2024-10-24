package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de transformación para el servicio de CambioMuestraService.getEmisionesMuestraInicial
 * donde
 * tipoRenta:  TipoValor.primaryKey.tipoRenta
 * tipoValor: TipoValor.primaryKey.tipoValor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposValor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipoRenta;
    private String tipoValor;
}
