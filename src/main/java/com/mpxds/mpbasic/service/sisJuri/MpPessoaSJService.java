package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpPessoaSJ;
import com.mpxds.mpbasic.repository.sisJuri.MpPessoaSJs;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpPessoaSJService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpPessoaSJs mpPessoaSJs;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpPessoaSJ salvar(MpPessoaSJ mpPessoaSJ) throws MpNegocioException {
		//
		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpPessoaSJ.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpPessoaSJ.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpPessoaSJ.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpPessoaSJs.guardar(mpPessoaSJ);
	}
	
}
