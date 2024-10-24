package com.bvm.mci.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bvm.mci.service.FileService;
import com.bvm.mci.shared.Constantes;
import com.bvm.mci.shared.Response;

@RestController
@RequestMapping("/download/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file1")
    public ResponseEntity<String> downloadFile(@RequestParam String datos) throws Exception {
        String processedData = fileService.processData(datos);
        byte[] bytes = processedData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"mcidata.csv\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(new String(bytes), headers, HttpStatus.OK);
    }

    @GetMapping("/mifileservlet2")
    public ResponseEntity<String> downloadFile2(@RequestParam String nombre) throws IOException {

        String fileContent = fileService.readFile(nombre);

        byte[] bytes = fileContent.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombre + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(new String(bytes), headers, HttpStatus.OK);

    }

    @GetMapping("/reporte")
    public ResponseEntity<String> getReport(@RequestParam String ruta, @RequestParam String nombre) throws IOException {

        String content = fileService.readReportFile(ruta, nombre);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + nombre + ".txt\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(new String(bytes), headers, HttpStatus.OK);

    }

    @PostMapping("/etfupload")
    public ResponseEntity<Response<Void>> etfUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("ruta") String rutaArchivosEtfs) throws Exception {
        Response<Void> response = new Response<>();

        fileService.uploadFile(file, rutaArchivosEtfs);

        response.setError(false);
        response.setDatos(null);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<Response<Void>> multiplesArchivos(@RequestParam("files") List<MultipartFile> files)
            throws Exception {
        Response<Void> response = new Response<>();

        fileService.uploadFiles(files);

        response.setError(false);
        response.setDatos(null);
        response.setCodigo(HttpStatus.OK.value());
        response.setMensaje(Constantes.EXITO);

        return ResponseEntity.ok(response);

    }

}