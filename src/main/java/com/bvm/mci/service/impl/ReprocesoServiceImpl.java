package com.bvm.mci.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bmv.mci.dao.CarteraDao;
import com.bmv.mci.dao.EmisionDao;
import com.bmv.mci.model.Emision;
import com.bmv.mci.vo.EmisionPrecios;
import com.bvm.mci.dto.EmisionAcciones;
import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceNivSumRef;
import com.bvm.mci.dto.IndiceSimple;
import com.bvm.mci.dto.IndiceSumatoria;
import com.bvm.mci.service.CambioMuestraService;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.service.ReprocesoService;
import com.bvm.mci.shared.Constantes;

@SuppressWarnings("all")
@Service("reprocesoService")
@Transactional
public class ReprocesoServiceImpl implements ReprocesoService {

	@Autowired
	private SessionFactory sessionFactoryOds;

	@Autowired
	private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

	@Autowired
	private CambioMuestraService cambioMuestraService;
	@Inject
	private String rutaArchivosCsv;

	@Inject
	@Named("highAvailabilityBean")
	private RealtimeAdminService cache;

	@Autowired
	private EmisionDao emisionDao;

	@Autowired
	private CarteraDao carteraDao;

	private static final Logger rootLogger = LogManager.getLogger(ReprocesoServiceImpl.class);

	@Override
	public List<EmisionSimple> getListaEmision(boolean opcionRT) {
		List<EmisionSimple> listaEmisiones = new ArrayList<EmisionSimple>();
		Query query = null; 
		// RDA SPI Se abre el filtro para traer cualquier emision
		// String sql= "select distinct new com.bmv.mci.admin.shared.dto.EmisionSimple("
		// +
		// "a.emision.id, a.emision.emisora || ' ' || a.emision.serie, " +
		// "a.emision.accionesCirculacion, 0.0" +
		// ") from EmisionMuestra a, Indice b " +
		// "where b.tipoRendimiento= ? and a.muestra= b.muestra " +
		// "order by 2 asc";
		String sql = "select distinct new com.bvm.mci.dto.EmisionSimple(" +
				"a.id, a.emisora || ' ' || a.serie, " +
				"a.accionesCirculacion, 0.0" +
				") from Emision a " +
				"order by 2 asc";

		query = sessionFactoryOds.getCurrentSession().createQuery(sql);
		// query.setParameter(0,
		// (opcionRT?TipoRendimiento.TOTAL:TipoRendimiento.SIMPLE));
		listaEmisiones = query.list();

		return listaEmisiones;
	}

	@Override
	public Double getPrecioEmisora(Long idEmision, boolean opcionRT, boolean rtCerrado) {
		Double precio = 0.0;
		// Query query= null;
		if (rtCerrado) {
			// String sql= "select " + (opcionRT?"a.precioRTActual":"a.precioActual") + " "
			// +
			// "from Emision a " +
			// "where a.id= ?";
			// query= sessionFactoryOds.getCurrentSession().createQuery(sql);
			// query.setParameter(0, idEmision);
			// precio = (Double)query.uniqueResult();
			precio = emisionDao.getPrecioEmisora(idEmision, opcionRT);
		} else {
			Map<Long, EmisionPrecios> emisMap = cache.getEmisionesMap();
			EmisionPrecios emisionPrecios = emisMap.get(idEmision);
			precio = emisionPrecios.getPrecioRTActual();
		}

		return precio;
	}

	@Override
	public Boolean getUpdateValores(Long idEmision, Double precioNuevo,
			Date fecha, boolean rtCerrado,
			List<IndiceSumatoria> listaSumatorias) {

		// Query query= null;
		Emision emision = null;
		System.out.println("idEmision:" + idEmision + "<-Precio:" + precioNuevo + "<-");

		// if(!rtCerrado){ // Modificado por Edgar Fuentes Ram�rez - if ORIGINAL
		if (rtCerrado) {
			System.out.println("ReprocesoServiceImpl::getUpdateValores(), Actualizar valor en la Base de Datos...");
			// String sql= "from Emision a where a.id= ?";
			// query= sessionFactoryOds.getCurrentSession().createQuery(sql);
			// query.setParameter(0, idEmision);
			// emision= (Emision)query.uniqueResult();
			emision = emisionDao.findById(idEmision);
			emision.setPrecioActual(precioNuevo);
			emision.getAuditoria().setModificadoPor(Constantes.USUARIO_MCI);
			emision.getAuditoria().setFechaModificacion(new Date());
			emisionDao.update(emision);
			// sessionFactoryOds.getCurrentSession().update(emision);
			System.out.println("ReprocesoServiceImpl::getUpdateValores(), ...done!");
		} else {
			// Enviando mensaje al RT para que cambie los valores
			System.out.println("Enviando cambios al RT");

			System.out.println("Nuevo precio de referencia: " + idEmision.toString());
			try {
				rootLogger.warn("Antes de \"createModificationMessage(\"Emision...\"");

				cache.createModificationMessage("Emision", idEmision.toString(), "precioReferencia",
						precioNuevo.toString());
				rootLogger.warn("Despues de \"createModificationMessage(\"Emision...\"");
			} catch (Exception e) {
				return false;
			}

		}

		return true;
	}

