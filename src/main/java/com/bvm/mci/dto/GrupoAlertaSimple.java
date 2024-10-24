package com.bvm.mci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrupoAlertaSimple {

   

    public static final String IDGRUPOALERTA = "idGrupoAlerta";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String IDDESTINATARIO = "idDestinatario";
    public static final String TIPO = "tipo";
    public static final String NOMBRECONTACTO = "nombreContacto";
    public static final String DESTINO = "destino";

    private Long idGrupoAlerta;
    private String nombre;
    private String descripcion;
    private Long idDestinatario;
    private String tipo;
    private String nombreContacto;
    private String destino;

    public GrupoAlertaSimple(Long idGrupoAlerta, String nombre, String descripcion) {
        this.idGrupoAlerta = idGrupoAlerta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idDestinatario = null;
        this.tipo = null;
        this.nombreContacto = null;
        this.destino = null;
    }
}
