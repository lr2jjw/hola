package com.bvm.mci.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.mci.dto.AjusteDerechoKey;
import com.bvm.mci.dto.ComboBoxValues;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/catalogos/api")
public class CatalogosController {
    @GetMapping("/country-codes")
    public ResponseEntity<Response<List<String[]>>> getCountryCodes() {
        Response<List<String[]>> response = new Response<>();

        List<String[]> countryCodes = Constantes.SISOCOUNTRYCODES;

        response.setError(false);
        response.setDatos(countryCodes);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/role-full-ventanas-ids")
    public ResponseEntity<Response<List<String>>> getRoleFullVentanasIds() {
        Response<List<String>> response = new Response<>();

        List<String> fullVentanasIds = Constantes.ROLE_FULL_VENTANAS_IDS;

        response.setError(false);
        response.setDatos(fullVentanasIds);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/role-monitoreo-ventanas-ids")
    public ResponseEntity<Response<List<String>>> getRoleMonitoreoVentanasIds() {
        Response<List<String>> response = new Response<>();

        List<String> monitoreoVentanasIds = Constantes.ROLE_MONITOREO_VENTANAS_IDS;

        response.setError(false);
        response.setDatos(monitoreoVentanasIds);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/proceso-ajuste-precio")
    public ResponseEntity<Response<Map<AjusteDerechoKey, List<Long>>>> getProcesoAjustePrecio() {
        Response<Map<AjusteDerechoKey, List<Long>>> response = new Response<>();

        Map<AjusteDerechoKey, List<Long>> procesoAjustePrecio = Constantes.PROCESO_AJUSTE_PRECIO;

        response.setError(false);
        response.setDatos(procesoAjustePrecio);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/permisos-codes")
    public ResponseEntity<Response<List<ComboBoxValues>>> getPermisosCodes() {
        Response<List<ComboBoxValues>> response = new Response<>();

        List<ComboBoxValues> permisosCodes = Constantes.SPERMISOSCODES;

        response.setError(false);
        response.setDatos(permisosCodes);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipos-destinatarios")
    public ResponseEntity<Response<List<ComboBoxValues>>> getTiposDestinatarios() {
        Response<List<ComboBoxValues>> response = new Response<>();

        List<ComboBoxValues> tiposDestinatarios = Constantes.TIPOSDESTINATARIOS;

        response.setError(false);
        response.setDatos(tiposDestinatarios);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipos-destinatarios-alertas")
    public ResponseEntity<Response<List<ComboBoxValues>>> getTiposDestinatariosAlertas() {
        Response<List<ComboBoxValues>> response = new Response<>();

        List<ComboBoxValues> tiposDestinatariosAlertas = Constantes.TIPOSDESTINATARIOS_ALERTAS;

        response.setError(false);
        response.setDatos(tiposDestinatariosAlertas);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipos-destinatarios-consulta")
    public ResponseEntity<Response<List<String>>> getTiposDestinatariosConsulta() {
        Response<List<String>> response = new Response<>();

        List<String> tiposDestinatariosConsulta = Constantes.TIPOSDESTINATARIOS_CONSULTA;

        response.setError(false);
        response.setDatos(tiposDestinatariosConsulta);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tipos-destinatarios-consulta-alertas")
    public ResponseEntity<Response<List<String>>> getTiposDestinatariosConsultaAlertas() {
        Response<List<String>> response = new Response<>();

        List<String> tiposDestinatariosConsultaAlertas = Constantes.TIPOSDESTINATARIOS_CONSULTA_ALERTAS;

        response.setError(false);
        response.setDatos(tiposDestinatariosConsultaAlertas);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/plantillas-excuidas")
    public ResponseEntity<Response<List<Long>>> getPlantillasExcuidas() {
        Response<List<Long>> response = new Response<>();

        List<Long> plantillasExcuidas = Constantes.LISTAPLANTILLASEXCUIDAS;

        response.setError(false);
        response.setDatos(plantillasExcuidas);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/niveles-alertas")
    public ResponseEntity<Response<List<ComboBoxValues>>> getNivelesAlertas() {
        Response<List<ComboBoxValues>> response = new Response<>();

        List<ComboBoxValues> nivelesAlertas = Constantes.LISTANIVELESALERTAS;

        response.setError(false);
        response.setDatos(nivelesAlertas);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);
    }
}
