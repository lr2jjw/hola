package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.EmisoraDetalle;
import com.bvm.mci.dto.TiposValor;
 

/**
 * @author �l
 *
 */  
public interface CambioMuestraService  {

	/**
	 * M&eacute;todo  que regresa un listado con los tipos de emisiones disponibles
	 * regresa datos del tipo TipoValor
	 * Emisoras Disponibles :Emision.emisora
	 * @return Listado de objetos Emision
	 */
	List<TiposValor> getEmisionesMuestraInicial();

	/**
	 * M&eacute;todo que calcula las acciones flotantes para una lista de objetos EmisoraDetalle
	 * Las acciones flotantes se calculan como accionesEnCirculacion * factorFloat  
	 *
	 * 	@param emisoraDetalleLista:  Lista de objetos tipo EmisioraDetalle con acciones modificadas
	 *  @return listado de objetos tipo EmisoraDetalle con el calculo de las acciones flotantes 
	 */
	List<EmisoraDetalle> getAccionesFlotantes(List<EmisoraDetalle> emisoraDetalleLista);

	Boolean guardaRIC(Long idEmision, Double precio, String RIC);
}
