package com.mpxds.mpbasic.repository.filter.iff;

import java.io.Serializable;

import com.mpxds.mpbasic.model.enums.MpSexo;
import com.mpxds.mpbasic.model.enums.iff.MpSetorIff;
import com.mpxds.mpbasic.repository.filter.MpFilterOrdenacao;

public class MpUsuarioIffFilter implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private MpSetorIff[] mpSetorIffs;;
	private String ramal;
			
	private MpSexo mpSexo;

	private MpFilterOrdenacao mpFilterOrdenacao = new MpFilterOrdenacao();
	
	// ---

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public MpSetorIff[] getMpSetorIffs() { return mpSetorIffs; }
	public void setMpSetorIffs(MpSetorIff[] mpSetorIffs) { this.mpSetorIffs = mpSetorIffs; }

	public String getRamal() { return ramal; }
	public void setRamal(String ramal) { this.ramal = ramal; }
  	
	public MpSexo getMpSexo() {	return mpSexo; }
	public void setMpSexo(MpSexo mpSexo) { this.mpSexo = mpSexo; }
	
	// ---

	public MpFilterOrdenacao getMpFilterOrdenacao() { return mpFilterOrdenacao; }
	public void setMpFilterOrdenacao(MpFilterOrdenacao mpFilterOrdenacao) {
												this.mpFilterOrdenacao = mpFilterOrdenacao; }

}