package com.mpxds.mpbasic.model.pt01;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Audited
@AuditTable(value="mp_pt01_data_processo_")
@Table(name="mp_pt01_data_processo")
public class MpDataProcesso extends MpBaseEntity {
	private static final long serialVersionUID = 1L;

	private Date dataProtocolo;  
	private Date dataAte;  
	private String protocoloInicial;  
	private String protocoloFinal;  

	// ----------
	
	public MpDataProcesso() {
		super();
	}

  	public MpDataProcesso(Date dataProtocolo
             , Date dataAte
             , String protocoloInicial
             , String protocoloFinal
             ) {
  		this.dataProtocolo = dataProtocolo;
  		this.dataAte = dataAte;
  		this.protocoloInicial = protocoloInicial;
  		this.protocoloFinal = protocoloFinal;
  	}
  	
  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_protocolo", unique = true, nullable = false)
  	public Date getDataProtocolo() { return this.dataProtocolo; }
  	public void setDataProtocolo(Date newDataProtocolo) { 
  														this.dataProtocolo = newDataProtocolo; }
  	
  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_ate", nullable = false)
  	public Date getDataAte() { return this.dataAte; }
  	public void setDataAte(Date newDataAte) { this.dataAte = newDataAte; }
  	
    @Column(name = "protocolo_inicial", nullable = false, length = 6)
  	public String getProtocoloInicial() { return this.protocoloInicial; }
  	public void setProtocoloInicial(String newProtocoloInicial) { 
  												this.protocoloInicial = newProtocoloInicial; }
  	
    @Column(name = "protocolo_final", nullable = false, length = 6)
  	public String getProtocoloFinal() { return this.protocoloFinal; }
  	public void setProtocoloFinal(String newProtocoloFinal) { 
  													this.protocoloFinal = newProtocoloFinal; }
 
}
