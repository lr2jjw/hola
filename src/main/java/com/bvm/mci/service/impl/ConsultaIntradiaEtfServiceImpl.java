package com.bvm.mci.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bmv.mci.distributor.InfoDistributorService;
import com.bmv.mci.model.ArchivosEtfs;
import com.bmv.mci.model.AuditoriaODS;
import com.bmv.mci.model.CalendarioMantenimientos;
import com.bmv.mci.model.Difusion;
import com.bmv.mci.model.Emision;
import com.bmv.mci.model.EstadoAtencionMantenimiento;
import com.bmv.mci.model.EstadoRealtime;
import com.bmv.mci.model.Etf;
import com.bmv.mci.model.EtfArchivos;
import com.bmv.mci.model.TasaDividendosIPC;
import com.bmv.mci.model.TipoCierre;
import com.bmv.mci.transform.services.TransformServices;
import com.bmv.mci.vo.CalcularEtfResult;
import com.bmv.mci.vo.CarteraEtfInfo;
import com.bmv.mci.vo.ConstantesEtf;
import com.bmv.mci.vo.DerivadosIpcInfo;
//import com.bmv.mci.vo.EmisionMuestraEtfInfo;
import com.bmv.mci.vo.EmisionPrecios;
import com.bmv.mci.vo.EtfInfo;
import com.bvm.mci.dto.ArchivoEtfSimple;
import com.bvm.mci.dto.DestinatarioSimple;
import com.bvm.mci.dto.EmisionIntradiaEtf;
import com.bvm.mci.dto.FormatoEtfIR;
import com.bvm.mci.dto.FormatoEtfIV;
import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.RealtimeOperacion;
import com.bvm.mci.dto.TasaDividendosSimple;
import com.bvm.mci.service.AdmonAlertasService;
import com.bvm.mci.service.ConsultaIntradiaEtfService;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.shared.Constantes;

import lombok.RequiredArgsConstructor;

/**
 * @author rdiaz
 *
 */
@SuppressWarnings("all")
@Service("consultaIntradiaEtfService")
@Lazy(true)
@Transactional
@RequiredArgsConstructor
public class ConsultaIntradiaEtfServiceImpl implements ConsultaIntradiaEtfService {

	@Named("sessionFactoryOds")
	private final SessionFactory sessionFactoryOds;

	@Autowired
	private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

	private Log log = LogFactory.getLog(ConsultaIntradiaEtfServiceImpl.class);

	@Inject
	@Qualifier("highAvailabilityBean")
	private RealtimeAdminService cache;

	@Inject
	private AdmonAlertasService admonAlertasService;

	@Inject
	private String rutaArchivosCsv;

	@Inject
	private String rutaArchivosEtfs;

	@Inject
	@Named("infoDistributorService")
	private InfoDistributorService infoDistributor;

	@Inject
	private TransactionTemplate txTemplate;

	/*
	 * @Inject
	 * 
	 * @Named("transformEtfServices")
	 * private TransformEtfServices transformEtfServices;
	 */

	@Inject
	@Named("transformServices")
	private TransformServices transformServices;

	@Resource(name = "profileId")
	private String profileId;

	@Inject
	private Integer intentosDelayArchivosEtfs;

	@Inject
	private Long sleepDelayArchivosEtfs;

	Map<Long, EtfInfo> datosIntradiaEtf;

	private final int LONGITUD_MENSAJE_H2H = 69;
	private final int LONGITUD_TOTAL_MENSAJE = 257;

	/**
	 * 
	 * DABE - AQUI SE HACE LA CONSULTA SI ESTA CERRADO VA A BASE SI NO VA A RMI
	 */
	public List<IndiceIntradiaEtf> getIndicesEtf() {
		List<IndiceIntradiaEtf> lista = new ArrayList<IndiceIntradiaEtf>();

		if (!obtenRTCerrado()) {
			try {
				datosIntradiaEtf = cache.getEtfsMap();
			} catch (Exception e) {
				return lista;
			}
			Long nextId;
			EtfInfo efInfo;
			Iterator<Long> iterator;
			if (datosIntradiaEtf != null) {
				iterator = datosIntradiaEtf.keySet().iterator();
				while (iterator.hasNext()) {
					nextId = iterator.next();
					efInfo = datosIntradiaEtf.get(nextId);
					if (efInfo.getEstado() == 1 ||
							efInfo.getEstado() == 2 ||
							efInfo.getEstado() == 3 ||
							efInfo.getEstado() == 4) {
						efInfo.setEstado(this.getLastEstadoCalendarizado(efInfo.getId(), efInfo.getEstado()));
						lista.add(new IndiceIntradiaEtf(
								efInfo.getId(),
								efInfo.getNumEmisionAnt(),
								efInfo.getInstrumento(),
								efInfo.getTipoValor(),
								efInfo.getSerie(),
								efInfo.getIdMuestraEtf(),

								// red efInfo.getIdIndice(),
								Long.valueOf(0),
								roundAvoid(efInfo.getComponenteEfectivo()),
								efInfo.getNumeroCertificados(),
								roundAvoid(efInfo.getPrecioTeoricoActual()),
								roundAvoid(efInfo.getPrecioTeoricoAnterior()),
								roundAvoid(efInfo.getPrecioTeoricoDiaAnterior()),
								// efInfo.getComposicionEtf()
								nextId, efInfo.getEstado(),
								efInfo.getEstadoRemate(),
								roundAvoid(efInfo.getComponenteEfectivoTitExc()) // Ticket#1191026 - Fase 3 Mejoras MCI
						));
					}

				}
			} else {
				return null;
			}

		} else {
			// System.out.println("ETFs: RT cerrado, trayendo datos de BD");

			Query query = sessionFactoryOds.getCurrentSession().createQuery(
					"from Etf A where A.estado IN (1, 2, 3, 4)");

			if (query != null) {
				if (query.list().size() > 0) {
					for (Etf etf : (List<Etf>) query.list()) {

						// etf.getAuditoria()
						// etf.getInstrumento()
						// etf.getTipoAlgoritmoCalculo()
						// etf.getTipoCalculo()
						lista.add(new IndiceIntradiaEtf(
								etf.getId(),
								etf.getNumEmisionAnt(),
								etf.getInstrumento(),
								etf.getTipoValor(),
								etf.getSerie(),
								etf.getIdMuestraEtf(),

								// red etf.getIdIndice(),
								Long.valueOf(0),

								etf.getComponenteEfectivo(),
								etf.getNumeroCertificados(),
								roundAvoid(etf.getPrecioTeoricoActual()),
								roundAvoid(etf.getPrecioTeoricoAnterior()),
								roundAvoid(etf.getPrecioTeoricoDiaAnterior()),
								Long.valueOf(-1),
								this.getLastEstadoCalendarizado(etf.getId(), etf.getEstado()),
								etf.getEstadoRemate(),
								etf.getComponenteEfectivoTitExc() // Ticket#1191026 - Fase 3 Mejoras MCI
						));
					}
				}
			}

		}

		return lista;
	}

