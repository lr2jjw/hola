package com.bvm.mci.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceCierre {

    public static final String ID_INDICE = "id_indice";
    public static final String NOMBRE_CORTO = "nombre_corto";
    public static final String ID_MUESTRA = "id_muestra";
    public static final String SUMATORIA_OBTENIDA = "sumatoriaObtenida";
    public static final String NIVEL_OBTENIDO = "nivelObtenido";
    public static final String SUMATORIA_ESPERADA = "sumatoriaEsperada";
    public static final String NIVEL_ESPERADO = "nivelEsperado";
    public static final String ESTADO = "estado";
    public static final String DEFINITIVOS_CONTADOR = "definitivosContador";
    public static final String DEFINITIVOS_FECHA_ULT = "definitivosFechaUlt";
    public static final String ID_VALIDACION = "idValidacion";
    public static final String ID_RESULTADO_VALIDACION = "idResultadoValidacion";
    public static final String CLAVE_INDICE = "clave_indice";

    private Long idIndice;
    private String nombreCorto;
    private Long idMuestra;
    private Double sumatoriaObtenida;
    private Double nivelObtenido;
    private Double sumatoriaEsperada;
    private Double nivelEsperado;
    private String estado;
    private Long definitivosContador;
    private Date definitivosFechaUlt;
    private Long idValidacion;
    private Long idResultadoValidacion;
    private String claveIndice;

     
    public void setNivelObtenido(Double nivelObtenido) {
        this.nivelObtenido = nivelObtenido;
    }
}
