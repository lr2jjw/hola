package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoIndice {

    private String idGrupo;
    private String grupoUsuario;
    private Short orden;
    private Boolean requiereClas;

}
