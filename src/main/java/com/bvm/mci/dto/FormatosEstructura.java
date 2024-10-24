package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Clase que almacena los formatos asignados a un índice.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FormatosEstructura {
 

    public static final String IDFORMATO = "idFormato";
    public static final String CVEFORMATODIF = "cveFormatoDif";    
    public static final String CAMPO = "campo";
    public static final String APLICACION = "aplicacion";
    public static final String LONGITUD = "longitud";
    public static final String TIPO = "tipo";
    public static final String POSICION = "posicion";
    public static final String LONGITUD_DEC = "longitudDec";
    public static final String HEADER = "header";
    public static final String DESCRIPCION = "descripcion";

    private Long idFormato;
    private String cveFormatoDif;
    private String campo;
    private String aplicacion;
    private Long longitud;
    private String tipo;
    private Long posicion;
    private Long longitudDec;
    private String header;
    private String descripcion;

    @Override
    public String toString() {
        return "FormatosEstructura{" +
                "idFormato=" + idFormato +
                ", cveFormatoDif='" + cveFormatoDif + '\'' +
                ", campo='" + campo + '\'' +
                ", aplicacion='" + aplicacion + '\'' +
                ", longitud=" + longitud +
                ", tipo='" + tipo + '\'' +
                ", posicion=" + posicion +
                ", longitudDec=" + longitudDec +
                ", header='" + header + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
