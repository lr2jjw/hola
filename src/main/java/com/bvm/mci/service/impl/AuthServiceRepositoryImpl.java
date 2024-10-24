package com.bvm.mci.service.impl;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.bmv.mci.model.Perfiles;
import com.bmv.mci.model.Permisos;
import com.bmv.mci.model.Usuarios;
import com.bvm.mci.service.AuthServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceRepositoryImpl implements AuthServiceRepository {

    private Log log = LogFactory.getLog(AuthServiceRepositoryImpl.class);

    private final SessionFactory sessionFactoryOds;

    @Named("preferenteU")
    private final String preferenteU;

    @Named("preferenteP")
    private final String preferenteP;

    @Override
    public Long getPerfil(String idUsuario) {
        Long idPerfil = null;

        //por normalizar con los objetos de bd oficiales
        if(idUsuario != null){
            Query query = sessionFactoryOds.getCurrentSession().createQuery(
                    "from Usuarios A where A.claveUsuario = ?"
            );
            query.setParameter(0, idUsuario);

            try {
                if(query != null){
                    if(query.list().size() == 1){
                        idPerfil = ((Usuarios) query.list().get(0)).getPerfiles().getId();
                    }
                }
            } catch (Exception e) {
                if(idUsuario.equals(preferenteU)){
                    idPerfil = Long.valueOf(0);
                }
                log.warn("getPerfil: " + idPerfil);
            }
        }

        return idPerfil;
    }

    @Override
    public Boolean isFunctionAccesible(String idUsuario, Long idActividad) {
        Boolean regresa = false;

        //logger.info("Valor de idUsuario: " + idUsuario + "; valor de idActividad: "
        //		+ idActividad);

        if((idUsuario != null) && (idActividad != null)){
            Query query = sessionFactoryOds.getCurrentSession().createQuery(
                    "from Permisos A where A.actividades.id = ? and A.perfiles.id = ?"
            );
            query.setParameter(0, idActividad);
            query.setParameter(1, getPerfil(idUsuario));

            if(query != null){
                try {
                    if(query.list().size() == 1){
                        regresa = ((Permisos) query.list().get(0)).getPermisoActivado();
                    }
                } catch (Exception e) {
                    if(idUsuario.equals(preferenteU)){
                        regresa = true;
                    }
                    log.warn("isFunctionAccesible: " + regresa);
                }
            }
        }

        return regresa;
    }

    @Override
    public String getClaveCodificada(String idUsuario) {
        String regresa = null;
		
		//logger.info("Valor de idUsuario: " + idUsuario);
		
		if(idUsuario != null){
			Query query = sessionFactoryOds.getCurrentSession().createQuery(
					"from Usuarios A where A.claveUsuario = ?"
					);
			query.setParameter(0, idUsuario);
		
			try {
			if(query != null){
				if(query.list().size() == 1){
					regresa = ((Usuarios) query.list().get(0)).getContrasena();
				}
					else{
						if(idUsuario.equals(preferenteU)){
							regresa = preferenteP;
						}
					}
				}
				else{
					if(idUsuario.equals(preferenteU)){
						regresa = preferenteP;
			}
		}
			} catch (Exception e) {
				if(idUsuario.equals(preferenteU)){
					regresa = preferenteP;
				}
			}
		}
		
	 
		
		return regresa;
    }

    @Override
    public String getDbName() {
        String regresa = null;
		
		Query query = sessionFactoryOds.getCurrentSession().createSQLQuery(
				"select ora_database_name from dual"
				);
	
		if(query != null){
			if(query.list().size() == 1){
				regresa = query.list().get(0).toString();
			}
		}

		return regresa;
    }

    @Override
    public String getPerfilDescripcion(Long idPerfil) {
     String descripcion = null;
		Perfiles p= new Perfiles();
		//por normalizar con los objetos de bd oficiales
		if(idPerfil != null){
			Query query = sessionFactoryOds.getCurrentSession().createQuery(
					"from Perfiles A where A.id = ?"
					);
			query.setParameter(0, idPerfil);
		
			if(query != null){
				if(query.list().size() == 1){
					descripcion = ((Perfiles) query.list().get(0)).getNombrePerfil();
				}
			}
		}

		return descripcion;
    }
}
