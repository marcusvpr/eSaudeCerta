package com.mpxds.mpbasic.model.pt01;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;
import com.mpxds.mpbasic.model.MpEnderecoLocal;

@Entity
@Audited
@AuditTable(value="mp_pt01_banco_")
@Table(name="mp_pt01_banco")
public class MpBanco extends MpBaseEntity {
	private static final long serialVersionUID = 1L;

	private String codigo;  
	private Integer agencia;  
	private String nome;  
	private String tipoDocumento;  
	private String numeroDocumento;  
	
	private MpEnderecoLocal mpEnderecoLocal;

	// ----------
	
	public MpBanco() {
		super();
	}

  	public MpBanco(String codigo
             , Integer agencia
             , String nome
             , String tipoDocumento
             , String numeroDocumento
             , MpEnderecoLocal mpEnderecoLocal
             ) {
  		this.codigo = codigo;
  		this.agencia = agencia;
  		this.nome = nome;
  		this.tipoDocumento = tipoDocumento;
  		this.numeroDocumento = numeroDocumento;
  		this.mpEnderecoLocal = mpEnderecoLocal;
  	}
  	  	
    @Column(name = "codigo", nullable = false, length = 7)
  	public String getCodigo() { return this.codigo; }
  	public void setCodigo(String newCodigo) { this.codigo = newCodigo; }
  	
    @Column(name = "agencia", nullable = false, length = 5)
  	public Integer getAgencia() { return this.agencia; }
  	public void setAgencia(Integer newAgencia) { this.agencia = newAgencia; }
	  	
  	@Column(name = "nome", nullable = false, length = 135)
	public String getNome() { return this.nome; }
	public void setNome(String newNome) { this.nome = newNome; }
	  	
  	@Column(name = "tipo_documento", nullable = false, length = 3)
	public String getTipoDocumento() { return this.tipoDocumento; }
	public void setTipoDocumento(String newTipoDocumento) {
														this.tipoDocumento = newTipoDocumento; }
  	
	@Column(name = "numero_documento", nullable = false, length = 14)
	public String getNumeroDocumento() { return this.numeroDocumento; }
	public void setNumeroDocumento(String newNumeroDocumento) {
													this.numeroDocumento = newNumeroDocumento; }
  	
	@Embedded
	public MpEnderecoLocal getMpEnderecoLocal() { return this.mpEnderecoLocal; }
	public void setMpEnderecoLocal(MpEnderecoLocal newMpEnderecoLocal) {
													this.mpEnderecoLocal = newMpEnderecoLocal; }
 
}
