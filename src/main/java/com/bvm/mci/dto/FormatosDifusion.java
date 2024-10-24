package com.bvm.mci.dto;

import com.bvm.mci.shared.Constantes;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Clase que almacena los formatos asignados a un índice.
 */
@Data 
@EqualsAndHashCode
public class FormatosDifusion {

 

    public static final String CVEFORMATODIF = "cveFormatoDif";
    public static final String DEFAULTFORMAT = "defaultFormat";

    private String cveFormatoDif;
    private Boolean defaultFormat;

    /**
     * Constructor con cveFormatoDif.
     */
    public FormatosDifusion(String cveFormatoDif) {
        this.cveFormatoDif = cveFormatoDif;
        this.defaultFormat = false;
    }

    /**
     * Constructor con cveFormatoDif y defaultFormat.
     */
    public FormatosDifusion(String cveFormatoDif, Boolean defaultFormat) {
        this.cveFormatoDif = cveFormatoDif;
        this.defaultFormat = defaultFormat;
    }

    /**
     * Constructor con asignarFormatoDefault.
     */
    public FormatosDifusion(Boolean asignarFormatoDefault) {
        this.cveFormatoDif = Constantes.FORMATO_DEFAULT_DIFUSION;
        this.defaultFormat = Constantes.BANDERA_FORMATO_DEFAULT;
    }

    @Override
    public String toString() {
        return "CveFormato: " + this.cveFormatoDif;
    }
}
