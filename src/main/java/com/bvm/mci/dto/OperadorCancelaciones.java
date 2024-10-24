package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorCancelaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idMensajeEntradac;
    private Date fechaCreacionc;
    private Long folioEntradaActualc;
    private String cveEmisionc;
    private String cveSeriec;
    private Double precioc;
    private Long volumenc;
    private Double importec;
    private String transaccionc;
    private String incidenciasc;
}
