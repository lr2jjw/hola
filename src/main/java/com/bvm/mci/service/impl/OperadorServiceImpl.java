package com.bvm.mci.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bmv.mci.dao.BitacoraMensajeEntradaDao;
import com.bmv.mci.dao.BitacoraMensajeSalidaDao;
import com.bmv.mci.dao.EmisionDao;
import com.bmv.mci.dao.EtfDao;
import com.bmv.mci.dao.FormatosDao;
import com.bmv.mci.model.BitacoraMensajeSalida;
import com.bmv.mci.model.Etf;
import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.OperadorAcumMonet;
import com.bvm.mci.dto.OperadorAcumulados;
import com.bvm.mci.dto.OperadorBitacoraRecibidos;
import com.bvm.mci.dto.OperadorCancelaciones;
import com.bvm.mci.dto.OperadorConteoFormatos;
import com.bvm.mci.dto.OperadorPrecisionPrecAcc;
import com.bvm.mci.dto.OperadorPrecuadre;
import com.bvm.mci.service.OperadorService;
import com.bvm.mci.shared.Constantes;

@SuppressWarnings("all")
@Service("operadorService")
@Transactional
public class OperadorServiceImpl implements OperadorService {

	// @Inject
	// @Named("jdbcOds")
	// private JdbcTemplate jdbc;
	//
	// @Autowired
	// private SessionFactory sessionFactoryOds;

	private static final Logger rootLogger = LogManager.getLogger(OperadorServiceImpl.class);

	@Inject
	@Qualifier("highAvailabilityBean")
	private RealtimeAdminService realtimeAdminService;

	@Inject
	private String rutaArchivosCsv;

	@Inject
	private TransactionTemplate txTemplate;

	@Autowired
	private BitacoraMensajeEntradaDao bitacoraMensajeEntradaDao;

	@Autowired
	private BitacoraMensajeSalidaDao bitacoraMensajeSalidaDao;

	@Autowired
	private EmisionDao emisionDao;

	@Autowired
	private EtfDao etfDao;

	@Autowired
	private FormatosDao formatosDao;

	DateFormat dfFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	// @Autowired
	public List<OperadorConteoFormatos> obtenConteoFormatos() {

		List<OperadorConteoFormatos> lista = new ArrayList<OperadorConteoFormatos>();
		String sql;
		String cadena;
		Long numero;

		// //Consulta sql estandar
		// sql = "select FORMATO, count(formato) as CONTEO from
		// admin_mci.mci_tbitacora_mensajes_enviads"
		// + " group by formato union select formato, count(formato) from "
		// + "admin_mci.mci_tbitacora_mensajes_recibid group by formato";
		//
		// List<Map<String, Object>> resultConsulta= jdbc.queryForList(sql);
		List<Object[]> resultConsulta = bitacoraMensajeEntradaDao.obtenConteosFormato();
		for (Object[] objects : resultConsulta) {
			cadena = (String) objects[0];
			numero = ((BigDecimal) objects[1]).longValue();

			lista.add(new OperadorConteoFormatos(cadena, numero));
		}

		// for(int i=0; i < resultConsulta.size(); i++) {
		// cadena =
		// resultConsulta.get(i).get(OperadorConteoFormatos.FORMATO).toString();
		// numero = new
		// Long(resultConsulta.get(i).get(OperadorConteoFormatos.CONTEO).toString());
		//
		// lista.add(new OperadorConteoFormatos(cadena, numero));
		// }

		return lista;
	}

