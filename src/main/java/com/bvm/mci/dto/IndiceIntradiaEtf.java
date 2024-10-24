package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceIntradiaEtf {

    public static final String ID = "id";
    public static final String NUMEMISIONANT = "numEmisionAnt";
    public static final String INSTRUMENTO = "instrumento";
    public static final String TIPOVALOR = "tipoValor";
    public static final String SERIE = "serie";
    public static final String IDMUESTRAETF = "idMuestraEtf";
    public static final String IDINDICE = "idIndice";
    public static final String COMPONENTEEFECTIVO = "componenteEfectivo";
    public static final String NUMEROCERTIFICADOS = "numeroCertificados";
    public static final String PRECIOTEORICOACTUAL = "precioTeoricoActual";
    public static final String PRECIOTEORICOANTERIOR = "precioTeoricoAnterior";
    public static final String PRECIOTEORICODIAANTERIOR = "precioTeoricoDiaAnterior";
    public static final String ESTADOREMATE = "estadoRemate";
    public static final String IDMAPA = "idMapa";
    public static final String ARCHIVO = "archivo";
    public static final String COMPONENTEEFECTIVOTITEXC = "componenteEfectivoTitExc"; // Ticket#1191026 - Fase 3 Mejoras MCI

    private Long id;
    private Long numEmisionAnt;
    private String instrumento;
    private String tipoValor;
    private String serie;
    private Long idMuestraEtf;
    private Long idIndice;
    private Double componenteEfectivo;
    private Long numeroCertificados;
    private Double precioTeoricoActual;
    private Double precioTeoricoAnterior;
    private Double precioTeoricoDiaAnterior;
    private Long idMapa;
    private Long estado;
    private String estadoRemate;
    private Double componenteEfectivoTitExc; // Ticket#1191026 - Fase 3 Mejoras MCI
    private Long calculaPtInicial;
    private Long validaCartera;
    private Long ir;
    private Long iv;
    private Long ir_ant;
    private Long iv_ant;
    private Long tienePrioridadIv;
    private Long idEtf;
    private String archivo;

    public IndiceIntradiaEtf(Long id, Long numEmisionAnt, String instrumento, String tipoValor, String serie,
                             Long idMuestraEtf, Long idIndice, Double componenteEfectivo, Long numeroCertificados,
                             Double precioTeoricoActual, Double precioTeoricoAnterior, Double precioTeoricoDiaAnterior,
                             Long idMapa, Double componenteEfectivoTitExc) {
        this.id = id;
        this.numEmisionAnt = numEmisionAnt;
        this.instrumento = instrumento;
        this.tipoValor = tipoValor;
        this.serie = serie;
        this.idMuestraEtf = idMuestraEtf;
        this.idIndice = idIndice;
        this.componenteEfectivo = componenteEfectivo;
        this.numeroCertificados = numeroCertificados;
        this.precioTeoricoActual = precioTeoricoActual;
        this.precioTeoricoAnterior = precioTeoricoAnterior;
        this.precioTeoricoDiaAnterior = precioTeoricoDiaAnterior;
        this.idMapa = idMapa;
        this.componenteEfectivoTitExc = componenteEfectivoTitExc;
    }

    public IndiceIntradiaEtf(Long id, Long numEmisionAnt, String instrumento, String tipoValor, String serie,
                             Long idMuestraEtf, Long idIndice, Double componenteEfectivo, Long numeroCertificados,
                             Double precioTeoricoActual, Double precioTeoricoAnterior, Double precioTeoricoDiaAnterior,
                             Long idMapa, Long estado, String estadoRemate, Double componenteEfectivoTitExc) {
        this(id, numEmisionAnt, instrumento, tipoValor, serie, idMuestraEtf, idIndice, componenteEfectivo,
             numeroCertificados, precioTeoricoActual, precioTeoricoAnterior, precioTeoricoDiaAnterior, idMapa,
             componenteEfectivoTitExc);
        this.estado = estado;
        this.estadoRemate = estadoRemate;
    }

    public IndiceIntradiaEtf(Long id, Long numEmisionAnt, String instrumento, String tipoValor, String serie,
                             Long idIndice, Long estado, Long idEtf, Long validaCartera) {
        this.id = id;
        this.numEmisionAnt = numEmisionAnt;
        this.instrumento = instrumento;
        this.tipoValor = tipoValor;
        this.serie = serie;
        this.idIndice = idIndice;
        this.estado = estado;
        this.idEtf = idEtf;
        this.validaCartera = validaCartera;
    }

    public IndiceIntradiaEtf(Long id, String instrumento) {
        this.id = id;
        this.instrumento = instrumento;
    }
}
