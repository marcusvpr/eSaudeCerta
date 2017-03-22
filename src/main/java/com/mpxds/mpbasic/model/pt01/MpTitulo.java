package com.mpxds.mpbasic.model.pt01;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.mpxds.mpbasic.model.MpBaseEntity;
import com.mpxds.mpbasic.model.pt05.MpValorAto;

@Entity
@Audited
@AuditTable(value="mp_pt01_titulo_")
@Table(name="mp_pt01_titulo")
public class MpTitulo extends MpBaseEntity {
	private static final long serialVersionUID = 1L;
	//
	private Date dataProtocolo;
	private String numeroProtocolo;
	private Date dataAte;
	private String codigoApresentante;
	private String convenio;
	private String empresa;
	private Date dataDistribuicao;
	private String numeroDistribuicao;
	private Date dataEmissao;
	private Date dataVencimento;
	private String numeroTitulo;
	private String numeroBanco;
	private String codigoEndosso;
	private String agenciaCedente;
	private BigDecimal valor;
	private BigDecimal saldo;
	private String especieCodigo;
	private String codigoAlinea;
	private String tipoApresentante;
	private String edital;
	private String microEmpresa;
	private String faixa;
	
	private MpValorAto mpValorAto = new MpValorAto(); // MpAto usa também ! 
	
	private BigDecimal valorVariavel;
	private BigDecimal intimacao;
	private BigDecimal coobrigado;
	private BigDecimal diligencia;
	private BigDecimal valorEdital;
	private BigDecimal outros;
	
	/* TODO */
	private BigDecimal totalEmolumento; // Campo Calculado? Vide Calculo abaixo  !
	private BigDecimal totalPagar; // Campo Calculado? Vide Calculo abaixo  !
	private BigDecimal totalDeposito; // Campo Calculado? Vide Calculo abaixo  !
	
	private Integer quantidadeDevedor; // cco virou Quantidade !
	private Integer quantidadeAvalista;
	private Integer quantidadeEndossante;
	private Integer quantidadeNotificado;
	private Integer quantidadeEnderecoIgual;
	private Integer quantidadeEnderecoDiferente;
	
	private String usuarioTitulo;
	private Date dataSistemaTitulo;
	private Date dataOcorrencia;
	private String codigoOcorrencia;
	private Integer numeroCancelamento;
	private String dinheiroCheque;
	
	private String observacaoOcorrencia;
	private String bloqueioOcorrencia;
	private String usuarioOcorrencia;
	private Date dataSistemaOcorrencia;
	
	private Date tituloDataRetirada;
	private Date tituloDataRepasse;
	private String tituloChequeRepasse;
	private String observacaoTitulo;
	private String motivo;
	private String custasPagar;
	private Date dataResultadoIntimacao;
	private String resultadoIntimacao;

	private Date dataEnvioCorreio;
	private Date dataRetornoCorreio;
	private String resultadoCorreio;
	
	private Date dataEdital;
	private String intimado;
	private String respondido;
	
	private Date dataEnvioSuspensaoEfeito;
	private Date dataResultadoSuspensaoEfeito;
	private String resultadoSuspensaoEfeito;
	
	private Date dataEnvioSustacaoProtesto;
	private Date dataResultadoSustacaoProtesto;
	private String resultadoSustacaoProtesto;
	
	private BigDecimal tituloValorReembolsoEdital;
	private String numeroCliente;
	private Date dataAviso;
	private Date dataResultadoAviso;
	
	private MpPessoaTitulo mpPessoaTituloApresentante = new MpPessoaTitulo(); // Normalizado !
	private MpPessoaTitulo mpPessoaTituloFavorecido = new MpPessoaTitulo(); // Normalizado !
	private MpPessoaTitulo mpPessoaTituloSacador = new MpPessoaTitulo(); // Normalizado !
		
