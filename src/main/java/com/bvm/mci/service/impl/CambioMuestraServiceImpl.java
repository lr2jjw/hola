package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bvm.mci.dto.EmisoraDetalle;
import com.bvm.mci.dto.TiposValor;
import com.bvm.mci.service.CambioMuestraService;
 

/**
 * @author �l Clase que implementa el servicio cambio muestra
 * 
 */
@SuppressWarnings("all")
@Service("cambioMuestraService")
@Transactional
public class CambioMuestraServiceImpl implements CambioMuestraService {

	//private Log log = LogFactory.getLog(CambioMuestraServiceImpl.class);
	//logger
	private static Logger rootLogger = Logger.getLogger("CambioMuestraServiceImpl.class");

//	@Inject
//	@Named("jdbcOds")
//	private JdbcTemplate jdbc;
//	
//	@Autowired
//	private SessionFactory sessionFactoryOds;

@Inject 
private String rutaArchivosCsv;

/**
 * @see com.bmv.mci.admin.client.service.intradia.CambioMuestraService#getEmisionesMuestraInicial()
 * @return List<TipoValor>
 */
public List<TiposValor> getEmisionesMuestraInicial() {
	List<TiposValor> tiposValor = new ArrayList<TiposValor>(0);
//		tiposValor.add(new TiposValor("EMISIONES INTERNACIONALES"));
//		String sql = "select distinct new com.bmv.mci.admin.shared.dto.TiposValor(tv.primaryKey.tipoRenta"
//				+ " ) from TipoValor tv";
//		Query query = sessionFactoryOds.getCurrentSession().createQuery(sql);
//		tiposValor.addAll(query.list());

	return tiposValor;
}

@Override
public List<EmisoraDetalle> getAccionesFlotantes(
		List<EmisoraDetalle> emisoraDetalleLista) {
	for (EmisoraDetalle e : emisoraDetalleLista) {
	//	e.set("accionesMuestra", e.getAccionesCirculacion() * e.getFactorFloat().longValue());
	e.setAccionesFlotantes(e.getAccionesCirculacion() * e.getFactorFloat().longValue());
	}
	return emisoraDetalleLista;
}

public Boolean guardaRIC(Long idEmision, Double precio, String RIC) {	
//		Query query= null;
//		Emision emision= null;
//		
//		System.out.println("---------->idEmision:" + idEmision + "<-Precio:" + precio + "<-RIC:"+ RIC +"<-");
//		String sql= "from Emision a where a.idEmisionODS= ?";
//		query= sessionFactoryOds.getCurrentSession().createQuery(sql);
//		query.setParameter(0, idEmision);
//		emision= (Emision)query.uniqueResult();		
//		
//		if (emision != null) {
//			emision.setPrecioActual(precio);
//			emision.setPrecioRTActual(precio);
//			//emision.setRic(RIC);		
//			sessionFactoryOds.getCurrentSession().update(emision);
//		} else {
//			return false;
//		}
//		return true;
	
	return false;
}

}
