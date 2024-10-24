package com.bvm.mci.service;

import java.util.Date;
import java.util.List;

import com.bvm.mci.dto.EmisionAcciones;
import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceSimple;
import com.bvm.mci.dto.IndiceSumatoria;

public interface ReprocesoService {

	List<EmisionSimple> getListaEmision(boolean opcionRT);

	Double getPrecioEmisora(Long idEmision, boolean opcionRT, boolean rtCerrado);

	Boolean getUpdateValores(Long idEmision, Double precioNuevo, Date fecha, boolean rtCerrado,
			List<IndiceSumatoria> listaSumatorias);

	List<EmisionAcciones> getListaEmisiones(Long idMuestra, boolean rtCerrado);

	String getRutaCompartida();

	Boolean obtenRTCerrado();

	List<IndiceSimple> getListaIndicesEdic();

	List<IndiceSumatoria> getEtfsData(Long idEmision);
}
