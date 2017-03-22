package com.mpxds.mpbasic.service.pt05;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjeto;
import com.mpxds.mpbasic.model.pt05.MpAto;
import com.mpxds.mpbasic.repository.pt05.MpAtos;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpAtoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpAtos mpAtos;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpAto salvar(MpAto mpAto) throws MpNegocioException {
		//
		if (null == mpAto.getId()) {
			//
			MpAto mpCodigoExistente = mpAtos.porCodigoSequencia(mpAto.getCodigo(), mpAto.getSequencia());
			
			if (mpCodigoExistente != null && !mpCodigoExistente.equals(mpAtos)) {
				throw new MpNegocioException("Já existe um ATO/SEQUÊNCIA... com os CÓDIGOs informados.");
			}
		}

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjeto mpAuditoriaObjeto;
		if (null == mpAto.getId()) { 
			mpAuditoriaObjeto = new MpAuditoriaObjeto();
			mpAuditoriaObjeto.setDtHrInc(new Date());
			mpAuditoriaObjeto.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjeto = mpAto.getMpAuditoriaObjeto();
			if (null == mpAuditoriaObjeto) mpAuditoriaObjeto = new MpAuditoriaObjeto();
			mpAuditoriaObjeto.setDtHrAlt(new Date());
			mpAuditoriaObjeto.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpAto.setMpAuditoriaObjeto(mpAuditoriaObjeto);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpAtos.guardar(mpAto);
	}
	
}
