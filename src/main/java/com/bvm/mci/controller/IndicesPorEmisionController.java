package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceSumatoria;
import com.bvm.mci.service.IndicesPorEmisionService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/api/indices-emision")
public class IndicesPorEmisionController {

    @Autowired
    private IndicesPorEmisionService indicesPorEmisionService;

    @GetMapping("/emisiones")
    public ResponseEntity<Response<List<EmisionSimple>>> getListaEmision() {
        Response<List<EmisionSimple>> response = new Response<>();
        List<EmisionSimple> emisiones = indicesPorEmisionService.getListaEmision();
        response.setError(false);
        response.setDatos(emisiones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/precios/{idEmision}")
    public ResponseEntity<Response<List<Object>>> getPreciosEmisora(
            @PathVariable Long idEmision,
            @RequestParam boolean rtCerrado) {
        Response<List<Object>> response = new Response<>();
        List<Object> precios = indicesPorEmisionService.getPreciosEmisora(idEmision, rtCerrado);
        response.setError(false);
        response.setDatos(precios);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ruta-compartida")
    public ResponseEntity<Response<String>> getRutaCompartida() {
        Response<String> response = new Response<>();
        String ruta = indicesPorEmisionService.getRutaCompartida();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtcerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        Boolean rtCerrado = indicesPorEmisionService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(rtCerrado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/baja")
    public ResponseEntity<Response<String>> bajaArchivoIndicesPorEmision(
            @RequestParam String nombreEmision,
            @RequestParam Double precioActual,
            @RequestParam Double precioRt,
            @RequestParam Long accionesCirculacion,
            @RequestParam Double valorMercado,
            @RequestBody List<IndiceSumatoria> listaIndicesSumatorias,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String resultado = indicesPorEmisionService.bajaArchivoIndicesPorEmision(
                nombreEmision, precioActual, precioRt, accionesCirculacion, valorMercado, listaIndicesSumatorias, idSession);
        response.setError(false);
        response.setDatos(resultado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
