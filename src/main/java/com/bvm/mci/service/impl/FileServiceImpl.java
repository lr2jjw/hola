package com.bvm.mci.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bvm.mci.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private Log log = LogFactory.getLog(FileServiceImpl.class);
    
    @Override
    public String processData(String datos) throws Exception {
        // Decodificar los datos
        datos = datos.replaceAll("%C3%BA", "�");
        datos = datos.replaceAll("%C3%AD", "�");
        return datos.replace('_', '\n');
    }

    @Override
    public String readFile(String nombreArchivo) throws IOException {
        File archivo = new File(nombreArchivo);
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("El archivo no se encuentra: " + nombreArchivo, e);
        }

        return contenido.toString();
    }

    @Override
    public String readReportFile(String ruta, String nombreArchivo) throws IOException {
        File archivo = new File(ruta);
        StringBuilder contenido = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            throw new IOException("El archivo no se encuentra: " + ruta, e);
        }

        return contenido.toString();
    }

    @Override
    public void uploadFile(MultipartFile file, String rutaArchivosEtfs) throws Exception {
        String nombreETFS = file.getOriginalFilename();
        File directorioRespaldo = new File(rutaArchivosEtfs + "RespaldoETFs");

        if (directorioRespaldo.exists() || directorioRespaldo.mkdir()) {
            // Genera respaldo de archivo
            File archivo = new File(rutaArchivosEtfs, nombreETFS);
            if (archivo.exists()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd_HHmmss");
                File rutaArchivoRespaldado = new File(directorioRespaldo,
                        formato.format(new Date()) + "_" + nombreETFS);
                copyFile(archivo, rutaArchivoRespaldado);
            }

            // Guarda el archivo
            file.transferTo(archivo);
            archivo.setWritable(true);
        } else {
            throw new IOException("No se pudo crear el directorio de respaldo");
        }
    }

    @SuppressWarnings("resource")
    private void copyFile(File source, File target) throws IOException {
        try (FileChannel in = new FileInputStream(source).getChannel();
                FileChannel out = new FileOutputStream(target).getChannel()) {
            in.transferTo(0, in.size(), out);
        }
    }

    @Override
    public void uploadFiles(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            try {
                // Guardar el archivo en el sistema de archivos
                File destinationFile = new File(file.getOriginalFilename());
                file.transferTo(destinationFile);
                log.info("Archivo subido: " + destinationFile.getName());
            } catch (IOException e) {
                log.info("Error al subir el archivo: " + file.getOriginalFilename());
                log.info("Error : " + e.getMessage());
           
            }
        }
    }

}
