package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpPessoaFisica;
import com.mpxds.mpbasic.repository.sisJuri.MpPessoaFisicas;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpPessoaFisicaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpPessoaFisicas mpPessoaFisicas;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpPessoaFisica salvar(MpPessoaFisica mpPessoaFisica) throws MpNegocioException {
		//
		if (null == mpPessoaFisica.getId()) {
			MpPessoaFisica mpPessoaFisicaExistente = mpPessoaFisicas.porNome(mpPessoaFisica.getNome());
		
			if (mpPessoaFisicaExistente != null && !mpPessoaFisicaExistente.equals(mpPessoaFisica)) {
				throw new MpNegocioException("Já existe uma Pessoa Fisica com um NOME informado.");
			}
		}

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpPessoaFisica.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpPessoaFisica.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpPessoaFisica.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpPessoaFisicas.guardar(mpPessoaFisica);
	}
	
}
