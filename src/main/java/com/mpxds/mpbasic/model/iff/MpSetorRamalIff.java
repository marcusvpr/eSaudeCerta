package com.mpxds.mpbasic.model.iff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Table(name = "mp_iff_setor_ramal")
public class MpSetorRamalIff extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;

	private MpAreaIff mpAreaIff;
	
	private String descricao;
	private String ramal1;
	private String ramal2;
	private String ramal3;
	
	// ----------
	
	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpAreaId")
	public MpAreaIff getMpAreaIff() { return mpAreaIff; }
	public void setMpAreaIff(MpAreaIff mpAreaIff) { this.mpAreaIff = mpAreaIff; }
	
	@NotBlank(message = "Por favor, informe a DESCRIÇÃO")
	@Column(nullable = false, unique = true, length = 200)
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }

	@NotBlank(message = "Por favor, informe a RAMAL(1)")
	@Column(nullable = false, length = 10)
	public String getRamal1() { return ramal1; }
	public void setRamal1(String ramal1) { this.ramal1 = ramal1; }

	@NotBlank(message = "Por favor, informe a RAMAL(2)")
	@Column(nullable = false, length = 10)
	public String getRamal2() { return ramal2; }
	public void setRamal2(String ramal2) { this.ramal2 = ramal2; }

	@NotBlank(message = "Por favor, informe a RAMAL(1)")
	@Column(nullable = false, length = 10)
	public String getRamal3() { return ramal3; }
	public void setRamal3(String ramal3) { this.ramal3 = ramal3; }
	
}