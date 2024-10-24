package com.bvm.mci.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActividadesPerfil implements Serializable, Comparable<ActividadesPerfil> {

    private Long idPerfil;
    private Long idPermiso;
    private Long idActividad;
    private String descripcionActividad;
    private String permitido;

    @Override
    public int compareTo(ActividadesPerfil o) {
        return o.getIdActividad().compareTo(idActividad);
    }
    //private String filtros;
   // private List<String> filtrosLista;
 
}
