package com.mpxds.mpbasic.model.pt05;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Column;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Audited
@AuditTable(value="mp_pt05_header_")
@Table(name="mp_pt05_header")
public class MpHeader extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;

	private MpRemessa mpRemessa;
	
//	private String identificacaoRegistro; // ???? Já é definido na carga !

//	private String codigoPortador; // ??? Passar Remessa 
//	private String nomePortador; // ??? Passar Remessa
	
//	private String dataDistribuicao;
	private String idenTransRemetente;
	private String idenTransDestinatario;
	private String idenTransTipo;
	private String numeroSeqRemessa;
	private String qtdRegRemessa;
	private String qtdTitRemessa;
	private String qtdIndRemessa;
	private String qtdOrigRemessa;
	private String agenciaCentralizadora;
	private String versaoLayout;
	private String complementoRegistro;
	private String numeroSeqRegistro;	

	// ----------
	
	public MpHeader() {
		super();
	}

  	public MpHeader(MpRemessa mpRemessa
//           , String identificacaoRegistro // ???
//           , String codigoPortador
//           , String nomePortador
//           , String dataDistribuicao
             , String idenTransRemetente
             , String idenTransDestinatario
             , String idenTransTipo
             , String numeroSeqRemessa
             , String qtdRegRemessa
             , String qtdTitRemessa
             , String qtdIndRemessa
             , String qtdOrigRemessa
             , String agenciaCentralizadora
             , String versaoLayout
             , String complementoRegistro
             , String numeroSeqRegistro
             ) {
  		this.mpRemessa = mpRemessa;
// 		this.identificacaoRegistro = identificacaoRegistro;
//  	this.codigoPortador = codigoPortador;
//  	this.nomePortador = nomePortador;
//  	this.dataDistribuicao = dataDistribuicao;
  		this.idenTransRemetente = idenTransRemetente;
  		this.idenTransDestinatario = idenTransDestinatario;
  		this.idenTransTipo = idenTransTipo;
  		this.numeroSeqRemessa = numeroSeqRemessa;
  		this.qtdRegRemessa = qtdRegRemessa;
  		this.qtdTitRemessa = qtdTitRemessa;
  		this.qtdIndRemessa = qtdIndRemessa;
  		this.qtdOrigRemessa = qtdOrigRemessa;
  		this.agenciaCentralizadora = agenciaCentralizadora;
  		this.versaoLayout = versaoLayout;
  		this.complementoRegistro = complementoRegistro;
  		this.numeroSeqRegistro = numeroSeqRegistro;
  	}
 
	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpRemessaId")
  	public MpRemessa getMpRemessa() { return this.mpRemessa; }
  	public void setMpRemessa(MpRemessa newMpRemessa) { this.mpRemessa = newMpRemessa; }
  	   	 
//  @Column(name = "identificacao_registro", nullable = false, length = 1)
//	public String getIdentificacaoRegistro() { return identificacaoRegistro; }
//	public void setIdentificacaoRegistro(String identificacaoRegistro)
//											{ this.identificacaoRegistro = identificacaoRegistro; }

//  @Column(name = "codigo_portador", nullable = false, length = 3)
//	public String getCodigoPortador() { return codigoPortador; }
//	public void setCodigoPortador(String codigoPortador) { this.codigoPortador = codigoPortador; }
//
//  @Column(name = "nome_portador", nullable = false, length = 45)
//	public String getNomePortador() { return nomePortador; }
//	public void setNomePortador(String nomePortador) { this.nomePortador = nomePortador; }

//  	@Column(name = "data_distribuicao", nullable = false, length = 8)
//	public String getDataDistribuicao() { return dataDistribuicao; }
//	public void setDataDistribuicao(String dataDistribuicao) 
//														{ this.dataDistribuicao = dataDistribuicao; }

  	@Column(name = "iden_trans_remetente", nullable = false, length = 3)
	public String getIdenTransRemetente() { return idenTransRemetente; }
	public void setIdenTransRemetente(String idenTransRemetente) 
												{ this.idenTransRemetente = idenTransRemetente; }

  	@Column(name = "iden_trans_destinatario", nullable = false, length = 3)
	public String getIdenTransDestinatario() { return idenTransDestinatario; }
	public void setIdenTransDestinatario(String idenTransDestinatario) 
											{ this.idenTransDestinatario = idenTransDestinatario; }

  	@Column(name = "iden_trans_tipo", nullable = false, length = 3)
	public String getIdenTransTipo() { return idenTransTipo; }
	public void setIdenTransTipo(String idenTransTipo) { this.idenTransTipo = idenTransTipo; }

  	@Column(name = "numero_seq_remessa", nullable = false, length = 6)
	public String getNumeroSeqRemessa() { return numeroSeqRemessa; }
	public void setNumeroSeqRemessa(String numeroSeqRemessa) 
													{ this.numeroSeqRemessa = numeroSeqRemessa; }

  	@Column(name = "qtd_reg_remessa", nullable = false, length = 4)
	public String getQtdRegRemessa() { return qtdRegRemessa; }
	public void setQtdRegRemessa(String qtdRegRemessa) { this.qtdRegRemessa = qtdRegRemessa; }

  	@Column(name = "qtd_tit_remessa", nullable = false, length = 4)
	public String getQtdTitRemessa() { return qtdTitRemessa; }
	public void setQtdTitRemessa(String qtdTitRemessa) { this.qtdTitRemessa = qtdTitRemessa; }

  	@Column(name = "qtd_ind_remessa", nullable = false, length = 4)
	public String getQtdIndRemessa() { return qtdIndRemessa; }
	public void setQtdIndRemessa(String qtdIndRemessa) { this.qtdIndRemessa = qtdIndRemessa; }

  	@Column(name = "qtd_orig_remessa", nullable = false, length = 4)
	public String getQtdOrigRemessa() { return qtdOrigRemessa; }
	public void setQtdOrigRemessa(String qtdOrigRemessa) { this.qtdOrigRemessa = qtdOrigRemessa; }

  	@Column(name = "agencia_centralizadora", nullable = false, length = 6)
	public String getAgenciaCentralizadora() { return agenciaCentralizadora; }
	public void setAgenciaCentralizadora(String agenciaCentralizadora) 
											{ this.agenciaCentralizadora = agenciaCentralizadora; }

  	@Column(name = "versao_layout", nullable = false, length = 3)
	public String getVersaoLayout() { return versaoLayout; }
	public void setVersaoLayout(String versaoLayout) { this.versaoLayout = versaoLayout; }

  	@Column(name = "complemento_registro", nullable = false, length = 1)
	public String getComplementoRegistro() { return complementoRegistro; }
	public void setComplementoRegistro(String complementoRegistro) 
												{ this.complementoRegistro = complementoRegistro; }

  	@Column(name = "numero_seq_registro", nullable = false, length = 4)
	public String getNumeroSeqRegistro() { return numeroSeqRegistro; }
	public void setNumeroSeqRegistro(String numeroSeqRegistro) 
													{ this.numeroSeqRegistro = numeroSeqRegistro; }

}
