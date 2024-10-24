package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Clase de transformaci&oacute;n para el servicio de EmisionesInternacionales
 * donde
 * idEmsion: Emision.id
 * acciones: Emision.accionesCirculacion
 * precio: Emision.precioActual
 * ric: Emision.ric
 * pais: Emision.CodigoIsoPais
 * 
 */ 
 


 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionInternacional implements Serializable {

    private static final long serialVersionUID = -6668550438902700262L;

    private Long idEmision;
    private Long idEmisionODS;
    private String emisora;
    private String serie;
    private Long accionesCirculacion;
    private Double precioActual;
    private String ric;
    private String pais;
    private String tipoRenta;

    // Constructor para consulta de emisiones internacionales
    public EmisionInternacional(Long idEmision,
                                Long idEmisionODS,
                                String emisora,
                                String serie,
                                Long accionesCirculacion,
                                Double precioActual,
                                String ric,
                                String pais) {
        this.idEmision = idEmision;
        this.idEmisionODS = idEmisionODS;
        this.emisora = emisora;
        this.serie = serie;
        this.accionesCirculacion = accionesCirculacion;
        this.precioActual = precioActual;
        this.ric = ric;
        this.pais = pais;
    }

    // Constructor para consulta de emisiones internacionales nuevas
    public EmisionInternacional(String emisora,
                                String serie,
                                Long accionesCirculacion,
                                Double precioActual,
                                String ric,
                                String pais) {
        this.emisora = emisora;
        this.serie = serie;
        this.accionesCirculacion = accionesCirculacion;
        this.precioActual = precioActual;
        this.ric = ric;
        this.pais = pais;
    }

    // Constructor para listado de emisiones de esquemas externos
    public EmisionInternacional(Long idEmisionODS,
                                String emisora,
                                String serie,
                                Long accionesCirculacion) {
        this.idEmisionODS = idEmisionODS;
        this.emisora = emisora;
        this.serie = serie;
        this.accionesCirculacion = accionesCirculacion;
    }
}