package com.bvm.mci.dto;

import java.io.Serializable;

import lombok.Data;

 
@Data  
public class TasaDividendosSimple implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double valor;
    private Double valorAnterior;
    private String usuario;


    public TasaDividendosSimple() {
		super();
	}
		
	public TasaDividendosSimple(Long id, Double valor, Double valorAnterior) {
		super();
		this.id = id;
		this.valor = valor;
		this.valorAnterior = valorAnterior;
	}
	


	public TasaDividendosSimple(Long id, Double valor, Double valorAnterior, String usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.valorAnterior = valorAnterior;
		this.usuario = usuario;
	}

	public TasaDividendosSimple(Long id, Double valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

}
