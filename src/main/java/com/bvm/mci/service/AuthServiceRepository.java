package com.bvm.mci.service;

 
/**
 * @author rdiaz
 */
 
public interface AuthServiceRepository   {

	Long getPerfil(String idUsuario);
	
	Boolean isFunctionAccesible(String idUsuario, Long idActividad);
	
	String getClaveCodificada(String idUsuario);

	String getDbName();
	
	String getPerfilDescripcion(Long idPerfil);
}
