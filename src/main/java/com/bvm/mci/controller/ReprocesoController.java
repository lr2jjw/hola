package com.bvm.mci.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.EmisionAcciones;
import com.bvm.mci.dto.EmisionSimple;
import com.bvm.mci.dto.IndiceSimple;
import com.bvm.mci.dto.IndiceSumatoria;
import com.bvm.mci.service.ReprocesoService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/api/reproceso")
public class ReprocesoController {
    @Autowired
    private ReprocesoService reprocesoService;

    @GetMapping("/emisiones")
    public ResponseEntity<Response<List<EmisionSimple>>> getListaEmision(@RequestParam boolean opcionRT) {
        Response<List<EmisionSimple>> response = new Response<>();
        List<EmisionSimple> emisiones = reprocesoService.getListaEmision(opcionRT);
        response.setError(false);
        response.setDatos(emisiones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/precio-emisora/{idEmision}")
    public ResponseEntity<Response<Double>> getPrecioEmisora(
            @PathVariable Long idEmision,
            @RequestParam boolean opcionRT,
            @RequestParam boolean rtCerrado) {
        Response<Double> response = new Response<>();
        Double precio = reprocesoService.getPrecioEmisora(idEmision, opcionRT, rtCerrado);
        response.setError(false);
        response.setDatos(precio);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-valores/{idEmision}")
    public ResponseEntity<Response<Boolean>> getUpdateValores(
            @PathVariable Long idEmision,
            @RequestParam Double precioNuevo,
            @RequestParam Date fecha,
            @RequestParam boolean rtCerrado,
            @RequestBody List<IndiceSumatoria> listaSumatorias) {
        Response<Boolean> response = new Response<>();
        Boolean resultado = reprocesoService.getUpdateValores(idEmision, precioNuevo, fecha, rtCerrado,
                listaSumatorias);
        response.setError(false);
        response.setDatos(resultado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/emisiones/muestra/{idMuestra}")
    public ResponseEntity<Response<List<EmisionAcciones>>> getListaEmisiones(
            @PathVariable Long idMuestra,
            @RequestParam boolean rtCerrado) {
        Response<List<EmisionAcciones>> response = new Response<>();
        List<EmisionAcciones> emisiones = reprocesoService.getListaEmisiones(idMuestra, rtCerrado);
        response.setError(false);
        response.setDatos(emisiones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ruta-compartida")
    public ResponseEntity<Response<String>> getRutaCompartida() {
        Response<String> response = new Response<>();
        String ruta = reprocesoService.getRutaCompartida();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtcerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        Boolean resultado = reprocesoService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(resultado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/indices")
    public ResponseEntity<Response<List<IndiceSimple>>> getListaIndicesEdic() {
        Response<List<IndiceSimple>> response = new Response<>();
        List<IndiceSimple> indices = reprocesoService.getListaIndicesEdic();
        response.setError(false);
        response.setDatos(indices);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/etfsdata/{idEmision}")
    public ResponseEntity<Response<List<IndiceSumatoria>>> getEtfsData(@PathVariable Long idEmision) {
        Response<List<IndiceSumatoria>> response = new Response<>();
        List<IndiceSumatoria> etfsData = reprocesoService.getEtfsData(idEmision);
        response.setError(false);
        response.setDatos(etfsData);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
