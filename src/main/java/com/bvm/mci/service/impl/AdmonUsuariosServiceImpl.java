package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmv.mci.dao.ActividadesDao;
import com.bmv.mci.dao.PerfilesDao;
import com.bmv.mci.dao.PermisosDao;
import com.bmv.mci.dao.UsuariosDao;
import com.bmv.mci.model.Actividades;
import com.bmv.mci.model.AuditoriaODS;
import com.bmv.mci.model.Perfiles;
import com.bmv.mci.model.Permisos;
import com.bmv.mci.model.Usuarios;
import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.PerfilSimple;
import com.bvm.mci.dto.UsuarioSimple;
import com.bvm.mci.service.AdmonUsuariosService;
import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.shared.Constantes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmonUsuariosServiceImpl implements AdmonUsuariosService {

    private final PermisosDao permisosDao;

    @Autowired
    private UsuariosDao usuariosDao;

    @Autowired
    private PerfilesDao perfilesDao;

    @Autowired
    private ActividadesDao actividadesDao;

    @Autowired
	private ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

    
	private Log log = LogFactory.getLog(AdmonUsuariosServiceImpl.class);
    @Override
    public List<UsuarioSimple> getUsuarios() {
        List<UsuarioSimple> lista = new ArrayList<UsuarioSimple>();
        for (Usuarios usuarios : usuariosDao.findAll()) {
            lista.add(convierteUsuariosAUsuarioSimple(usuarios));
        }
        // Query query = sessionFactoryOds.getCurrentSession().createQuery(
        // "from Usuarios"
        // );
        //
        // if(query != null){
        // if(query.list().size() > 0){
        // for(Usuarios usuarios: (List<Usuarios>)query.list()){
        // lista.add(convierteUsuariosAUsuarioSimple(usuarios));
        // }
        // }
        // }

        return lista;
    }

    private UsuarioSimple convierteUsuariosAUsuarioSimple(Usuarios usuarios) {
        UsuarioSimple usuarioSimple = null;

        if (usuarios != null) {
            usuarioSimple = new UsuarioSimple(
                    usuarios.getId(),
                    usuarios.getPerfiles().getId(),
                    usuarios.getClaveUsuario(),
                    usuarios.getNombre(),
                    usuarios.getApellidoPaterno(),
                    usuarios.getApellidoMaterno(),
                    usuarios.getPerfiles().getNombrePerfil());
        }

        return usuarioSimple;
    }

    @Override
    public UsuarioSimple getUsuario(String idUsuario) {
        UsuarioSimple usuarioSimple = null;

        if (!idUsuario.equals(" ")) {
            usuarioSimple = convierteUsuariosAUsuarioSimple(usuariosDao.findById(Long.valueOf(idUsuario)));

            // Query query = sessionFactoryOds.getCurrentSession().createQuery(
            // "from Usuarios where id = ?"
            // );
            // query.setParameter(0, Long.valueOf(idUsuario));
            //
            // if(query != null){
            // if(query.list().size() > 0){
            // for(Usuarios usuarios: (List<Usuarios>)query.list()){
            // usuarioSimple = convierteUsuariosAUsuarioSimple(usuarios);
            // }
            // }
            // }
        }

        return usuarioSimple;

    }

    @Override
    public UsuarioSimple getUsuarioPorClave(String clave) {
        UsuarioSimple usuarioSimple = null;

        if (clave != null) {
            usuarioSimple = convierteUsuariosAUsuarioSimple(usuariosDao.findByClaveUsuario(clave));

            // Query query = sessionFactoryOds.getCurrentSession().createQuery(
            // "from Usuarios where claveUsuario = ?"
            // );
            // query.setParameter(0, clave);
            //
            // if(query != null){
            // if(query.list().size() > 0){
            // for(Usuarios usuarios: (List<Usuarios>)query.list()){
            // usuarioSimple = convierteUsuariosAUsuarioSimple(usuarios);
            // }
            // }
            // }
        }

        return usuarioSimple;
    }

    public List<ActividadesPerfil> getActividadesPerfil(Long idPerfil) {
        List<ActividadesPerfil> lista = new ArrayList<ActividadesPerfil>();

        for (Permisos permisos : permisosDao.findPermisosByPerfil(idPerfil)) {
            lista.add(conviertePerfilAActividadesPerfil(permisos));
        }
        // Query query = sessionFactoryOds.getCurrentSession().createQuery(
        // "from Permisos A where A.perfiles.id = ?"
        // );
        // query.setParameter(0, idPerfil);
        //
        // if(query != null){
        // if(query.list().size() > 0){
        // for(Permisos permisos: (List<Permisos>)query.list()){
        // lista.add(conviertePerfilAActividadesPerfil(permisos));
        // }
        // }
        // }

        return lista;
    }

    public Usuarios getUsuarioBD(Long idUsuario) {
        Usuarios usuariosRegresa = null;

        if (!idUsuario.equals(" ")) {
            usuariosRegresa = usuariosDao.findById(Long.valueOf(idUsuario));
            // Query query = sessionFactoryOds.getCurrentSession().createQuery(
            // "from Usuarios where id = ?"
            // );
            // query.setParameter(0, Long.valueOf(idUsuario));
            //
            // if(query != null){
            // if(query.list().size() > 0){
            // for(Usuarios usuarios: (List<Usuarios>)query.list()){
            // usuariosRegresa = usuarios;
            // }
            // }
            // }
        }

        return usuariosRegresa;
    }

    public String getDescripcionPerfil(Long idPerfil) {
        String regresa = null;
        Perfiles perfil = perfilesDao.findById(idPerfil);
        if (perfil != null) {
            regresa = perfil.getDescripcion();
        }

        // Query query = sessionFactoryOds.getCurrentSession().createQuery(
        // "from Perfiles A where A.id = ?"
        // );
        // query.setParameter(0, idPerfil);
        //
        // if(query != null){
        // if(query.list().size() > 0){
        // for(Perfiles perfil: (List<Perfiles>)query.list()){
        // regresa = perfil.getDescripcion();
        // }
        // }
        // }

        return regresa;
    }

    public Perfiles getPerfil(Long idPerfil) {
        Perfiles perfil = null;

        if (idPerfil != null) {
            perfil = perfilesDao.findById(idPerfil);
            // Query query = sessionFactoryOds.getCurrentSession().createQuery(
            // "from Perfiles where id = ?"
            // );
            // query.setParameter(0, idPerfil);
            //
            // if(query != null){
            // if(query.list().size() > 0){
            // for(Perfiles perfiles: (List<Perfiles>)query.list()){
            // perfil = perfiles;
            // }
            // }
            // }
        }

        return perfil;
    }

    @Override
    public List<PerfilSimple> getPerfiles() {
        List<PerfilSimple> lista = new ArrayList<PerfilSimple>();
        for (Perfiles perfiles : perfilesDao.findAll()) {
            lista.add(conviertePerfilesAPerfilSimple(perfiles));
        }

        // Query query = sessionFactoryOds.getCurrentSession().createQuery(
        // "from Perfiles"
        // );
        //
        // if(query != null){
        // if(query.list().size() > 0){
        // for(Perfiles perfiles: (List<Perfiles>)query.list()){
        // lista.add(conviertePerfilesAPerfilSimple(perfiles));
        // }
        // }
        // }

        return lista;
    }

    private PerfilSimple conviertePerfilesAPerfilSimple(Perfiles perfiles) {
        PerfilSimple perfilSimple = null;

        if (perfiles != null) {
            perfilSimple = new PerfilSimple(
                    perfiles.getId(),
                    perfiles.getNombrePerfil(),
                    perfiles.getDescripcion());
        }

        return perfilSimple;
    }

    public Integer guardaUsuario(String claveUsuario, String nombre, String apellidoPaterno,
            String apellidoMaterno, String contrasena, String perfilId) {
        Integer regresa = new Integer(0);
        UsuarioSimple usuarioSimple = null;
        Perfiles perfiles = null;
       // Usuarios usuarios;
        // Long ultimoIdUsuarios;

        String nombreCorregido;
        String apellidoPaternoCorregido;
        String apellidoMaternoCorregido;

        usuarioSimple = getUsuarioPorClave(claveUsuario);
        if (usuarioSimple == null) {
            log.info("+Se registrar� un usuario con los siguientes datos:" +
                    "\n        claveUsuario: " + claveUsuario +
                    "\n        nombre: " + nombre +
                    "\n        apellidoPaterno: " + apellidoPaterno +
                    "\n        apellidoMaterno: " + apellidoMaterno +
                    "\n        contrasena: " + contrasena +
                    "\n        perfilId: " + perfilId);
            perfiles = getPerfil(Long.valueOf(perfilId));
            if (perfiles != null) {
                try {

                    // ultimoIdUsuarios = getUltimoIdUsuario();

                    // rootLogger.info("Ultimo id de usuario disponible: " + ultimoIdUsuarios);

                    AuditoriaODS auditoria = new AuditoriaODS();
                    auditoria.setCreadoPor(Constantes.USUARIO_MCI);
                    auditoria.setFechaCreacion(new Date());
                    auditoria.setSistema(Constantes.SISTEMA_MCI);

                    String contraCod;
                    StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
                    contraCod = standardPasswordEncoder.encode(contrasena);

                    nombreCorregido = nombre.replace("'", "''");
                    apellidoPaternoCorregido = apellidoPaterno.replace("'", "''");
                    apellidoMaternoCorregido = apellidoMaterno.replace("'", "''");

                    // String sql = "INSERT INTO \"ADMIN_MCI\".\"MCI_TUSUARIOS\"(\"ID_USUARIO\",
                    // \"ID_PERFIL\", \"CLAVE_USUARIO\"," +
                    // " \"CONTRASENA\", \"NOMBRE\", \"APELLIDO_PATERNO\", \"APELLIDO_MATERNO\",
                    // \"CREADO_POR\", \"FECHA_CREACION\"," +
                    // " \"SISTEMA\") VALUES(" + ultimoIdUsuarios.toString() + ", " +
                    // perfiles.getId().toString() +
                    // ", '" + claveUsuario + "', '" + contraCod + "', '" + nombreCorregido + "', '"
                    // + apellidoPaternoCorregido +
                    // "', '" + apellidoMaternoCorregido + "', '" + Constantes.USUARIO_MCI + "',
                    // sysdate, '" + Constantes.SISTEMA_MCI + "')";
                    //
                    // jdbc.execute(sql);
                    Usuarios usuario = new Usuarios();
                    usuario.setApellidoMaterno(apellidoMaternoCorregido);
                    usuario.setApellidoPaterno(apellidoPaternoCorregido);
                    usuario.setAuditoria(auditoria);
                    usuario.setClaveUsuario(claveUsuario);
                    usuario.setContrasena(contrasena);
                    usuario.setNombre(nombreCorregido);
                    usuario.setPerfiles(perfiles);
                    usuariosDao.insert(usuario);

                    regresa = new Integer(1);
                } catch (Exception e) {
                    log.info("Error al guardar el nuevo usuario: " + e.getMessage());
                }
            }
        } else {
            regresa = new Integer(2);
        }

        /*
         * Valores que regresa:
         * 0 - No se guard�
         * 1 - Se guard�
         * 2 - Usuario preexistente
         */
        return regresa;
    }

    public Integer guardaUsuarioEditado(String claveUsuario, String nombre, String apellidoPaterno,
            String apellidoMaterno, String contrasena, String perfilId, UsuarioSimple usuarioAnterior) {
        Integer regresa = new Integer(0);
        Perfiles perfiles = null;
        Usuarios usuarios;
        UsuarioSimple usuarioSimple = null;

        usuarios = getUsuarioBD(usuarioAnterior.getIdUsuario());
        if (usuarios != null) {
            Boolean pasa = false;

            if (claveUsuario.equals(usuarioAnterior.getClaveUsuario())) {
                pasa = true;
            } else {
                usuarioSimple = getUsuarioPorClave(claveUsuario);
                if (usuarioSimple == null)
                    pasa = true;
            }

            if (pasa) {

                log.info("+Se actualizar�n los siguientes datos:" +
                        "\n        idUsuario: " + usuarioAnterior.getIdUsuario() +
                        "\n        claveUsuario: " + usuarioAnterior.getClaveUsuario() + " -> " + claveUsuario +
                        "\n        nombre: " + usuarioAnterior.getNombre() + " -> " + nombre +
                        "\n        apellidoPaterno: " + usuarioAnterior.getApellidoPaterno() + " -> " + apellidoPaterno
                        +
                        "\n        apellidoMaterno: " + usuarioAnterior.getApellidoMaterno() + " -> " + apellidoMaterno
                        +
                        "\n        perfilId: " + usuarioAnterior.getIdPerfil() + " -> " + perfilId);
                perfiles = getPerfil(Long.valueOf(perfilId));
                if (perfiles != null) {
                    try {
                        usuarios.setClaveUsuario(claveUsuario);
                        usuarios.setNombre(nombre);
                        usuarios.setApellidoPaterno(apellidoPaterno);
                        usuarios.setApellidoMaterno(apellidoMaterno);
                        usuarios.setPerfiles(perfiles);

                        if (!contrasena.equals("xxxxxxxx")) {
                            String contraCod;
                            StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
                            contraCod = standardPasswordEncoder.encode(contrasena);
                            usuarios.setContrasena(contraCod);
                        }

                        usuariosDao.update(usuarios);

                        // sessionFactoryOds.getCurrentSession().update(usuarios);

                        regresa = new Integer(1);
                    } catch (Exception e) {
                        log.info("Error al guardar cambios del usuario: " + e.getMessage());
                    }
                }
            } else {
                regresa = new Integer(2);
            }
        }

        /*
         * Valores que regresa:
         * 0 - No se guard�
         * 1 - Se guard�
         * 2 - Usuario preexistente
         */
        return regresa;
    }

    public Integer borraUsuario(Long idUsuario) {
        Integer regresa = new Integer(0);

        if (idUsuario != null) {
            log.info("Se elimina el usuario con Id : " + idUsuario);
            try {
                // String sql = "DELETE FROM \"ADMIN_MCI\".\"MCI_TUSUARIOS\" WHERE ID_USUARIO =
                // " + idUsuario;

                // jdbc.execute(sql);
                usuariosDao.deleteById(idUsuario);
                regresa = new Integer(1);
            } catch (Exception e) {
                log.info("Error al guardar el nuevo usuario: " + e.getMessage());
            }
        } else {
            regresa = new Integer(2);
        }

        /*
         * Valores que regresa:
         * 0 - No se borr�
         * 1 - Se borr�
         * 2 - Id inv�lido
         */
        return regresa;
    }

    public Integer creaPerfil(String nombre) {
        Integer regresa = new Integer(0);
      //  Long idPerfil;
        String nombreCorregido;

        Perfiles perfil = null;

        perfil = getPerfilPorNombre(nombre);

        if (perfil == null) {
            // idPerfil = getUltimoIdPerfiles();
            //
            // if(idPerfil != null){
            //
            nombreCorregido = nombre.replace("'", "''");

            // String sql = "INSERT INTO \"ADMIN_MCI\".\"MCI_CPERFILES\"(\"ID_PERFIL\", " +
            // "\"NOMBRE_PERFIL\", \"DESCRIPCION\", \"CREADO_POR\", " +
            // "\"FECHA_CREACION\", \"SISTEMA\") VALUES(" +
            // idPerfil + ", '" + nombreCorregido + "', ' ', '" + Constantes.USUARIO_MCI +
            // "', SYSDATE, '" + Constantes.SISTEMA_MCI + "')";
            //
            // jdbc.execute(sql);
            AuditoriaODS auditoria = new AuditoriaODS();
            auditoria.setCreadoPor(Constantes.USUARIO_MCI);
            auditoria.setFechaCreacion(new Date());
            auditoria.setSistema(Constantes.SISTEMA_MCI);

            Perfiles perfiles = new Perfiles();
            perfiles.setDescripcion(" ");
            perfiles.setNombrePerfil(nombreCorregido);
            perfiles.setAuditoria(auditoria);
            perfilesDao.insert(perfiles);

            log.info("Perfil creado: " + nombre + " (" + perfiles.getId() + ")");
            log.info("Agregando actividades con valores dafault ...");

            guardaPerfil(perfiles.getId().toString(), " ", new ArrayList<ActividadesPerfil>());

            regresa = new Integer(1);
            // }
        } else {
            regresa = new Integer(2);
        }

        return regresa;
    }

    private Perfiles getPerfilPorNombre(String nombre) {
        Perfiles perfil = null;

        if (nombre != null) {
            perfilesDao.findByNombrePerfil(nombre);
            // Query query = sessionFactoryOds.getCurrentSession().createQuery(
            // "from Perfiles where nombrePerfil = ?"
            // );
            // query.setParameter(0, nombre);
            //
            // if(query != null){
            // if(query.list().size() > 0){
            // for(Perfiles perfiles: (List<Perfiles>)query.list()){
            // perfil = perfiles;
            // }
            // }
            // }
        }

        return perfil;
    }

    public Integer guardaPerfil(String idPerfil,
            String descripcion,
            List<ActividadesPerfil> listaActividadesPerfil) {

        Integer regresa = new Integer(0);
        Perfiles perfiles = null;
        List<Actividades> listaActividades = null;
        List<Long> listaIdsActividadesActualizadas = new ArrayList<Long>();
        boolean permitido = false;
        Long ultimoIdPermisos = null;
        boolean sigue = true;

        log.info("Se actualizar� el perfil (id):" + idPerfil);

        perfiles = getPerfil(Long.valueOf(idPerfil));
        listaActividades = getActividadesGeneral();

        log.info("Se actualizar� el perfil (nombre):" + perfiles.getNombrePerfil());

        if ((perfiles != null) && (listaActividades != null)) {

            try {

                log.info("Actualizada la descripcion: " + perfiles.getDescripcion() + " -> " + descripcion);
                perfiles.setDescripcion(descripcion);

                for (Permisos permisos : perfiles.getPermisos()) {
                    listaIdsActividadesActualizadas.add(permisos.getActividades().getId());

                    permitido = false;
                    sigue = true;

                    for (int i = 0; (i < listaActividadesPerfil.size()) && (sigue); i++) {
                        ActividadesPerfil actividadesPerfil = (ActividadesPerfil) listaActividadesPerfil.get(i);

                        if (permisos.getActividades().getId().longValue() == actividadesPerfil.getIdActividad()
                                .longValue()) {
                            permitido = actividadesPerfil.getPermitido().equals("true");
                            // rootLogger.warning("Evaluando: " + actividadesPerfil.getPermitido());
                            sigue = false;
                        }
                    }
                    permisos.setPermisoActivado(permitido);

                    permisosDao.update(permisos);

                    // sessionFactoryOds.getCurrentSession().update(permisos);

                }
                log.info("Actualizadas " + listaIdsActividadesActualizadas.size() + " actividades");

                if (listaActividades.size() > listaIdsActividadesActualizadas.size()) {
                    log.info("Agregando registros de actividades faltantes ... ");

                    // ultimoIdPermisos = getUltimoIdPermisos();
                    AuditoriaODS auditoria = new AuditoriaODS();
                    auditoria.setCreadoPor(Constantes.USUARIO_MCI);
                    auditoria.setFechaCreacion(new Date());
                    auditoria.setSistema(Constantes.SISTEMA_MCI);

                    for (Actividades actividades : listaActividades) {
                        if (!listaIdsActividadesActualizadas.contains(actividades.getId())) {
                            // String sql = "INSERT INTO \"ADMIN_MCI\".\"MCI_CPERMISOS\"(\"ID_PERMISO\", " +
                            // "\"ID_ACTIVIDAD\", \"ID_PERFIL\", \"PERMISO_ACTIVADO\", \"CREADO_POR\", " +
                            // "\"FECHA_CREACION\", \"SISTEMA\") VALUES(" + ultimoIdPermisos + ", " +
                            // actividades.getId() + ", " + perfiles.getId() + ", 0, '" +
                            // Constantes.USUARIO_MCI +
                            // "', SYSDATE, '" + Constantes.SISTEMA_MCI + "')";
                            //
                            // jdbc.execute(sql);
                            //

                            Permisos permisos = new Permisos();
                            permisos.setActividades(actividades);
                            permisos.setAuditoria(auditoria);
                            permisos.setPerfiles(perfiles);
                            permisos.setPermisoActivado(false);
                            permisosDao.insert(permisos);
                            // ultimoIdPermisos++;
                            log.info("Agregada actividad: " + actividades.getId() + " - "
                                    + actividades.getDescripcion());
                        }
                    }
                }

                regresa = new Integer(1);
            } catch (Exception e) {
                log.info("Error al guardar perfil: " + e.getMessage());
            }
        } else {
            if (perfiles == null)
            log.info("Perfil no encontrado");
            if (listaActividades == null)
            log.info("Lista de actividades no encontrada");
        }

        /*
         * Valores que regresa:
         * 0 - No se guard�
         * 1 - Se guard� 
         */
        return regresa;
    }

    public Integer eliminaPerfil(String idPerfil) {

        Integer regresa = new Integer(0);
        Perfiles perfiles = null;

        log.info("Se eliminar� el perfil (id):" + idPerfil);

        perfiles = getPerfil(Long.valueOf(idPerfil));

        log.info("Se eliminar� el perfil (nombre):" + perfiles.getNombrePerfil());

        if (perfiles != null) {
            try {
                boolean borra = false;
                List<Usuarios> usuarios = usuariosDao.findByPerfil(perfiles.getId());
                if (usuarios == null) {
                    // borra perfil
                    borra = true;
                } else if (usuarios.size() == 0) {
                    // borra perfil
                    borra = true;
                } else {
                    regresa = new Integer(2);
                }

                if (borra) {
                    log.info("Eliminando permisos ...");
                    for (Permisos per : perfiles.getPermisos()) {
                        permisosDao.delete(per);
                        // sessionFactoryOds.getCurrentSession().delete(per);
                    }

                    log.info("Eliminando perfil ...");
                    perfilesDao.delete(perfiles);
                    // sessionFactoryOds.getCurrentSession().delete(perfiles);

                    regresa = new Integer(1);
                    log.info("Perfil eliminado.");
                }

            } catch (Exception e) {
                log.info("Error al eliminar perfil: " + e.getMessage());
            }
        } else {
            log.info("Perfil no encontrado");
        }

        /*
         * Valores que regresa:
         * 0 - No se borr�
         * 1 - Se borr�
         * 2 - Usuarios con ese perfil
         */
        return regresa;
    }

    List<Actividades> getActividadesGeneral() {
        List<Actividades> lista = (List) actividadesDao.findAll();

        // Query query = sessionFactoryOds.getCurrentSession().createQuery(
        // "from Actividades A order by A.id"
        // );
        //
        // if(query != null){
        // if(query.list().size() > 0){
        // lista = query.list();
        // }
        // }
        //
        return lista;
    }

    @Override
    public Boolean obtenRTCerrado() {
        return consultaIntradiaMuestraService.obtenRTCerrado();
    }

    private ActividadesPerfil conviertePerfilAActividadesPerfil(Permisos permisos) {
        ActividadesPerfil actividadesPerfil = null;
        // String cadena = new String();
        //List<String> lista = new ArrayList<String>();

        if (permisos != null) {
            /*
             * for(Filtros filtros: permisos.getFiltros()){
             * lista.add(filtros.getValor());
             * if(cadena.length() == 0)
             * cadena = filtros.getValor();
             * else
             * cadena = cadena + " " + filtros.getValor();
             * }
             */

            actividadesPerfil = new ActividadesPerfil(
                    permisos.getPerfiles().getId(),
                    permisos.getId(),
                    permisos.getActividades().getId(),
                    permisos.getActividades().getDescripcion(),
                    permisos.getPermisoActivado().toString()
            // cadena,
            // lista
            );
        }

        return actividadesPerfil;
    }
}