	private Long getLastEstadoCalendarizado(Long idEtf, Long estadoEtf) {
		Long estado = 0L;
		if (idEtf != null) {
			estado = estadoEtf;// en caso de no encontrar estado continuamos con el original
			// log.warn(idEtf+" su estado Original:"+estado);
			Session s = sessionFactoryOds.getCurrentSession();
			String hql = "from CalendarioMantenimientos cal "
					+ " where cal.etf.id=:idEtf "
					+ " order by cal.fechaEfectiva asc ";
			Query query = s.createQuery(hql);
			query.setParameter("idEtf", idEtf);
			if (query != null) {
				if (query.list().size() > 0) {

					for (CalendarioMantenimientos cal : (List<CalendarioMantenimientos>) query.list()) {
						if (Constantes.CALENDARIO_MANTENIMIENTO_BAJA.equals(cal.getAccion())) {
							estado = Constantes.ESTATUS_INACTIVO_TEMP;
						}
					}
				}
			}
		}
		// log.warn(idEtf+" su estado Final:"+estado);
		return estado;
	}

	public List<EmisionIntradiaEtf> getMuestraEtf(Long idEtf) {
		List<EmisionIntradiaEtf> lista = new ArrayList<EmisionIntradiaEtf>();

		if (idEtf != null) {
			Session s = sessionFactoryOds.getCurrentSession();
			String hql = "SELECT e.id, etf.id, C.titulos, C.titulosExcluidos, e.emisora, "
					+ "e.serie, e.precioReferencia, ti.claveInstrumento, e.precioActual, C.primaryKey.cveEmision, "
					+ "C.primaryKey.cveSerie,  C.precioReferencia, d.idInstrumento, d.precioReferencia, d.precioReferenciaAnterior "
					+ "from Carteras C "
					+ "join C.primaryKey.etf etf "
					+ "join C.tipoInstrumento ti "
					+ "left join C.emisionMci e "
					+ "left join DerivadosIpc d on C.primaryKey.cveEmision = d.claseClavePrecioEjercicio and C.primaryKey.cveSerie = d.claveVencimiento "
					+ "where etf.id = :idEtf";
			Query query = s.createQuery(hql);
			query.setParameter("idEtf", idEtf);
			for (Object[] data : (List<Object[]>) query.list()) {
				lista.add(new EmisionIntradiaEtf(
						(Long) data[0],
						(Long) data[1],
						(Long) data[12],
						(Integer) data[2],
						(Double) data[3],
						(String) (data[4] != null ? data[4] : data[9]),
						(String) (data[5] != null ? data[5] : data[10]),
						(Double) (data[6] != null ? data[6] : (data[14] != null ? data[14] : data[11])),
						(String) data[7],
						(Double) (data[8] != null ? data[8] : (data[13] != null ? data[13] : null))));
			}
			if (!obtenRTCerrado() && lista != null & !lista.isEmpty()) {
				Map<Long, Map<String, CarteraEtfInfo>> mapa = cache.getCarterasMap();
				Map<Long, EmisionPrecios> emisMap = cache.getEmisionesMap();
				for (EmisionIntradiaEtf emisionIntradiaEtf : lista) {
					if (emisionIntradiaEtf.getIdEmision() != null) {
						EmisionPrecios emisionPrecios = emisMap.get(emisionIntradiaEtf.getIdEmision());
						if (emisionPrecios != null) {
							emisionIntradiaEtf.setPrecioActual(emisionPrecios.getPrecioActual());
						}
					} else if (emisionIntradiaEtf.getIdDerivadosIpc() != null) {

						DerivadosIpcInfo derivadosIpcInfo = cache.getDerivadosIpcMap()
								.get(emisionIntradiaEtf.getIdDerivadosIpc());
						if (derivadosIpcInfo != null) {
							emisionIntradiaEtf.setPrecioActual(derivadosIpcInfo.getPrecioReferencia());
						}
					} else {
						cache.getCarterasMap();
					}

				}
			}
		} else {
			return null;
		}

		return lista;
	}

	public Boolean obtenRTCerrado() {
		return consultaIntradiaMuestraService.obtenRTCerrado();
	}

	public Boolean obtenRTStatus() {
		return consultaIntradiaMuestraService.obtenRTStatus();
	}

	public RealtimeOperacion getRealtimeOperacion() {
		RealtimeOperacion result = new RealtimeOperacion();
		try {
			result.setEstadoUsuarioRealtime(consultaIntradiaMuestraService.getStatusUserRT());
			result.setEstadoRealtime(consultaIntradiaMuestraService.getEstadoRealtime());
			result.setActivo(consultaIntradiaMuestraService.obtenRTStatus());
			result.setCerrado(consultaIntradiaMuestraService.obtenRTCerrado());// Realtime desconectado o Cierre de
																				// Mercado Captiales(ultimo U-DE 15:15)
		} catch (Exception e) {
			result.setActivo(false);
			result.setCerrado(false);
			result.setEstadoRealtime(null);
			result.setCerrado(false);
			result.setEstadoUsuarioRealtime("NA");
		}
		/*
		 * log.warn("RealtimeOperacion:"
		 * +result.getEstadoUsuarioRealtime()+":"
		 * +result.getEstadoRealtime()+":"
		 * +result.isActivo()+":"
		 * +result.isCerrado());
		 */
		return result;
	}

	public void recargaArchivosEmisnet() {
		log.warn("Antes de \"reiniciaCargaInicialETFs\"");
		cache.reiniciaCargaInicialETFs();
		log.warn("Despues de \"reiniciaCargaInicialETFs\"");
	}

	public String getRutaCompartida() {
		return rutaArchivosCsv;
	}

	public String getRutaCompartidaETFs() {
		return rutaArchivosEtfs;
	}

	public String subeArchivo(String ruta, Boolean sonInstrumentos) {
		String resultado = "";
		File archivoCsv = new File(ruta);

		if (sonInstrumentos) {
			List<FormatoEtfIR> listaIR = transformaArchivoIR(archivoCsv);
			mandaMensajesIR(listaIR);
			guardaPrecioTeorico(listaIR);
		} else {
			List<FormatoEtfIV> listaIV = transformaArchivoIV(archivoCsv);
			mandaMensajesIV(listaIV);
		}

		return "ok";
	}

	private List<FormatoEtfIV> transformaArchivoIV(File archivoCsv) {
		List<FormatoEtfIV> listaFormatoEtfIV = new ArrayList<FormatoEtfIV>();
		BufferedReader bufRdr = null;
		String line = null;

		System.out.println("Nombre del archivo CSV: " + archivoCsv.getPath() + ":" + archivoCsv.getName());

		try {
			bufRdr = new BufferedReader(new FileReader(archivoCsv));
			while ((line = bufRdr.readLine()) != null) {
				String[] items = line.split(",");
				if (items != null && items.length == 11) {

					try {

						FormatoEtfIV formatoEtfIV = new FormatoEtfIV(
								items[0].toString(),
								items[1].toString(),
								items[2].toString(),
								items[3].toString(),
								Long.valueOf(Double.valueOf(items[4].toString()).longValue()),
								Double.valueOf(items[5].toString()),
								Double.valueOf(items[6].toString()),
								Double.valueOf(items[7].toString()),
								Double.valueOf(items[8].toString()),
								Long.valueOf(Double.valueOf(items[9].toString()).longValue()),
								Double.valueOf(items[10].toString()),
								Double.valueOf(items[11].toString()));

						listaFormatoEtfIV.add(formatoEtfIV);

					} catch (Exception e) {
						System.out.println("Registro IV excluido: " + items[0].toString());
					}

				} else {
					throw new RuntimeException();
				}
			}
			bufRdr.close();
		} catch (Exception e) {
			e.getCause();
			throw new RuntimeException("error:" + e.getMessage());
		}
		return listaFormatoEtfIV;
	}

