package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author �l
 * Clase de transformaci&oacute;n para el servicio de GrupoListaService.getDetalleGrupo
 * donde
 * idIndice: Indice.id
 * idMuestra: Indice.muestra.id
 * identificador: Indice.
 * indicador: Indice.nombreCorto
 * indice actual:Indice.nivelActual
 * indice anterior:Indice.nivelAnterior
 * variacion: Indice.variacionPuntos
 * porcentaje Indice.variacionPorcentual
 * sumatoria actual:Indice.sumatoriaValMcdoActual
 * sumatoria anterior:Indice.sumatoriaValMcdoReferencia
 * estado:Indice.estadoDifusion
 * rendimiento(Rendimiento total o simple): Indice.tipoRendimiento 
 */
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDetalle {

    public static final String IDINDICE = "idIndice";
    public static final String IDMUESTRA = "idMuestra";
    public static final String IDENTIFICADOR = "identificador";
    public static final String INDICEACTUAL = "indiceActual";
    public static final String INDICEANTERIOR = "indiceAnterior";
    public static final String VARIACION = "variacion";
    public static final String PORCENTAJEINDICE = "porcentajeIndice";
    public static final String SUMATORIAACTUAL = "sumatoriaActual";
    public static final String SUMATORIAANTERIOR = "sumatoriaAnterior";
    public static final String ESTADO = "estado";
    public static final String GRUPOUSUARIO = "grupoUsuario";
    public static final String RENDIMIENTO = "rendimiento";
    public static final String INDICADOR = "indicador";
    public static final String SUMVMREFERENCIA = "sumVMReferencia";
    public static final String MAXVALORFECHA = "maxValorFecha";
    public static final String MINVALORFECHA = "minValorFecha";

    private Long idIndice;
    private Long idMuestra;
    private String identificador;
    private String indicador;
    private Double indiceActual;
    private Double indiceAnterior;
    private Double variacion;
    private Double porcentajeIndice;
    private Double sumatoriaActual;
    private Double sumatoriaAnterior;
    private String estado;
    private String rendimiento;
    private String grupoUsuario;
    private Double sumVMReferencia;
    private String maxValorFecha;
    private String minValorFecha;

    // Constructor para GUI emergente
    public GrupoDetalle(Long idIndice, Long idMuestra, String identificador, String indicador, String grupoUsuario, Double sumVMReferencia) {
        this.idIndice = idIndice;
        this.idMuestra = idMuestra;
        this.identificador = identificador;
        this.indicador = indicador;
        this.grupoUsuario = grupoUsuario;
        this.sumVMReferencia = sumVMReferencia;
    }

    // Constructor para mostrar sumatoria RT de referencia
    public GrupoDetalle(Long idIndice, String indicador, Double sumVMReferencia) {
        this.idIndice = idIndice;
        this.indicador = indicador;
        this.sumVMReferencia = sumVMReferencia;
    }
}