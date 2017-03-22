package com.mpxds.mpbasic.model.iff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Table(name = "mp_iff_area")
public class MpAreaIff extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descricao;
	
	// ----------
		
	@NotBlank(message = "Por favor, informe o CÓDIGO")
	@Column(nullable = false, unique = true, length = 10)
	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }
	
	@NotBlank(message = "Por favor, informe a DESCRIÇÃO")
	@Column(nullable = false, length = 200)
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao ; }
	
}