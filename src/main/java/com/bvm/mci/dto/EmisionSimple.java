package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 	/**
	 * @param idEmision Id de la emision
	 * @param nombreEmision Nombre corto de la emision
	 * @param acciones Numero de acciones de la emision
	 * @param factorFloat Factor float de la emision
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionSimple implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idEmision;
    private String nombreEmision;
    private Long acciones;
    private Double factorFloat;
}
