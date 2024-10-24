package com.bvm.mci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bvm.mci.shared.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response<Void>> handleResourceNotFoundException(BusinessException ex) {
        
        Response <Void> response = new Response<>();
        response.setError(true);
        response.setMensaje("Ocurrió un error al iniciar sesión. Consulte con el Administrador del sistema.");
        response.setCodigo(500);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleGeneralException(Exception ex) {
 
        Response <Void> response = new Response<>();
        response.setError(true);
        response.setMensaje(ex.getMessage());
        response.setCodigo(500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}