package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpProcesso;
import com.mpxds.mpbasic.repository.sisJuri.MpProcessos;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpProcessoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpProcessos mpProcessos;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpProcesso salvar(MpProcesso mpProcesso) throws MpNegocioException {
		//
//		MpProcesso mpProcessoExistente = mpProcessos.porDataCadastro(mpProcesso.getDataCadastro());
//		
//		if (mpProcessoExistente != null && !mpProcessoExistente.equals(mpProcesso)) {
//			throw new MpNegocioException("Já existe um PROCESSO com a DATA informada.");
//		}

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpProcesso.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpProcesso.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpProcesso.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpProcessos.guardar(mpProcesso);
	}
	
}
