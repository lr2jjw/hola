package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtimeOperacion {
    private Integer estadoRealtime;
    private boolean isActivo;
    private String estadoUsuarioRealtime;
    private boolean isCerrado;
}
