package com.bvm.mci.dto;

import java.io.Serializable;
import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MuestraAjuste implements Serializable, Comparator<MuestraAjuste> {
    private static final long serialVersionUID = 1L;

    private Long idMuestra;
    private String nombre;

    @Override
    public int compare(MuestraAjuste o1, MuestraAjuste o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }
}
