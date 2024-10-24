package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.DiferenciaSimple;
import com.bvm.mci.service.ValidaOperacionService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/api/operacion")
public class ValidaOperacionController {

    @Autowired
    private ValidaOperacionService validaOperacionService;

    /**
     * Endpoint para consultar el estado de la operación.
     *
     * @return estado de la operación.
     */
    @GetMapping("/estado")
    public ResponseEntity<Response<Boolean>> validaOperacion() {
        Response<Boolean> response = new Response<>();
        boolean estado = validaOperacionService.validaOperacion();
        response.setError(false);
        response.setDatos(estado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para actualizar el estado de la operación a cerrado.
     *
     * @return respuesta del servidor.
     */
    @PostMapping("/cerrar")
    public ResponseEntity<Response<Void>> actualizaOperacion() {
        Response<Void> response = new Response<>();
        validaOperacionService.actualizaOperacion();
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para tomar un snapshot al cierre.
     *
     * @return respuesta del servidor.
     */
    @PostMapping("/snapshot")
    public ResponseEntity<Response<Void>> tomaSnapShotAlCierre() {
        Response<Void> response = new Response<>();
        validaOperacionService.tomaSnapShotAlCierre();
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para enviar un mensaje para ignorar diferencias.
     *
     * @param lista lista de diferencias a ignorar.
     * @return respuesta del servidor.
     */
    @PostMapping("/ignorar-diferencias")
    public ResponseEntity<Response<Void>> enviaMensajeIgnorarDiferencias(@RequestBody List<DiferenciaSimple> lista) {
        Response<Void> response = new Response<>();
        validaOperacionService.enviaMensajeIgnorarDiferencias(lista);
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
