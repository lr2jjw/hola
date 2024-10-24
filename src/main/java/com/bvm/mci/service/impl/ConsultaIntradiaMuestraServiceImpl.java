package com.bvm.mci.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bmv.mci.model.EstadoRealtime;
import com.bmv.mci.model.ParametroOperacionOds;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;

@Service
@Lazy
@Transactional
public class ConsultaIntradiaMuestraServiceImpl implements ConsultaIntradiaMuestraService {

	// logger
	//private static Logger rootLogger = Logger.getLogger("ConsultaIntradiaMuestraServiceImpl.class");

	@Inject
	private SessionFactory sessionFactoryOds;

	@Inject
	@Named("highAvailabilityBean")
	private RealtimeAdminService cache;

	@Inject
	@Named("rutaArchivosCsv")
	private String rutaArchivosCsv;

	public static Boolean IS_RT_ACTIVO = true;

	private EstadoRealtime estadoRealtime = null;
	private String statusUserRT = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bmv.mci.admin.client.service.intradia.ConsultaIntradiaMuestraService#
	 * obtenRTCerrado()
	 */
	@Override
	public boolean obtenRTCerrado() {
		return !(IS_RT_ACTIVO && !isRtDbCerrado());
		// return isRtDbCerrado();
	}

	@Override
	public boolean obtenRTStatus() {
		return IS_RT_ACTIVO;
	}

	private boolean isRtDbCerrado() {
		// rootLogger.warning("isRtDbCerrado");
		boolean isRtDbCerrado = false;

		Query query = sessionFactoryOds.getCurrentSession().createQuery(" FROM ParametroOperacionOds po ");
		ParametroOperacionOds resultado = (ParametroOperacionOds) query.uniqueResult();

		if (!resultado.getRealTimeCerrado() && resultado.getRealTimeInicializado()) {
			isRtDbCerrado = false; // ARRIBA
		} else {
			isRtDbCerrado = true; // ABAJO
		}
		return isRtDbCerrado;
	}

	private void loadEstadoUsuario(Integer estadoRealtime) {
		String result = "INICIALIZADO";
		// rootLogger.warning("loadEstadoUsuario:"+estadoRealtime);
		if (estadoRealtime == null) {
			Query query = sessionFactoryOds.getCurrentSession().createQuery(" FROM ParametroOperacionOds po ");
			ParametroOperacionOds resultado = (ParametroOperacionOds) query.uniqueResult();
			EstadoRealtime estado = resultado.getEstadoRealtime();
			// rootLogger.warning("ESTADO: "+estado);
			if (estado != null) {
				// rootLogger.warning("ESTADO: "+estado+" code:"+estado.getCode());
				estadoRealtime = estado.getCode();
			}
			this.setEstadoRealtime(estado);
		}

		if (estadoRealtime != null) {
			// rootLogger.warning("BUSCANDO estadoRealtime:" +estadoRealtime);
			Query query2 = sessionFactoryOds.getCurrentSession().createSQLQuery("SELECT ESTADO_USUARIO "
					+ "FROM MCI_CESTADOS_REAL_TIME "
					+ "WHERE ID_ESTADO_REAL_TIME=:id");
			query2.setInteger("id", estadoRealtime);
			result = (String) query2.uniqueResult();
			// rootLogger.warning("Descripcion:"+result);

		}
		this.setStatusUserRT(result);
	}

	@Scheduled(cron = "*/10 * * * * *")
	public void verificaEstadoRt() {
		try {
			IS_RT_ACTIVO = cache.isRtActive();// siempre sera true
			if (this.estadoRealtime == null) {
				this.loadEstadoUsuario(null);
			} else {
				this.loadEstadoUsuario(cache.getEstadoRealtime().getCode());
			}
			this.estadoRealtime = cache.getEstadoRealtime();
		} catch (Exception e) {
			IS_RT_ACTIVO = false;
			this.loadEstadoUsuario(null);
		}
	}

	/**
	 * Permite saber si el estado del Real Time es FIN_P - Fin de recepcion de
	 * formatos P
	 * 
	 * @return boolean true si es el real time tiene el estado de fin de formatos P
	 *         false en caso contrario.
	 */
	@Override
	public boolean esFinFormatosP() {
		Query query = sessionFactoryOds.getCurrentSession().createQuery(" FROM ParametroOperacionOds po ");
		ParametroOperacionOds resultado = (ParametroOperacionOds) query.uniqueResult();

		if (resultado.getEstadoRealtime() == EstadoRealtime.FIN_P ||
				resultado.getEstadoRealtime() == EstadoRealtime.FIN_INDICES ||
				resultado.getEstadoRealtime() == EstadoRealtime.FIN_DERIVADOS) {
			return true;
		} else {
			return false;
		}
	}

	public String getStatusUserRT() {
		return statusUserRT;
	}

	public void setStatusUserRT(String statusUserRT) {
		this.statusUserRT = statusUserRT;
	}

	public Integer getEstadoRealtime() {
		if (this.estadoRealtime == null) {
			return null;
		}
		return estadoRealtime.getCode();
	}

	public void setEstadoRealtime(EstadoRealtime estadoRealtime) {
		this.estadoRealtime = estadoRealtime;
	}
}
