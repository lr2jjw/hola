package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.DiferenciaSimple;

public interface ValidaOperacionService {

	/**
	 * M&eacute;todo que consulta el estado de Real Time en
	 * mci_tparametros_operacion.
	 * realt_time_cerrado
	 * regresa verdadero si esta cerrado falso si esta en operacion
	 * 
	 * @return EstadoOperacion
	 */
	boolean validaOperacion();

	/**
	 * M&eacute;todo que actualiza a cerrado
	 * mci_tparametros_operacion.realt_time_cerrado
	 */
	void actualizaOperacion();

	void tomaSnapShotAlCierre();

	// red List<DiferenciaSimple> validaConsistenciaBD();

	void enviaMensajeIgnorarDiferencias(List<DiferenciaSimple> lista);
}
