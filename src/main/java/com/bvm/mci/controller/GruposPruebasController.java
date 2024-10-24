package com.bvm.mci.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.mci.dto.GrupoIndice;
import com.bvm.mci.service.GruposPruebasService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/grupos-pruebas")
public class GruposPruebasController {
    
    @Autowired
    private final GruposPruebasService gruposPruebasService;

    @GetMapping
    public ResponseEntity<Response<List<GrupoIndice>>> getGrupos() {
        Response<List<GrupoIndice>> response = new Response<>();
        List<GrupoIndice> grupos = gruposPruebasService.getGrupos();
        response.setError(false);
        response.setDatos(grupos);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);
        return ResponseEntity.ok(response);
    }
}
