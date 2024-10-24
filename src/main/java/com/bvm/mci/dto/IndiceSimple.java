package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceSimple implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long idIndice;
    private String nombreIndice;
    private Long idAccionario;

    /**
     * Comparador para ordenar por nombreIndice.
     */
    public static final Comparator<IndiceSimple> COMPARATOR_BY_NAME = 
        Comparator.comparing(IndiceSimple::getNombreIndice);
}