	// @Autowired
	public List<OperadorCancelaciones> obtenCancelaciones() {

		List<OperadorCancelaciones> lista = new ArrayList<OperadorCancelaciones>();
		String sql;

		Long idMensajeEntradac;
		Date fechaCreacionc;
		Long folioEntradaActualc;
		String cveEmisionc;
		String cveSeriec;
		Double precioc;
		Long volumenc;
		Double importec;
		String transaccionc;
		String incidenciasc;

		// Consulta sql estandar
		// sql = "SELECT a.id_mensaje_entrada as
		// ID_MENSAJE_ENTRADAC,to_char(a.fecha_creacion,'YYYY-MM-DD hh:MI:SS') as
		// FECHA_CREACIONC, a.folio_entrada_actual as FOLIO_ENTRADA_ACTUALC, " +
		// "d.cve_emision as CVE_EMISIONC, d.cve_serie as CVE_SERIEC, a.precio as
		// PRECIOC, a.volumen as VOLUMENC, a.importe as IMPORTEC, " +
		// "transaccion as TRANSACCIONC, CASE WHEN incidencias = 2 THEN 'CANCELACION'
		// WHEN incidencias = 3 THEN 'MODIFICACION' ELSE 'DESCONOCIDO' END AS
		// INCIDENCIASC " +
		// "FROM admin_mci.mci_tbitacora_mensajes_recibid a, (SELECT
		// folio_entrada_actual,ID_EMISION_MCI, COUNT(*) AS incidencias " +
		// "FROM admin_mci.mci_tbitacora_mensajes_recibid WHERE formato = 'P' " +
		// "AND transaccion IS NOT NULL GROUP BY folio_entrada_actual,ID_EMISION_MCI
		// HAVING COUNT(*) > 1) b, admin_mci.mci_temisiones d " +
		// "WHERE formato = 'P' AND a.folio_entrada_actual = b.folio_entrada_actual and
		// a.id_emision_mci=b.id_emision_mci " +
		// "and b.id_emision_mci=d.id_emision_mci order by 2,1";
		//
		// List<Map<String, Object>> resultConsulta= jdbc.queryForList(sql);
		List<Object[]> resultConsulta = bitacoraMensajeEntradaDao.obtenCancelaciones();
		for (Object[] objects : resultConsulta) {
			try {
				idMensajeEntradac = ((BigDecimal) objects[0]).longValue();
				fechaCreacionc = dfFecha.parse((String) objects[1]);
				folioEntradaActualc = ((BigDecimal) objects[2]).longValue();
				cveEmisionc = ((String) objects[3]);
				cveSeriec = ((String) objects[4]);
				precioc = ((BigDecimal) objects[5]).doubleValue();
				volumenc = ((BigDecimal) objects[6]).longValue();
				;
				importec = ((BigDecimal) objects[7]).doubleValue();
				transaccionc = ((String) objects[8]);
				incidenciasc = ((String) objects[9]);
				lista.add(new OperadorCancelaciones(idMensajeEntradac, fechaCreacionc, folioEntradaActualc, cveEmisionc,
						cveSeriec, precioc, volumenc, importec, transaccionc, incidenciasc));
			} catch (ParseException e) {
				rootLogger.info(e.getMessage());
			}
		}

		return lista;
	}

