package com.bvm.mci.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bvm.mci.service.GruposPruebasService; 

@Service("gruposPruebasService")
public class GruposPruebasServiceImpl implements GruposPruebasService{
	public List getGrupos(){
		List grupos=new ArrayList();
		
		grupos.add("Rm");
		grupos.add("Principales");
		grupos.add("Fijos");
		
		return grupos;
	}
}
