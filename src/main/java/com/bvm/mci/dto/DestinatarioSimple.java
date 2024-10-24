package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DestinatarioSimple implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idDestinatario;
    private String tipo;
    private String nombreContacto;
    private String destino;
}
