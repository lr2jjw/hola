package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emision implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emisora;
    private String serie;
}
