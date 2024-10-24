package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.OperadorAcumMonet;
import com.bvm.mci.dto.OperadorAcumulados;
import com.bvm.mci.dto.OperadorBitacoraRecibidos;
import com.bvm.mci.dto.OperadorCancelaciones;
import com.bvm.mci.dto.OperadorConteoFormatos;
import com.bvm.mci.dto.OperadorPrecisionPrecAcc;
import com.bvm.mci.dto.OperadorPrecuadre;

public interface OperadorService {

	List<IndiceIntradiaEtf> getListaEtfs();

	String descargaMensajesEnviados(String isntrumento, String formatos, String nombre, String idSession);

	List<OperadorConteoFormatos> obtenConteoFormatos();

	List<OperadorCancelaciones> obtenCancelaciones();

	List<OperadorAcumulados> obtenAcumulados();

	Integer obtenOperacionesXM();

	List<OperadorPrecisionPrecAcc> obtenPrecisionPrecAcc();

	List<OperadorAcumMonet> obtenAcumMonet();

	List<OperadorBitacoraRecibidos> obtenBitacoraRecibidos(String contenido, String formatos, String tipoOperacion,
			String tipoConcertacion);

	List<OperadorBitacoraRecibidos> obtenBitacoraEnviados(String contenido, String formatos, String tipoOperacion,
			String tipoConcertacion);

	String guardaArchivoConteoFormatos(List<OperadorConteoFormatos> lista, String nombre, String idSession);

	String guardaArchivoCancelaciones(List<OperadorCancelaciones> lista, String nombre, String idSession);

	String guardaArchivoAcumulados(List<OperadorAcumulados> lista, List<OperadorAcumMonet> listaMonet, String nombre,
			String idSession);

	String guardaArchivoPrecisionPrecAcc(List<OperadorPrecisionPrecAcc> lista, String nombre, String idSession);

	String guardaArchivoPrecuadre(List<OperadorPrecuadre> lista, String nombre, String idSession);

	String guardaArchivoBitacoraRecibidos(String contenido, String formatos, String tipoOperacion,
			String tipoConcertacion, String nombre, String idSession);

	String guardaArchivoBitacoraEnviados(String contenido, String formatos, String nombre, String idSession);

	String getRutaCompartida();

}
