package com.mpxds.mpbasic.model.pt05;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Audited
@AuditTable(value="mp_pt05_ato_composicao_")
@Table(name = "mp_pt05_ato_composicao")
public class MpAtoComposicao extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;

	private MpCustasComposicao mpCustasComposicao;

	private String complemento;
	private String excessao;

	private BigDecimal valorCusta = BigDecimal.ZERO; 
	
	private MpAto mpAto;
	
	// ---
	
	public MpAtoComposicao(){
		super();
	}

	@ManyToOne
	@JoinColumn(name = "mpCustasComposicao_id", nullable = true)
	public MpCustasComposicao getMpCustasComposicao() { return mpCustasComposicao; }
	public void setMpCustasComposicao(MpCustasComposicao mpCustasComposicao) {
													this.mpCustasComposicao = mpCustasComposicao; }
	
	@NotBlank(message = "Por favor, informe o COMPLEMENTO")
	@Column(nullable = false, length = 20)
	public String getComplemento() { return complemento; }
	public void setComplemento(String complemento) { this.complemento = complemento; }

	@NotBlank(message = "Por favor, informe a EXCESSÃO")
	@Column(nullable = false, length = 50)
	public String getExcessao() { return excessao; }
	public void setExcessao(String excessao) { this.excessao = excessao; }
	
	@Column(name = "valor_custa", nullable = true, precision = 10, scale = 2)
	public BigDecimal getValorCusta() { return valorCusta; }
	public void setValorCusta(BigDecimal valorCusta) { this.valorCusta = valorCusta; }
	
	@ManyToOne
	@JoinColumn(name = "mpAto_id", nullable = false)
	public MpAto getMpAto() { return mpAto; }
	public void setMpAto(MpAto mpAto) { this.mpAto = mpAto; }
	
}