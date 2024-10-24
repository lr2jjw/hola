package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dividendo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEmision;
    private Date fecha;
    private Double monto;
    private String claveIso;
}
