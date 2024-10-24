package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.IndiceDetalle;

 
public interface CierreDetalleService {


	/**
	 * M&eacute;todo que muestra un listado  de objetos tipo GrupoIndice, para la pantalla cierre.
	 * Para la caracteristica  sumatoria esperada se utiliza:
	 * SUM(EmisionMuestra.accionesEnMuestra(i) * Emisora.precioActual(i)),  para todas las Emisiones
	 * que componen una muestra (i = 0..n).
	 * Para la sumatoria nivel esperado se utiliza:
	 * (Indice.sumatoriaValMcdoReferencia  / Indice.sumatoriaValMcdoHechoAnterior) * Indice.nivelAnterior
	 * nivel obtenido: Indice.nivelActual
	 * nivel esperado:sumatoriaValMcdoReferencia
	 *  @return lista de objetos GrupoIndice
	 */
	List getIndices();


	/**
	 * M&eacute;todo que actualiza informaci&oacute;n obtenida de la pantalla cierre
	 * las caracteristicas son:
	 * Indice.sumatoriaValMcdoReferNoAjustes
	 * Indice.sumatoriaValMcdoHechoAnterior
	 * Indice.nivelAnterior
	 * @param indice correspondiente al objeto Indice sobre los que se realizar&aacute;n los cambios.
	 */
	void setDetalleIndice(List<IndiceDetalle> indice);

	/**
	 * M&eacute;todo usado para recibir y enviar los valores definitivos de cierre a
	 * los &iacute;ndices seleccionados de la pantalla cierre
	 * recibe como param&eacute;tro un listado de &iacute;ndices  a difundir.
	 * @param Indice  Listado de &iacute;ndices seleccionados para ser difundidos.
	 */
	void setDifusion(List<IndiceDetalle> indice);



	/**
	 * M&eacute;todo que muestra informaci&oacute;n los cambios que ha sufrido un &iacute;ndice seleccionado
	 * el detalle de la muestra se representa por
	 * emision: EmsionMuestra.primaryKey.emision
	 * serie: EmsionMuestra.primaryKey.serie
	 * precio: Emision.precioActual
	 * xRate: Tasa de conversion entre divisas
	 * @param idIndice Identificador  del indice seleccionado
	 * @return Listado de objetos tipo EmisionMuestra
	 */
	List getHistoricoIndices(Long idIndice);



}