	private String numeroLivro;
	private String numeroFolha;
//	private String encerrado; // Virou indDigital !
	private Boolean indDigital;
	private String codigoIrregularidade;
	private String nihil; // Sigla = Gratis (Recolhendo Emolumentos salvo Justiça Gratis)
	private String aVista;
//	private String rsDesiste; // Não Utilizado !

	private String talaoNumeroLivro;
	private String convenioNumerolivro;
	private String numeroTalao3Oficio;
	private String aceite;
	private String finsFalimentares;
	private String arquivamento;
	
	// ----------
	
	public MpTitulo() {
		super();
	}

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_protocolo", nullable = false)
	public Date getDataProtocolo() { return dataProtocolo; }
	public void setDataProtocolo(Date dataProtocolo) { this.dataProtocolo = dataProtocolo; }

	public String getNumeroProtocolo() { return numeroProtocolo; }
	public void setNumeroProtocolo(String numeroProtocolo) {
													this.numeroProtocolo = numeroProtocolo;	}

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_ate", nullable = false)
	public Date getDataAte() { return dataAte; }
	public void setDataAte(Date dataAte) { this.dataAte = dataAte; }

	public String getCodigoApresentante() { return codigoApresentante; }
	public void setCodigoApresentante(String codigoApresentante) {
												this.codigoApresentante = codigoApresentante; }

	public String getConvenio() { return convenio; }
	public void setConvenio(String convenio) { this.convenio = convenio; }

