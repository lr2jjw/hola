package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceNivSumRef {

    private String claveIndice;
    private Short sector;
    private Short subSector;
    private Short ramo;
    private Short subRamo;
    private Double nivelReferencia;
    private Double sumatoriaValMcdoReferencia;
}
