package com.bvm.mci.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bvm.mci.dto.ArchivoEtfSimple;
import com.bvm.mci.dto.EmisionIntradiaEtf;
import com.bvm.mci.dto.IndiceIntradiaEtf;
import com.bvm.mci.dto.RealtimeOperacion;
import com.bvm.mci.dto.TasaDividendosSimple;
import com.bvm.mci.service.ConsultaIntradiaEtfService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/consulta-intradia-etf")
public class ConsultaIntradiaEtfController {
 
    private final ConsultaIntradiaEtfService consultaIntradiaEtfService;

    @GetMapping("/indices")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getIndicesEtf() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> indices = consultaIntradiaEtfService.getIndicesEtf();
        response.setError(false);
        response.setDatos(indices);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/muestra/{idEtf}")
    public ResponseEntity<Response<List<EmisionIntradiaEtf>>> getMuestraEtf(@PathVariable Long idEtf) {
        Response<List<EmisionIntradiaEtf>> response = new Response<>();
        List<EmisionIntradiaEtf> muestra = consultaIntradiaEtfService.getMuestraEtf(idEtf);
        response.setError(false);
        response.setDatos(muestra);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtcerrado")
    public ResponseEntity<Response<Boolean>> obtenRTCerrado() {
        Response<Boolean> response = new Response<>();
        Boolean cerrado = consultaIntradiaEtfService.obtenRTCerrado();
        response.setError(false);
        response.setDatos(cerrado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rtstatus")
    public ResponseEntity<Response<Boolean>> obtenRTStatus() {
        Response<Boolean> response = new Response<>();
        Boolean status = consultaIntradiaEtfService.obtenRTStatus();
        response.setError(false);
        response.setDatos(status);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ruta-compartida")
    public ResponseEntity<Response<String>> getRutaCompartida() {
        Response<String> response = new Response<>();
        String ruta = consultaIntradiaEtfService.getRutaCompartida();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ruta-compartida-etfs")
    public ResponseEntity<Response<String>> getRutaCompartidaETFs() {
        Response<String> response = new Response<>();
        String ruta = consultaIntradiaEtfService.getRutaCompartidaETFs();
        response.setError(false);
        response.setDatos(ruta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/realtime-operacion")
    public ResponseEntity<Response<RealtimeOperacion>> getRealtimeOperacion() {
        Response<RealtimeOperacion> response = new Response<>();
        RealtimeOperacion operacion = consultaIntradiaEtfService.getRealtimeOperacion();
        response.setError(false);
        response.setDatos(operacion);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/emisiones")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEmisionesEtf() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> emisiones = consultaIntradiaEtfService.getEmisionesEtf();
        response.setError(false);
        response.setDatos(emisiones);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Response<Long>> guardaETF(@RequestBody IndiceIntradiaEtf etf) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.guardaETF(etf);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.CREATED.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/consolidar")
    public ResponseEntity<Response<Long>> consolidarEtf(@RequestBody IndiceIntradiaEtf etf) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.consolidarEtf(etf);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEtfActivos() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> activos = consultaIntradiaEtfService.getEtfActivos();
        response.setError(false);
        response.setDatos(activos);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos/sin-baja")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEtfActivosSinBaja() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> activosSinBaja = consultaIntradiaEtfService.getEtfActivosSinBaja();
        response.setError(false);
        response.setDatos(activosSinBaja);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos/en-baja")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEtfActivosEnBaja() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> activosEnBaja = consultaIntradiaEtfService.getEtfActivosEnBaja();
        response.setError(false);
        response.setDatos(activosEnBaja);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos/pendientes")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEtfActivosYPendientes() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> activosPendientes = consultaIntradiaEtfService.getEtfActivosYPendientes();
        response.setError(false);
        response.setDatos(activosPendientes);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/baja-logica/{idEtf}")
    public ResponseEntity<Response<Boolean>> bajaLogicaETF(@PathVariable Long idEtf) {
        Response<Boolean> response = new Response<>();
        boolean result = consultaIntradiaEtfService.bajaLogicaETF(idEtf);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reactivar/{idEtf}")
    public ResponseEntity<Response<Boolean>> reactivarETF(@PathVariable Long idEtf) {
        Response<Boolean> response = new Response<>();
        boolean result = consultaIntradiaEtfService.reactivarETF(idEtf);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/catalogo-archivos")
    public ResponseEntity<Response<List<ArchivoEtfSimple>>> getCatalogoArchivosEtf() {
        Response<List<ArchivoEtfSimple>> response = new Response<>();
        List<ArchivoEtfSimple> catalogo = consultaIntradiaEtfService.getCatalogoArchivosEtf();
        response.setError(false);
        response.setDatos(catalogo);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/catalogo-archivo/{idEtf}")
    public ResponseEntity<Response<Long>> getIdCatArchivoPorIdEtf(@PathVariable Long idEtf) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.getIdCatArchivoPorIdEtf(idEtf);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Response<Long>> actualizaETF(@RequestBody IndiceIntradiaEtf etf) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.actualizaETF(etf);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasa-dividendos")
    public ResponseEntity<Response<TasaDividendosSimple>> consultaTasaDividendos() {
        Response<TasaDividendosSimple> response = new Response<>();
        TasaDividendosSimple tasa = consultaIntradiaEtfService.consultaTasaDividendos();
        response.setError(false);
        response.setDatos(tasa);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/tasa-dividendos/actualizar")
    public ResponseEntity<Response<Long>> actualizaTasaDividendos(@RequestBody TasaDividendosSimple tasaDividendosSimple) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.actualizaTasaDividendos(tasaDividendosSimple);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mensaje/rmi/carga")
    public ResponseEntity<Response<Boolean>> enviaMensajeRMIenCargaYReprocesoEtf(@RequestParam String idEtfSeleccionado, 
                                                                                    @RequestParam String idArchivoDeEtfs, 
                                                                                    @RequestParam Boolean isDelay) {
        Response<Boolean> response = new Response<>();
        boolean result = consultaIntradiaEtfService.enviaMensajeRMIenCargaYReprocesoEtf(idEtfSeleccionado, idArchivoDeEtfs, isDelay);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mensaje/rmi/difusion")
    public ResponseEntity<Response<Boolean>> enviaMensajeRMIenDifusionEtf(@RequestBody IndiceIntradiaEtf etf) {
        Response<Boolean> response = new Response<>();
        boolean result = consultaIntradiaEtfService.enviaMensajeRMIenDifusionEtf(etf);
        response.setError(false);
        response.setDatos(result);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fin-formatos-p")
    public ResponseEntity<Response<Boolean>> esFinFormatosP() {
        Response<Boolean> response = new Response<>();
        boolean fin = consultaIntradiaEtfService.esFinFormatosP();
        response.setError(false);
        response.setDatos(fin);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cerrado/{idEtf}")
    public ResponseEntity<Response<Boolean>> esEtfCerrado(@PathVariable Long idEtf) {
        Response<Boolean> response = new Response<>();
        boolean cerrado = consultaIntradiaEtfService.esEtfCerrado(idEtf);
        response.setError(false);
        response.setDatos(cerrado);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activos/con-defectos")
    public ResponseEntity<Response<List<IndiceIntradiaEtf>>> getEtfActivosYConDefectos() {
        Response<List<IndiceIntradiaEtf>> response = new Response<>();
        List<IndiceIntradiaEtf> activosConDefectos = consultaIntradiaEtfService.getEtfActivosYConDefectos();
        response.setError(false);
        response.setDatos(activosConDefectos);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/archivos/cache/{idEtfArchivos}")
    public ResponseEntity<Response<Long>> sendArchivosCache(@PathVariable Long idEtfArchivos) {
        Response<Long> response = new Response<>();
        Long id = consultaIntradiaEtfService.sendArchivosCache(idEtfArchivos);
        response.setError(false);
        response.setDatos(id);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
  }
