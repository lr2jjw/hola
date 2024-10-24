package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.ArchivoEtfSimple;
import com.bvm.mci.dto.EmisionIntradiaEtf;
import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.RealtimeOperacion;
import com.bvm.mci.dto.TasaDividendosSimple;
 



/**
 * @author rdiaz
 */ 
public interface ConsultaIntradiaEtfService  {

	/**
	 * 
	 * @return
	 */
	List<IndiceIntradiaEtf> getIndicesEtf();
	
	List<EmisionIntradiaEtf> getMuestraEtf(Long idEtf);

	//void recargaArchivosEmisnet();
	
	Boolean obtenRTCerrado();
	
	Boolean obtenRTStatus();
	
	String getRutaCompartida();
	
	String getRutaCompartidaETFs();
	
	RealtimeOperacion getRealtimeOperacion();
	
	//String subeArchivo(String ruta, Boolean sonInstrumentos);
	
	/**
	 * Permite consultar las emisones ordenadas por instrumento
	 * @return
	 */
	List<IndiceIntradiaEtf> getEmisionesEtf();

	/**
	 * Permite guardar un ETF
	 * @param etf
	 * @return
	 */
	Long guardaETF(IndiceIntradiaEtf etf);
	 
	 /**
	 * Permite consolidar un ETF
	 * @param etf
	 * @return
	 */ 
	 Long consolidarEtf(IndiceIntradiaEtf etf);
	 
	 /**
	 * Permite consultar ETF activos ordenados por instrumento 
	 * @return
	 */
	 List<IndiceIntradiaEtf> getEtfActivos();
	 
	 /**
	 * Permite consultar ETF activos ordenados por instrumento que no estan registrados como baja
	 * @return
	 */
	 List<IndiceIntradiaEtf> getEtfActivosSinBaja();	 
	 
	 /**
	 * Permite consultar ETF en baja o por dar de baja ordenados por instrumento
	 * @return
	 */
	 List<IndiceIntradiaEtf> getEtfActivosEnBaja();
	 
	/**
	 * Permite consultar ETF activos y pendientes ordenados por instrumento 
	 * @return
	 */
	List<IndiceIntradiaEtf> getEtfActivosYPendientes();	 
	 
	/**
	 * Permite dar de baja logicamente un ETF
	 * @return
	 */
	 boolean bajaLogicaETF(Long idEtf);
	 
	 
	 /**
	 * Permite reactivar un ETF
	 * @return
	 */
	 boolean reactivarETF(Long idEtf);
	 
	 
	 /**
	 * Permite consultar catalogo de archivos ETF 
	 * @return
	 */
	public List<ArchivoEtfSimple> getCatalogoArchivosEtf();
	
	/**
	 * Permite consultar el id del catalogo de archivos en base al id del ETF 
	 * @return
	 */
	public Long getIdCatArchivoPorIdEtf(Long idEft);
	
	/**
	 * Permite actualizar un EFT
	 * @param etf
	 * @return id Etf actualizado
	 */
	public Long actualizaETF(IndiceIntradiaEtf etf);
	
	
	/**
	 * Permite consultar la TasaDividendos 
	 * @return
	 */
	public TasaDividendosSimple consultaTasaDividendos();

	/**
	 * Permite actualizar una Tasa Dividendos
	 * @param tasaDividendosSimple
	 * @return id tasaDividendo actualizado
	 */
	public Long actualizaTasaDividendos(TasaDividendosSimple tasaDividendosSimple);

	
	/**
	 * Permite enviar un mensaje RMI al Real Time para avisar que se tiene que recargar el archivo ETF
	 * @param idEtfSeleccionado
	 * @param idArchivoDeEtfs
	 */
	public boolean enviaMensajeRMIenCargaYReprocesoEtf(String idEtfSeleccionado, String idArchivoDeEtfs, Boolean isDelay);
	
	
	public boolean enviaMensajeRMIenDifusionEtf(IndiceIntradiaEtf etf);
	
	/**
	 * Permite saber si el estado del Real Time es FIN_P - Fin de recepcion de formatos P
	 * @return boolean true si es el real time tiene el estado de fin de formatos P
	 *                 false en caso contrario. 
	 */
	public boolean esFinFormatosP();
	
	
	/**
	 * Permite saber si un etf Esta cerrado
	 * @param idEtf
	 * @return
	 */
	public boolean esEtfCerrado(Long idEtf);
	
	/**
	 * Permite consultar ETF activos y defectuosos ordenados por instrumento
	 * Se usa en la pantalla de Difusion de etf
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivosYConDefectos();
	
	/**
	 * Actualiza la informaci&oacute;n de los archivos en el cache
	 * @param idEtfArchivos
	 */
	Long sendArchivosCache(Long idEtfArchivos );
	
}
