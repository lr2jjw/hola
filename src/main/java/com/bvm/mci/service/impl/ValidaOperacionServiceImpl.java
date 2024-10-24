package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.List;

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
//import com.bmv.mci.alerts.alertas.DifusorAlertas;
import com.bmv.mci.model.ParametroOperacionOds;
//import com.bmv.mci.vo.DiferenciaCampoBD;
//import com.bmv.mci.vo.DiferenciasBD;
import com.bvm.mci.dto.DiferenciaSimple;
import com.bvm.mci.service.ValidaOperacionService;

@SuppressWarnings("all")
@Service("validaOperacionService")
@Transactional
public class ValidaOperacionServiceImpl implements ValidaOperacionService {

	@Autowired
	private SessionFactory sessionFactoryOds;

	@Inject
	@Named("highAvailabilityBean")
	private RealtimeAdminService cache;

	// @Inject
	// @Named("difusorAlertas")
	// private DifusorAlertas difusorAlertas;

	private static final Logger rootLogger = LogManager.getLogger(ValidaOperacionServiceImpl.class);

	/**
	 * @see com.bmv.mci.admin.server.service.bloqueador.ValidaOperacionService#validaOperacion()
	 */
	@Override
	public boolean validaOperacion() {
		ParametroOperacionOds as = new ParametroOperacionOds();
		Query query = sessionFactoryOds.getCurrentSession().createQuery(" from ParametroOperacionOds po ");
		ParametroOperacionOds resultado = (ParametroOperacionOds) query.uniqueResult();
		return resultado.getRealTimeCerrado();

	}

	/**
	 * @see com.bmv.mci.admin.server.service.bloqueador.ValidaOperacionService#actualizaOperacion()
	 */
	@Override
	public void actualizaOperacion() {
		Query query = sessionFactoryOds.getCurrentSession().createQuery(" from ParametroOperacionOds po ");
		ParametroOperacionOds po = (ParametroOperacionOds) query.uniqueResult();
		po.setRealTimeCerrado(true);
		sessionFactoryOds.getCurrentSession().saveOrUpdate(po);
	}

	@Override
	public void tomaSnapShotAlCierre() {
		// red cache.tomaSnapShotAlCierre();
	}

	// red
	/*
	 * @Override
	 * public List<DiferenciaSimple> validaConsistenciaBD(){
	 * List<DiferenciaSimple> regresa = new ArrayList<DiferenciaSimple>();
	 * List<DiferenciasBD> lista = null;
	 * DiferenciaSimple temporal;
	 * long contadorTotal = 0;
	 * long contadorRegistro = 0;
	 * 
	 * rootLogger.warning("Llamando al servicio de validaci�n ...");
	 * lista = cache.validaConsistenciaBD();
	 * if(lista != null){
	 * rootLogger.warning("Elementos traidos: " + lista.size());
	 * for(DiferenciasBD diferenciasBD: lista){
	 * contadorRegistro++;
	 * 
	 * for(DiferenciaCampoBD diferenciaCampoBD:
	 * diferenciasBD.getDiferenciasCamposList()){
	 * contadorTotal++;
	 * rootLogger.warning("Procesando registro: " + contadorTotal);
	 * 
	 * temporal = new DiferenciaSimple();
	 * temporal.setId(contadorTotal);
	 * temporal.setIdRegistro(contadorRegistro);
	 * temporal.setTabla(diferenciasBD.getTabla());
	 * temporal.setCampo(diferenciaCampoBD.getCampo());
	 * temporal.setValorCierre(diferenciaCampoBD.getValorCierre());
	 * temporal.setValorActual(diferenciaCampoBD.getValorActual());
	 * 
	 * regresa.add(temporal);
	 * }
	 * }
	 * }
	 * rootLogger.warning("Termina llamado al servicio de validaci�n");
	 * 
	 * return regresa;
	 * }
	 */

		@Override
		public void enviaMensajeIgnorarDiferencias(List<DiferenciaSimple> lista) {
			List<String> diferencias = new ArrayList<String>();
			String tabla = new String();

			for (DiferenciaSimple diferenciaSimple : lista) {
				if (!tabla.equals(diferenciaSimple.getTabla())) {
					diferencias.add("\nTabla: " + diferenciaSimple.getTabla() + "\n");
					tabla = diferenciaSimple.getTabla();
				}
				diferencias.add(
						"#: " + diferenciaSimple.getId() + ", " +
								"campo: " + diferenciaSimple.getCampo() + ", " +
								"valor al cierre: " + diferenciaSimple.getValorCierre() + ", " +
								"valor actual: " + diferenciaSimple.getValorActual());
			}

			rootLogger.info("Se enviaran los mensaje de diferencias ignoradas");
			// red cache.notificaIgnorarDiferencias(diferencias);

			rootLogger.info("El email y sms de diferencias ignoradas se ha enviado");
		}
}
