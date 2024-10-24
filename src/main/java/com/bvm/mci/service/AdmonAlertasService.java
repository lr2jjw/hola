package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.DestinatarioSimple;


public interface AdmonAlertasService   {
	
	List<DestinatarioSimple> getListaDestinatarios();
	
	Long saveDestinatario(
			String tipo,
			String nombreContacto,
			String destino);
	
	Long deleteDestinatario(List<Long> lista);
	
	Long updateDestinatario(List<DestinatarioSimple> lista);

	String getRutaCompartida();
	
	Boolean obtenRTCerrado();
}