	private List<FormatoEtfIR> transformaArchivoIR(File archivoCsv) {
		List<FormatoEtfIR> listaFormatoEtfIR = new ArrayList<FormatoEtfIR>();
		BufferedReader bufRdr = null;
		String line = null;

		System.out.println("Nombre del archivo CSV: " + archivoCsv.getPath() + ":" + archivoCsv.getName());

		try {
			bufRdr = new BufferedReader(new FileReader(archivoCsv));
			while ((line = bufRdr.readLine()) != null) {
				String[] items = line.split(",");
				if (items != null && items.length == 8) {

					try {

						FormatoEtfIR formatoEtfIR = new FormatoEtfIR(
								items[0].toString(),
								items[1].toString(),
								Double.valueOf(items[2].toString()),
								Double.valueOf(items[3].toString()),
								items[4].toString(),
								items[5].toString(),
								Long.valueOf(items[6].toString()),
								Long.valueOf(items[7].toString()));

						listaFormatoEtfIR.add(formatoEtfIR);

					} catch (Exception e) {
						System.out.println("Registro IR excluido: " + items[0].toString());
					}

				} else {
					throw new RuntimeException();
				}
			}
			bufRdr.close();
		} catch (Exception e) {
			e.getCause();
			throw new RuntimeException("error:" + e.getMessage());
		}
		return listaFormatoEtfIR;
	}

	private void mandaMensajesIR(List<FormatoEtfIR> listaIR) {
		List<CalcularEtfResult> listCalcularEtfResult = new ArrayList<CalcularEtfResult>();
		List<String> listax = new ArrayList<String>();
		StringBuffer filler = new StringBuffer();
		String mensajecorto;

		for (FormatoEtfIR formatoEtfIR : listaIR) {
			CalcularEtfResult calcularEtfResult = new CalcularEtfResult();

			calcularEtfResult.setInstrumento(formatoEtfIR.getInstrumento());
			calcularEtfResult.setNumEmision(formatoEtfIR.getNumeroFeed());
			calcularEtfResult.setNumEmisionAnt(formatoEtfIR.getNumEmision());
			calcularEtfResult.setPrecioTeoricoAnterior(formatoEtfIR.getPrecioDiaAnterior());
			calcularEtfResult.setPrecioTeoricoCalculado(formatoEtfIR.getPrecioUltimo());
			calcularEtfResult.setSerie(formatoEtfIR.getSerie());
			calcularEtfResult.setTipoValor(formatoEtfIR.getTipoValor());

			listCalcularEtfResult.add(calcularEtfResult);
		}

		for (CalcularEtfResult calcularEtfResult : listCalcularEtfResult) {
			listax.add(llamaTransformador(calcularEtfResult, Constantes.formatoIR));
		}

		for (String cadena : listax) {
			filler.delete(0, filler.length());
			mensajecorto = cadena.substring(0, LONGITUD_MENSAJE_H2H);
			for (int i = 0; i < (LONGITUD_TOTAL_MENSAJE - LONGITUD_MENSAJE_H2H); i++) {
				filler.append(" ");
			}
			mensajecorto = mensajecorto + filler.toString();

			mandaMensajeH2H(mensajecorto);
			mandaMensajeNSO(cadena);
		}
	}

	private void mandaMensajesIV(List<FormatoEtfIV> listaIV) {
		List<CalcularEtfResult> listCalcularEtfResult = new ArrayList<CalcularEtfResult>();
		List<String> listax = new ArrayList<String>();
		StringBuffer filler = new StringBuffer();
		String mensajecorto;

		for (FormatoEtfIV formatoEtfIV : listaIV) {
			CalcularEtfResult calcularEtfResult = new CalcularEtfResult();

			// formatoEtfIV.getFormato()

			// calcularEtfResult.setCancelarPeristenciaBitacora(cancelarPeristenciaBitacora);
			calcularEtfResult.setComponenteEfectico(formatoEtfIV.getComponenteEfectivo());
			calcularEtfResult.setComponenteEfectivoTitExc(formatoEtfIV.getComponenteEfectivoTitExc()); // Ticket#1191026
																										// - Fase 3
																										// Mejoras MCI
			calcularEtfResult.setEmisora(formatoEtfIV.getEmisora());
			// calcularEtfResult.setIdEtf(idEtf);
			calcularEtfResult.setInstrumento(formatoEtfIV.getInstrumento());
			calcularEtfResult.setNoCertificados(formatoEtfIV.getNumeroCertificados());
			// calcularEtfResult.setNumEmision();
			// calcularEtfResult.setNumEmisionAnt(numEmisionAnt);
			calcularEtfResult.setPrecio(formatoEtfIV.getPrecio());
			calcularEtfResult.setPrecioTeorico(formatoEtfIV.getPrecioTeorico());
			// calcularEtfResult.setPrecioTeoricoAnterior(precioTeoricoAnterior);
			// calcularEtfResult.setPrecioTeoricoCalculado(precioTeoricoCalculado);
			calcularEtfResult.setSerie(formatoEtfIV.getSerie());
			// calcularEtfResult.setTipoValor();
			calcularEtfResult.setTitulos(formatoEtfIV.getTitulos());
			calcularEtfResult.setTitulosExcluidos(formatoEtfIV.getTitulosExcluidos());
			calcularEtfResult.setValorExcluido(formatoEtfIV.getValorExcluido());

			listCalcularEtfResult.add(calcularEtfResult);
		}

		for (CalcularEtfResult calcularEtfResult : listCalcularEtfResult) {
			listax.add(llamaTransformador(calcularEtfResult, Constantes.formatoIV));
		}

		for (String cadena : listax) {
			mandaMensajeH2H(cadena);
		}
	}

	private String llamaTransformador(final CalcularEtfResult calcularEtfResult, final String formato) {
		String cadena = transformServices.outputTransformEtfs(calcularEtfResult, formato);

		return cadena;
	}

	private void mandaMensajeNSO(String cadena) {
		try {
			System.out.println("Mandando mendaje a NSO ...");
			log.warn("Antes de \"difundirMensajeEtfNso\"");
			cache.difundirMensajeEtfNso(cadena);
			log.warn("Despues de \"difundirMensajeEtfNso\"");
		} catch (Exception e) {
			System.err.println("Falla al enviar el mensaje etf a NSO: " + e.getMessage());
		}
	}

