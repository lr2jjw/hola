package com.bvm.mci.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.service.ConsultaIntradiaMuestraService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(Constantes.PATH + "consulta-intradia-muestra")
public class ConsultaIntradiaMuestraController {

    private final ConsultaIntradiaMuestraService consultaIntradiaMuestraService;

    @GetMapping("/rt-cerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        boolean isClosed = consultaIntradiaMuestraService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(isClosed);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/rt-status")
    public ResponseEntity<Response<Boolean>> obtenRTStatus() {
        Response<Boolean> response = new Response<>();
        boolean status = consultaIntradiaMuestraService.obtenRTStatus();
        response.setError(false);
        response.setDatos(status);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/verifica-estado-rt")
    public ResponseEntity<Response<Void>> verificaEstadoRt() {
        Response<Void> response = new Response<>();
        consultaIntradiaMuestraService.verificaEstadoRt();
        response.setError(false);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/fin-formatos-p")
    public ResponseEntity<Response<Boolean>> esFinFormatosP() {
        Response<Boolean> response = new Response<>();
        boolean isFinFormatosP = consultaIntradiaMuestraService.esFinFormatosP();
        response.setError(false);
        response.setDatos(isFinFormatosP);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/status-user-rt")
    public ResponseEntity<Response<String>> getStatusUserRT() {
        Response<String> response = new Response<>();
        String status = consultaIntradiaMuestraService.getStatusUserRT();
        response.setError(false);
        response.setDatos(status);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/estado-realtime")
    public ResponseEntity<Response<Integer>> getEstadoRealtime() {
        Response<Integer> response = new Response<>();
        Integer estado = consultaIntradiaMuestraService.getEstadoRealtime();
        response.setError(false);
        response.setDatos(estado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);

    }
}