	@Override
	public List<EmisionAcciones> getListaEmisiones(Long idMuestra, boolean rtCerrado) {
		List<EmisionAcciones> lista = new ArrayList<EmisionAcciones>();
		// Query query= null;
		//
		// String sql= "select new com.bmv.mci.admin.shared.dto.EmisionAcciones (" +
		// "a.emision.id, a.emision.emisora || ' ' || a.emision.serie,
		// a.accionesEnMuestra, " +
		// (rtCerrado?"a.emision.precioActual, a.emision.precioRTActual
		// ":"a.emision.precioReferencia, a.emision.precioRTReferencia ") +
		// ", a.emision.accionesCirculacion) " +
		// "from EmisionMuestra a " +
		// "where a.muestra.id= ? ";
		// query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		// query.setParameter(0, idMuestra);
		// lista= query.list();

		return lista;
	}

	@Override
	public List<IndiceSumatoria> getEtfsData(Long idEmision) {
		List<IndiceSumatoria> lista = new ArrayList<IndiceSumatoria>();
		// Query query= null;
		//
		// String sql= "select new com.bmv.mci.admin.shared.dto.IndiceSumatoria (" +
		// "c.primaryKey.etf.instrumento, c.primaryKey.etf.precioTeoricoActual) " +
		// "from Carteras c " +
		// "where c.emisionMci.id= ? ";
		// query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		//
		// query.setParameter(0, idEmision);
		// lista= query.list();
		List<Object[]> listObjects = carteraDao.getEtfsData(idEmision);

		for (Object[] objects : listObjects) {
			lista.add(new IndiceSumatoria((String) objects[0], (Double) objects[1]));
		}

		return lista;
	}

	public String getRutaCompartida() {
		return rutaArchivosCsv;
	}

	public Boolean obtenRTCerrado() {
		return consultaIntradiaMuestraService.obtenRTCerrado();
	}

	/**
	 * M&eacute;todo para transformar un archivo en objetos de tipo dto,
	 * IndiceNivSumRef.
	 * para la transformacion del archivo se consideran
	 * 
	 * @param archivoCsv
	 * @return Listado de objetos tipo IndiceNivSumRef
	 */
	private List<IndiceNivSumRef> transformaArchivo(final File archivoCsv) {
		List<IndiceNivSumRef> listaIndiceNivSumRef = new ArrayList<IndiceNivSumRef>();
		BufferedReader bufRdr = null;
		String line = null;

		try {
			bufRdr = new BufferedReader(new FileReader(archivoCsv));
			while ((line = bufRdr.readLine()) != null) {
				String[] items = line.split(",");
				if (items != null && (items.length == 7 || items.length == 6)) {
					IndiceNivSumRef indiceNivSumRef = new IndiceNivSumRef(
							items[0].toString(),
							Short.valueOf(items[1].toString()),
							Short.valueOf(items[2].toString()),
							Short.valueOf(items[3].toString()),
							Short.valueOf(items[4].toString()),
							(items[5].toString().trim().length() == 0 ? null : Double.valueOf(items[5].toString())),
							(items.length == 6 ? null : Double.valueOf(items[6].toString())));

					listaIndiceNivSumRef.add(indiceNivSumRef);
				} else {
					throw new RuntimeException();
				}
			}
			bufRdr.close();
		} catch (Exception e) {
			e.getCause();
			throw new RuntimeException("error:" + e.getMessage());

		}
		return listaIndiceNivSumRef;
	}

	@Override
	public List<IndiceSimple> getListaIndicesEdic() {
		// Query query = null;
		List<IndiceSimple> indices = new ArrayList<IndiceSimple>(0);

		// String sql =
		// "select new com.bmv.mci.admin.shared.dto.IndiceSimple("+
		// "A.id, " +
		//
		// //"replace(A.claveIndice || A.sector || A.subSector || " +
		// //"A.ramo || A.subRamo, '0000', '') || ' ' || '(' || " +
		// "A.claveIndice || A.sector || A.subSector || " +
		// "A.ramo || A.subRamo || ' ' || '(' || " +
		//
		// "A.nombre || ')'"+
		// ") "+
		// "from Indice A where A.calculoActivado is true";
		//
		// try{
		// query = sessionFactoryOds.getCurrentSession().createQuery(sql);
		// }catch(Exception e){
		// System.out.println("Edicion de Indices: Error al hacer consulta: " +
		// e.getMessage());
		// }
		// if(query == null){
		// System.out.println("Edicion de Indices: Leido Null");
		// }else{
		// System.out.println("Indices Obtenidos size: "+query.list().size());
		// indices.addAll( query.list());
		// }
		return indices;
	}

}
