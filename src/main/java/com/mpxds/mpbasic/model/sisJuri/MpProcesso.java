package com.mpxds.mpbasic.model.sisJuri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.sisJuri.MpBaseEntitySJ;
import com.mpxds.mpbasic.model.sisJuri.MpTabelaInternaSJ;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_PROCESSO")
@ToString
public class MpProcesso extends MpBaseEntitySJ {
	//
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false, length = 10) // , unique = true
	@Getter @Setter
	private Date dataCadastro;
	
	@NotBlank(message = "Código é obrigatório")
 	@Column(name = "processo_codigo", nullable = true, length = 30)
	@Getter @Setter
	private String processoCodigo;

	@NotBlank(message = "Autor é obrigatório")
 	@Column(nullable = true, length = 150)
	@Getter @Setter
	private String autor;
	
	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpParte_Contraria_id")
	@Getter @Setter
	private MpPessoaSJ mpParteContraria;

	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpCliente_id")
	@Getter @Setter
	private MpClienteSJ mpClienteSJ;

	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpAdvogado_Responsavel_id")
	@Getter @Setter
	private MpPessoaSJ mpAdvogadoResponsavel;

	@ManyToOne
	@JoinColumn(name = "mpComarca_id", nullable = true)
	@Getter @Setter
	private MpTabelaInternaSJ mpComarca; // TAB_1008 (Pai)

	@ManyToOne
	@JoinColumn(name = "mpComarca_cartorio_id", nullable = true)
	@Getter @Setter
	private MpTabelaInternaSJ mpComarcaCartorio; // TAB_1042 (Filha)
	
	@Column(name = "pasta_cliente", nullable = false, length = 200)
	@Getter @Setter
	private String pastaCliente;
	
	@OneToMany(mappedBy = "mpProcesso", cascade = CascadeType.ALL, orphanRemoval = true,
																	fetch = FetchType.LAZY)
	@Getter @Setter
	private List<MpProcessoAndamento> mpAndamentos = new ArrayList<MpProcessoAndamento>();
		
	// ---
	
	@Transient
	public MpProcessoAndamentoDataModel getMpAndamentosDataModel() {
		//
		return new MpProcessoAndamentoDataModel(this.mpAndamentos);
	}
		
}
