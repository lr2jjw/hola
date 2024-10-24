package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorAcumulado implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OperadorAcumulados> lista;
    private List<OperadorAcumMonet> listaMonet;
}