	private void mandaMensajeH2H(String cadena) {
		try {
			System.out.println("Mandando mendaje a H2H ...");
			log.warn("Antes de \"difundirMensajeEtfH2h\"");
			cache.difundirMensajeEtfH2h(cadena);
			log.warn("Despues de \"difundirMensajeEtfH2h\"");
		} catch (Exception e) {
			System.err.println("Falla al enviar el mensaje etf a H2H: " + e.getMessage());
		}
	}

	private void guardaPrecioTeorico(List<FormatoEtfIR> listaIR) {
		Session s = sessionFactoryOds.getCurrentSession();
		Query query;

		for (FormatoEtfIR formatoEtfIR : listaIR) {
			query = s.createQuery("from Etf A "
					+ "where A.instrumento = :instru and A.serie = :serie");
			query.setParameter("instru", formatoEtfIR.getInstrumento());
			query.setParameter("serie", formatoEtfIR.getSerie());

			System.out.println(
					"Buscando id de " + formatoEtfIR.getInstrumento() + "." + formatoEtfIR.getSerie() + " ...");

			if (query != null) {
				for (Etf etf : (List<Etf>) query.list()) {
					System.out.println("Encontrado id de " + formatoEtfIR.getInstrumento() + "."
							+ formatoEtfIR.getSerie() + ": " + etf.getId());
					etf.setPrecioTeoricoActual(formatoEtfIR.getPrecioUltimo());
					// etf.setPrecioTeoricoAnterior();
					etf.setPrecioTeoricoDiaAnterior(formatoEtfIR.getPrecioDiaAnterior());
					etf.getAuditoria().setFechaModificacion(new Date());
					etf.getAuditoria().setModificadoPor(Constantes.USUARIO_MCI);
					sessionFactoryOds.getCurrentSession().saveOrUpdate(etf);
				}
			}
		}
	}

