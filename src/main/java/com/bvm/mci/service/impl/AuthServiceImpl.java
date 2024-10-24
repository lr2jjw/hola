package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bmv.mci.admin.realtime.RealtimeAdminFailoverProxyFactory;
import com.bmv.mci.admin.realtime.RealtimeAdminService;
import com.bvm.mci.dto.InformacionSistemaDTO;
import com.bvm.mci.dto.RealtimeOperacion;
import com.bvm.mci.service.AuthService;
import com.bvm.mci.service.AuthServiceRepository;
import com.bvm.mci.service.ConsultaIntradiaEtfService;

import lombok.RequiredArgsConstructor;

//@SuppressWarnings("serial")
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthServiceRepository authRepositoryService;

	@Autowired
	private ConsultaIntradiaEtfService consultaIntradiaEtfService;

	@Inject
	@Qualifier("rtIP01")
	private String rtIP01;

	@Inject
	@Qualifier("rtIP02")
	private String rtIP02;

	@Inject
	@Qualifier("rtPuerto")
	private String rtPuerto;

	@Inject
	@Qualifier("perfilCompilacion")
	private String perfilCompilacion;

	@Inject
	@Qualifier("versionCompilacion")
	private String versionCompilacion;

	@Inject
	@Qualifier("highAvailabilityBean")
	private RealtimeAdminService cache;

	@Override
	public String retrieveUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			System.out.println("Not logged in");
			return null;
		} else {
			return (String) authentication.getPrincipal();
		}

	}

	public String logOut() {
		String regresa = new String();

		SecurityContextHolder.clearContext();

		return regresa;
	}

	public List<String> getRoles(String username) {
		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities()) {
			roles.add(grantedAuthority.getAuthority());
		}

		return roles;
	}

	public Long getPerfil(String idUsuario) {
		return authRepositoryService.getPerfil(idUsuario);
	}

	public Boolean isFunctionAccesible(String idUsuario, Long idActividad) {
		return authRepositoryService.isFunctionAccesible(idUsuario, idActividad);
	}

	public String getDbName() {
		return authRepositoryService.getDbName();
	}

	public String getPerfilDescripcion(Long idUsuario) {
		return authRepositoryService.getPerfilDescripcion(idUsuario);
	}

	public String switchRT(boolean aplicaCambio) {
		if (aplicaCambio) {
			if (RealtimeAdminFailoverProxyFactory.REALTIME_PRIMARIO) {
				RealtimeAdminFailoverProxyFactory.REALTIME_PRIMARIO = false;
				return this.getDatosRT();
				// return perfilCompilacion + " (" + rtIP02 + ":" + rtPuerto + ")";
			} else {
				RealtimeAdminFailoverProxyFactory.REALTIME_PRIMARIO = true;
				return this.getDatosRT();
				// return perfilCompilacion + " (" + rtIP01 + ":" + rtPuerto + ")";
			}
		} else {
			if (RealtimeAdminFailoverProxyFactory.REALTIME_PRIMARIO) {
				return perfilCompilacion + " (" + rtIP02 + ":" + rtPuerto + ") - 2";
			} else {
				return perfilCompilacion + " (" + rtIP01 + "-:" + rtPuerto + ") - 1";
			}
		}

	}

	public String getDatosRT() {
		String regresa;
		String perfilCompilacionRT = null;
		String ipRT = null;
		Integer puertoRT = null;
		RealtimeOperacion rtOper = consultaIntradiaEtfService.getRealtimeOperacion();
		try {
			perfilCompilacionRT = cache.getPerfilRT();
			// ipRT = cache.getIpRT();
			// puertoRT = cache.getPuertoRT();
		} catch (Exception e) {
			perfilCompilacionRT = "NA";
		}
		// Por ahora no se registra
		if (RealtimeAdminFailoverProxyFactory.REALTIME_PRIMARIO) {
			ipRT = rtIP01;
			puertoRT = Integer.valueOf(rtPuerto);
		} else {
			ipRT = rtIP02;
			puertoRT = Integer.valueOf(rtPuerto);
		}

		if (!rtOper.isActivo()) {
			regresa = "Abajo (" + ipRT + ":" + puertoRT
					+ ") - " + rtOper.getEstadoUsuarioRealtime();
		} else {
			regresa = perfilCompilacionRT + " (" + ipRT + ":" + puertoRT + ") - " + rtOper.getEstadoUsuarioRealtime();
		}

		return regresa;
	}

	public String getDatosWebApp() {
		return perfilCompilacion;
	}

	public String getMciVersion() {
		return versionCompilacion;
	}

	public Boolean setNuevoRT(String ip1, String ip2, Integer puerto) {
		// TODO llamar al remoting client para traer los datos del RT

		return false;
	}

	public InformacionSistemaDTO  getInformacionSistema( String usuario,Long idPerfil){
		InformacionSistemaDTO info= new InformacionSistemaDTO();
		info.setNombreBase( getDbName());
		info.setUsuario(usuario);
		info.setPerfil(getPerfilDescripcion( idPerfil));
		info.setVersionMCI(getMciVersion());
		info.setAmbienteWepApp(getDatosWebApp());
		info.setAmbienteRealTime(getDatosRT());
		return info;
	}
}
