package com.bvm.mci.dto;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertasIndicesSimple {

    private Long idIndice;
    private Integer nivel;
    private Long idGrupo;
    private Double limite;
    private String nombreIndice;
    private String nombreGrupo;

    // Constants for field names
    public static final String IDINDICE = "idIndice";
    public static final String NIVEL = "nivel";
    public static final String IDGRUPO = "idGrupo";
    public static final String LIMITE = "limite";
    public static final String NOMBREINDICE = "nombreIndice";
    public static final String NOMBREGRUPO = "nombreGrupo";
}