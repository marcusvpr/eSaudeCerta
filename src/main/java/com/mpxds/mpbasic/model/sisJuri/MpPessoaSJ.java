package com.mpxds.mpbasic.model.sisJuri;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpArquivoBD;
import com.mpxds.mpbasic.model.sisJuri.MpBaseEntitySJ;
import com.mpxds.mpbasic.model.MpEnderecoLocalX;
import com.mpxds.mpbasic.model.sisJuri.MpTabelaInternaSJ;
import com.mpxds.mpbasic.model.enums.MpArquivoAcao;
import com.mpxds.mpbasic.model.enums.MpEstadoUF;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public abstract class MpPessoaSJ extends MpBaseEntitySJ {
	//
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Por favor, informe o NOME")
	@Column(nullable = false, length = 100) // , unique = true)
	@Getter @Setter
	private String nome;
	
	@Column(nullable = true)
	@Getter @Setter
	private Boolean indResponsavel;
	
	@Column(nullable = true)
	@Getter @Setter
	private Boolean indParteContraria;
	
	@Column(nullable = false, length = 50)
	@Getter @Setter
	private String oab;

	@Enumerated(EnumType.STRING)
	@Column(name = "mpOab_UF", nullable = true, length = 2)
	@Getter @Setter
	private MpEstadoUF mpOabUF;
	
	@ManyToOne
	@JoinColumn(name = "mpBanco_id", nullable = true)
	@Getter @Setter
	private MpTabelaInternaSJ mpBanco; // TAB_0015

	@Column(nullable = true, length = 10)
	@Getter @Setter
	private String bancoAgencia;
	
	@Column(nullable = true, length = 20)
	@Getter @Setter
	private String bancoConta;

	@NotBlank(message = "Por favor, informe o EMAIL")
	@Column(nullable = false, length = 255)
	@Getter @Setter
	private String email;

	@Column(nullable = true, length = 50)
	@Getter @Setter
	private String telefone;

	@Column(nullable = true, length = 255)
	@Getter @Setter
	private String webPage;
	
	@Column(nullable = true, length = 255)
	@Getter @Setter
	private String observacao;
		  
	@Embedded
	@Getter @Setter
	private MpEnderecoLocalX mpEnderecoLocalX;

	@Lob
	@Column(columnDefinition = "blob", nullable = true, length = 10000)
	@Getter @Setter
	private byte[] arquivoBD;

	@Enumerated(EnumType.STRING)
	@Column(name = "mpArquivo_acao", nullable = true, length = 15)
	@Getter @Setter
	private MpArquivoAcao mpArquivoAcao;

	@ManyToOne
	@JoinColumn(name = "mpArquivoBD_id", nullable = true)
	@Getter @Setter
	private MpArquivoBD mpArquivoBD;
	
}
