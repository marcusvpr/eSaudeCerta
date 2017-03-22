package com.mpxds.mpbasic.model.pt01;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Audited
@AuditTable(value="mp_pt01_especie_")
@Table(name="mp_pt01_especie")
public class MpEspecie extends MpBaseEntity {
	private static final long serialVersionUID = 1L;

	private String especieCodigo;  
	private String descricao;  
	private Integer numeroCodigo;  

	// ----------
	
	public MpEspecie() {
		super();
	}

  	public MpEspecie(String especieCodigo
             , String descricao
             , Integer numeroCodigo
             ) {
  		this.especieCodigo = especieCodigo;
  		this.descricao = descricao;
  		this.numeroCodigo = numeroCodigo;
  	}
 
    @Column(name = "especie_codigo", nullable = false, length = 3)
  	public String getEspecieCodigo() { return this.especieCodigo; }
  	public void setEspecieCodigo(String newEspecieCodigo) { this.especieCodigo = newEspecieCodigo; }
  	 
  	@Column(nullable = false, length = 25)
  	public String getDescricao() { return this.descricao; }
  	public void setDescricao(String newDescricao) { this.descricao = newDescricao; }
  	  
  	@Column(name = "numero_codigo", nullable = false, length = 2)
  	public Integer getNumeroCodigo() { return this.numeroCodigo; }
  	public void setNumeroCodigo(Integer newNumeroCodigo) { this.numeroCodigo = newNumeroCodigo; }
 
}
