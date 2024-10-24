package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperadorAcumMonet {

    private Long numeroOperaciones;
    private Long volumenOperado;
    private Double importeOperado;
}