	public String getEmpresa() { return empresa; }
	public void setEmpresa(String empresa) { this.empresa = empresa; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_distribuicao", nullable = false)
	public Date getDataDistribuicao() { return dataDistribuicao; }
	public void setDataDistribuicao(Date dataDistribuicao) { 
														this.dataDistribuicao = dataDistribuicao; }

	public String getNumeroDistribuicao() { return numeroDistribuicao; }
	public void setNumeroDistribuicao(String numeroDistribuicao) {
													this.numeroDistribuicao = numeroDistribuicao; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_emissao", nullable = false)
	public Date getDataEmissao() { return dataEmissao; }
	public void setDataEmissao(Date dataEmissao) { this.dataEmissao = dataEmissao; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_vencimento", nullable = false)
	public Date getDataVencimento() { return dataVencimento; }
	public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }

	public String getNumeroTitulo() { return numeroTitulo; }
	public void setNumeroTitulo(String numeroTitulo) { this.numeroTitulo = numeroTitulo; }

	public String getNumeroBanco() { return numeroBanco; }
	public void setNumeroBanco(String numeroBanco) { this.numeroBanco = numeroBanco; }

	public String getCodigoEndosso() { return codigoEndosso; }
	public void setCodigoEndosso(String codigoEndosso) { this.codigoEndosso = codigoEndosso; }

	public String getAgenciaCedente() {	return agenciaCedente; }
	public void setAgenciaCedente(String agenciaCedente) { this.agenciaCedente = agenciaCedente; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getSaldo() { return saldo; }
	public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

	public String getEspecieCodigo() { return especieCodigo; }
	public void setEspecieCodigo(String especieCodigo) { this.especieCodigo = especieCodigo; }

	public String getCodigoAlinea() { return codigoAlinea; }
	public void setCodigoAlinea(String codigoAlinea) { this.codigoAlinea = codigoAlinea; }

	public String getTipoApresentante() { return tipoApresentante; }
	public void setTipoApresentante(String tipoApresentante) {
														this.tipoApresentante = tipoApresentante; }

	public String getEdital() {	return edital; }
	public void setEdital(String edital) { this.edital = edital; }

	public String getMicroEmpresa() { return microEmpresa; }
	public void setMicroEmpresa(String microEmpresa) { this.microEmpresa = microEmpresa; }

	public String getFaixa() { return faixa; }
	public void setFaixa(String faixa) { this.faixa = faixa; }

	@Embedded
	public MpValorAto getMpValorAto() { return mpValorAto; }
	public void setMpValorAto(MpValorAto mpValorAto) { this.mpValorAto = mpValorAto; }

	@Column(name = "valor_variavel", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorVariavel() { return valorVariavel; }
	public void setValorVariavel(BigDecimal valorVariavel) { this.valorVariavel = valorVariavel; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getIntimacao() { return intimacao; }
	public void setIntimacao(BigDecimal intimacao) { this.intimacao = intimacao; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getCoobrigado() { return coobrigado; }
	public void setCoobrigado(BigDecimal coobrigado) { this.coobrigado = coobrigado; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getDiligencia() { return diligencia; }
	public void setDiligencia(BigDecimal diligencia) { this.diligencia = diligencia; }

	@Column(name = "valor_edital", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorEdital() { return valorEdital; }
	public void setValorEdital(BigDecimal valorEdital) { this.valorEdital = valorEdital; }

	@Column(nullable = false, precision = 10, scale = 2)
	public BigDecimal getOutros() {	return outros; }
	public void setOutros(BigDecimal outros) { this.outros = outros; }

	@Column(name = "total_emolumento", nullable = false, precision = 10, scale = 2)
	public BigDecimal getTotalEmolumento() { return totalEmolumento; }
	public void setTotalEmolumento(BigDecimal totalEmolumento) {
														this.totalEmolumento = totalEmolumento; }

	@Column(name = "total_pagar", nullable = false, precision = 10, scale = 2)
	public BigDecimal getTotalPagar() {	return totalPagar; }	
	public void setTotalPagar(BigDecimal totalPagar) { this.totalPagar = totalPagar; }

	@Column(name = "total_deposito", nullable = false, precision = 10, scale = 2)
	public BigDecimal getTotalDeposito() { return totalDeposito; }
	public void setTotalDeposito(BigDecimal totalDeposito) { this.totalDeposito = totalDeposito; }

	public Integer getQuantidadeDevedor() { return quantidadeDevedor; }
	public void setQuantidadeDevedor(Integer quantidadeDevedor) { 
														this.quantidadeDevedor = quantidadeDevedor; }

	public Integer getQuantidadeAvalista() { return quantidadeAvalista; }
	public void setQuantidadeAvalista(Integer quantidadeAvalista) { 
													this.quantidadeAvalista = quantidadeAvalista; }

	public Integer getQuantidadeEndossante() { return quantidadeEndossante; }
	public void setQuantidadeEndossante(Integer quantidadeEndossante) { 
													this.quantidadeEndossante = quantidadeEndossante; }

	public Integer getQuantidadeNotificado() {	return quantidadeNotificado; }
	public void setQuantidadeNotificado(Integer quantidadeNotificado) { 
													this.quantidadeNotificado = quantidadeNotificado; }

	public Integer getQuantidadeEnderecoIgual() { return quantidadeEnderecoIgual; }
	public void setQuantidadeEnderecoIgual(Integer quantidadeEnderecoIgual) {
											this.quantidadeEnderecoIgual = quantidadeEnderecoIgual; }

	public Integer getQuantidadeEnderecoDiferente() { return quantidadeEnderecoDiferente; }
	public void setQuantidadeEnderecoDiferente(Integer quantidadeEnderecoDiferente) {
									this.quantidadeEnderecoDiferente = quantidadeEnderecoDiferente; }

	public String getUsuarioTitulo() { return usuarioTitulo; }
	public void setUsuarioTitulo(String usuarioTitulo) { this.usuarioTitulo = usuarioTitulo; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Sistema_Titulo", nullable = false)
	public Date getDataSistemaTitulo() { return dataSistemaTitulo; }
	public void setDataSistemaTitulo(Date dataSistemaTitulo) { 
													this.dataSistemaTitulo = dataSistemaTitulo; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_ocorrencia", nullable = false)
	public Date getDataOcorrencia() { return dataOcorrencia; }
	public void setDataOcorrencia(Date dataOcorrencia) { this.dataOcorrencia = dataOcorrencia; }

	public String getCodigoOcorrencia() { return codigoOcorrencia; }
	public void setCodigoOcorrencia(String codigoOcorrencia) { 
													this.codigoOcorrencia = codigoOcorrencia; }

	public Integer getNumeroCancelamento() { return numeroCancelamento; }
	public void setNumeroCancelamento(Integer numeroCancelamento) { 
												this.numeroCancelamento = numeroCancelamento; }

	public String getDinheiroCheque() {	return dinheiroCheque; }
	public void setDinheiroCheque(String dinheiroCheque) { this.dinheiroCheque = dinheiroCheque; }

	public String getObservacaoOcorrencia() { return observacaoOcorrencia; }
	public void setObservacaoOcorrencia(String observacaoOcorrencia) {
												this.observacaoOcorrencia = observacaoOcorrencia; }

	public String getBloqueioOcorrencia() {	return bloqueioOcorrencia; }
	public void setBloqueioOcorrencia(String bloqueioOcorrencia) {
												this.bloqueioOcorrencia = bloqueioOcorrencia; }

	public String getUsuarioOcorrencia() { return usuarioOcorrencia; }
	public void setUsuarioOcorrencia(String usuarioOcorrencia) {
													this.usuarioOcorrencia = usuarioOcorrencia; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_sistema_ocorrencia", nullable = false)
	public Date getDataSistemaOcorrencia() { return dataSistemaOcorrencia; }
	public void setDataSistemaOcorrencia(Date dataSistemaOcorrencia) {
												this.dataSistemaOcorrencia = dataSistemaOcorrencia;	}

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "titulo_data_Retirada", nullable = false)
	public Date getTituloDataRetirada() { return tituloDataRetirada; }
	public void setTituloDataRetirada(Date tituloDataRetirada) {
													this.tituloDataRetirada = tituloDataRetirada; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "Titulo_Data_Repasse", nullable = false)
	public Date getTituloDataRepasse() { return tituloDataRepasse; }
	public void setTituloDataRepasse(Date tituloDataRepasse) {
													this.tituloDataRepasse = tituloDataRepasse; }

	public String getTituloChequeRepasse() { return tituloChequeRepasse; }
	public void setTituloChequeRepasse(String tituloChequeRepasse) { 
												this.tituloChequeRepasse = tituloChequeRepasse; }

	public String getObservacaoTitulo() { return observacaoTitulo; }
	public void setObservacaoTitulo(String observacaoTitulo) { 
														this.observacaoTitulo = observacaoTitulo; }

	public String getMotivo() { return motivo; }
	public void setMotivo(String motivo) { this.motivo = motivo; }

	public String getCustasPagar() { return custasPagar; }
	public void setCustasPagar(String custasPagar) { this.custasPagar = custasPagar; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "Data_Resultado_Intimacao", nullable = false)
	public Date getDataResultadoIntimacao() { return dataResultadoIntimacao; }
	public void setDataResultadoIntimacao(Date dataResultadoIntimacao) {
											this.dataResultadoIntimacao = dataResultadoIntimacao; }

	public String getResultadoIntimacao() { return resultadoIntimacao; }
	public void setResultadoIntimacao(String resultadoIntimacao) {
													this.resultadoIntimacao = resultadoIntimacao; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "Data_Envio_Correio", nullable = false)
	public Date getDataEnvioCorreio() { return dataEnvioCorreio; }
	public void setDataEnvioCorreio(Date dataEnvioCorreio) { 
														this.dataEnvioCorreio = dataEnvioCorreio; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "Data_Retorno_Correio", nullable = false)
	public Date getDataRetornoCorreio() { return dataRetornoCorreio; }
	public void setDataRetornoCorreio(Date dataRetornoCorreio) {
													this.dataRetornoCorreio = dataRetornoCorreio; }

	public String getResultadoCorreio() { return resultadoCorreio; }
	public void setResultadoCorreio(String resultadoCorreio) {
														this.resultadoCorreio = resultadoCorreio; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_edital", nullable = false)
	public Date getDataEdital() { return dataEdital; }
	public void setDataEdital(Date dataEdital) { this.dataEdital = dataEdital; }

	public String getIntimado() { return intimado; }
	public void setIntimado(String intimado) { this.intimado = intimado; }

	public String getRespondido() { return respondido; }
	public void setRespondido(String respondido) { this.respondido = respondido; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Envio_Suspensao_Efeito", nullable = false)
	public Date getDataEnvioSuspensaoEfeito() { return dataEnvioSuspensaoEfeito; }
	public void setDataEnvioSuspensaoEfeito(Date dataEnvioSuspensaoEfeito) {
										this.dataEnvioSuspensaoEfeito = dataEnvioSuspensaoEfeito; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Resultado_Suspensao_Efeito", nullable = false)
	public Date getDataResultadoSuspensaoEfeito() { return dataResultadoSuspensaoEfeito; }
	public void setDataResultadoSuspensaoEfeito(Date dataResultadoSuspensaoEfeito) {
								this.dataResultadoSuspensaoEfeito = dataResultadoSuspensaoEfeito; }

	public String getResultadoSuspensaoEfeito() { return resultadoSuspensaoEfeito; }
	public void setResultadoSuspensaoEfeito(String resultadoSuspensaoEfeito) { 
										this.resultadoSuspensaoEfeito = resultadoSuspensaoEfeito; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Envio_Sustacao_Protesto", nullable = false)
	public Date getDataEnvioSustacaoProtesto() { return dataEnvioSustacaoProtesto; }
	public void setDataEnvioSustacaoProtesto(Date dataEnvioSustacaoProtesto) {
										this.dataEnvioSustacaoProtesto = dataEnvioSustacaoProtesto;	}

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Resultado_Sustacao_Protesto", nullable = false)
	public Date getDataResultadoSustacaoProtesto() { return dataResultadoSustacaoProtesto; }
	public void setDataResultadoSustacaoProtesto(Date dataResultadoSustacaoProtesto) {
								this.dataResultadoSustacaoProtesto = dataResultadoSustacaoProtesto;	}

	public String getResultadoSustacaoProtesto() { return resultadoSustacaoProtesto; }
	public void setResultadoSustacaoProtesto(String resultadoSustacaoProtesto) {
										this.resultadoSustacaoProtesto = resultadoSustacaoProtesto; }

	@Column(name = "Titulo_Valor_Reembolso_Edital", nullable = false, precision = 10, scale = 2)
	public BigDecimal getTituloValorReembolsoEdital() { return tituloValorReembolsoEdital; }
	public void setTituloValorReembolsoEdital(BigDecimal tituloValorReembolsoEdital) {
										this.tituloValorReembolsoEdital = tituloValorReembolsoEdital; }

	public String getNumeroCliente() { return numeroCliente; }
	public void setNumeroCliente(String numeroCliente) { this.numeroCliente = numeroCliente; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_aviso", nullable = false)
	public Date getDataAviso() { return dataAviso; }
	public void setDataAviso(Date dataAviso) { this.dataAviso = dataAviso; }

  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name = "data_Resultado_Aviso", nullable = false)
	public Date getDataResultadoAviso() { return dataResultadoAviso; }
	public void setDataResultadoAviso(Date dataResultadoAviso) { 
													this.dataResultadoAviso = dataResultadoAviso; }

	@NotNull
	@ManyToOne
	@JoinColumn(name = "mpApresentante_id", nullable = false)
	public MpPessoaTitulo getMpPessoaTituloApresentante() {	return mpPessoaTituloApresentante; }
	public void setMpPessoaTituloApresentante(MpPessoaTitulo mpPessoaTituloApresentante) {
									this.mpPessoaTituloApresentante = mpPessoaTituloApresentante; }

	@NotNull
	@ManyToOne
	@JoinColumn(name = "mpFavorecido_id", nullable = false)
	public MpPessoaTitulo getMpPessoaTituloFavorecido() { return mpPessoaTituloFavorecido; }
	public void setMpPessoaTituloFavorecido(MpPessoaTitulo mpPessoaTituloFavorecido) {
										this.mpPessoaTituloFavorecido = mpPessoaTituloFavorecido; }

	@NotNull
	@ManyToOne
	@JoinColumn(name = "mpSacador_id", nullable = false)
	public MpPessoaTitulo getMpPessoaTituloSacador() { return mpPessoaTituloSacador; }
	public void setMpPessoaTituloSacador(MpPessoaTitulo mpPessoaTituloSacador) {
												this.mpPessoaTituloSacador = mpPessoaTituloSacador;	}

	public String getNumeroLivro() { return numeroLivro; }
	public void setNumeroLivro(String numeroLivro) { this.numeroLivro = numeroLivro; }

	public String getNumeroFolha() { return numeroFolha; }
	public void setNumeroFolha(String numeroFolha) { this.numeroFolha = numeroFolha; }

	public Boolean getIndDigital() { return indDigital; }
	public void setIndDigital(Boolean indDigital) { this.indDigital = indDigital; }

	public String getCodigoIrregularidade() { return codigoIrregularidade; }
	public void setCodigoIrregularidade(String codigoIrregularidade) {
												this.codigoIrregularidade = codigoIrregularidade; }

	public String getNihil() { return nihil; }
	public void setNihil(String nihil) { this.nihil = nihil; }

	public String getAVista() {	return aVista; }
	public void setAVista(String aVista) { this.aVista = aVista; }

	public String getTalaoNumeroLivro() { return talaoNumeroLivro; }
	public void setTalaoNumeroLivro(String talaoNumeroLivro) {
														this.talaoNumeroLivro = talaoNumeroLivro; }

	public String getConvenioNumerolivro() { return convenioNumerolivro; }
	public void setConvenioNumerolivro(String convenioNumerolivro) {
												this.convenioNumerolivro = convenioNumerolivro; }

	public String getNumeroTalao3Oficio() {	return numeroTalao3Oficio; }
	public void setNumeroTalao3Oficio(String numeroTalao3Oficio) {
													this.numeroTalao3Oficio = numeroTalao3Oficio; }

	public String getAceite() {	return aceite; }
	public void setAceite(String aceite) { this.aceite = aceite; }

	public String getFinsFalimentares() { return finsFalimentares; }
	public void setFinsFalimentares(String finsFalimentares) {
														this.finsFalimentares = finsFalimentares; }

	public String getArquivamento() { return arquivamento; }
	public void setArquivamento(String arquivamento) { this.arquivamento = arquivamento; }	
	
	// ---
		
	public void recalcularTotalEmolumento() {
		BigDecimal total = BigDecimal.ZERO;
		
//		total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
//		
//		for (MpItemPedido item : this.getMpItens()) {
//			if (item.getMpProduto() != null && item.getMpProduto().getId() != null) {
//				total = total.add(item.getValorTotal());
//			}
//		}
		//
		this.setTotalEmolumento(total);
	}

	public void recalcularTotalPagar() {
		BigDecimal total = BigDecimal.ZERO;
		
//		total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
//		
//		for (MpItemPedido item : this.getMpItens()) {
//			if (item.getMpProduto() != null && item.getMpProduto().getId() != null) {
//				total = total.add(item.getValorTotal());
//			}
//		}
		//
		this.setTotalPagar(total);
	}

	public void recalcularTotalDeposito() {
		BigDecimal total = BigDecimal.ZERO;
		
//		total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());
//		
//		for (MpItemPedido item : this.getMpItens()) {
//			if (item.getMpProduto() != null && item.getMpProduto().getId() != null) {
//				total = total.add(item.getValorTotal());
//			}
//		}
		//
		this.setTotalDeposito(total);
	}
	
}
