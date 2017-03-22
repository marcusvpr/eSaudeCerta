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
@AuditTable(value="mp_pt05_remessa_")
@Table(name="mp_pt05_remessa")
public class MpRemessa extends MpBaseEntity {
	//
	private static final long serialVersionUID = 1L;
	
	private MpImportarControle mpImportarControle;

	private String nomeArquivo;

	private String protocoloInicial;  
	private String protocoloFinal; 

	// ----------
	
	public MpRemessa() {
		super();
	}

  	public MpRemessa(MpImportarControle mpImportarControle
             , String nomeArquivo
             , String protocoloInicial
             , String protocoloFinal
             ) {
  		this.mpImportarControle = mpImportarControle;
  		this.nomeArquivo = nomeArquivo;
  		this.protocoloInicial = protocoloInicial;
  		this.protocoloFinal = protocoloFinal;
  	}
 
	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpImportarControleId")
  	public MpImportarControle getMpImportarControle() { return this.mpImportarControle; }
  	public void setMpImportarControle(MpImportarControle newMpImportarControle) {
  													this.mpImportarControle = newMpImportarControle; }
  	 
  	@Column(name = "nome_arquivo", nullable = false, length = 50) // Was=11???
  	public String getNomeArquivo() { return this.nomeArquivo; }
  	public void setNomeArquivo(String newNomeArquivo) { this.nomeArquivo = newNomeArquivo; }
  	  
  	@Column(name = "protocolo_inicial", nullable = false, length = 6)
  	public String getProtocoloInicial() { return this.protocoloInicial; }
  	public void setProtocoloInicial(String newProtocoloInicial) { 
  														this.protocoloInicial = newProtocoloInicial; }
	  
	@Column(name = "protocolo_final", nullable = false, length = 6)
	public String getProtocoloFinal() { return this.protocoloFinal; }
	public void setProtocoloFinal(String newProtocoloFinal) { this.protocoloFinal = newProtocoloFinal; }
 
}
