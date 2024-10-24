package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmv.mci.model.AuditoriaODS;
import com.bmv.mci.model.Destinatario;
import com.bvm.mci.dto.DestinatarioSimple;
import com.bvm.mci.service.AdmonAlertasService;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.shared.Constantes;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AdmonAlertasServiceImpl implements AdmonAlertasService {


    @Named("sessionFactoryOds")
	private final SessionFactory sessionFactoryOds;

    @Autowired
    private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

    @Inject
    @Named("rutaArchivosCsv")
    private String rutaArchivosCsv;

    private Log log = LogFactory.getLog(AdmonAlertasServiceImpl.class);

    @Override
    public List<DestinatarioSimple> getListaDestinatarios() {
        List<DestinatarioSimple> lista = new ArrayList<DestinatarioSimple>();
		Query query= null;
		
		String sql= "select new com.bvm.mci.dto.DestinatarioSimple(" +
			"A.id, A.tipo, A.nombreContacto, A.destino" +
			") from Destinatario A where A.tipo in (:lista)";
		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		query.setParameterList("lista", Constantes.TIPOSDESTINATARIOS_CONSULTA_ALERTAS);
		
		lista = query.list();
	
		return lista;
	}
   

    @Override
    public Long saveDestinatario(String tipo, String nombreContacto, String destino) {
       Long regresa = new Long(0);

		//red regresa = admonReportesService.saveDestinatario(tipo, nombreContacto, destino, null, null, null);
		Destinatario destinatario = new Destinatario();
		//Long id = null;
		
		destinatario.setTipo(tipo);
		destinatario.setNombreContacto(nombreContacto);
		destinatario.setDestino(destino);
		
		AuditoriaODS auditoria = new AuditoriaODS();
		auditoria.setCreadoPor(Constantes.USUARIO_MCI);
		auditoria.setFechaCreacion(new Date());
		auditoria.setSistema(Constantes.SISTEMA_MCI);
		
		destinatario.setAuditoria(auditoria);
		
		try {
			sessionFactoryOds.getCurrentSession().persist(destinatario);
			regresa = Long.valueOf(1);
		} catch (Exception e) {
			regresa = Long.valueOf(2);
            
			//rootLogger.warning("No se pudo insertar el destinatario (" + nombreContacto + "): " + e.getMessage());
            log.error("No se pudo insertar el destinatario (" + nombreContacto + "): " + e.getMessage());
            
		}

		return regresa;
    }

    @Override
    public Long deleteDestinatario(List<Long> lista) {
        Long regresa = new Long(0);
		
		//red regresa = admonReportesService.deleteDestinatario(lista);
		List<Destinatario> listaDestinatarios = new ArrayList<Destinatario>();
		Query query= null;
		
		String sql= "from Destinatario A where A.id in (:lista)";
		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		
		query.setParameterList("lista", lista);
		
		listaDestinatarios = query.list();
		
		if(listaDestinatarios == null){
			regresa = Long.valueOf(2);
		}
		else{
			if(listaDestinatarios.size() == 0){
				regresa = Long.valueOf(0);
			}
			else{
				for(Destinatario destinatario: listaDestinatarios){
					sessionFactoryOds.getCurrentSession().delete(destinatario);
				}
				regresa = Long.valueOf(1);
			}
		}
		
		return regresa;

    }

    @Override
    public Long updateDestinatario(List<DestinatarioSimple> lista) {
        Long regresa = new Long(0);
		
		//red regresa = admonReportesService.updateDestinatario(lista);
		List<Long> listaIds = new ArrayList<Long>();
		List<Destinatario> listaDest = new ArrayList<Destinatario>();
		Query query= null;
		
		for(DestinatarioSimple des: lista){
			listaIds.add(des.getIdDestinatario());
		}

		String sql= "from Destinatario A where A.id in (:lista)";
		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
		
		query.setParameterList("lista", listaIds);
		
		listaDest = query.list();
		
		if(listaDest == null){
			regresa = Long.valueOf(2);
		}
		else{
			if(listaDest.size() == 0){
				regresa = Long.valueOf(0);
			}
			else{

				for(Destinatario destinatario: listaDest){
					for(DestinatarioSimple ds: lista){
						if(ds.getIdDestinatario().longValue() == destinatario.getId().longValue()){
							destinatario.setTipo(ds.getTipo());
							destinatario.setNombreContacto(ds.getNombreContacto());
							destinatario.setDestino(ds.getDestino());
							
							destinatario.getAuditoria().setModificadoPor(Constantes.SISTEMA_MCI);
							destinatario.getAuditoria().setFechaModificacion(new Date());
						}
					}
					
					sessionFactoryOds.getCurrentSession().update(destinatario);
				}
				regresa = Long.valueOf(1);
			}
		}
		
		return regresa;
	
    }

    @Override
    public String getRutaCompartida() {
        return rutaArchivosCsv;
    }

    @Override
    public Boolean obtenRTCerrado() {
        return consultaIntradiaMuestraService.obtenRTCerrado();
    }
}
