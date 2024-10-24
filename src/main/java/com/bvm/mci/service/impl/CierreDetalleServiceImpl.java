package com.bvm.mci.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.bvm.mci.dto.IndiceDetalle;
import com.bvm.mci.service.CierreDetalleService;

 



@Service
@Lazy
@Transactional
public class CierreDetalleServiceImpl  implements CierreDetalleService{

	@Override
	public List getHistoricoIndices(Long idIndice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getIndices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDetalleIndice(List<IndiceDetalle> indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDifusion(List<IndiceDetalle> indice) {
		// TODO Auto-generated method stub
		
	}

	


	
	

}
