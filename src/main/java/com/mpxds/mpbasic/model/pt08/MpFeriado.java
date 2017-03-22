package com.mpxds.mpbasic.model.pt08;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;

@Entity
@Audited
@AuditTable(value="mp_pt08_feriado_")
@Table(name="mp_pt08_feriado")
public class MpFeriado extends MpBaseEntity {
	private static final long serialVersionUID = 1L;

	private Date dataFeriado;  
	private String descricao;  

	// ----------
	
	public MpFeriado() {
		super();
	}

  	public MpFeriado(Date dataFeriado, String descricao
             ) {
  		this.dataFeriado = dataFeriado;
  		this.descricao = descricao;
  	}
 
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_feriado", nullable = false, length = 10)
  	public Date getDataFeriado() { return this.dataFeriado; }
  	public void setDataFeriado(Date newDataFeriado) { this.dataFeriado = newDataFeriado; }
  	 
  	@Column(nullable = false, length = 100)
  	public String getDescricao() { return this.descricao; }
  	public void setDescricao(String newDescricao) { this.descricao = newDescricao; }
  	 
}