	// @Autowired
	public List<OperadorAcumulados> obtenAcumulados() {
		List<OperadorAcumulados> lista = new ArrayList<OperadorAcumulados>();
		String sql;

		String mercado;
		Long sumNoOp;
		Long sumVol;
		Double sumImp;
		Long sumRvAlza;
		Long sumRvBaja;
		Long sumRvSc;

		// //Consulta sql estandar
		// sql = "select 'RV' as MERCADO, sum(numero_operaciones) AS
		// SUM_NO_OP,sum(volumen) AS SUM_VOL,sum(importe) AS SUM_IMP ,sum(rv_alza) AS
		// SUM_RV_ALZA ,sum(rv_baja) AS SUM_RV_BAJA ,sum(rv_sin_cambio) AS SUM_RV_SC
		// from admin_mci.mci_temisiones where (es_rv=1) " +
		// "union " +
		// "select 'MG',
		// sum(numero_operaciones),sum(volumen),sum(importe),sum(rv_alza),sum(rv_baja),sum(rv_sin_cambio)
		// from admin_mci.mci_temisiones where (es_mg=1) " +
		// "union " +
		// "select 'TOTAL',
		// sum(numero_operaciones),sum(volumen),sum(importe),sum(rv_alza),sum(rv_baja),sum(rv_sin_cambio)
		// from admin_mci.mci_temisiones where (es_rv=1 or es_mg=1)";
		//
		// List<Map<String, Object>> resultConsulta= jdbc.queryForList(sql);
		List<Object[]> resultConsulta = emisionDao.obtenAcumulados();
		for (Object[] objects : resultConsulta) {
			mercado = (String) objects[0];
			sumNoOp = ((BigDecimal) objects[1]).longValue();
			sumVol = ((BigDecimal) objects[2]).longValue();
			sumImp = ((BigDecimal) objects[3]).doubleValue();
			sumRvAlza = ((BigDecimal) objects[4]).longValue();
			sumRvBaja = ((BigDecimal) objects[5]).longValue();
			sumRvSc = ((BigDecimal) objects[6]).longValue();
			lista.add(new OperadorAcumulados(mercado, sumNoOp, sumVol, sumImp, sumRvAlza, sumRvBaja, sumRvSc));
		}

		// for(int i=0; i < resultConsulta.size(); i++) {
		// mercado = resultConsulta.get(i).get(OperadorAcumulados.MERCADO).toString();
		// sumNoOp = new
		// Long(resultConsulta.get(i).get(OperadorAcumulados.SUM_NO_OP).toString());
		// sumVol = new
		// Long(resultConsulta.get(i).get(OperadorAcumulados.SUM_VOL).toString());
		// sumImp = new
		// Double(resultConsulta.get(i).get(OperadorAcumulados.SUM_IMP).toString());
		// sumRvAlza = new
		// Long(resultConsulta.get(i).get(OperadorAcumulados.SUM_RV_ALZA).toString());
		// sumRvBaja = new
		// Long(resultConsulta.get(i).get(OperadorAcumulados.SUM_RV_BAJA).toString());
		// sumRvSc = new
		// Long(resultConsulta.get(i).get(OperadorAcumulados.SUM_RV_SC).toString());
		//
		// lista.add(new OperadorAcumulados(mercado, sumNoOp, sumVol,
		// sumImp, sumRvAlza, sumRvBaja, sumRvSc));
		// }

		return lista;
	}

	public Integer obtenOperacionesXM() {
		String sql;
		Integer noOpXM = 0;

		// //Consulta sql estandar
		// sql = "select count(*) as CONTEO_XM "+
		// "from admin_mci.mci_tbitacora_mensajes_recibid "+
		// "where substr(contenido,64,2)='XM'";
		//
		// noOpXM = jdbc.queryForInt(sql);

		noOpXM = bitacoraMensajeEntradaDao.obtenOperacionesXM();

		return noOpXM;
	}

