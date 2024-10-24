package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monedas implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idMoneda;
    private String descripcion;
    private Date fechaCreacion;
    private String creadoPor;
    private String sistema;
    private Date fechaModificacion;
    private String modificadoPor;
    private String codigoISO;
    private String operada;
    private Long operaEmisoras;
    private Long activoEmisnet;
}
