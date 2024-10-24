package com.bvm.mci.controller;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.OperadorAcumMonet;
import com.bvm.mci.dto.OperadorAcumulado;
import com.bvm.mci.dto.OperadorAcumulados;
import com.bvm.mci.dto.OperadorBitacoraRecibidos;
import com.bvm.mci.dto.OperadorCancelaciones;
import com.bvm.mci.dto.OperadorConteoFormatos;
import com.bvm.mci.dto.OperadorPrecisionPrecAcc;
import com.bvm.mci.dto.OperadorPrecuadre;
import com.bvm.mci.service.OperadorService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/api/operador")
public class OperadorController {
    @Autowired
    private OperadorService operadorService;

   
   
    @GetMapping("/etfs")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getListaEtfs() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> etfs = operadorService.getListaEtfs();
        response.setError(false);
        response.setDatos(etfs);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/descargaMensajesEnviados")
    public ResponseEntity<Response<String>> descargaMensajesEnviados(
            @RequestParam String instrumento,
            @RequestParam String formatos,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.descargaMensajesEnviados(instrumento, formatos, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/conteoFormatos")
    public ResponseEntity<Response<List<OperadorConteoFormatos>>> obtenConteoFormatos() {
        Response<List<OperadorConteoFormatos>> response = new Response<>();
        List<OperadorConteoFormatos> conteoFormatos = operadorService.obtenConteoFormatos();
        response.setError(false);
        response.setDatos(conteoFormatos);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancelaciones")
    public ResponseEntity<Response<List<OperadorCancelaciones>>> obtenCancelaciones() {
        Response<List<OperadorCancelaciones>> response = new Response<>();
        List<OperadorCancelaciones> cancelaciones = operadorService.obtenCancelaciones();
        response.setError(false);
        response.setDatos(cancelaciones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/acumulados")
    public ResponseEntity<Response<List<OperadorAcumulados>>> obtenAcumulados() {
        Response<List<OperadorAcumulados>> response = new Response<>();
        List<OperadorAcumulados> acumulados = operadorService.obtenAcumulados();
        response.setError(false);
        response.setDatos(acumulados);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/operacionesXM")
    public ResponseEntity<Response<Integer>> obtenOperacionesXM() {
        Response<Integer> response = new Response<>();
        Integer operacionesXM = operadorService.obtenOperacionesXM();
        response.setError(false);
        response.setDatos(operacionesXM);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/precisionPrecAcc")
    public ResponseEntity<Response<List<OperadorPrecisionPrecAcc>>> obtenPrecisionPrecAcc() {
        Response<List<OperadorPrecisionPrecAcc>> response = new Response<>();
        List<OperadorPrecisionPrecAcc> precision = operadorService.obtenPrecisionPrecAcc();
        response.setError(false);
        response.setDatos(precision);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/acumMonet")
    public ResponseEntity<Response<List<OperadorAcumMonet>>> obtenAcumMonet() {
        Response<List<OperadorAcumMonet>> response = new Response<>();
        List<OperadorAcumMonet> acumMonet = operadorService.obtenAcumMonet();
        response.setError(false);
        response.setDatos(acumMonet);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bitacoraRecibidos")
    public ResponseEntity<Response<List<OperadorBitacoraRecibidos>>> obtenBitacoraRecibidos(
            @RequestParam String contenido,
            @RequestParam String formatos,
            @RequestParam String tipoOperacion,
            @RequestParam String tipoConcertacion) {
        Response<List<OperadorBitacoraRecibidos>> response = new Response<>();
        List<OperadorBitacoraRecibidos> bitacora = operadorService.obtenBitacoraRecibidos(contenido, formatos, tipoOperacion, tipoConcertacion);
        response.setError(false);
        response.setDatos(bitacora);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bitacoraEnviados")
    public ResponseEntity<Response<List<OperadorBitacoraRecibidos>>> obtenBitacoraEnviados(
            @RequestParam String contenido,
            @RequestParam String formatos,
            @RequestParam String tipoOperacion,
            @RequestParam String tipoConcertacion) {
        Response<List<OperadorBitacoraRecibidos>> response = new Response<>();
        List<OperadorBitacoraRecibidos> bitacora = operadorService.obtenBitacoraEnviados(contenido, formatos, tipoOperacion, tipoConcertacion);
        response.setError(false);
        response.setDatos(bitacora);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoConteoFormatos")
    public ResponseEntity<Response<String>> guardaArchivoConteoFormatos(
            @RequestBody List<OperadorConteoFormatos> lista,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoConteoFormatos(lista, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoCancelaciones")
    public ResponseEntity<Response<String>> guardaArchivoCancelaciones(
            @RequestBody List<OperadorCancelaciones> lista,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoCancelaciones(lista, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoAcumulados")
    public ResponseEntity<Response<String>> guardaArchivoAcumulados(
            @RequestBody  OperadorAcumulado lista ,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoAcumulados(lista.getLista(), lista.getListaMonet(), nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoPrecisionPrecAcc")
    public ResponseEntity<Response<String>> guardaArchivoPrecisionPrecAcc(
            @RequestBody List<OperadorPrecisionPrecAcc> lista,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoPrecisionPrecAcc(lista, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoPrecuadre")
    public ResponseEntity<Response<String>> guardaArchivoPrecuadre(
            @RequestBody List<OperadorPrecuadre> lista,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoPrecuadre(lista, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoBitacoraRecibidos")
    public ResponseEntity<Response<String>> guardaArchivoBitacoraRecibidos(
            @RequestParam String contenido,
            @RequestParam String formatos,
            @RequestParam String tipoOperacion,
            @RequestParam String tipoConcertacion,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoBitacoraRecibidos(contenido, formatos, tipoOperacion, tipoConcertacion, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardaArchivoBitacoraEnviados")
    public ResponseEntity<Response<String>> guardaArchivoBitacoraEnviados(
            @RequestParam String contenido,
            @RequestParam String formatos,
            @RequestParam String nombre,
            @RequestParam String idSession) {
        Response<String> response = new Response<>();
        String result = operadorService.guardaArchivoBitacoraEnviados(contenido, formatos, nombre, idSession);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rutaCompartida")
    public ResponseEntity<Response<String>> getRutaCompartida() {
        Response<String> response = new Response<>();
        String ruta = operadorService.getRutaCompartida();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