	public List<OperadorPrecisionPrecAcc> obtenPrecisionPrecAcc() {
		List<OperadorPrecisionPrecAcc> lista = new ArrayList<OperadorPrecisionPrecAcc>();
		String sql;

		String cveEmision;
		String cveSerie;
		Double precioActual;
		Double ultimoPrecioOperado;
		Long numeroOperacionesMci;
		Long numeroOperacionesOds;

		// Consulta sql estandar
		// sql = "select
		// D.cve_emision,D.cve_serie,D.precio_actual,A.ULTIMO_PRECIO_OPERADO,D.NUMERO_OPERACIONES
		// as NUMERO_OPERACIONES_MCI,A.NUMERO_OPERACIONES as NUMERO_OPERACIONES_ODS " +
		// "from comunity_admin.COM_TRESUMEN_EMISIONES A, admin_mci.mci_temisiones D " +
		// "where D.id_emision=A.id_emision " +
		// "and D.tipo_valor not in (select ID_TPVALOR from
		// admin_mci.MCI_V_TIPOS_VALORES where TIPO_RENTA='SIC') " +
		// "and ((ULTIMO_PRECIO_OPERADO is null and PRECIO_ACTUAL<>PRECIO_ANTERIOR) " +
		// "or (ULTIMO_PRECIO_OPERADO is not null and
		// (PRECIO_ACTUAL<>ULTIMO_PRECIO_OPERADO or
		// D.NUMERO_OPERACIONES<>A.NUMERO_OPERACIONES))) " +
		// "and PRECIO_PROMEDIO_PONDERADO=0 " +
		// "and A.num_feed=(select max(B.num_feed) from
		// comunity_admin.COM_TRESUMEN_EMISIONES B where B.id_emision=D.id_emision) " +
		// "union " +
		// "select
		// D.cve_emision,D.cve_serie,D.precio_actual,A.PRECIO_PROMEDIO_PONDERADO,D.NUMERO_OPERACIONES,A.NUMERO_OPERACIONES
		// " +
		// "from comunity_admin.COM_TRESUMEN_EMISIONES A, admin_mci.mci_temisiones D " +
		// "where D.id_emision=A.id_emision " +
		// "and D.tipo_valor not in (select ID_TPVALOR from
		// admin_mci.MCI_V_TIPOS_VALORES where TIPO_RENTA='SIC') " +
		// "and (PRECIO_ACTUAL<>ULTIMO_PRECIO_OPERADO or
		// D.NUMERO_OPERACIONES<>A.NUMERO_OPERACIONES) " +
		// "and (PRECIO_ACTUAL<>PRECIO_PROMEDIO_PONDERADO or
		// D.NUMERO_OPERACIONES<>A.NUMERO_OPERACIONES) " +
		// "and PRECIO_PROMEDIO_PONDERADO>0 " +
		// "and A.num_feed=(select max(B.num_feed) " +
		// "from comunity_admin.COM_TRESUMEN_EMISIONES B where
		// B.id_emision=D.id_emision)";
		//
		// List<Map<String, Object>> resultConsulta= jdbc.queryForList(sql);

		List<Object[]> resultConsulta = emisionDao.obtenPrecisionPrecAcc();

		for (Object[] objects : resultConsulta) {
			cveEmision = (String) objects[0];
			cveSerie = (String) objects[1];
			precioActual = ((BigDecimal) objects[2]).doubleValue();
			ultimoPrecioOperado = ((BigDecimal) objects[3]).doubleValue();
			numeroOperacionesMci = ((BigDecimal) objects[4]).longValue();
			numeroOperacionesOds = ((BigDecimal) objects[5]).longValue();

			lista.add(new OperadorPrecisionPrecAcc(cveEmision, cveSerie, precioActual, ultimoPrecioOperado,
					numeroOperacionesMci, numeroOperacionesOds));
		}

		// for(int i=0; i < resultConsulta.size(); i++) {
		// cveEmision =
		// resultConsulta.get(i).get(OperadorPrecisionPrecAcc.CVE_EMISION).toString();
		// cveSerie =
		// resultConsulta.get(i).get(OperadorPrecisionPrecAcc.CVE_SERIE).toString();
		// precioActual = new
		// Double(resultConsulta.get(i).get(OperadorPrecisionPrecAcc.PRECIO_ACTUAL).toString());
		// ultimoPrecioOperado = new
		// Double(resultConsulta.get(i).get(OperadorPrecisionPrecAcc.ULTIMO_PRECIO_OPERADO).toString());
		// numeroOperacionesMci = new
		// Long(resultConsulta.get(i).get(OperadorPrecisionPrecAcc.NUMERO_OPERACIONES_MCI).toString());
		// numeroOperacionesOds = new
		// Long(resultConsulta.get(i).get(OperadorPrecisionPrecAcc.NUMERO_OPERACIONES_ODS).toString());
		//
		// lista.add(new OperadorPrecisionPrecAcc(cveEmision, cveSerie, precioActual,
		// ultimoPrecioOperado, numeroOperacionesMci, numeroOperacionesOds));
		// }

		return lista;
	}

