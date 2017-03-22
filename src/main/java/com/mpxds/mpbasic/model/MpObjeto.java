package com.mpxds.mpbasic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.engreq.MpItemObjeto;
import com.mpxds.mpbasic.model.enums.MpGrupamentoMenu;
import com.mpxds.mpbasic.model.enums.MpGrupoMenu;
import com.mpxds.mpbasic.model.enums.MpStatusObjeto;
import com.mpxds.mpbasic.model.enums.MpTipoObjeto;
import com.mpxds.mpbasic.model.enums.MpTipoObjetoSistema;

@Entity
@Audited
@AuditTable(value="mp_objeto_")
@Table(name = "mp_objeto")
public class MpObjeto extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;
	
	private String codigoId;
	private String transacao;
	private String codigo;
	private String nome;
	private String descricao;
	private String informacao;
	private String classeAssociada;
	private String urlHelp;
	private String urlHelpVideo;
	private String icon;
	private Boolean indSeparator = false;
	private Boolean indResponsive = false;

	private MpStatusObjeto mpStatusObjeto;
	private MpGrupoMenu mpGrupoMenu;
	private MpGrupamentoMenu mpGrupamentoMenu;
	private MpTipoObjeto mpTipoObjeto;
	private MpTipoObjetoSistema mpTipoObjetoSistema;
		
	private List<MpItemObjeto> mpItens = new ArrayList<MpItemObjeto>();
	
	// ----
	
	public MpObjeto() {
	  super();
	}

	public  MpObjeto(String codigoId
						, String transacao
            			, String codigo
                        , String nome
                        , String descricao
                        , String informacao
                        , String classeAssociada
                        , String icon
                        , Boolean indSeparator
                        , Boolean indResponsive
                        , MpStatusObjeto mpStatusObjeto
                        , MpGrupoMenu mpGrupoMenu
                        , MpGrupamentoMenu mpGrupamentoMenu
                        , MpTipoObjeto mpTipoObjeto
                        , MpTipoObjetoSistema mpTipoObjetoSistema
                 ) {
		this.codigoId = codigoId;
		this.transacao = transacao;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.informacao = informacao;
		this.classeAssociada = classeAssociada;
		this.icon = icon;
		this.indSeparator = indSeparator;
		this.indResponsive = indResponsive;
		this.mpStatusObjeto = mpStatusObjeto;
		this.mpGrupoMenu = mpGrupoMenu;
		this.mpGrupamentoMenu = mpGrupamentoMenu;
		this.mpTipoObjeto = mpTipoObjeto;
		this.mpTipoObjetoSistema = mpTipoObjetoSistema;
	}

	@NotBlank(message = "Por favor, informe código identificação!")
//	@Column(nullable = false, length = 50, unique = true)
	@Column(nullable = true, length = 50, name = "codigo_id")
	public String getCodigoId() { return this.codigoId; }
	public void setCodigoId(String newCodigoId) { this.codigoId = newCodigoId; }
	
	@NotBlank(message = "Por favor, informe código da Transação!")
	@Column(nullable = false, length = 20, unique = true)
	public String getTransacao() { return this.transacao; }
	public void setTransacao(String newTransacao) { this.transacao = newTransacao; }
  
	@NotBlank(message = "Por favor, informe código!")
	@Column(nullable = false, length = 150, unique = true)
	public String getCodigo() { return this.codigo; }
	public void setCodigo(String newCodigo) { this.codigo = newCodigo; }
	
	@Column(nullable = false, length = 150)
	public String getNome() { return this.nome; }
	public void setNome(String newNome) { this.nome = newNome; }

	@Column(nullable = false, length = 255)
	public String getDescricao() { return this.descricao; }
	public void setDescricao(String newDescricao) { this.descricao = newDescricao; }

	@Lob
	@Column(nullable = true, length = 10000)
	public String getInformacao() { return this.informacao; }
	public void setInformacao(String newInformacao) { this.informacao = newInformacao; }

	@Column(nullable = true, length = 255)
	public String getClasseAssociada() { return this.classeAssociada; }
	public void setClasseAssociada(String newClasseAssociada) { 
														this.classeAssociada = newClasseAssociada; }

	@Column(nullable = true, length = 255)
	public String getUrlHelp() { return this.urlHelp; }
	public void setUrlHelp(String newUrlHelp) { this.urlHelp = newUrlHelp; }

	@Column(nullable = true, length = 255)
	public String getUrlHelpVideo() { return this.urlHelpVideo; }
	public void setUrlHelpVideo(String newUrlHelpVideo) { this.urlHelpVideo = newUrlHelpVideo; }

	@Column(nullable = true, length = 100)
	public String getIcon() { return this.icon; }
	public void setIcon(String newIcon) { this.icon = newIcon; }

	@Column(nullable = true, name = "ind_separator")
	public Boolean getIndSeparator() { return this.indSeparator; }
	public void setIndSeparator(Boolean newIndSeparator) { this.indSeparator = newIndSeparator; }

	@Column(nullable = true, name = "ind_responsive")
	public Boolean getIndResponsive() { return this.indResponsive; }
	public void setIndResponsive(Boolean newIndResponsive) { 
														this.indResponsive = newIndResponsive; }

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50, name = "mpStatus_objeto")
	public MpStatusObjeto getMpStatusObjeto() { return this.mpStatusObjeto; }
	public void setMpStatusObjeto(MpStatusObjeto newMpStatusObjeto) {
														this.mpStatusObjeto = newMpStatusObjeto; }  

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50, name = "mpGrupo_menu")
	public MpGrupoMenu getMpGrupoMenu() { return this.mpGrupoMenu; }
	public void setMpGrupoMenu(MpGrupoMenu newMpGrupoMenu) { this.mpGrupoMenu = newMpGrupoMenu; }

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50, name = "mpGrupamento_menu")
	public MpGrupamentoMenu getMpGrupamentoMenu() { return this.mpGrupamentoMenu; }
	public void setMpGrupamentoMenu(MpGrupamentoMenu newMpGrupamentoMenu) {
													this.mpGrupamentoMenu = newMpGrupamentoMenu; }

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50, name = "mpTipo_objeto")
	public MpTipoObjeto getMpTipoObjeto() { return this.mpTipoObjeto; }
	public void setMpTipoObjeto(MpTipoObjeto newMpTipoObjeto) {
															this.mpTipoObjeto = newMpTipoObjeto; }

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50, name = "mpTipo_objeto_sistema")
	public MpTipoObjetoSistema getMpTipoObjetoSistema() { return this.mpTipoObjetoSistema; }
	public void setMpTipoObjetoSistema(MpTipoObjetoSistema newMpTipoObjetoSistema) {
												this.mpTipoObjetoSistema = newMpTipoObjetoSistema; }
	
	@OneToMany(mappedBy = "mpObjeto", cascade = CascadeType.ALL, orphanRemoval = true,
																		fetch = FetchType.LAZY)
	public List<MpItemObjeto> getMpItens() { return mpItens; }
	public void setMpItens(List<MpItemObjeto> mpItens) { this.mpItens = mpItens; }
		  
	@Transient
	public String getMpGrupamentoNome() {
		//
	    return this.mpGrupamentoMenu + this.nome.trim();
	}
	
}