	/**
	 * RGG: Permite consultar las emisiones ordenadas por instrumento para el
	 * ALTA de ETFs
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEmisionesEtf() {
		List<IndiceIntradiaEtf> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM Emision E WHERE E.tipoValor IN('1B','1C') AND E.emisora NOT IN(" +
						"   SELECT f.emisionMci.emisora FROM Etf f WHERE f.emisionMci.id IS NOT NULL" +
						") order by E.emisora, E.auditoria.fechaCreacion asc");
		HashMap<String, Emision> mapEmisorasRepetidas = new HashMap<String, Emision>();
		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<IndiceIntradiaEtf>();
				for (Emision emision : (List<Emision>) query.list()) {
					if (mapEmisorasRepetidas.get(emision.getEmisora()) == null) {
						lista.add(new IndiceIntradiaEtf(
								emision.getId(),
								emision.getIdEmisionODS(),
								emision.getEmisora(),
								emision.getTipoValor(),
								emision.getSerie(),
								Long.valueOf(0),
								Long.valueOf(0),
								Long.valueOf(0),
								Long.valueOf(0)));
						mapEmisorasRepetidas.put(emision.getEmisora(), emision);
					}

				}
			}
		}
		return lista;
	}

	/**
	 * Permite consultar ETF activos ordenados por instrumento
	 * Se usa en la pantalla de BAJA de etf
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivos() {
		List<IndiceIntradiaEtf> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM Etf etf WHERE etf.estado IN (1, 2, 3) order by etf.instrumento");

		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<IndiceIntradiaEtf>();
				for (Etf etf : (List<Etf>) query.list()) {
					lista.add(new IndiceIntradiaEtf(
							null,
							null,
							etf.getInstrumento(),
							etf.getTipoValor(),
							etf.getSerie(),
							Long.valueOf(0),
							etf.getEstado(),
							etf.getId(),
							Long.valueOf(0)));
				}
			}
		}
		return lista;
	}

	/**
	 * Permite consultar ETF activos ordenados por instrumento
	 * Se usa en la pantalla de BAJA de etf
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivosSinBaja() {
		List<IndiceIntradiaEtf> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM Etf etf WHERE etf.estado IN (1, 2, 3) "
						+ " and not exists("
						+ "    from CalendarioMantenimientos cal"
						+ "    where cal.etf=etf"
						+ "    and cal.accion IN('" + Constantes.CALENDARIO_MANTENIMIENTO_BAJA + "') "
						+ " ) "
						+ " order by etf.instrumento");
		// log.warn("getEtfActivosSinBaja modificar para carga");
		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<IndiceIntradiaEtf>();
				for (Etf etf : (List<Etf>) query.list()) {
					lista.add(new IndiceIntradiaEtf(
							null,
							null,
							etf.getInstrumento(),
							etf.getTipoValor(),
							etf.getSerie(),
							Long.valueOf(0),
							etf.getEstado(),
							etf.getId(),
							Long.valueOf(0)));
				}
			}
		}
		return lista;
	}

	/**
	 * Permite consultar ETF en baja o por dar de baja ordenados por instrumento
	 * Se usa en la pantalla de REACTIVACION de etf
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivosEnBaja() {
		List<IndiceIntradiaEtf> lista = new ArrayList<IndiceIntradiaEtf>();
		Query query = sessionFactoryOds.getCurrentSession().createQuery("FROM Etf etf WHERE (etf.estado = 0 and "
				+ "not exists (from CalendarioMantenimientos cal " + "		where cal.etf = etf and cal.accion = '"
				+ Constantes.CALENDARIO_MANTENIMIENTO_REACTIVACION + "' and cal.atendido = "
				+ EstadoAtencionMantenimiento.NO_APLICADO.getCode() + ")) "
				+ "or exists(from CalendarioMantenimientos cal where cal.etf = etf" + "    and cal.accion = '"
				+ Constantes.CALENDARIO_MANTENIMIENTO_BAJA + "' and cal.atendido = "
				+ EstadoAtencionMantenimiento.NO_APLICADO.getCode() + " ) ");
		if (query != null) {
			if (query.list().size() > 0) {
				for (Etf etf : (List<Etf>) query.list()) {
					lista.add(new IndiceIntradiaEtf(
							null,
							null,
							etf.getInstrumento(),
							etf.getTipoValor(),
							etf.getSerie(),
							Long.valueOf(0),
							etf.getEstado(),
							etf.getId(),
							Long.valueOf(0)));
				}
			}
		}

		return lista;

		// +" and not exists("
		// + " from CalendarioMantenimientos cal"
		// + " where cal.etf=etf"
		// + " and cal.accion IN('"+Constantes.CALENDARIO_MANTENIMIENTO_BAJA+"') "
		// + " ) "
		// + " order by etf.instrumento"
	}

	/**
	 * Permite consultar ETF activos y pendientes ordenados por instrumento
	 * Se usa en la pantalla de Carga de etf
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivosYPendientes() {
		List<IndiceIntradiaEtf> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM Etf etf WHERE etf.estado IN(1, 2, 3) order by etf.instrumento");

		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<IndiceIntradiaEtf>();
				for (Etf etf : (List<Etf>) query.list()) {
					IndiceIntradiaEtf loIndiceIntradiaEtf = new IndiceIntradiaEtf(
							null,
							null,
							etf.getInstrumento(),
							etf.getTipoValor(),
							etf.getSerie(),
							Long.valueOf(0),
							etf.getEstado(),
							etf.getId(),
							etf.getValidaCartera());
					if (etf.getDifusiones() != null && !etf.getDifusiones().isEmpty()) {
						for (Difusion difusion : etf.getDifusiones()) {
							if (difusion.getFormato().equals(Constantes.formatoIV)) {
								loIndiceIntradiaEtf.setIv(Constantes.ESTATUS_ACTIVO);
							} else {
								if (difusion.getFormato().equals(Constantes.formatoIR)) {
									loIndiceIntradiaEtf.setIr(Constantes.ESTATUS_ACTIVO);
								}
							}
						}
					}
					lista.add(loIndiceIntradiaEtf);
				}
			}
		}
		return lista;
	}

	/**
	 * Permite consultar ETF activos y defectuosos ordenados por instrumento
	 * Se usa en la pantalla de Difusion de etf
	 * 
	 * @return
	 */
	public List<IndiceIntradiaEtf> getEtfActivosYConDefectos() {
		List<IndiceIntradiaEtf> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM Etf etf WHERE etf.estado IN(1, 2) order by etf.instrumento");

		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<IndiceIntradiaEtf>();
				for (Etf etf : (List<Etf>) query.list()) {
					IndiceIntradiaEtf loIndiceIntradiaEtf = new IndiceIntradiaEtf(
							null,
							null,
							etf.getInstrumento(),
							etf.getTipoValor(),
							etf.getSerie(),
							Long.valueOf(0),
							etf.getEstado(),
							etf.getId(),
							etf.getValidaCartera());
					if (etf.getDifusiones() != null && !etf.getDifusiones().isEmpty()) {
						for (Difusion difusion : etf.getDifusiones()) {
							if (difusion.getFormato().equals(Constantes.formatoIV)) {
								loIndiceIntradiaEtf.setIv(Constantes.ESTATUS_ACTIVO);
							} else {
								if (difusion.getFormato().equals(Constantes.formatoIR)) {
									loIndiceIntradiaEtf.setIr(Constantes.ESTATUS_ACTIVO);
								}
							}
						}
					}
					lista.add(loIndiceIntradiaEtf);
				}
			}
		}
		return lista;
	}

	/**
	 * Permite dar de baja logicamente un ETF
	 * 
	 * @return
	 */
	public boolean bajaLogicaETF(Long idEtf) {
		boolean respuesta = false;
		try {
			log.warn("bajaLogicaETF: " + idEtf);
			// Datos de auditoria
			AuditoriaODS loAuditoriaODS = new AuditoriaODS();
			loAuditoriaODS.setCreadoPor(Constantes.USUARIO_MCI);
			loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
			loAuditoriaODS.setFechaCreacion(new Date());
			Etf etf = (Etf) sessionFactoryOds.getCurrentSession().get(Etf.class, idEtf);
			log.warn("getInstrumento: " + etf.getInstrumento());
			CalendarioMantenimientos calMant = new CalendarioMantenimientos();
			calMant.setEtf(etf);
			calMant.setAccion(Constantes.CALENDARIO_MANTENIMIENTO_BAJA);
			calMant.setMensaje("BAJA PROGRAMADA DESDE MCI-GUI");
			calMant.setAtendido(EstadoAtencionMantenimiento.NO_APLICADO);
			calMant.setFechaEfectiva(new Date());
			calMant.setAuditoria(loAuditoriaODS);
			sessionFactoryOds.getCurrentSession().save(calMant);
			Long idMantenimiento = calMant.getId();
			log.warn(" Registro de Mantenimiento: " + idMantenimiento);
			respuesta = true;
		} catch (Exception e) {
			log.warn("Error al dar de baja ETF: " + e.getMessage());
		}

		return respuesta;
	}

	/**
	 * Permite dar de baja logicamente un ETF
	 * 
	 * @return
	 */
	public boolean reactivarETF(Long idEtf) {
		boolean respuesta = false;
		try {
			log.warn("bajaLogicaETF: " + idEtf);
			// Datos de auditoria
			AuditoriaODS loAuditoriaODS = new AuditoriaODS();
			loAuditoriaODS.setCreadoPor(Constantes.USUARIO_MCI);
			loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
			loAuditoriaODS.setFechaCreacion(new Date());
			Etf etf = (Etf) sessionFactoryOds.getCurrentSession().get(Etf.class, idEtf);
			if (etf.getEstado() == ConstantesEtf.ESTADO_CANCELADO) {
				log.warn("getInstrumento: " + etf.getInstrumento());
				CalendarioMantenimientos calMant = new CalendarioMantenimientos();
				calMant.setEtf(etf);
				calMant.setAccion(Constantes.CALENDARIO_MANTENIMIENTO_REACTIVACION);
				calMant.setMensaje("REACTIVACION PROGRAMADA DESDE MCI-GUI");
				calMant.setAtendido(EstadoAtencionMantenimiento.NO_APLICADO);
				calMant.setFechaEfectiva(new Date());
				calMant.setAuditoria(loAuditoriaODS);
				sessionFactoryOds.getCurrentSession().save(calMant);
				Long idMantenimiento = calMant.getId();
				log.warn(" Registro de Mantenimiento: " + idMantenimiento);
			} else {
				javax.persistence.Query query = null;
				List<CalendarioMantenimientos> lista = new ArrayList<CalendarioMantenimientos>();
				String sql = "select d from CalendarioMantenimientos d where d.etf.id = :idEtf "
						+ "and d.atendido = " + EstadoAtencionMantenimiento.NO_APLICADO.getCode() + " "
						+ "and d.accion = '" + Constantes.CALENDARIO_MANTENIMIENTO_BAJA + "' ";
				query = sessionFactoryOds.getCurrentSession().createQuery(sql, CalendarioMantenimientos.class);
				query.setParameter("idEtf", idEtf);

				CalendarioMantenimientos calendarioMantenimientos = (CalendarioMantenimientos) query.getSingleResult();
				if (calendarioMantenimientos != null) {
					sessionFactoryOds.getCurrentSession().delete(calendarioMantenimientos);
				}
			}
			respuesta = true;
		} catch (Exception e) {
			log.warn("Error al dar de baja ETF: " + e.getMessage());
		}

		return respuesta;
	}

	/**
	 * Permite guardar un EFT
	 * 
	 * @param etf
	 * @return id Etf guardado
	 */
	public Long guardaETF(IndiceIntradiaEtf etf) {
		Long regresa = new Long(0);
		try {
			EtfArchivos etfArchivos = agregaEtf(etf);
			regresa = etfArchivos.getId();

		} catch (Exception e) {
			log.info("Error al guardar el nuevo ETF: " + e.getMessage());
		}
		return regresa;
	}

	public Long sendArchivosCache(Long idEtfArchivos) {
		if (consultaIntradiaMuestraService.obtenRTStatus()) {
			Query<EtfArchivos> query = null;
			String sql = "from EtfArchivos d where d.id = :idEtfArchivos";
			query = sessionFactoryOds.getCurrentSession().createQuery(sql);
			query.setParameter("idEtfArchivos", idEtfArchivos);
			EtfArchivos etfArchivos = query.uniqueResult();
			if (etfArchivos != null) {
				cache.cargaCacheETF("ID", etfArchivos.getEtf().getId());
				cache.cargaCacheListArchivosEtfs("ID", etfArchivos.getArchivosEtfs().getId());
				return 1L;
			}
			return 0L;
		} else {
			return 1L;
		}

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public EtfArchivos agregaEtf(IndiceIntradiaEtf etf) {
		Long regresa = new Long(0);
		Etf loEtF = new Etf();
		loEtF.setSerie(etf.getSerie());
		loEtF.setInstrumento(etf.getInstrumento());
		loEtF.setNumEmisionAnt(etf.getNumEmisionAnt());
		loEtF.setCalculaPtInicial(etf.getCalculaPtInicial());
		loEtF.setValidaCartera(etf.getValidaCartera());
		loEtF.setTipoValor(etf.getTipoValor());
		Emision emisionMci = new Emision();
		emisionMci.setId(etf.getId());
		loEtF.setEmisionMci(emisionMci);
		TipoCierre tipoCierre = new TipoCierre();
		tipoCierre.setId(1L);
		loEtF.setTipoCierre(tipoCierre);
		// Datos de auditoria
		AuditoriaODS loAuditoriaODS = new AuditoriaODS();
		loAuditoriaODS.setCreadoPor(Constantes.USUARIO_MCI);
		loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
		loAuditoriaODS.setFechaCreacion(new Date());
		loEtF.setAuditoria(loAuditoriaODS);
		loEtF.setEstado(Constantes.ESTATUS_PENDIENTE);
		loEtF.setCierre(0);
		loEtF.setEstadoRemate(Constantes.REMATE_APERTURA);
		agregaDifusiones(loEtF, etf);
		sessionFactoryOds.getCurrentSession().save(loEtF);
		EtfArchivos etfArchivos = agregarArchivo(loEtF, etf.getArchivo());
		return etfArchivos;

	}

	public EtfArchivos agregarArchivo(Etf loEtF, String archivo) {
		Long regresa = null;
		EtfArchivos etfArchivos = null;
		Query<ArchivosEtfs> query = null;
		Query<EtfArchivos> queryArchivo = null;
		try {

			String sql = "from ArchivosEtfs d where d.nombreArchivo = :archivo";
			query = sessionFactoryOds.getCurrentSession().createQuery(sql);
			query.setParameter("archivo", archivo);
			AuditoriaODS loAuditoriaODS = new AuditoriaODS();
			loAuditoriaODS.setCreadoPor(Constantes.USUARIO_MCI);
			loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
			loAuditoriaODS.setFechaCreacion(new Date());

			ArchivosEtfs archivosEtfs = query.uniqueResult();
			if (archivosEtfs == null) {
				archivosEtfs = new ArchivosEtfs();
				archivosEtfs.setAuditoria(loAuditoriaODS);
				archivosEtfs.setNombreArchivo(archivo);
				sessionFactoryOds.getCurrentSession().save(archivosEtfs);
				regresa = archivosEtfs.getId();
			}

			sql = "from EtfArchivos d where d.etf.instrumento = :instrumento and d.archivosEtfs.nombreArchivo = :nombreArchivo";
			queryArchivo = sessionFactoryOds.getCurrentSession().createQuery(sql);
			queryArchivo.setParameter("instrumento", loEtF.getInstrumento());
			queryArchivo.setParameter("nombreArchivo", archivo);
			etfArchivos = queryArchivo.uniqueResult();
			if (etfArchivos == null) {
				etfArchivos = new EtfArchivos();
				etfArchivos.setEtf(loEtF);
				etfArchivos.setArchivosEtfs(archivosEtfs);
				etfArchivos.setAuditoria(loAuditoriaODS);
				sessionFactoryOds.getCurrentSession().save(etfArchivos);
			}
		} catch (Exception e) {
			log.info("Error al agregarArchivo etf: " + e.getMessage());
		}
		return etfArchivos;
	}

	/**
	 * Permite actualizar un EFT
	 * 
	 * @param etf
	 * @return id Etf actualizado
	 */
	public Long actualizaETF(IndiceIntradiaEtf etf) {
		Long regresa = new Long(0);
		eliminaDifusionesDeEtf(etf.getIdEtf());
		Etf loEtF = (Etf) sessionFactoryOds.getCurrentSession().get(Etf.class, etf.getIdEtf());
		loEtF.getAuditoria().setFechaModificacion(new Date());
		agregaDifusiones(loEtF, etf);
		try {
			sessionFactoryOds.getCurrentSession().merge(loEtF);
			regresa = loEtF.getId();
		} catch (Exception e) {
			log.info("Error al actualizaETF el nuevo ETF: " + e.getMessage());
		}
		/*
		 * Valores que regresa:
		 * 0 - No se actulizo
		 * >0 - Se guardo
		 */
		return regresa;
	}

	/**
	 * Permite dar de baja las emisiones asociadas a un ETF
	 * 
	 * @param idEtf
	 */
	private void eliminaDifusionesDeEtf(Long idEtf) {
		try {
			List<Difusion> listaDifusion = new ArrayList<Difusion>();
			Query query = null;

			String sql = "from Difusion d where d.etf.id = :idEtf";
			query = sessionFactoryOds.getCurrentSession().createQuery(sql);
			query.setParameter("idEtf", idEtf);

			listaDifusion = query.list();

			if (listaDifusion != null && !listaDifusion.isEmpty()) {
				for (Difusion difusion : listaDifusion) {
					sessionFactoryOds.getCurrentSession().delete(difusion);
				}
			}
		} catch (Exception e) {
			log.info("Error al dar de baja las difusiones de un ETF: " + e.getMessage());
		}
	}

	/**
	 * Permite agregar una posible lista de difusiones a la entidad
	 * 
	 * @param dto
	 * @return
	 */
	private void agregaDifusiones(Etf etf, IndiceIntradiaEtf dto) {
		AuthServiceImpl AuthServiceImpl = new AuthServiceImpl();

		List<Difusion> listaDifusion = null;
		// Dara de alta los Iv y los Ir
		if (dto.getIv() != null || dto.getIr() != null) {
			listaDifusion = new ArrayList<Difusion>();
			// Datos de auditoria
			AuditoriaODS loAuditoriaODS = new AuditoriaODS();
			loAuditoriaODS.setCreadoPor(Constantes.USUARIO_MCI);
			loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
			loAuditoriaODS.setFechaCreacion(new Date());
			if (dto.getIv() != null) {
				Difusion difusionIV = new Difusion();
				difusionIV.setEtf(etf);
				difusionIV.setAuditoria(loAuditoriaODS);
				difusionIV.setFormato(Constantes.formatoIV);
				listaDifusion.add(difusionIV);
			}
			if (dto.getIr() != null) {
				Difusion difusionIR = new Difusion();
				difusionIR.setEtf(etf);
				difusionIR.setAuditoria(loAuditoriaODS);
				difusionIR.setFormato(Constantes.formatoIR);
				listaDifusion.add(difusionIR);
			}

			if (etf.getCveETF() != null) {
				// Se envia notificacion por correo electronico ETF
				String[] listaDestinatarios = generaListaDeDestinatarios(admonAlertasService.getListaDestinatarios(),
						Constantes.TIPOSDESTINATARIOS_MAIL);
				String DatoIV = "";
				String DatoIR = "";
				String DatoIV_Anterior = "";
				String DatoIR_Anterior = "";
				if (dto.getIv() != null) {
					DatoIV = "[" + Constantes.formatoIV + "]";
				}
				if (dto.getIr() != null) {
					DatoIR = "-[" + Constantes.formatoIR + "]";
				}
				if (dto.getIr_ant() != null) {
					DatoIR_Anterior = "-[" + Constantes.formatoIR + "]";
				}
				if (dto.getIv_ant() != null) {
					DatoIV_Anterior = "[" + Constantes.formatoIV + "]";
				}
				if (listaDestinatarios != null && listaDestinatarios.length > 0) {
					String ambiente = profileId;
					String asunto = "Alarma MCI " + ambiente + ": Actualizaci\u00F3n de ETF.";
					String cuerpoCorreo = "El usuario: " + AuthServiceImpl.retrieveUsername()
							+ "\n\n\n Actualizo el ETF: " + etf.getCveETF() + "\n\n\n   Difusi\u00F3n anterior:"
							+ DatoIV_Anterior + DatoIR_Anterior + "\n\n\n   Difusi\u00F3n nueva:" + DatoIV + "  "
							+ DatoIR + "";
					infoDistributor.sendByMail(listaDestinatarios, asunto, cuerpoCorreo, null);
				}

			}

		}
		etf.setDifusiones(listaDifusion);
	}

	/**
	 * Permite consultar catalogo de archivos ETF
	 * 
	 * @return
	 */
	public List<ArchivoEtfSimple> getCatalogoArchivosEtf() {
		List<ArchivoEtfSimple> lista = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM ArchivosEtfs cat  order by cat.nombreArchivo");

		if (query != null) {
			if (query.list().size() > 0) {
				lista = new ArrayList<ArchivoEtfSimple>();
				for (ArchivosEtfs archivoETF : (List<ArchivosEtfs>) query.list()) {
					lista.add(new ArchivoEtfSimple(
							archivoETF.getId(), archivoETF.getNombreArchivo()));
				}
			}
		}
		return lista;
	}

	/**
	 * Permite consultar el id del catalogo de archivos en base al id del ETF
	 * 
	 * @return
	 */
	public Long getIdCatArchivoPorIdEtf(Long idEft) {
		Long idCatalogoArchivo = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"SELECT a.archivosEtfs.id FROM EtfArchivos a WHERE a.etf.id = " + idEft);
		idCatalogoArchivo = (Long) query.uniqueResult();
		return idCatalogoArchivo;
	}

	/**
	 * Permite consultar la TasaDividendos
	 * 
	 * @return
	 */
	public TasaDividendosSimple consultaTasaDividendos() {
		List<TasaDividendosIPC> lista = null;
		TasaDividendosSimple respuesta = null;
		Query query = sessionFactoryOds.getCurrentSession().createQuery(
				"FROM TasaDividendosIPC t ORDER BY t.id DESC");
		lista = (List<TasaDividendosIPC>) query.list();
		if (lista != null && !lista.isEmpty()) {
			respuesta = new TasaDividendosSimple(lista.get(0).getId(), lista.get(0).getValor());
		}
		return respuesta;
	}

	/**
	 * Permite actualizar una Tasa Dividendos
	 * 
	 * @param tasaDividendosSimple
	 * @return id tasaDividendo actualizado
	 */
	public Long actualizaTasaDividendos(TasaDividendosSimple tasaDividendosSimple) {
		String usuario = tasaDividendosSimple.getUsuario();
		Long regresa = new Long(0);
		TasaDividendosIPC loTasaDividendosIPC = new TasaDividendosIPC();
		loTasaDividendosIPC.setValor(tasaDividendosSimple.getValor());
		AuditoriaODS loAuditoriaODS = new AuditoriaODS();
		loAuditoriaODS.setCreadoPor(usuario);
		loAuditoriaODS.setSistema(Constantes.SISTEMA_MCI);
		loAuditoriaODS.setFechaCreacion(new Date());
		loTasaDividendosIPC.setAuditoria(loAuditoriaODS);
		try {
			sessionFactoryOds.getCurrentSession().save(loTasaDividendosIPC);
			regresa = loTasaDividendosIPC.getId();
			// Se envia notificacion por correo electronico
			String[] listaDestinatarios = generaListaDeDestinatarios(admonAlertasService.getListaDestinatarios(),
					Constantes.TIPOSDESTINATARIOS_MAIL);
			if (listaDestinatarios != null && listaDestinatarios.length > 0) {
				String ambiente = profileId;
				String asunto = "Alarma MCI " + ambiente + ": Actualizaci\u00F3n de Tasa de Dividendos del IPC.";
				String cuerpoCorreo = generaCuerpoCorreoTasaDividendos(tasaDividendosSimple, ambiente, usuario);
				infoDistributor.sendByMail(listaDestinatarios, asunto, cuerpoCorreo, null);
			}
			String[] listaDest_SMS = generaListaDeDestinatarios(admonAlertasService.getListaDestinatarios(),
					Constantes.TIPOSDESTINATARIOS_SMS);
			if (listaDest_SMS != null && listaDest_SMS.length > 0) {
				String ambiente = profileId;
				String asunto = "Alarma MCI " + ambiente + ": Actualizacion de Tasa de Dividendos del IPC:"
						+ loTasaDividendosIPC.getValor();
				infoDistributor.sendBySms(listaDest_SMS, asunto);
			}
		} catch (Exception e) {
			log.info("Error al actualizar la Tasa Dividendos: " + e.getMessage());
		}

		/*
		 * Valores que regresa:
		 * 0 - No se actualizo
		 * >0 - Se actualizo
		 */
		return regresa;
	}

	private String[] generaListaDeDestinatarios(List<DestinatarioSimple> listaDestinatariosSimple,
			String tipoDestinatario) {
		List<String> listaDestinatarios = new ArrayList<String>();
		String[] respuesta = {};
		if (listaDestinatariosSimple != null && !listaDestinatariosSimple.isEmpty()) {
			for (DestinatarioSimple destinatarioSimple : listaDestinatariosSimple) {
				if (destinatarioSimple.getTipo().equals(tipoDestinatario)) {
					listaDestinatarios.add(destinatarioSimple.getDestino());
				}
			}
		}
		return listaDestinatarios.toArray(respuesta);
	}

	/**
	 * Mensaje:
	 * Se ha registrado una actualizacion en la tasa de dividendos del IPC, cuyos
	 * valores son los siguientes:
	 * Valor Anterior: {valorAnterior}
	 * Valor Nuevo: {valorNuevo}
	 * Hora del Movimiento: {horaModificacion en formato 24 horas} horas.
	 * Usuario: {usuarioCambio}
	 * 
	 * Informacion perteneciente al ambiente: {ambiente}
	 * 
	 *
	 * @param tasaDividendosSimple
	 * @return
	 */
	private String generaCuerpoCorreoTasaDividendos(TasaDividendosSimple tasaDividendosSimple, String ambiente,
			String usuario) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		StringBuilder mensaje = new StringBuilder();
		mensaje.append(
				"Se ha registrado una actualizaci\u00F3n en la tasa de dividendos del IPC, cuyos valores son los siguientes:\n")
				.append("Valor Anterior: ").append(tasaDividendosSimple.getValorAnterior()).append("\n")
				.append("Valor Nuevo: ").append(tasaDividendosSimple.getValor()).append("\n")
				.append("Hora del Movimiento: ").append(format.format(new Date())).append(" horas.").append("\n")
				.append("Usuario: ").append(usuario).append("\n\n")
				.append("Informaci\u00F3n perteneciente al ambiente: ").append(ambiente);
		return mensaje.toString();
	}

	/**
	 * Permite enviar un mensaje RMI al Real Time para avisar que se tiene que
	 * recargar el archivo ETF
	 * 
	 * @param idEtfSeleccionado
	 * @param idArchivoDeEtfs
	 */
	public boolean enviaMensajeRMIenCargaYReprocesoEtf(String idEtfSeleccionado, String idArchivoDeEtfs,
			Boolean isDelay) {
		try {
			RealtimeOperacion rtOper = this.getRealtimeOperacion();
			if (isDelay && !rtOper.isActivo()) {
				log.warn(
						"No se realiza reprocesaEtfArchivo, ya que no se encuentra Activo, solo se realiz� la carga del Archivo");
				return true;
			} else if (!rtOper.isActivo()) {
				log.warn(
						"No se realiza reprocesaEtfArchivo, ya que no se encuentra Activo, este no requiere transferencia");
				return false;
			}
			// Solo cuando llega la peticion de una recarga.
			if (isDelay && (EstadoRealtime.FIN_P.getCode().equals(rtOper.getEstadoRealtime()) ||
					EstadoRealtime.FIN_INDICES.getCode().equals(rtOper.getEstadoRealtime()) ||
					EstadoRealtime.FIN_DERIVADOS.getCode().equals(rtOper.getEstadoRealtime()))) {
				log.warn(
						"No se realiza reprocesaEtfArchivo, ya que solo se permitir� la transferencia del archivo, pero NO el reenvio NAV");
				return true;
			}

			String idUpdate = UUID.randomUUID().toString();
			cache.reprocesaEtfArchivo(idUpdate, idEtfSeleccionado.toString(), idArchivoDeEtfs.toString(), isDelay);
			if (isDelay) {
				Integer totalIntentos = 0;
				String estatus = "";
				while ((estatus != null
						&& (!estatus.equals(ConstantesEtf.FILE_END) && !estatus.equals(ConstantesEtf.FILE_ERROR)))
						&& totalIntentos <= intentosDelayArchivosEtfs) {
					totalIntentos++;
					log.warn(totalIntentos + " vs " + intentosDelayArchivosEtfs);
					try {
						Thread.sleep(sleepDelayArchivosEtfs);
						estatus = cache.getStatusTransferencia(idUpdate);
					} catch (Exception e) {
						log.warn(e);
					}
				}
				cache.cleanStatusTransferencia(idUpdate);
				if (estatus != null
						&& (estatus.equals(ConstantesEtf.FILE_END) || estatus.equals(ConstantesEtf.FILE_ERROR))) {
					if (estatus.equals(ConstantesEtf.FILE_END)) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("Error al cargar/recargar ETF's", e);
			return false;
		}
		return false;
	}

	/**
	 * Permite enviar un mensaje RMI al Real Time para la difusion de ETFs
	 * 
	 * @param etf
	 */
	public boolean enviaMensajeRMIenDifusionEtf(IndiceIntradiaEtf etf) {
		boolean status = false;
		try {
			cache.actualizaDifusionDeFormatosDeEtf(etf.getIdEtf(), etf.getEstado().intValue(),
					etf.getTienePrioridadIv() != null ? true : false);
			status = true;
		} catch (Exception e) {
			log.error("ERROR en enviaMensajeRMIenDifusionEtf", e);
		}
		return status;
	}

	/**
	 * Permite saber si el estado del Real Time es FIN_P - Fin de recepcion de
	 * formatos P
	 * 
	 * @return boolean true si es el real time tiene el estado de fin de formatos P
	 *         false en caso contrario.
	 */
	public boolean esFinFormatosP() {
		return consultaIntradiaMuestraService.esFinFormatosP();
	}

	/**
	 * Permite saber si un etf Esta cerrado
	 * 
	 * @param idEtf
	 * @return
	 */
	public boolean esEtfCerrado(Long idEtf) {
		boolean respuesta = false;
		try {
			respuesta = cache.getEtfsMap().get(idEtf).getCierre() == 0 ? true : false;
		} catch (Exception e) {
			log.warn("No esta conectado ");
			respuesta = true;
		}
		log.warn("esEtfCerrado:::: " + respuesta);
		return respuesta;
	}

	public static Double roundAvoid(Double value) {
		if (value != null) {
			double scale = Math.pow(10, 6);
			return Math.round(value * scale) / scale;
		}
		return null;
	}

	/**
	 * Permite obtener el status de tranferencia de carga del archivo ETF
	 * 
	 * @param id
	 * @return
	 */
	public String getStatusTransferencia(String id) {
		return cache.getStatusTransferencia(id);
	}

	/**
	 * Coloca el status de tranferencia como terminado
	 * 
	 * @param id
	 */
	public void cleanStatusTransferencia(String id) {
		cache.cleanStatusTransferencia(id);
	}

	@Override
	public Long consolidarEtf(IndiceIntradiaEtf etf) {
		Long regresa = null;
		try {
			if (consultaIntradiaMuestraService.obtenRTStatus()) {
				String idPeticion = UUID.randomUUID().toString();
				cache.consolidacionEmisoras(idPeticion);
				Integer totalIntentos = 0;
				String estatus = "";
				while ((estatus != null
						&& (!estatus.equals(ConstantesEtf.FILE_END) && !estatus.equals(ConstantesEtf.FILE_ERROR)))
						&& totalIntentos <= intentosDelayArchivosEtfs) {
					totalIntentos++;
					log.warn("Intentos de espera en consolidacion " + totalIntentos + " vs "
							+ intentosDelayArchivosEtfs);
					try {
						Thread.sleep(sleepDelayArchivosEtfs);
						estatus = cache.getStatusTransferencia(idPeticion);

					} catch (Exception e) {
						log.warn(e);
					}
				}
				if (estatus != null
						&& (estatus.equals(ConstantesEtf.FILE_END) || estatus.equals(ConstantesEtf.FILE_ERROR))) {
					if (estatus.equals(ConstantesEtf.FILE_END)) {
						regresa = 1L;
					} else {
						regresa = 0L;
					}
				}
				cache.cleanStatusTransferencia(idPeticion);
			}

		} catch (Exception e) {
			log.error("Error al consolidar emisiones", e);
		}
		return regresa;
	}
}