	public List<OperadorAcumMonet> obtenAcumMonet() {
		List<OperadorAcumMonet> lista = new ArrayList<OperadorAcumMonet>();
		String sql;

		Long numeroOperaciones;
		Long volumenOperado;
		Double importeOperado;

		// Consulta sql estandar
		// sql = "SELECT SUM(NUMERO_OPERACIONES) as NUMERO_OPERACIONES,
		// SUM(VOLUMEN_OPERADO) as VOLUMEN_OPERADO, SUM(IMPORTE_OPERADO) as
		// IMPORTE_OPERADO " +
		// "FROM admin_mcn.MCN_TINSTRUMENTOS " +
		// "WHERE ESTADO_ADMIN=1 " +
		// "AND ID_TIPO_VALOR IN " +
		// "(SELECT ID_TIPO_VALOR " +
		// "FROM comunity_admin.COM_CTIPOS_VALORES " +
		// "WHERE ID_BOLSA=101 " +
		// "AND ((ID_MERCADO_OPERA = 1 AND ID_TPINVERSION NOT IN('SI','BB', 'RF', 'WR'))
		// OR (ID_MERCADO_OPERA = 4 AND ID_TPINVERSION = 'RV')) " +
		// ")";
		//
		// List<Map<String, Object>> resultConsulta= jdbc.queryForList(sql);
		//
		List<Object[]> resultConsulta = emisionDao.obtenAcumMonet();
		for (Object[] objects : resultConsulta) {
			numeroOperaciones = ((BigDecimal) objects[0]).longValue();
			volumenOperado = ((BigDecimal) objects[1]).longValue();
			importeOperado = ((BigDecimal) objects[2]).doubleValue();
			lista.add(new OperadorAcumMonet(numeroOperaciones, volumenOperado, importeOperado));
		}

		// for(int i=0; i < resultConsulta.size(); i++) {
		// numeroOperaciones = new
		// Long(resultConsulta.get(i).get(OperadorAcumMonet.NUMERO_OPERACIONES).toString());
		// volumenOperado = new
		// Long(resultConsulta.get(i).get(OperadorAcumMonet.VOLUMEN_OPERADO).toString());
		// importeOperado = new
		// Double(resultConsulta.get(i).get(OperadorAcumMonet.IMPORTE_OPERADO).toString());
		//
		// lista.add(new OperadorAcumMonet(numeroOperaciones, volumenOperado,
		// importeOperado));
		// }

		return lista;
	}

	public List<OperadorBitacoraRecibidos> obtenBitacoraRecibidos(String contenido, String formatos,
			String tipoOperacion, String tipoConcertacion) {
		List<OperadorBitacoraRecibidos> lista = new ArrayList<OperadorBitacoraRecibidos>();
		String sql;

		Long idMensaje;
		String formato;
		String contenidoMsj = null;
		String fechaHora;

		boolean alMenosUnFiltro = false;

		// sql = "select bmr.id_mensaje_entrada as id_mensaje,
		// to_char(bmr.fecha_creacion, 'YYYYMMDD-HH24MISS') as fecha_hora,
		// bmr.contenido, bmr.formato "+
		// "from admin_mci.mci_tbitacora_mensajes_recibid bmr "+
		// "where ";
		//
		// if(contenido!=null && contenido.trim().length() > 0){
		// sql += "bmr.contenido like '%"+contenido+"%' ";
		// alMenosUnFiltro = true;
		// }
		//
		// if(formatos!=null && formatos.trim().length() > 0){
		// sql += alMenosUnFiltro ? "and " : "";
		// sql += "bmr.formato in (" + formatos + ") ";
		// alMenosUnFiltro = true;
		// }
		//
		// if(tipoOperacion!=null && tipoOperacion.trim().length() > 0){
		// sql += alMenosUnFiltro ? "and " : "";
		// sql += "bmr.formato='P' and substr(bmr.contenido,64,2)='" + tipoOperacion +
		// "' ";
		// alMenosUnFiltro = true;
		// }
		//
		// if(tipoConcertacion!=null && tipoConcertacion.trim().length() > 0){
		// sql += alMenosUnFiltro ? "and " : "";
		// sql += "bmr.formato='P' and substr(bmr.contenido,126,2)='" + tipoConcertacion
		// + "' ";
		// alMenosUnFiltro = true;
		// }
		//
		// List<Map<String, Object>> resultConsulta=jdbc.queryForList(sql);

		List<Object[]> resultConsulta = bitacoraMensajeEntradaDao.obtenBitacoraRecibidos(contenidoMsj, formatos,
				tipoOperacion, tipoConcertacion);
		for (Object[] objects : resultConsulta) {
			idMensaje = ((BigDecimal) objects[1]).longValue();
			formato = (String) objects[1];
			contenidoMsj = (String) objects[2];
			fechaHora = (String) objects[3];

			lista.add(new OperadorBitacoraRecibidos(idMensaje, formato, contenidoMsj, fechaHora));

			rootLogger.info("OperadorServiceImpl.obtenBitacoraRecibidos lista.size:" + lista.size());
		}
		// for(int i=0; i < resultConsulta.size(); i++) {
		// idMensaje =
		// Long.valueOf(resultConsulta.get(i).get(OperadorBitacoraRecibidos.ID_MENSAJE).toString());
		// formato =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.FORMATO).toString();
		// contenidoMsj =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.CONTENIDO).toString();
		// fechaHora =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.FECHA_HORA).toString();
		//
		// lista.add(new OperadorBitacoraRecibidos(idMensaje, formato, contenidoMsj,
		// fechaHora));
		//
		// rootLogger.info("OperadorServiceImpl.obtenBitacoraRecibidos lista.size:"+
		// lista.size());
		//
		// }

		return lista;
	}

