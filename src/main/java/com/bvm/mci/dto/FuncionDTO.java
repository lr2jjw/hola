package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FuncionDTO implements Serializable, Comparable<FuncionDTO> {

    private static final long serialVersionUID = -3910522029521798458L;
    private Long idFuncion;
    private String descripcion;
    private Boolean accesible;
    private Long idPermiso;

    @Override
    public int compareTo(FuncionDTO arg0) {
        return arg0.getIdFuncion().compareTo(idFuncion);
    }
}
