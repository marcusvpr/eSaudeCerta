package com.mpxds.mpbasic.model.sisJuri;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.mpxds.mpbasic.model.sisJuri.MpBaseEntitySJ;
import com.mpxds.mpbasic.model.sisJuri.MpTabelaInternaSJ;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MP_SJ_PROCESSO_ANDAMENTO")
@ToString
public class MpProcessoAndamento extends MpBaseEntitySJ {
	//
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "mpProcesso_id", nullable = false)
	@Getter @Setter
    private MpProcesso mpProcesso;

  	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false)
	@Getter @Setter
	private Date dataCadastro = new Date();

	@Column(nullable = true, length = 250)
	@Getter @Setter
  	private String detalhamento; // <1070>
	
	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpAndamento_Tipo_Id")
	@Getter @Setter
	private MpTabelaInternaSJ mpAndamentoTipo; // <1022>

	@OneToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "mpAto_Praticado_Id")
	@Getter @Setter
	private MpTabelaInternaSJ mpAtoPraticado; // <1040>

	@Column(nullable = true, length = 100)
	@Getter @Setter
	private String obsFotoBD;

	@Column(nullable = true, length = 10)
	@Getter @Setter
	private String tipoFotoBD;

	@Lob
	@Column(columnDefinition = "blob", nullable = true, length = 10000)
	@Getter @Setter
	private byte[] fotoBD;
	
	// ---
	
	@Transient
	public boolean isFotoBD() {
		//
		if (null == this.fotoBD) return false;
		else return true;
	}
	
	@Transient
	public String getDataCadastroSDF() {
		//		
		if (null == this.dataCadastro) this.dataCadastro = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return sdf.format(this.dataCadastro);
	}
	
}