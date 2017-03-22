package com.mpxds.mpbasic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.mpxds.mpbasic.model.enums.MpEstadoUF;

@Embeddable
public class MpEnderecoLocalX implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Size(max = 150)
	@Column(name = "endereco_logradouro", nullable = true, length = 150)
	@Getter @Setter
	private String logradouro;

	@Size(max = 20)
	@Column(name = "endereco_numero", nullable = true, length = 20)
	@Getter @Setter
	private String numero;
	
	@Size(max = 150)
	@Column(name = "endereco_complemento", nullable = true, length = 150)
	@Getter @Setter
	private String complemento;
	
	@Size(max = 60)
	@Column(name = "endereco_cidade", nullable = true, length = 60)
	@Getter @Setter
	private String cidade;
	
	@Size(max = 60)
	@Column(name = "endereco_bairro", nullable = true, length = 60)
	@Getter @Setter
	private String bairro;
	
	@Size(max = 9)
	@Column(name = "endereco_cep", nullable = true, length = 9)
	@Getter @Setter
	private String cep;

	@Enumerated(EnumType.STRING)
	@Column(name = "endereco_estado_uf", nullable = true, length = 2)
	@Getter @Setter
	private MpEstadoUF mpEstadoUF;

}
