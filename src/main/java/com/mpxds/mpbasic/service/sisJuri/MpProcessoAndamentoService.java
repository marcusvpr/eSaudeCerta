package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpProcessoAndamento;
import com.mpxds.mpbasic.repository.sisJuri.MpProcessoAndamentos;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpProcessoAndamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpProcessoAndamentos mpProcessoAndamentos;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpProcessoAndamento salvar(MpProcessoAndamento mpProcessoAndamento) throws MpNegocioException {
		//
		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpProcessoAndamento.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpProcessoAndamento.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpProcessoAndamento.getMpAuditoriaObjetoX();
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpProcessoAndamentos.guardar(mpProcessoAndamento);
	}
	
}
