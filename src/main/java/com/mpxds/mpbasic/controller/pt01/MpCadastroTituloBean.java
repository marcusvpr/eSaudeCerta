package com.mpxds.mpbasic.controller.pt01;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mpxds.mpbasic.model.pt01.MpTitulo;

import com.mpxds.mpbasic.repository.pt01.MpTitulos;

import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.service.pt01.MpTituloService;
import com.mpxds.mpbasic.exception.MpNegocioException;

import com.mpxds.mpbasic.util.jsf.MpFacesUtil;

@Named
@ViewScoped
public class MpCadastroTituloBean implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Inject
	private MpTitulos mpTitulos;

	@Inject
	private MpSeguranca mpSeguranca;

	@Inject
	private MpTituloService mpTituloService;

	// --- 
	
	private MpTitulo mpTitulo = new MpTitulo();
	private MpTitulo mpTituloAnt;

	private Boolean indEditavel = true;
	private Boolean indEditavelNav = true;
	private Boolean indNaoEditavel = false;
	
	private String txtModoTela = "";
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// ---------------------

	public MpCadastroTituloBean() {
		//
		if (null == this.mpTitulo)
			this.limpar();
	}
	
	public void inicializar() {
		//
		if (null == this.mpTitulo) {
			this.limpar();
			//
			this.mpFirst(); // Posiciona no primeiro registro !!!
		}
		
		this.setMpTituloAnt(this.mpTitulo);
		//
		this.indEditavelNav = this.mpSeguranca.getMpUsuarioLogado().getMpUsuario().
																			getIndBarraNavegacao();
	}
				
	public void salvar() {
		//
		this.mpTitulo = this.mpTituloService.salvar(this.mpTitulo);
		//
		MpFacesUtil.addInfoMessage("Titulo... salvo com sucesso!");
	}
			
	// -------------------------------- //
	// -------- Trata Navegação ------- //
	// -------------------------------- //

	public void mpFirst() {
		//
		try {
			this.mpTitulo = this.mpTitulos.porNavegacao("mpFirst", sdf.parse("01/01/1900"));
			if (null == this.mpTitulo)
				this.limpar();
			//
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		//
		this.txtModoTela = "( Início )";
	}
	
	public void mpPrev() {
		//
		if (null == this.mpTitulo.getDataProtocolo()) return;
		//
		this.setMpTituloAnt(this.mpTitulo);
		//
		this.mpTitulo = this.mpTitulos.porNavegacao("mpPrev", mpTitulo.getDataProtocolo());
		if (null == this.mpTitulo) {
			this.mpTitulo = this.mpTituloAnt;
			//
			this.txtModoTela = "( Anterior - Inicio )";
		} else
			this.txtModoTela = "( Anterior )";
		//
	}

	public void mpNew() {
		//
		this.setMpTituloAnt(this.mpTitulo);
		
		this.limpar();
		//
		this.indEditavel = false;
		this.indEditavelNav = false;
		this.indNaoEditavel = true;
		//
		this.txtModoTela = "( Novo )";
	}
	
	public void mpEdit() {
		//
		if (null == this.mpTitulo.getId()) return;
		//
		this.setMpTituloAnt(this.mpTitulo);
		
		this.indEditavel = false;
		this.indEditavelNav = false;
		this.indNaoEditavel = true;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpTitulo.getId()) return;
		//
		try {
			this.mpTitulos.remover(mpTitulo);
			
			MpFacesUtil.addInfoMessage("Titulo... " + this.sdf.format(
						this.mpTitulo.getDataProtocolo()) + " ... excluído com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
		try {
			this.salvar();
			//
		} catch (Exception e) {
			//
			MpFacesUtil.addInfoMessage("Erro na Gravação! ( " + e.toString());
			return;
		}

		this.setMpTituloAnt(this.mpTitulo);
		//
		this.indEditavel = true;
		this.indEditavelNav = this.mpSeguranca.getMpUsuarioLogado().getMpUsuario().
																			getIndBarraNavegacao();
		this.indNaoEditavel = false;
		//
		this.txtModoTela = "";
	}
	
	public void mpDesfaz() {
		//
		this.mpTitulo = this.mpTituloAnt;		
		//
		this.indEditavel = true;
		this.indEditavelNav = this.mpSeguranca.getMpUsuarioLogado().getMpUsuario().
																			getIndBarraNavegacao();
		this.indNaoEditavel = false;
		//
		this.txtModoTela = "";
		//
	}
	
	public void mpNext() {
		//
		if (null == this.mpTitulo.getDataProtocolo()) return;
		//
		this.setMpTituloAnt(this.mpTitulo);
		//
		this.mpTitulo = this.mpTitulos.porNavegacao("mpNext", mpTitulo.getDataProtocolo());
		if (null == this.mpTitulo) {
			this.mpTitulo = this.mpTituloAnt;
			//
			this.txtModoTela = "( Próximo - Fim )";
		} else
			this.txtModoTela = "( Próximo )";
		//
	}

	public void mpEnd() {
		//
		try {
			this.mpTitulo = this.mpTitulos.porNavegacao("mpEnd", sdf.parse("01/01/2099"));
			if (null == this.mpTitulo)
				this.limpar();
			//
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		//
		this.txtModoTela = "( Fim )";
	}
	
	public void mpClone() {
		//
		if (null == this.mpTitulo.getId()) return;

		try {
			this.setMpTituloAnt(this.mpTitulo);

			this.mpTitulo = (MpTitulo) this.mpTitulo.clone();
			//
			this.mpTitulo.setId(null);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//
		this.indEditavel = false;
		this.indEditavelNav = false;
		this.indNaoEditavel = true;
		//
		this.txtModoTela = "( Clone )";
	}

	// ---
	
	private void limpar() {
		//
		this.mpTitulo = new MpTitulo();
		this.mpTitulo.setDataProtocolo(new Date());
		//
	}
		
	// ---
	
	public boolean isEditando() { return this.mpTitulo.getId() != null; }
	
	public MpTitulo getMpTitulo() { return mpTitulo; }
	public void setMpTitulo(MpTitulo mpTitulo) { this.mpTitulo = mpTitulo; }

	public MpTitulo getMpTituloAnt() { return mpTituloAnt; }
	public void setMpTituloAnt(MpTitulo mpTituloAnt) { 
		//
		try {
			this.mpTituloAnt = (MpTitulo) this.mpTitulo.clone();
			this.mpTituloAnt.setId(this.mpTitulo.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	// --- 
	
	public boolean getIndEditavel() { return indEditavel; }
	public void setIndEditavel(Boolean indEditavel) { this.indEditavel = indEditavel; }
	
	public boolean getIndEditavelNav() { return indEditavelNav; }
	public void setIndEditavelNav(Boolean indEditavelNav) { this.indEditavelNav = indEditavelNav; }
	
	public boolean getIndNaoEditavel() { return indNaoEditavel; }
	public void setIndNaoEditavel(Boolean indNaoEditavel) { this.indNaoEditavel = indNaoEditavel; }
	
	public String getTxtModoTela() { return txtModoTela; }
	public void setTxtModoTela(String txtModoTela) { this.txtModoTela = txtModoTela; }

}