package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformacionSistemaDTO {
    private String versionMCI;
    private String usuario;
    private String nombreBase;
    private String perfil; 
    private String ambienteWepApp;
    private String ambienteRealTime;

}
