package com.mpxds.mpbasic.model.sisJuri;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpArquivoBD;
import com.mpxds.mpbasic.model.sisJuri.MpBaseEntitySJ;
import com.mpxds.mpbasic.model.MpEnderecoLocalX;
import com.mpxds.mpbasic.model.enums.MpArquivoAcao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_CLIENTE")
@ToString
public class MpClienteSJ extends MpBaseEntitySJ {
	//
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Por favor, informe o NOME")
	@Column(nullable = false, length = 100, unique = true)
	@Getter @Setter
	private String nome;

	@Column(nullable = false, length = 20)
	@Getter @Setter
	private String cpfCnpj;

	@Column(nullable = true)
	@Getter @Setter
	private Boolean indParteContraria;
	
	@Column(nullable = true, length = 255)
	@Getter @Setter
	private String email;

	@Column(nullable = true, length = 50)
	@Getter @Setter
	private String telefone;

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
