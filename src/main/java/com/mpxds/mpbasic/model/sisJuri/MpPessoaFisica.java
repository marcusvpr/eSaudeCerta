package com.mpxds.mpbasic.model.sisJuri;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_PESSOA_FISICA")
@PrimaryKeyJoinColumn(name="id")
@ToString
public class MpPessoaFisica extends MpPessoaSJ {
	//
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 20)
	@Getter @Setter
	private String cpf;
	
}
