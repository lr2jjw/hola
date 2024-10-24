package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.IndiceDetalle;
import com.bvm.mci.service.CierreDetalleService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cierre-detalle")
public class CierreDetalleController {

    @Autowired
    private final CierreDetalleService cierreDetalleService;

    @GetMapping("/indices")
    public ResponseEntity<Response<List<Object>>> getIndices() {
        Response<List<Object>> response = new Response<>();
        List<Object> indices = cierreDetalleService.getIndices();
        response.setError(false);
        response.setDatos(indices);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/detalle-indice")
    public ResponseEntity<Response<Void>> setDetalleIndice(@RequestBody List<IndiceDetalle> indiceDetalles) {
        Response<Void> response = new Response<>();
        cierreDetalleService.setDetalleIndice(indiceDetalles);
        response.setError(false);
        response.setDatos(null);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/difusion")
    public ResponseEntity<Response<Void>> setDifusion(@RequestBody List<IndiceDetalle> indiceDetalles) {
        Response<Void> response = new Response<>();
        cierreDetalleService.setDifusion(indiceDetalles);
        response.setError(false);
        response.setDatos(null);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/historico/{idIndice}")
    public ResponseEntity<Response<List<Object>>> getHistoricoIndices(@PathVariable Long idIndice) {
        Response<List<Object>> response = new Response<>();
        List<Object> historicoIndices = cierreDetalleService.getHistoricoIndices(idIndice);
        response.setError(false);
        response.setDatos(historicoIndices);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
