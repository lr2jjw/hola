package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorBitacoraRecibidos implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idMensaje;
    private String formato;
    private String contenido;
    private String fechaHora;
}
