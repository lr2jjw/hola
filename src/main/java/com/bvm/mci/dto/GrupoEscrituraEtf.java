package com.bvm.mci.dto;

import java.nio.file.attribute.GroupPrincipal;

import lombok.Data;

@Data
public class GrupoEscrituraEtf implements GroupPrincipal {

    private String name;

    @Override
    public String getName() {
        return name;
    }
}
