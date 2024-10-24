package com.bvm.mci.service;

import java.util.List;

import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.PerfilSimple;
import com.bvm.mci.dto.UsuarioSimple;

 

/**
 * @author rdiaz
 */

public interface AdmonUsuariosService {
	
	List<UsuarioSimple> getUsuarios();
	
	UsuarioSimple getUsuario(String idUsuario);
	
	UsuarioSimple getUsuarioPorClave(String clave);
	
	List<ActividadesPerfil> getActividadesPerfil(Long idPerfil);
	
	String getDescripcionPerfil(Long idPerfil);
	
	List<PerfilSimple> getPerfiles();
	
	Integer guardaUsuario(String claveUsuario, String nombre, String apellidoPaterno,
			String apellidoMaterno, String contrasena, String perfilId);
	
	Integer guardaUsuarioEditado(String claveUsuario, String nombre, String apellidoPaterno,
			String apellidoMaterno, String contrasena, String perfilId, UsuarioSimple usuarioAnterior);
	
	Integer borraUsuario(Long idUsuario);
	
	Integer creaPerfil(String nombre);
	
	Integer guardaPerfil(String idPerfil,
			String descripcion,
			List<ActividadesPerfil> listaActividadesPerfil);
	
	Integer eliminaPerfil(String idPerfil);
	
	Boolean obtenRTCerrado();
	
}