	public List<OperadorBitacoraRecibidos> obtenBitacoraEnviados(String contenido, String formatos,
			String tipoOperacion, String tipoConcertacion) {
		List<OperadorBitacoraRecibidos> lista = new ArrayList<OperadorBitacoraRecibidos>();
		String sql;

		Long idMensaje;
		String formato;
		String contenidoMsj = null;
		String fechaHora;

		boolean alMenosUnFiltro = false;

		// sql = "select bme.id_mensaje_salida as id_mensaje,
		// to_char(bme.fecha_creacion, 'YYYYMMDD-HH24MISS') as fecha_hora,
		// bme.contenido, bme.formato "+
		// "from admin_mci.mci_tbitacora_mensajes_enviads bme "+
		// "where ";
		//
		// if(contenido!=null && contenido.trim().length() > 0){
		// sql += "bme.contenido like '%"+contenido+"%' ";
		// alMenosUnFiltro = true;
		// }
		//
		// if(formatos!=null && formatos.trim().length() > 0){
		// sql += alMenosUnFiltro ? "and " : "";
		// sql += "bme.formato in (" + formatos + ") ";
		// alMenosUnFiltro = true;
		// }
		//
		// List<Map<String, Object>> resultConsulta=jdbc.queryForList(sql);

		List<Object[]> resultConsulta = bitacoraMensajeSalidaDao.obtenBitacoraEnviados(contenidoMsj, formatos,
				tipoOperacion, tipoConcertacion);
		for (Object[] objects : resultConsulta) {
			idMensaje = ((BigDecimal) objects[1]).longValue();
			formato = (String) objects[1];
			contenidoMsj = (String) objects[2];
			fechaHora = (String) objects[3];

			lista.add(new OperadorBitacoraRecibidos(idMensaje, formato, contenidoMsj, fechaHora));
		}

		// for(int i=0; i < resultConsulta.size(); i++) {
		// idMensaje =
		// Long.valueOf(resultConsulta.get(i).get(OperadorBitacoraRecibidos.ID_MENSAJE).toString());
		// formato =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.FORMATO).toString();
		// contenidoMsj =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.CONTENIDO).toString();
		// fechaHora =
		// resultConsulta.get(i).get(OperadorBitacoraRecibidos.FECHA_HORA).toString();
		//
		// lista.add(new OperadorBitacoraRecibidos(idMensaje, formato, contenidoMsj,
		// fechaHora));
		// }

		rootLogger.info("OperadorServiceImpl.obtenBitacoraEnviados lista.size:" + lista.size());

		return lista;
	}

