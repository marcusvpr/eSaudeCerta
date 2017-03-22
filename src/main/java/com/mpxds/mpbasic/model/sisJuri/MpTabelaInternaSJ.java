package com.mpxds.mpbasic.model.sisJuri;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.enums.sisJuri.MpTipoTabelaInternaSJ;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="mp_sj_tabela_interna")
public class MpTabelaInternaSJ extends MpBaseEntitySJ {
	private static final long serialVersionUID = 1L;
	
//	@NotNull(message = "Por favor, informe o Tipo Tabela")
//	@Column(name="tipo_tabela_interna_id", nullable = false, length = 10)
//	// @Enumerated(EnumType.STRING) -> Transformei para tratar melhor no hql JPA ! ....
//	// @Getter @Setter
//	private String tipoTabelaInternaId;	  

	@NotNull(message = "Por favor, informe o Tipo Tabela")
	@Column(name="mptipo_tabela_interna_id", nullable = false, length = 10)
	// @Enumerated(EnumType.STRING) -> Transformei usando CONVERTER... para tratar melhor no hql JPA ! ....
	@Getter @Setter
	private MpTipoTabelaInternaSJ mpTipoTabelaInternaSJ;	  

	@NotBlank(message = "Por favor, informe o Código")
	@Column(nullable = false, length = 50)
	@Getter @Setter
	private String codigo;
	
	@NotBlank(message = "Por favor, informe a Descrição")
	@Getter @Setter
	private String descricao;
	
	//	
	@ManyToOne
	@JoinColumn(name = "mpPai_Id")
	@Getter @Setter
	private MpTabelaInternaSJ mpPai;
	
	@OneToMany(mappedBy = "mpPai", cascade = CascadeType.ALL)
	@Getter @Setter
	private List<MpTabelaInternaSJ> mpFilhas = new ArrayList<>();

	// ---
	
//    public MpTipoTabelaInternaSJ getMpTipoTabelaInterna() {
//        								return MpTipoTabelaInternaSJ.capturaTipoTabela(this.tipoTabelaInternaId); }
//    public void setMpTipoTabelaInternaSJ(MpTipoTabelaInternaSJ mpTipoTabelaInternaSJ) {
//        											this.tipoTabelaInternaId = mpTipoTabelaInternaSJ.getTabela(); }
    
	// ---
	
	@Transient
	public String getDescricaoNumero() { 
		//
		if (null==this.descricao) this.descricao = "";
		//
	    return this.descricao.trim() + " ( " + this.mpTipoTabelaInternaSJ.getDescricao() + " / " +
	    									   this.mpTipoTabelaInternaSJ.getTabela() + " )";
	}
	
}
