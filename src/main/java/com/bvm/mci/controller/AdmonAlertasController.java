package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.DestinatarioSimple;
import com.bvm.mci.service.AdmonAlertasService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admon-alertas")
public class AdmonAlertasController {

    @Autowired
    private final AdmonAlertasService admonAlertasService;

    @GetMapping("/destinatarios")
    public ResponseEntity<Response<List<DestinatarioSimple>>> getListaDestinatarios() {
        Response<List<DestinatarioSimple>> response = new Response<>();
        List<DestinatarioSimple> destinatarios = admonAlertasService.getListaDestinatarios();
        response.setError(false);
        response.setDatos(destinatarios);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/destinatarios")
    public ResponseEntity<Response<Long>> saveDestinatario(
            @RequestParam String tipo,
            @RequestParam String nombreContacto,
            @RequestParam String destino) {
        Response<Long> response = new Response<>();
        Long id = admonAlertasService.saveDestinatario(tipo, nombreContacto, destino);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.CREATED.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/destinatarios")
    public ResponseEntity<Response<Long>> deleteDestinatario(@RequestBody List<Long> lista) {
        Response<Long> response = new Response<>();
        Long deletedCount = admonAlertasService.deleteDestinatario(lista);
        response.setError(false);
        response.setDatos(deletedCount);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/destinatarios")
    public ResponseEntity<Response<Long>> updateDestinatario(@RequestBody List<DestinatarioSimple> lista) {
        Response<Long> response = new Response<>();
        Long updatedCount = admonAlertasService.updateDestinatario(lista);
        response.setError(false);
        response.setDatos(updatedCount);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ruta-compartida")
    public ResponseEntity<Response<String>> getRutaCompartida() {
        Response<String> response = new Response<>();
        String ruta = admonAlertasService.getRutaCompartida();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtc-cerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        Boolean rtcCerrado = admonAlertasService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(rtcCerrado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}