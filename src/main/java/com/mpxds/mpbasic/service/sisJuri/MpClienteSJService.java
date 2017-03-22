package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpClienteSJ;
import com.mpxds.mpbasic.repository.sisJuri.MpClienteSJs;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpClienteSJService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpClienteSJs mpClienteSJs;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpClienteSJ salvar(MpClienteSJ mpClienteSJ) throws MpNegocioException {
		//
		if (null == mpClienteSJ.getId()) {
			MpClienteSJ mpClienteSJExistente = mpClienteSJs.porNome(mpClienteSJ.getNome());
		
			if (mpClienteSJExistente != null && !mpClienteSJExistente.equals(mpClienteSJ)) {
				throw new MpNegocioException("Já existe um CLIENTE com um NOME informado.");
			}
		}
		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpClienteSJ.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpClienteSJ.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpClienteSJ.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpClienteSJs.guardar(mpClienteSJ);
	}
	
}
