package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionAcumulados implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_emision_mci;
    private String emisora;
    private String serie;
    private Long numeroOperaciones;
    private Long volumen;
    private Double importe;
}
