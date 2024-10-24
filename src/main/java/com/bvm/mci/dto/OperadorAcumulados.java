package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorAcumulados implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mercado;
    private Long sumNoOp;
    private Long sumVol;
    private Double sumImp;
    private Long sumRvAlza;
    private Long sumRvBaja;
    private Long sumRvSc;
}
