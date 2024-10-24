package com.bvm.mci.dto;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ?l
 * Clase de transformación para el servicio de ConsultaIntradiaMuestraService.getListadoEmisiones
 * donde
 * idEmsion: Emision.id
 * idMuestra: Muestra.id
 * emisora: Emision.emisora
 * serie: Emision.Serie
 * tipo valor: Emision.TipoValor
 * acciones en Muestra: EmisionMuestra.AccionesEnMuestra
 * ultimo Hecho:  Emision.PrecioHechoAnterior
 * precio Cierre: Emision.PrecioUltimo
 * influencia: influenceCalculator.calculaParticipaciones(eInfluence)
 * tope: Muestra.Tope
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmisoraDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEmision;
    private Long idMuestra;
    private Long idEmisionOds;
    private String emisora;
    private String serie;
    private String tipoValor;
    private Double ultimoHecho;
    private Double precioCierre;
    private Long accionesMuestra;
    private Long accionesCirculacion;
    private Double influencia;
    private Double factorFloat;
    private Date fechaPrecioCierre;
    private Double influenciaSimulada;
    private String condicion;
    private String restriccion;
    private Long accionesFlotantes;
    private Long accionesMuestraNuevas;
    private String ric;
    private Double precioRTRreferencia;
    private Double precioReferencia;
    private Double precioActual;
    private Double precioRTActual;
    private Double tope;
    private String tipoRenta;
    private Long accionesMuestraSimuladas;
    private Long accionesCirculacionReb;
    private Long accionesMuestraActuales;
    private Double precioRTReferencia;
    private Boolean mg;
    private Boolean rv;
    private Double influenciaFlotante;

   

}
