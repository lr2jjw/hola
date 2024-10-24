package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.bmv.mci.dao.PerfilesDao;
import com.bmv.mci.dao.UsuariosDao;
import com.bmv.mci.model.Perfiles;
import com.bmv.mci.model.Permisos;
import com.bmv.mci.model.Usuarios;
import com.bvm.mci.dto.FuncionDTO;
import com.bvm.mci.dto.PerfilDTO;
import com.bvm.mci.dto.UsuarioDTO;
import com.bvm.mci.exception.BusinessException;
import com.bvm.mci.service.OperacionesWebService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OperacionesWebServiceImpl implements OperacionesWebService {

    private final UsuariosDao usuariosDao;

    private final PerfilesDao perfilesDao;

    private final String preferenteU;

    private final String preferenteP;

    private Log log = LogFactory.getLog(OperacionesWebServiceImpl.class);


    @Override
    public String getClaveCodificada(String idUsuario) {
        String regresa = null;
        Usuarios usuario;

        if(preferenteU.equals(idUsuario)){
            regresa = preferenteP;
        }else{
            usuario = usuariosDao.findByClaveUsuario(idUsuario);
            if(usuario != null){
                regresa = usuario.getContrasena();
            }
        }
        return regresa;
    }

    @Override
    public PerfilDTO getPerfilByuser(String usuario) throws BusinessException {
        PerfilDTO perfilDTO;
        Perfiles perfil;
        Long idPerfil = getPerfil(usuario);
        perfil = perfilesDao.findById(idPerfil);

        if(perfil != null){
            perfilDTO = new PerfilDTO();
            perfilDTO.setIdPerfil(perfil.getId());
            perfilDTO.setNombrePerfil(perfil.getNombrePerfil());
            perfilDTO.setDescripcion(perfil.getDescripcion());

            List<FuncionDTO> lista = new ArrayList<>();
            if(perfil.getPermisos() != null){
                for(Permisos permiso: perfil.getPermisos()){
                    lista.add(new FuncionDTO(
                            permiso.getActividades().getId(),
                            permiso.getActividades().getDescripcion(),
                            permiso.getPermisoActivado(),
                            permiso.getId()));
                }
            }
            Collections.sort(lista);
            perfilDTO.setListaFunciones(lista);
        }else{
            return null;
        }
        return perfilDTO;
    }

    @Override
    public Long getPerfil(String usuarioName) throws BusinessException{
        Long regresa = null;
        Usuarios usuario;
        usuario =usuariosDao.findByClaveUsuario(usuarioName);
        if(usuario != null){
            regresa = usuario.getPerfiles().getId();
        }
        return regresa;
    }

    @Override
    public UsuarioDTO getUser(String userName)  {
        log.info("Inicia la consulta para obtener información del usuario, username => "+ userName);
        UsuarioDTO userDTO = new UsuarioDTO();
        Usuarios usuario;

        usuario = usuariosDao.findByClaveUsuario(userName);

        if(usuario != null){
            userDTO.setNombre(usuario.getNombre());
            userDTO.setApellidoPaterno(usuario.getApellidoPaterno());
            userDTO.setClaveUsuario(usuario.getClaveUsuario());
        }
        return userDTO;
    }
}
