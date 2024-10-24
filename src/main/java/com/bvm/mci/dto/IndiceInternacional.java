package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndiceInternacional {

    private String nombreEmision;
    private Float numAcciones;
    private Float precio;
    private String ric;
    private String pais;
}
