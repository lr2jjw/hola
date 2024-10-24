package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmisionCierreHistorico implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emisora;
    private String serie;
    private Date fecha1;
    private Double precioMXN1;
    private Double precioUSD1;
    private Double exRate1;
}
