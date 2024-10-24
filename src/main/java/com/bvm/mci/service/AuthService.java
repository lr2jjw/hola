package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.InformacionSistemaDTO;

 

/**
 * The client side stub for the RPC service.
 */
 
public interface AuthService   {
	String retrieveUsername();
	
	String logOut();
	
	List<String> getRoles(String username);
	
	Long getPerfil(String idUsuario);
	
	Boolean isFunctionAccesible(String idUsuario, Long idActividad);
	
	String getDbName();
	
	String getPerfilDescripcion(Long idUsuario);
	
	String getDatosRT();
	
	String getDatosWebApp();
	
	String getMciVersion();
	
	Boolean setNuevoRT(String ip1, String ip2, Integer puerto);
	
	String switchRT(boolean aplicaCambio);

	 InformacionSistemaDTO  getInformacionSistema(  String usuario,Long idPerfil);
}
