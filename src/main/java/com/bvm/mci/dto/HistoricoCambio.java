package com.bvm.mci.dto;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Clase de transformaci&oacute;n para el servicio de historico de cambio
 * donde
 * idEmsion: HistoricoCambio.Emision.id
 * serie: Emision.Serie
 * precio MXN : HistoricoCambio.precioPesos
 * predio USD : HistoricoCambio.precioOrigen
 * ExRate : HistoricoCambio.promedioTipoCambio
 * fecha : HistoricoCambio.fechaHoraSolicitud
 */ 

 @Data
@NoArgsConstructor
public class HistoricoCambio {

    private Long idEmision;
    private String serie;
    private String emisora;
    private Double precioMXN;
    private Double precioUSD;
    private Double promedioTipoCambio;
    private Date fechaHoraSolicitud;
    private String mensajeRetorno;

    public HistoricoCambio(Long idEmision, String serie, String emisora, 
                           Double precioMXN, Double precioUSD, 
                           Double promedioTipoCambio, Date fechaHoraSolicitud) {
        this.idEmision = idEmision;
        this.serie = serie;
        this.emisora = emisora;
        this.precioMXN = precioMXN;
        this.precioUSD = precioUSD;
        this.promedioTipoCambio = promedioTipoCambio;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public HistoricoCambio(Long idEmision, String serie, String emisora, 
                           Double precioMXN, Double precioUSD, 
                           Double promedioTipoCambio, Date fechaHoraSolicitud, 
                           String mensajeRetorno) {
        this.idEmision = idEmision;
        this.serie = serie;
        this.emisora = emisora;
        this.precioMXN = precioMXN;
        this.precioUSD = precioUSD;
        this.promedioTipoCambio = promedioTipoCambio;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.mensajeRetorno = mensajeRetorno;
    }
}