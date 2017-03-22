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
@AuditTable(value="mp_pt01_pessoa_titulo_")
@Table(name="mp_pt01_pessoa_titulo")
public class MpPessoaTitulo extends MpBaseEntity {
	private static final long serialVersionUID = 1L;
	//
	private String nome;	
	private String tipoDocumento;
	private String numeroDocumento;

	private MpEnderecoLocal mpEnderecoLocal; 

	// ----------
	
	public MpPessoaTitulo() {
		super();
	}

  	public MpPessoaTitulo(String nome
             , String tipoDocumento
             , String numeroDocumento
             , MpEnderecoLocal mpEnderecoLocal
             ) {
  		this.nome = nome;
  		this.tipoDocumento = tipoDocumento;
  		this.numeroDocumento = numeroDocumento;
  		this.mpEnderecoLocal = mpEnderecoLocal;
  	}
  	  		  	
  	@Column(name = "nome", nullable = false, length = 150)
	public String getNome() { return this.nome; }
	public void setNome(String newNome) { this.nome = newNome; }
	  	
  	@Column(name = "tipo_documento", nullable = false, length = 10)
	public String getTipoDocumento() { return this.tipoDocumento; }
	public void setTipoDocumento(String newTipoDocumento) {
														this.tipoDocumento = newTipoDocumento; }
  	
	@Column(name = "numero_documento", nullable = false, length = 50)
	public String getNumeroDocumento() { return this.numeroDocumento; }
	public void setNumeroDocumento(String newNumeroDocumento) {
													this.numeroDocumento = newNumeroDocumento; }
  	
	@Embedded
	public MpEnderecoLocal getMpEnderecoLocal() { return this.mpEnderecoLocal; }
	public void setMpEnderecoLocal(MpEnderecoLocal newMpEnderecoLocal) {
													this.mpEnderecoLocal = newMpEnderecoLocal; }
 
}
