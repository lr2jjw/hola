package com.bvm.mci.service.impl;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bmv.mci.dao.EmisionDao;
import com.bmv.mci.vo.EmisionPrecios;
import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceSumatoria;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.service.IndicesPorEmisionService;

@SuppressWarnings("all")
@Service("indicesPorEmisionService")
@Transactional
public class IndicesPorEmisionServiceImpl implements IndicesPorEmisionService {
		
//	@Autowired
//	private SessionFactory sessionFactoryOds;
	
	@Autowired
	private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;
	
	@Inject 
	private String rutaArchivosCsv;
	
	@Inject
	@Named("highAvailabilityBean")
	private RealtimeAdminService cache;
	
	@Autowired
	private EmisionDao emisionDao; 
	
	private static Logger rootLogger = Logger.getLogger("IndicesPorEmisionImpl.class");
	
	@Override
	public List<EmisionSimple> getListaEmision() {
		List<EmisionSimple> listaEmisiones = new ArrayList<EmisionSimple> ();
		Query query= null;
		
		//red
		/*
		String sql= "select distinct new com.bmv.mci.admin.shared.dto.EmisionSimple(" +
				  	"a.emision.id, a.emision.emisora || ' ' || a.emision.serie, " +
				  	"a.emision.accionesCirculacion, 0.0" +
				  	") from EmisionMuestra a, Indice b " +
				  	"where a.muestra= b.muestra " +
				  	
				  	"and b.calculoActivado = 1 " +
				  	
				  	"order by 2 asc";		
		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		listaEmisiones= query.list();
		*/
	
		return listaEmisiones;
	}
	
	@Override
	public List<Object> getPreciosEmisora (Long idEmision, boolean rtCerrado) {
		List<Object> precios = new ArrayList<Object>();
		Object[] objetos;
		Query query= null;
		
		Map<Long, EmisionPrecios> obtenerEmisionesIntradia = null;
		Double precioSimple;
		Double precioRt;
		
//		String sql= "select a.precioActual, a.precioRTActual, " +
//					" a.accionesCirculacion, a.precioActual * a.accionesCirculacion" +
//				  	" from Emision a " +
//				  	" where a.id= ?";
//		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
//		query.setParameter(0, idEmision);
//		
//		rootLogger.warning("Id emision buscado: " + idEmision);
//		
//		objetos = (Object[])query.uniqueResult();
		objetos = emisionDao.getPreciosEmisora(idEmision);
		
		if(objetos != null){
			precios.add(objetos[0]==null?Double.valueOf(0.0):(Double)objetos[0]);
			precios.add(objetos[1]==null?Double.valueOf(0.0):(Double)objetos[1]);
			precios.add(objetos[2]==null?Long.valueOf(0):(Long)objetos[2]);
			precios.add(objetos[3]==null?Double.valueOf(0.0):(Double)objetos[3]);
		}
		
		
		//Trayendo datos del RT
		if (!consultaIntradiaMuestraService.obtenRTCerrado()) {
			//Dstos del RT para la emision muestra
			
			try{
				obtenerEmisionesIntradia = cache.getEmisionesMap();
				precioSimple = obtenerEmisionesIntradia.get(Long.valueOf(idEmision)).getPrecioActual();
				precioRt = obtenerEmisionesIntradia.get(Long.valueOf(idEmision)).getPrecioRTActual();
				
				precios.add(objetos[0]==null?Double.valueOf(0.0):(Double)objetos[0]);
				precios.add(objetos[1]==null?Double.valueOf(0.0):(Double)objetos[1]);
				precios.add(objetos[2]==null?Long.valueOf(0):(Long)objetos[2]);
				precios.add(objetos[3]==null?Double.valueOf(0.0):(Double)objetos[3]);
				
				precios.clear();
				precios.add(precioSimple==null?Double.valueOf(0.0):precioSimple);
				precios.add(precioRt==null?Double.valueOf(0.0):precioRt);
				precios.add(objetos[2]==null?Long.valueOf(0):(Long)objetos[2]);
				precios.add(precioSimple==null?Double.valueOf(0.0):precioSimple*(Long)objetos[2]);
				
			}catch(Exception e){
				rootLogger.warning("Error ObtenerEmisionesIntradia: "+e.getStackTrace());
			}
			
		}
		
		return precios;
	}

	public String getRutaCompartida(){
		return rutaArchivosCsv;
	}
	
	public Boolean obtenRTCerrado(){
		return consultaIntradiaMuestraService.obtenRTCerrado();
	}
	
	public String bajaArchivoIndicesPorEmision(String nombreEmision, Double precioActual, Double precioRt,
			Long accionesCirculacion, Double valorMercado, List<IndiceSumatoria> listaIndicesSumatorias,
			String idSession){
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file=null;
		String nombreArchivo = new String(rutaArchivosCsv+idSession+"IndicesPorEmision-Bajar.txt");
		String cadena = null;

		try {
			System.out.println ("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);
			
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);
			
			DecimalFormat formatoSumatoria = new DecimalFormat("0.000000");
			NumberFormat formatoAcciones = new DecimalFormat("000000000000000");
			
			//Se escriben los datos generales
			cadena = "Emisi�n," + nombreEmision + "\n";
			printWriter.write(cadena);
			cadena = "Precio simple," + precioActual + "\n";
			printWriter.write(cadena);
			cadena = "Precio RT," + precioRt + "\n";
			printWriter.write(cadena);
			cadena = "Acciones en circulaci�n," + accionesCirculacion + "\n";
			printWriter.write(cadena);
			cadena = "Valor de mercado total," + valorMercado + "\n";
			printWriter.write(cadena);
			cadena = "Indices que contienen la emisi�n\n";
			printWriter.write(cadena);
			cadena = "Indice,Nivel,Nivel de referencia,Sumatoria,Sumatoria de referencia," +
					"VM Simple de la emisi�n,VM RT de la emisi�n,Acciones en muestra, Participaci�n\n";
			printWriter.write(cadena);
			
			if(listaIndicesSumatorias != null){
				for(IndiceSumatoria indiceSumatoria: listaIndicesSumatorias){
					cadena = "\"" + indiceSumatoria.getNombreIndice() + "\"," +
						indiceSumatoria.getNivel() + ',' +
						indiceSumatoria.getNivelReferencia() + ',' +
						indiceSumatoria.getSumatoriaValMcdoActual() + ',' +
						indiceSumatoria.getSumatoriaValMcdoRef() + ',' +
						indiceSumatoria.getVMSimple() + ',' +
						indiceSumatoria.getVMRt() + ',' +
						indiceSumatoria.getAccionesEnMuestra() + ',' +
						indiceSumatoria.getParticipacion() + '\n';
					printWriter.write(cadena);
				}
			}
			
			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		}
		catch(java.io.IOException e) {
			throw new RuntimeException(e); 
		}
		
		return nombreArchivo;
	}
}
