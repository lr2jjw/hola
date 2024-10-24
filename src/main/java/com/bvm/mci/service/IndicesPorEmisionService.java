package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceSumatoria;

public interface IndicesPorEmisionService {
	
	List<EmisionSimple> getListaEmision();

	List<Object> getPreciosEmisora (Long idEmision, boolean rtCerrado);

	String getRutaCompartida();
	
	Boolean obtenRTCerrado();
	
	String bajaArchivoIndicesPorEmision(String nombreEmision, Double precioActual, Double precioRt,
			Long accionesCirculacion, Double valorMercado, List<IndiceSumatoria> listaIndicesSumatorias,
			String idSession);
}
