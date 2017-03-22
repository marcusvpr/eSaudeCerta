package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpPessoaJuridica;
import com.mpxds.mpbasic.repository.sisJuri.MpPessoaJuridicas;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpPessoaJuridicaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpPessoaJuridicas mpPessoaJuridicas;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpPessoaJuridica salvar(MpPessoaJuridica mpPessoaJuridica) throws MpNegocioException {
		//
		if (null == mpPessoaJuridica.getId()) {
			MpPessoaJuridica mpPessoaJuridicaExistente = mpPessoaJuridicas.porNome(mpPessoaJuridica.getNome());
		
			if (mpPessoaJuridicaExistente != null && !mpPessoaJuridicaExistente.equals(mpPessoaJuridica)) {
				throw new MpNegocioException("Já existe uma Pessoa Juridica com um NOME informado.");
			}
		}

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpPessoaJuridica.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpPessoaJuridica.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpPessoaJuridica.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpPessoaJuridicas.guardar(mpPessoaJuridica);
	}
	
}
