package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de transformación para el servicio de GrupoListaService.getIndiceDetalle
 * donde
 * 
 * idIndice: Indice.id
 * nombre: Indice.nombreCorto
 * nombreLargo: Indice.nombre
 * clasificacion: Indice.sector + Indice.subsector + Indice.ramo + Indice.subramo
 * descripcion: Indice.descripcion
 * tipoRendimiento: Indice.tipoRendimiento
 * grupoUsuario: GrupoIndice.descripcion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceDetalle {

    private Long idIndice;
    private Long idMuestra;
    private String nombre;
    private String nombreLargo;
    private String clasificacion;
    private String descripcion;
    private String tipoRendimiento;
    private String grupoUsuario;
    private Double tope;
}
