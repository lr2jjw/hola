package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.mci.dto.InformacionSistemaDTO;
import com.bvm.mci.service.impl.AuthServiceImpl;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/auth")
public class ValidacionesController {

    @Autowired
    private AuthServiceImpl authService;

    @GetMapping("/username")
    public ResponseEntity<Response<String>> retrieveUsername() {
        Response<String> response = new Response<>();
        response.setDatos(authService.retrieveUsername());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/roles/{username}")
    public ResponseEntity<Response<List<String>>> getRoles(@PathVariable String username) {
        Response<List<String>> response = new Response<>();
        response.setDatos(authService.getRoles(username));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil/{idUsuario}")
    public ResponseEntity<Response<Long>> getPerfil(@PathVariable String idUsuario) {
        Response<Long> response = new Response<>();
        response.setDatos(authService.getPerfil(idUsuario));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/function-access/{idUsuario}/{idActividad}")
    public ResponseEntity<Response<Boolean>> isFunctionAccessible(@PathVariable String idUsuario,
            @PathVariable Long idActividad) {
        Response<Boolean> response = new Response<>();
        response.setDatos(authService.isFunctionAccesible(idUsuario, idActividad));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/db-name")
    public ResponseEntity<Response<String>> getDbName() {
        Response<String> response = new Response<>();
        response.setDatos(authService.getDbName());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil-descripcion/{idPerfil}")
    public ResponseEntity<Response<String>> getPerfilDescripcion(@PathVariable Long idPerfil) {
        Response<String> response = new Response<>();
        response.setDatos(authService.getPerfilDescripcion(idPerfil));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refrescar/datos")
    public ResponseEntity<Response<String>> getRefrescarDatos() {
        Response<String> response = new Response<>();
        response.setDatos(authService.getDatosRT());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/datos-real-time")
    public ResponseEntity<Response<String>> getDatosRealTime() {
        Response<String> response = new Response<>();
        response.setDatos(authService.getDatosRT());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/datos-webapp")
    public ResponseEntity<Response<String>> getDatosWebApp() {
        Response<String> response = new Response<>();
        response.setDatos(authService.getDatosWebApp());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mci-version")
    public ResponseEntity<Response<String>> getMciVersion() {
        Response<String> response = new Response<>();
        response.setDatos(authService.getMciVersion());
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/nuevo-rt")
    public ResponseEntity<Response<Boolean>> setNuevoRT(@RequestParam String ip1, @RequestParam String ip2,
            @RequestParam Integer puerto) {
        Response<Boolean> response = new Response<>();
        response.setDatos(authService.setNuevoRT(ip1, ip2, puerto));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/switch-rt")
    public ResponseEntity<Response<String>> switchRT(@RequestParam boolean aplicaCambio) {
        Response<String> response = new Response<>();
        response.setDatos(authService.switchRT(aplicaCambio));
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/informacion/sistema/{usuario}/{idPerfil}")
    public ResponseEntity<Response<InformacionSistemaDTO>> getInformacionSistema(
            @PathVariable String usuario,
            @PathVariable Long idPerfil) {
                Response<InformacionSistemaDTO> response = new Response<>();
                InformacionSistemaDTO informacionSistema = authService.getInformacionSistema(usuario, idPerfil);
                response.setDatos(informacionSistema);
                response.setError(false);
                response.setCodigo(HttpStatus.OK.value());
                response.setMensaje("Éxito");
                return ResponseEntity.ok(response);

            }
}
