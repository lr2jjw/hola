package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceSumatoria implements Serializable {

    public IndiceSumatoria(String instrumento, Double precio) {
        this.instrumento = instrumento;
        this.precio = precio;
    }
    private static final long serialVersionUID = 1L;

    private Long idMuestra;
    private Long idIndice;
    private String nombreIndice;
    private Double sumatoriaValMcdoActual;
    private Double sumatoriaValMcdoNueva;
    private Double nivel;
    private Double vMSimple;
    private Double vMRt;
    private Double nivelReferencia;
    private Double sumatoriaValMcdoRef;
    private Long accionesEnMuestra;
    private Double participacion;
    private Long emisionesActualizadas;
    private Double precio;
    private String instrumento;
}
