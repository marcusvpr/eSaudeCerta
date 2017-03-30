package com.mpxds.mpbasic.controller.iff;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mpxds.mpbasic.model.enums.iff.MpFuncionarioIff;
import com.mpxds.mpbasic.model.enums.iff.MpSetorIff;
import com.mpxds.mpbasic.model.enums.MpSexo;
import com.mpxds.mpbasic.model.iff.MpUsuarioIff;
import com.mpxds.mpbasic.repository.iff.MpUsuarioIffs;

import com.mpxds.mpbasic.security.MpSeguranca;
import com.mpxds.mpbasic.service.iff.MpUsuarioIffService;
import com.mpxds.mpbasic.exception.MpNegocioException;

import com.mpxds.mpbasic.util.jsf.MpFacesUtil;

@Named
@ViewScoped
public class MpCadastroUsuarioIffBean implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Inject
	private MpUsuarioIffs mpUsuarioIffs;

	@Inject
	private MpSeguranca mpSeguranca;

	@Inject
	private MpUsuarioIffService mpUsuarioIffService;

	// --- 
	
	private MpUsuarioIff mpUsuarioIff = new MpUsuarioIff();
	private MpUsuarioIff mpUsuarioIffAnt;

	private Boolean indEditavel = true;
	private Boolean indEditavelNav = true;
	private Boolean indNaoEditavel = false;
		
	private String txtModoTela = "";
	
	private MpSetorIff mpSetorIff;
	private List<MpSetorIff> mpSetorIffList;

	private MpSexo mpSexo;
	private List<MpSexo> mpSexoList;
	
	// ---------------------

	public MpCadastroUsuarioIffBean() {
		//
		if (null == this.mpUsuarioIff)
			this.limpar();
	}
	
	public void inicializar() {
		//
		System.out.println("MpCadastroUsuarioIffBean.inicializar() - Entrou 000");

		// Verificar Carga quando arquivo estiver vazio !
		if (mpUsuarioIffs.byNomeList().isEmpty())
			this.carregarUsuarioIff();
		//
		if (null == this.mpUsuarioIff) {
			this.limpar();
			//
			this.mpFirst(); // Posiciona no primeiro registro !!!
		}
		// Verifica TenantId ?
		if (!mpSeguranca.capturaTenantId().trim().equals("0")) {
			if (!this.mpUsuarioIff.getTenantId().trim().equals(mpSeguranca.capturaTenantId().trim())) {
				//
				MpFacesUtil.addInfoMessage("Error Violação! Contactar o Suporte!");
				//
				this.limpar();
				return;
			}
		}
		
		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
		//		
		this.indEditavelNav = this.mpSeguranca.getMpUsuarioLogado().getMpUsuario().
																		getIndBarraNavegacao();
		//
		this.mpSetorIffList = Arrays.asList(MpSetorIff.values());
		this.mpSexoList = Arrays.asList(MpSexo.values());
	}

	public void carregarUsuarioIff() {
		//
		List<MpFuncionarioIff> mpFuncionarioIffListX = Arrays.asList(MpFuncionarioIff.values());

		System.out.println("MpCadastroUsuarioIffBean.carregarUsuarioIff() / Size = " + 
																		mpFuncionarioIffListX.size());
		//
		for (MpFuncionarioIff mpFuncionarioIff : mpFuncionarioIffListX) {
			//
			MpUsuarioIff mpUsuarioIffX = new MpUsuarioIff();
			
			mpUsuarioIffX.setNome(mpFuncionarioIff.getNome());
			mpUsuarioIffX.setMpSetorIff(MpSetorIff.valueOf(mpFuncionarioIff.getSetor()));
			// Rotina para capturar a primeira e ultima palavra do Nome ...
			// ------------------------------------------------------------
			String[] palavras = mpFuncionarioIff.getNome().trim().split(" ");
			
			String primeiroNome = palavras[0];
			String ultimoNome = palavras[palavras.length-1];
			
			mpUsuarioIffX.setEmail(primeiroNome + "." + ultimoNome + "@iff.fiocruz.br");
			mpUsuarioIffX.setRamal(".");
			mpUsuarioIffX.setMpSexo(MpSexo.FEMININO);
			//
			System.out.println("MpCadastroUsuarioIffBean.carregarUsuarioIff() / Nome = " + 
																	mpFuncionarioIff.getNome());
			//
			this.mpUsuarioIffService.salvar(mpUsuarioIffX);
		}

	}
	
	public void salvar() {
		//
		this.mpUsuarioIff = this.mpUsuarioIffService.salvar(this.mpUsuarioIff);
		//
		MpFacesUtil.addInfoMessage("Usuário Iff... salvo com sucesso!");
	}
		
	// -------------------------------- //
	// -------- Trata Navegação ------- //
	// -------------------------------- //

	public void mpFirst() {
		//
		this.mpUsuarioIff = this.mpUsuarioIffs.porNavegacao("mpFirst", " "); 
		if (null == this.mpUsuarioIff)
			this.limpar();
		//
		this.txtModoTela = "( Início )";
	}
	
	public void mpPrev() {
		//
		if (null == this.mpUsuarioIff.getNome()) return;
		//
		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
		//
		this.mpUsuarioIff = this.mpUsuarioIffs.porNavegacao("mpPrev", mpUsuarioIff.getNome());
		if (null == this.mpUsuarioIff) {
			this.mpUsuarioIff = this.mpUsuarioIffAnt;
			//
			this.txtModoTela = "( Anterior - Inicio )";
		} else
			this.txtModoTela = "( Anterior )";
	}

	public void mpNew() {
		//
		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
		
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
		if (null == this.mpUsuarioIff.getId()) return;
		//
		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
		
		this.indEditavel = false;
		this.indEditavelNav = false;
		this.indNaoEditavel = true;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpUsuarioIff.getId()) return;
		//
		try {
			this.mpUsuarioIffs.remover(mpUsuarioIff);
			
			MpFacesUtil.addInfoMessage("Usuário Iff... " + this.mpUsuarioIff.getNome()
																	+ " excluído com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
		if (null == this.mpUsuarioIff.getMpSetorIff()) {
			//
			MpFacesUtil.addInfoMessage("Informar Setor !");
			return;
		}
		//
		try {
			this.salvar();
			//
		} catch (Exception e) {
			//
			MpFacesUtil.addInfoMessage("Erro na Gravação! ( " + e.toString());
			return;
		}

		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
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
		this.mpUsuarioIff = this.mpUsuarioIffAnt;		
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
		if (null == this.mpUsuarioIff.getNome()) return;
		//
		this.setMpUsuarioIffAnt(this.mpUsuarioIff);
		//
		this.mpUsuarioIff = this.mpUsuarioIffs.porNavegacao("mpNext", mpUsuarioIff.getNome());

		if (null == this.mpUsuarioIff) {
			this.mpUsuarioIff = this.mpUsuarioIffAnt;
			//
			this.txtModoTela = "( Próximo - Fim )";
		} else
			this.txtModoTela = "( Próximo )";
		//
	}
	
	public void mpEnd() {
		//
		this.mpUsuarioIff = this.mpUsuarioIffs.porNavegacao("mpEnd", "ZZZZZZZZ"); 
		if (null == this.mpUsuarioIff)
			this.limpar();
		//
		this.txtModoTela = "( Fim )";
	}
	
	public void mpClone() {
		//
		if (null == this.mpUsuarioIff.getId()) return;

		try {
			this.setMpUsuarioIffAnt(this.mpUsuarioIff);
			
			this.mpUsuarioIff = (MpUsuarioIff) this.mpUsuarioIff.clone();
			//
			this.mpUsuarioIff.setId(null);
			
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
		this.mpUsuarioIff = new MpUsuarioIff();
				
		this.mpUsuarioIff.setNome("");
		this.mpUsuarioIff.setEmail("");
		//
	}
		
	// ---
	
	public boolean isEditando() { return this.mpUsuarioIff.getId() != null; }
	
	public MpUsuarioIff getMpUsuarioIff() { return mpUsuarioIff; }
	public void setMpUsuarioIff(MpUsuarioIff mpUsuarioIff) { this.mpUsuarioIff = mpUsuarioIff; }

	public MpUsuarioIff getMpUsuarioIffAnt() { return mpUsuarioIffAnt; }
	public void setMpUsuarioIffAnt(MpUsuarioIff mpUsuarioIffAnt) { 
		//
		try {
			this.mpUsuarioIffAnt = (MpUsuarioIff) this.mpUsuarioIff.clone();
			this.mpUsuarioIffAnt.setId(this.mpUsuarioIff.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public MpSetorIff getMpSetorIff() { return mpSetorIff; }
	public void setMpSetorIff(MpSetorIff mpSetorIff) { this.mpSetorIff = mpSetorIff; }
	public List<MpSetorIff> getMpSetorIffList() { return mpSetorIffList; }
	
	public MpSexo getMpSexo() { return mpSexo; }
	public void setMpSexo(MpSexo mpSexo) { this.mpSexo = mpSexo; }
	public List<MpSexo> getMpSexoList() { return mpSexoList; }
		
	// --- 
	
	public boolean getIndEditavel() { return indEditavel; }
	public void setIndEditavel(Boolean indEditavel) { this.indEditavel = indEditavel; }
	
	public boolean getIndEditavelNav() { return indEditavelNav; }
	public void setIndEditavelNav(Boolean indEditavelNav) { 
														this.indEditavelNav = indEditavelNav; }
	
	public boolean getIndNaoEditavel() { return indNaoEditavel; }
	public void setIndNaoEditavel(Boolean indNaoEditavel) { this.indNaoEditavel = indNaoEditavel; }
	
	public String getTxtModoTela() { return txtModoTela; }
	public void setTxtModoTela(String txtModoTela) { this.txtModoTela = txtModoTela; }

}