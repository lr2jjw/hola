package com.bvm.mci.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String processData(String datos) throws Exception;

    String readFile(String nombreArchivo) throws IOException;

    String readReportFile(String ruta, String nombreArchivo) throws IOException;

    void uploadFile(MultipartFile file, String rutaArchivosEtfs) throws Exception;

    void uploadFiles(List<MultipartFile> files);
}