	public String guardaArchivoConteoFormatos(List<OperadorConteoFormatos> lista, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-ConteoFormatos-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "Formato, Contador\n";
			printWriter.write(cadena);

			for (OperadorConteoFormatos operadorConteoFormatos : lista) {
				cadena = "\"" + operadorConteoFormatos.getFormato() + "\","
						+ operadorConteoFormatos.getConteo().toString() + '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoCancelaciones(List<OperadorCancelaciones> lista, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-Cancelaciones-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "Id_mensaje_entrada,Fecha_creacion,Folio_entrada_actual,Cve_emision,Cve_serie,"
					+ "Precio,Volumen,Importe,Transaccion,Incidencias\n";

			printWriter.write(cadena);

			for (OperadorCancelaciones operadorCancelaciones : lista) {
				cadena = operadorCancelaciones.getIdMensajeEntradac().toString() + ","
						+ dfFecha.format(operadorCancelaciones.getFechaCreacionc()) + ","
						+ operadorCancelaciones.getFolioEntradaActualc().toString() + ","
						+ operadorCancelaciones.getCveEmisionc() + "," + operadorCancelaciones.getCveSeriec() + ","
						+ operadorCancelaciones.getPrecioc().toString() + ","
						+ operadorCancelaciones.getVolumenc().toString() + ","
						+ operadorCancelaciones.getImportec().toString() + "," + "\""
						+ operadorCancelaciones.getTransaccionc() + "\"," + operadorCancelaciones.getIncidenciasc()
						+ '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoAcumulados(List<OperadorAcumulados> lista, List<OperadorAcumMonet> listaMonet,
			String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-Acumulados-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "MCI\nMercado, SumNoOp, SumVol," + "SumImp,SumRvAlza,SumRvBaja,SumRvSc\n";

			printWriter.write(cadena);

			for (OperadorAcumulados operadorAcumulados : lista) {
				cadena = operadorAcumulados.getMercado().toString() + "," + operadorAcumulados.getSumNoOp().toString()
						+ "," + operadorAcumulados.getSumVol().toString() + ","
						+ operadorAcumulados.getSumImp().toString() + "," + operadorAcumulados.getSumRvAlza().toString()
						+ "," + operadorAcumulados.getSumRvBaja().toString() + "," + operadorAcumulados.getSumRvSc()
						+ '\n';
				printWriter.write(cadena);
			}

			// second grid
			cadena = "MoNet\n,Numero de operaciones,volumen operado,importe operado\n";
			printWriter.write(" \n \n");
			printWriter.write(cadena);

			for (OperadorAcumMonet operadorAcumMonet : listaMonet) {
				cadena = "," + operadorAcumMonet.getNumeroOperaciones() + "," + operadorAcumMonet.getVolumenOperado()
						+ "," + operadorAcumMonet.getImporteOperado() + '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoPrecisionPrecAcc(List<OperadorPrecisionPrecAcc> lista, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-PrecisionPrecAcc-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "Cve_emision,Cve_serie,"
					+ "Precio actual,Ultimo precio operado,Numero operaciones MCI,Numero operaciones ODS\n";

			printWriter.write(cadena);

			for (OperadorPrecisionPrecAcc operadorPrecisionPrecAcc : lista) {
				cadena = operadorPrecisionPrecAcc.getCveEmision() + "," + operadorPrecisionPrecAcc.getCveSerie() + ","
						+ operadorPrecisionPrecAcc.getPrecioActual().toString() + ","
						+ operadorPrecisionPrecAcc.getUltimoPrecioOperado().toString() + ","
						+ operadorPrecisionPrecAcc.getNumeroOperacionesMci().toString() + ","
						+ operadorPrecisionPrecAcc.getNumeroOperacionesOds() + '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoPrecuadre(List<OperadorPrecuadre> lista, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-Precuadre-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "Cve Indice, valor Indice,variacion Porcentual, variacion Puntos, valorIndiceReferencia, "
					+ "valor Maximo Indice, valorMinimoIndice, hora Maximo, hora Minimo, "
					+ "sum Vm Actual, sum Vm Referencia\n";

			printWriter.write(cadena);

			for (OperadorPrecuadre operadorPrecuadre : lista) {
				cadena = operadorPrecuadre.getCveIndice() + "," + operadorPrecuadre.getValorIndice().toString() + ","
						+ operadorPrecuadre.getVariacionPorcentual().toString() + ","
						+ operadorPrecuadre.getVariacionPuntos().toString() + ","
						+ operadorPrecuadre.getValorIndiceReferencia().toString() + ","
						+ operadorPrecuadre.getValorMaximoIndice().toString() + ","
						+ operadorPrecuadre.getValorMinimoIndice().toString() + ","
						+ dfFecha.format(operadorPrecuadre.getHoraMaximo()) + ","
						+ dfFecha.format(operadorPrecuadre.getHoraMinimo()) + ","
						+ operadorPrecuadre.getSumVmActual().toString() + "," + operadorPrecuadre.getSumVmReferencia()
						+ '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoBitacoraRecibidos(String contenido, String formatos, String tipoOperacion,
			String tipoConcertacion, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-BitacoraRecibidos-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "IdMensaje, Formato, Contenido, Fecha-Hora (24H)\n";

			printWriter.write(cadena);

			List<OperadorBitacoraRecibidos> lista = obtenBitacoraRecibidos(contenido, formatos, tipoOperacion,
					tipoConcertacion);

			for (OperadorBitacoraRecibidos obr : lista) {
				cadena = obr.getIdMensaje() + "," + obr.getFormato() + "," + obr.getContenido() + ","
						+ obr.getFechaHora() + '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String guardaArchivoBitacoraEnviados(String contenido, String formatos, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-BitacoraEnviados-" + nombre + ".txt" + idSession);
		String cadena = null;

		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			cadena = "IdMensaje, Formato, Contenido, Fecha-Hora (24H)\n";

			printWriter.write(cadena);

			List<OperadorBitacoraRecibidos> lista = obtenBitacoraEnviados(contenido, formatos, null, null);

			for (OperadorBitacoraRecibidos obr : lista) {
				cadena = obr.getIdMensaje() + "," + obr.getFormato() + "," + obr.getContenido() + ","
						+ obr.getFechaHora() + '\n';
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}

		return nombreArchivo;
	}

	public String getRutaCompartida() {
		return rutaArchivosCsv;
	}

	@Override
	public List<IndiceIntradiaEtf> getListaEtfs() {
		List<IndiceIntradiaEtf> result = new ArrayList<IndiceIntradiaEtf>();
		List<Etf> lista = etfDao.findAll();
		for (Etf etf : lista) {
			result.add(new IndiceIntradiaEtf(etf.getId(), etf.getInstrumento()));
		}
		return result;
	}

	@Override
	public String descargaMensajesEnviados(String instrumento, String formatos, String nombre, String idSession) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		PrintWriter printWriter;
		File file = null;
		String nombreArchivo = new String(rutaArchivosCsv + "Opera-BitacoraEnviados-" + nombre + ".txt" + idSession);
		String cadena = null;
		String contenido = null;
		Integer headerSize = 35;
		try {
			System.out.println("Archivo temporal generado: " + nombreArchivo);
			file = new File(nombreArchivo);
			fileWriter = new FileWriter(file);

			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter);

			List<BitacoraMensajeSalida> lista = bitacoraMensajeSalidaDao.getByInstrumentoAndFormato(formatos,
					instrumento);
			if (lista != null && !lista.isEmpty()) {
				if (Constantes.formatoIV.equals(formatos)) {
					cadena += "TipoReg,Instrumento,Emisora,Serie,Titulos,TitulosExcluidos,Precio,ComponenteEfectivo,ValorExcluido,NumeroCertificados,PrecioTeorico\n";
					for (BitacoraMensajeSalida bitacoraMensajeSalida : lista) {
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 0, headerSize + 0 + 2)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 2, headerSize + 2 + 8)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 10, headerSize + 10 + 7)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 17, headerSize + 17 + 6)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 23, headerSize + 23 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 35, headerSize + 35 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 47, headerSize + 47 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 59, headerSize + 59 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 71, headerSize + 71 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 83, headerSize + 83 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 95, headerSize + 95 + 12)
								+ "\n";
					}
				} else if (Constantes.formatoIR.equals(formatos)) {
					cadena += "TipoReg,Instrumento,PrecioUltimo,PrecioDiaAnterior,TipoValor,Serie,NumEmision,NumeroFeed\n";
					for (BitacoraMensajeSalida bitacoraMensajeSalida : lista) {
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 0, headerSize + 0 + 2)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 2, headerSize + 2 + 8)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 10, headerSize + 10 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 22, headerSize + 22 + 12)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 34, headerSize + 34 + 2)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 36, headerSize + 36 + 5)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 41, headerSize + 41 + 5)
								+ ",";
						cadena += bitacoraMensajeSalida.getContenido().substring(headerSize + 46, headerSize + 46 + 10)
								+ "\n";
					}
				}
				printWriter.write(cadena);
			}

			printWriter.close();
			bufferedWriter.close();
			fileWriter.close();
		} catch (java.io.IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
