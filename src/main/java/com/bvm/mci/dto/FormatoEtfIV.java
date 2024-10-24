package com.bvm.mci.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un formato de ETF IV.
 */
@Data
@NoArgsConstructor 
public class FormatoEtfIV {
    private String formato;
    private String instrumento;
    private String emisora;
    private String serie;
    private Long titulos;
    private Double titulosExcluidos;
    private Double precio;
    private Double componenteEfectivo;
    private Double componenteEfectivoTitExc; // Ticket#1191026 - Fase 3 Mejoras MCI
    private Double valorExcluido;
    private Long numeroCertificados;
    private Double precioTeorico;

    // Additional constants
    public static final String FORMATO = "formato";
    public static final String INSTRUMENTO = "instrumento";
    public static final String EMISORA = "emisora";
    public static final String SERIE = "serie";
    public static final String TITULOS = "titulos";
    public static final String TITULOSEXCLUIDOS = "titulosExcluidos";
    public static final String PRECIO = "precio";
    public static final String COMPONENTEEFECTIVO = "componenteEfectivo";
    public static final String COMPONENTEEFECTIVOTITEXC = "componenteEfectivoTitExc"; // Ticket#1191026 - Fase 3 Mejoras MCI
    public static final String VALOREXCLUIDO = "valorExcluido";
    public static final String NUMEROCERTIFICADOS = "numeroCertificados";
    public static final String PRECIOTEORICO = "precioTeorico";


    public FormatoEtfIV(
        String formato,
        String instrumento,
        String emisora,
        String serie,
        Long titulos,
        Double titulosExcluidos,
        Double precio,
        Double componenteEfectivo,
        Double componenteEfectivoTitExc, // Ticket#1191026 - Fase 3 Mejoras MCI
        Long numeroCertificados,
        Double valorExcluido,
        Double precioTeorico
) {
    this.formato = formato;
    this.instrumento = instrumento;
    this.emisora = emisora;
    this.serie = serie;
    this.titulos = titulos;
    this.titulosExcluidos = titulosExcluidos;
    this.precio = precio;
    this.componenteEfectivo = componenteEfectivo;
    this.componenteEfectivoTitExc = componenteEfectivoTitExc;
    this.valorExcluido = valorExcluido;
    this.numeroCertificados = numeroCertificados;
    this.precioTeorico = precioTeorico;
}
}
