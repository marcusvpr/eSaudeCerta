package com.mpxds.mpbasic.model.iff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpBaseEntity;
import com.mpxds.mpbasic.model.enums.iff.MpSetorIff;
import com.mpxds.mpbasic.model.enums.MpSexo;

@Entity
@Table(name = "mp_iff_usuario")
public class MpUsuarioIff extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String ramal;
			
	private MpSetorIff mpSetorIff;
	private MpSexo mpSexo;
	
	// ----------
	
		
	@NotBlank(message = "Por favor, informe o NOME")
	@Column(nullable = false, length = 100)
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	@NotBlank(message = "Por favor, informe o E-MAIL")
	@Column(nullable = false, unique = true, length = 200)
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	@NotBlank(message = "Por favor, informe a RAMAL")
	@Column(nullable = false, length = 15)
	public String getRamal() { return ramal; }
	public void setRamal(String ramal) { this.ramal = ramal; }
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 10)
	public MpSetorIff getMpSetorIff() { return mpSetorIff; }
	public void setMpSetorIff(MpSetorIff mpSetorIff) { this.mpSetorIff = mpSetorIff; }
  	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public MpSexo getMpSexo() {	return mpSexo; }
	public void setMpSexo(MpSexo mpSexo) { this.mpSexo = mpSexo; }
	
}