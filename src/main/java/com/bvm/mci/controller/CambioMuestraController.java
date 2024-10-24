package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.EmisoraDetalle;
import com.bvm.mci.dto.TiposValor;
import com.bvm.mci.service.CambioMuestraService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cambio-muestra")
public class CambioMuestraController {

    @Autowired
    private final CambioMuestraService cambioMuestraService;

    @GetMapping("/emisiones")
    public ResponseEntity<Response<List<TiposValor>>> getEmisionesMuestraInicial() {
        Response<List<TiposValor>> response = new Response<>();
        List<TiposValor> emisiones = cambioMuestraService.getEmisionesMuestraInicial();
        response.setError(false);
        response.setDatos(emisiones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/acciones-flotantes")
    public ResponseEntity<Response<List<EmisoraDetalle>>> getAccionesFlotantes(
            @RequestBody List<EmisoraDetalle> emisoraDetalleLista) {
        Response<List<EmisoraDetalle>> response = new Response<>();
        List<EmisoraDetalle> accionesFlotantes = cambioMuestraService.getAccionesFlotantes(emisoraDetalleLista);
        response.setError(false);
        response.setDatos(accionesFlotantes);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardar-ric")
    public ResponseEntity<Response<Boolean>> guardaRIC(@RequestParam Long idEmision,
            @RequestParam Double precio,
            @RequestParam String RIC) {
        Response<Boolean> response = new Response<>();
        Boolean resultado = cambioMuestraService.guardaRIC(idEmision, precio, RIC);
        response.setError(false);
        response.setDatos(resultado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
