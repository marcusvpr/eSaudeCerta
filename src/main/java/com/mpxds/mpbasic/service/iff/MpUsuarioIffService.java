package com.mpxds.mpbasic.service.iff;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.mpxds.mpbasic.exception.MpNegocioException;
import com.mpxds.mpbasic.model.MpAuditoriaObjeto;
import com.mpxds.mpbasic.model.enums.iff.MpFuncionarioIff;
import com.mpxds.mpbasic.model.enums.iff.MpSetorIff;
import com.mpxds.mpbasic.model.iff.MpUsuarioIff;
import com.mpxds.mpbasic.repository.iff.MpUsuarioIffs;
import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.util.jpa.MpTransactional;

public class MpUsuarioIffService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MpUsuarioIffs mpUsuarioIffs;

	@Inject
	private MpSeguranca mpSeguranca;

	// -------------
	
	@MpTransactional
	public MpUsuarioIff salvar(MpUsuarioIff mpUsuarioIff) throws MpNegocioException {
		//
		MpUsuarioIff mpCodigoExistente = mpUsuarioIffs.porEmail(mpUsuarioIff.getEmail());
		
		if (mpCodigoExistente != null && !mpCodigoExistente.equals(mpUsuarioIff)) {
			throw new MpNegocioException("Já existe um USUÁRIO... com o EMAIL informado.");
		}
		//

		// INICIO - Trata dados auditoria ! -----------------------------------
		MpAuditoriaObjeto mpAuditoriaObjeto;
		if (null == mpUsuarioIff.getId()) { 
			mpAuditoriaObjeto = new MpAuditoriaObjeto();
			mpAuditoriaObjeto.setDtHrInc(new Date());
			mpAuditoriaObjeto.setUserInc(mpSeguranca.getLoginUsuario());
		} else {
			mpAuditoriaObjeto = mpUsuarioIff.getMpAuditoriaObjeto();
			if (null == mpAuditoriaObjeto) mpAuditoriaObjeto = new MpAuditoriaObjeto();
			mpAuditoriaObjeto.setDtHrAlt(new Date());
			mpAuditoriaObjeto.setUserAlt(mpSeguranca.getLoginUsuario());				
		}
		mpUsuarioIff.setMpAuditoriaObjeto(mpAuditoriaObjeto);
		// FIM - Trata dados auditoria ! -----------------------------------
				
		return mpUsuarioIffs.guardar(mpUsuarioIff);
	}

}
