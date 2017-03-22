package com.mpxds.mpbasic.model.sisJuri;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_PESSOA_JURIDICA")
@PrimaryKeyJoinColumn(name="id")
@ToString
public class MpPessoaJuridica extends MpPessoaSJ {
	//
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, length = 20)
	@Getter @Setter
	private String cnpj;

	@Column(nullable = true, length = 150)
	@Getter @Setter
	private String razaoSocial;
	
	@Column(nullable = true, length = 150)
	@Getter @Setter
	private String responsavel;
	
	@Column(nullable = true, length = 150)
	@Getter @Setter
	private String ramoAtividade;

}
