package com.bvm.mci.service;

 

/**
 * @author �l
 *
 */  
public interface ConsultaIntradiaMuestraService   {
	/**
	 * M&eacute;todo que  consulta en ParametrosDeOperacion si el RT esta cerrado
	 * @return verdadero si ParametrosDeOperacion.RealTimeCerrado es 1
	 */
	boolean obtenRTCerrado();
	
	boolean obtenRTStatus();
	
	void verificaEstadoRt();
	/**
	 * Permite saber si el estado del Real Time es FIN_P - Fin de recepcion de formatos P
	 * @return boolean true si es el real time tiene el estado de fin de formatos P
	 *                 false en caso contrario. 
	 */
	 public  boolean esFinFormatosP();
	 
	 public String getStatusUserRT();
	 
	 public Integer getEstadoRealtime();
}
