package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.ActividadesPerfil;
import com.bvm.mci.dto.PerfilSimple;
import com.bvm.mci.dto.UsuarioSimple;
import com.bvm.mci.service.AdmonUsuariosService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class AdmonUsuariosController {

    @Autowired
    private final AdmonUsuariosService admonUsuariosService;

    @GetMapping("/usuarios")
    public ResponseEntity<Response<List<UsuarioSimple>>> getUsuarios() {
        Response<List<UsuarioSimple>> response = new Response<>();
        List<UsuarioSimple> usuarios = admonUsuariosService.getUsuarios();
        response.setError(false);
        response.setDatos(usuarios);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Response<UsuarioSimple>> getUsuario(@PathVariable String idUsuario) {
        Response<UsuarioSimple> response = new Response<>();
        UsuarioSimple usuario = admonUsuariosService.getUsuario(idUsuario);
        response.setError(false);
        response.setDatos(usuario);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clave/{clave}")
    public ResponseEntity<Response<UsuarioSimple>> getUsuarioPorClave(@PathVariable String clave) {
        Response<UsuarioSimple> response = new Response<>();
        UsuarioSimple usuario = admonUsuariosService.getUsuarioPorClave(clave);
        response.setError(false);
        response.setDatos(usuario);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil/{idPerfil}/actividades")
    public ResponseEntity<Response<List<ActividadesPerfil>>> getActividadesPerfil(@PathVariable Long idPerfil) {
        Response<List<ActividadesPerfil>> response = new Response<>();
        List<ActividadesPerfil> actividades = admonUsuariosService.getActividadesPerfil(idPerfil);
        response.setError(false);
        response.setDatos(actividades);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil/{idPerfil}/descripcion")
    public ResponseEntity<Response<String>> getDescripcionPerfil(@PathVariable Long idPerfil) {
        Response<String> response = new Response<>();
        String descripcion = admonUsuariosService.getDescripcionPerfil(idPerfil);
        response.setError(false);
        response.setDatos(descripcion);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfiles")
    public ResponseEntity<Response<List<PerfilSimple>>> getPerfiles() {
        Response<List<PerfilSimple>> response = new Response<>();
        List<PerfilSimple> perfiles = admonUsuariosService.getPerfiles();
        response.setError(false);
        response.setDatos(perfiles);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<Response<Integer>> guardaUsuario(@RequestParam String claveUsuario,
            @RequestParam String nombre,
            @RequestParam String apellidoPaterno,
            @RequestParam String apellidoMaterno,
            @RequestParam String contrasena,
            @RequestParam String perfilId) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.guardaUsuario(claveUsuario, nombre, apellidoPaterno, apellidoMaterno,
                contrasena, perfilId);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("modifica-usuario/{claveUsuario}")
    public ResponseEntity<Response<Integer>> guardaUsuarioEditado(@PathVariable String claveUsuario,
            @RequestParam String nombre,
            @RequestParam String apellidoPaterno,
            @RequestParam String apellidoMaterno,
            @RequestParam String contrasena,
            @RequestParam String perfilId,
            @RequestBody UsuarioSimple usuarioAnterior) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.guardaUsuarioEditado(claveUsuario, nombre, apellidoPaterno,
                apellidoMaterno, contrasena, perfilId, usuarioAnterior);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Response<Integer>> borraUsuario(@PathVariable Long idUsuario) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.borraUsuario(idUsuario);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/crearperfil")
    public ResponseEntity<Response<Integer>> creaPerfil(@RequestParam String nombre) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.creaPerfil(nombre);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar-perfil/{idPerfil}")
    public ResponseEntity<Response<Integer>> guardaPerfil(@PathVariable String idPerfil,
            @RequestParam String descripcion,
            @RequestBody List<ActividadesPerfil> listaActividadesPerfil) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.guardaPerfil(idPerfil, descripcion, listaActividadesPerfil);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar-perfil/{idPerfil}")
    public ResponseEntity<Response<Integer>> eliminaPerfil(@PathVariable String idPerfil) {
        Response<Integer> response = new Response<>();
        Integer result = admonUsuariosService.eliminaPerfil(idPerfil);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtcerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        Boolean result = admonUsuariosService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
