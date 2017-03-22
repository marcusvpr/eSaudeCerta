package com.mpxds.mpbasic.service.sisJuri;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;
import com.mpxds.mpbasic.model.sisJuri.MpTabelaInternaSJ;
import com.mpxds.mpbasic.repository.sisJuri.MpTabelaInternaSJs;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpTabelaInternaSJServiceX implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpTabelaInternaSJs mpTabelaInternaSJs;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpTabelaInternaSJ salvar(MpTabelaInternaSJ mpTabelaInternaSJ) throws MpNegocioException {
		MpTabelaInternaSJ mpTabelaInternaSJExistente =
			mpTabelaInternaSJs.porMpNumeroCodigo(mpTabelaInternaSJ.getMpTipoTabelaInternaSJ(), 
																	mpTabelaInternaSJ.getCodigo());
		
		if (mpTabelaInternaSJExistente != null && !mpTabelaInternaSJExistente.equals(
																			mpTabelaInternaSJ)) {
			throw new MpNegocioException("Já existe um registro com o Tipo Tabela/Código informado.");
		}

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjetoX mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
		
		if (null == mpTabelaInternaSJ.getId()) { 
			mpAuditoriaObjetoX.setDtHrInc(new Date());
			mpAuditoriaObjetoX.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjetoX = mpTabelaInternaSJ.getMpAuditoriaObjetoX();
			if (null == mpAuditoriaObjetoX) mpAuditoriaObjetoX = new MpAuditoriaObjetoX();
			mpAuditoriaObjetoX.setDtHrAlt(new Date());
			mpAuditoriaObjetoX.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpTabelaInternaSJ.setMpAuditoriaObjetoX(mpAuditoriaObjetoX);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpTabelaInternaSJs.guardar(mpTabelaInternaSJ);
	}

}
