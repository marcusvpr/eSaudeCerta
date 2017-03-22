package com.mpxds.mpbasic.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.mpxds.mpbasic.util.MpAppUtil;
import com.mpxds.mpbasic.util.jsf.MpFacesUtil;

@ManagedBean
@Named
@ViewScoped
public class MpGeraArtefatosBean implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	private boolean makeAsReadOnly;
	//
    private String nomeClasse = "Xyyyyyy";
    private String sqlFonte;
    
    private String resultFonteModel;
    private String resultFonteFilter;
    
    private Boolean indGeraModel = false;
    private Boolean indGeraFilter = false;
    private Boolean indGeraRepository = false;       
    private Boolean indGeraService = false;
    private Boolean indGeraConverter = false;
    private Boolean indGeraTelaCadastro = false;
    private Boolean indGeraBotaoCadastro = false;
    private Boolean indGeraControllerCadastro = false;
    private Boolean indGeraTelaPesquisa = false;
    private Boolean indGeraControllerPesquisa = false;
    
    // ---
    	
    private String artefatoSels = "";
    private Boolean indSelecao = false;
        
    private StringBuilder outCampos = new StringBuilder();  
    //
    private StreamedContent fileModelX;
    private StreamedContent fileFilterX;
	
    // -------------------------------- Inicio ------------------------------------

    public void geraArtefatos() {
    	//
    	String msgX = "";
    	if (null == this.nomeClasse || this.nomeClasse.isEmpty())
    		msgX += "(Nome Classe)";
    	//
    	if (this.indGeraModel == false 
    	&&  this.indGeraFilter == false
    	&&  this.indGeraRepository == false
    	&&  this.indGeraService == false
    	&&  this.indGeraConverter == false
    	&&  this.indGeraTelaCadastro == false
    	&&  this.indGeraBotaoCadastro == false 
    	&&  this.indGeraControllerCadastro == false
    	&&  this.indGeraTelaPesquisa == false 
    	&&  this.indGeraControllerPesquisa == false)
    		msgX += "(Selecione uma opção)";
    	
    	if (msgX.isEmpty())
    		assert(true); // nop
    	else{
    		//
    		MpFacesUtil.addInfoMessage("Verificar ! " + msgX);
    		return;
    	}
    	//
    	if (this.indGeraModel) trataGeraModel();    	
    	if (this.indGeraFilter) trataGeraFilter();    	
    	//
    	MpFacesUtil.addInfoMessage("Geração Artefato(s)... Efetuada ! ( Classe = " + this.nomeClasse +
    		" ) / Ind.s (M= " + this.indGeraModel +
	    	" /F= " + this.indGeraFilter + " /R= " + this.indGeraRepository +
	    	" /S= " + this.indGeraService + " /C= " + this.indGeraConverter + 
	    	" /TC= " + this.indGeraTelaCadastro + " /BC= " + this.indGeraBotaoCadastro +
	    	" /CC= " + this.indGeraControllerCadastro + " /TP= " + this.indGeraTelaPesquisa +
	    	" /CP= " + this.indGeraControllerPesquisa);
    }

    private void trataGeraModel() {
    	//
//	    MpAppUtil.PrintarLn("MpGeraartafatosBean.trataGeraModel() - Classe = " + this.nomeClasse.trim());
    	
        this.outCampos = new StringBuilder();  
        Boolean indCampos = true;

        this.trataCampoSQL();
    	//
        try {
        	InputStream is = MpGeraArtefatosBean.class.getResourceAsStream("/artefatos/base/MpDolar.java");
        	
        	InputStreamReader r = new InputStreamReader(is);
        	BufferedReader br = new BufferedReader(r);
            //
            StringBuilder out = new StringBuilder();
            String line;
            //
			while ((line = br.readLine()) != null) {
				// Ignora campos exemplos ...
	    		if (line.indexOf("@Column") >= 0 
		    	||	line.indexOf("@Getter") >= 0
			    ||	line.indexOf("private Date") >= 0) {
	    			// Trata inclusão de campos ...
	    			if (indCampos) {
	    				//
	    			    out.append(outCampos.toString());

	    			    indCampos = false;
	    			}
	    			//
	    			continue; 
	    		}
				//
				line = line.replace("Dolar", this.nomeClasse.trim());					
				line = line.replace("dolar", this.nomeClasse.trim().substring(0,1).toLowerCase() +
											 this.nomeClasse.trim().substring(1));
				//		
			    out.append(line + "\n");
			    
//			    MpAppUtil.PrintarLn("MpGeraartafatosBean.trataGeraModel() - Line = " + line);
			}
			//
			this.resultFonteModel = out.toString();
			//
		} catch (IOException e) {
    		MpFacesUtil.addInfoMessage("trataGeraModel() Error : e = " + e);
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraModel() - Error Geração : e = " + e);
		}    	

		// Trata gravação arquivo...
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();						

		try {
			String nomeArquivo = "Mp" + this.nomeClasse.trim() + ".java";
			
			File fileModel = new File(extContext.getRealPath("//resources//artefatos//" + nomeArquivo));
			FileWriter fileWriter = new FileWriter(fileModel);
			
			fileWriter.write(this.resultFonteModel);
			
			fileWriter.flush();
			fileWriter.close();
			//
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraModel() - File = " + fileModel.getAbsolutePath());
		    //
		    this.trataFileDownload(fileModel, "model");
		    //
		} catch (IOException e) {
    		MpFacesUtil.addInfoMessage("trataGeraModel() Error Gravação : e = " + e);
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraModel() - Error Gravação : e = " + e);
		}
    }
    
    private void trataCampoSQL() {
    	//
    	if (null == this.sqlFonte || this.sqlFonte.isEmpty()) return;    	
    	//    	
    	String campo = "";
    	String formato = "";
    	String tamanho = "";
    	String indNull = "";
    	
    	String[] linhas = this.sqlFonte.split("\n");
    	
    	for(String linha: linhas) {
    		//
//    		MpAppUtil.PrintarLn("MpGeraartafatosBean.trataCampoSQL() (Linha = " + linha);
    		//
    		if (linha.indexOf("NULL") >= 0) {
    			//
    			if (linha.indexOf("[quick~rs]") >= 0) continue;
    			// 
    			String wordCs[] = linha.split("]");
    			campo = wordCs[0].trim();
    			campo = campo.replace("[", "");
    			campo = campo.replace("]", "");
    			campo = campo.replace("_", "");
    			//
        		formato = "String";
        		if (linha.indexOf("[decimal]") >= 0
            	||  linha.indexOf("[numeric]") >= 0) {
        			formato = "BigDecimal";
        			// Trata captura tamanho campo DECIMAL !
        			int posX1 = linha.indexOf("(");
        			int posX2 = linha.indexOf(",");
        			int posX3 = linha.indexOf(")");
        			//
        			tamanho = "precision = " + linha.substring(posX1+1, posX2) + ", scale = " + 
        									   linha.substring(posX2+1, posX3);
        		} else
                if (linha.indexOf("[text]") >= 0)
                	tamanho = "";
                else
                if (linha.indexOf("[image]") >= 0) {
                	formato = " byte[]";
                   	tamanho = "";
    			} else
            	if (linha.indexOf("[bit]") >= 0) {
            		formato = "Boolean";
            		tamanho = "";
            	} else
        		if (linha.indexOf("time]") >= 0) {
        			formato = "Date";
        			tamanho = "";
        		} else
            	if (linha.indexOf("int]") >= 0
            	||  linha.indexOf("[float]") >= 0) {
        			formato = "Integer";
        			tamanho = "";
        		} else {
        			//
        			String wordTs[] = linha.split(" ");
        			tamanho = wordTs[2];
        			tamanho = tamanho.replace("(", "");
        			tamanho = tamanho.replace(")", "");
        		}
        		//
        		if (linha.indexOf("NOT NULL") >= 0) {
        			indNull = "false";
    			    this.outCampos.append("        @NotNull(message = \"Valor é obrigatório\")\n");
        		} else
        			indNull = "true";    			
    			//
        		if (formato.equals("byte[]")) {
    			    this.outCampos.append("        @Lob\n");
    			    this.outCampos.append(
    			    		"        @Column(columnDefinition = \"blob\", nullable = true, length = 10000)\n");
        		} else
        		if (formato.equals("Date")) {
    			    this.outCampos.append("        @Temporal(TemporalType.TIMESTAMP)\n");
    			    this.outCampos.append("        @Column(nullable = " + indNull + ")\n");
        		} else
            	if (formato.equals("Integer") 
            	||  formato.equals("Boolean")) 
    			    this.outCampos.append("        @Column(nullable = " + indNull + ")\n");
        		else
            	if (formato.equals("BigDecimal")) 
            		this.outCampos.append("        @Column(nullable = " + indNull + ", " + tamanho + ")\n");
            	else
            		if (tamanho.isEmpty())
            			this.outCampos.append("        @Column(nullable = " + indNull + ")\n");
            		else
            			this.outCampos.append("        @Column(nullable = " + indNull + ", length = " + 
            																				tamanho + ")\n");
        		//
        		this.outCampos.append("        @Getter @Setter\n");
        		this.outCampos.append("        private " + formato + " " + campo.substring(0,1).toLowerCase() +
        																   campo.substring(1) + ";\n");
        		this.outCampos.append("\n");
    		}
    	}
    	//
//		MpAppUtil.PrintarLn("MpGeraartafatosBean.trataCampoSQL() (campo = " + campo);
    }

    private void trataGeraFilter() {
    	//
        try {
        	InputStream is = MpGeraArtefatosBean.class.getResourceAsStream("/artefatos/base/MpDolarFilter.java");
        	
        	InputStreamReader r = new InputStreamReader(is);
        	BufferedReader br = new BufferedReader(r);
            //
            StringBuilder out = new StringBuilder();
            String line;
            //
			while ((line = br.readLine()) != null) {
				//
				line = line.replace("Dolar", this.nomeClasse.trim());					
				line = line.replace("dolar", this.nomeClasse.trim().substring(0,1).toLowerCase() +
											 this.nomeClasse.trim().substring(1));
				//		
			    out.append(line + "\n");
			}
			//
			this.resultFonteFilter = out.toString();
			//
		} catch (IOException e) {
    		MpFacesUtil.addInfoMessage("trataGeraRepositoryFilter() Error : e = " + e);
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraRepositoryFilter() - Error Geração : e = " + e);
		}    	

		// Trata gravação arquivo...
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();						

		try {
			String nomeArquivo = "Mp" + this.nomeClasse.trim() + "Filter.java";
			
			File fileFilter = new File(extContext.getRealPath("//resources//artefatos//" + nomeArquivo));
			FileWriter fileWriter = new FileWriter(fileFilter);
			
			fileWriter.write(this.resultFonteFilter);
			
			fileWriter.flush();
			fileWriter.close();
			//
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraModel() - File = " + fileFilter.getAbsolutePath());
		    //
		    this.trataFileDownload(fileFilter, "filter");
		    //
		} catch (IOException e) {
    		MpFacesUtil.addInfoMessage("trataGeraModel() Error Gravação : e = " + e);
		    MpAppUtil.PrintarLn("MpGerartafatosBean.trataGeraModel() - Error Gravação : e = " + e);
		}
    }
        
    // ====
    
    public void trataFileDownload(File fileDown, String tipo) {
    	//
    	ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    	
        InputStream streamX;
        
		try {
			streamX = new FileInputStream(fileDown);

			if (tipo.equals("model"))
				this.fileModelX = new DefaultStreamedContent(streamX, externalContext.getMimeType(fileDown.getName()),
																				"downloaded_" + fileDown.getName());
			else
			if (tipo.equals("filter"))
				this.fileFilterX = new DefaultStreamedContent(streamX, externalContext.getMimeType(fileDown.getName()),
																				"downloaded_" + fileDown.getName());
			//
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//
    }
    
//    public StreamedContent prepDownloadModel() throws Exception {
//    	//
//        StreamedContent download=new DefaultStreamedContent();
//        File file = new File("C:\\file.csv");
//        InputStream input = new FileInputStream(file);
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        download = new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName());
//        System.out.println("PREP = " + download.getName());
//        return download;
//    }    
        
    // ========================================================
    
    public String getNomeClasse() { return this.nomeClasse; }
    public void setNomeClasse(String newNomeClasse) { this.nomeClasse = newNomeClasse; }
    
    public String getSqlFonte() { return this.sqlFonte; }
    public void setSqlFonte(String newSqlFonte) { this.sqlFonte = newSqlFonte; }
    
    public String getResultFonteModel() { return this.resultFonteModel; }
    public void setResultFonteModel(String newResultFonteModel) { this.resultFonteModel = newResultFonteModel; }

    public String getResultFonteFilter() { return this.resultFonteFilter; }
    public void setResultFonteFilter(String newResultFonteFilter) { this.resultFonteFilter = newResultFonteFilter; }

    public boolean getIndGeraModel() { return this.indGeraModel; }
    public void setIndGeraModel(boolean newIndGeraModel) { this.indGeraModel = newIndGeraModel; }

    public boolean getIndGeraFilter() { return this.indGeraFilter; }
    public void setIndGeraFilter(boolean newIndGeraFilter) { this.indGeraFilter = newIndGeraFilter; }

    public boolean getIndGeraRepository() { return this.indGeraRepository; }
    public void setIndGeraRepository(boolean newIndGeraRepository) { this.indGeraRepository = newIndGeraRepository; }

    public boolean getIndGeraService() { return this.indGeraService; }
    public void setIndGeraService(boolean newIndGeraService) { this.indGeraService = newIndGeraService; }

    public boolean getIndGeraConverter() { return this.indGeraConverter; }
    public void setIndGeraConverter(boolean newIndGeraConverter) { this.indGeraConverter = newIndGeraConverter; }

    public boolean getIndGeraTelaCadastro() { return this.indGeraTelaCadastro; }
    public void setIndGeraTelaCadastro(boolean newIndGeraTelaCadastro) { 
    															this.indGeraTelaCadastro = newIndGeraTelaCadastro; }

    public boolean getIndGeraBotaoCadastro() { return this.indGeraBotaoCadastro; }
    public void setIndGeraBotaoCadastro(boolean newIndGeraBotaoCadastro) { 
    														this.indGeraBotaoCadastro = newIndGeraBotaoCadastro; }

    public boolean getIndGeraControllerCadastro() { return this.indGeraControllerCadastro; }
    public void setIndGeraControllerCadastro(boolean newIndGeraControllerCadastro) { 
    												this.indGeraControllerCadastro = newIndGeraControllerCadastro; }

    public boolean getIndGeraTelaPesquisa() { return this.indGeraTelaPesquisa; }
    public void setIndGeraTelaPesquisa(boolean newIndGeraTelaPesquisa) { 
    															this.indGeraTelaPesquisa = newIndGeraTelaPesquisa; }

    public boolean getIndGeraControllerPesquisa() { return this.indGeraControllerPesquisa; }
    public void setIndGeraControllerPesquisa(boolean newIndGeraControllerPesquisa) { 
    												this.indGeraControllerPesquisa = newIndGeraControllerPesquisa; }
    
    // ---
    
    public boolean getMakeAsReadOnly() { return this.makeAsReadOnly; }
    public void setMakeAsReadOnly(boolean newMakeAsReadOnly) { this.makeAsReadOnly = newMakeAsReadOnly; }

    public void setArtefatoSels(String newArtefatoSels) { this.artefatoSels = newArtefatoSels; }
    public String getArtefatoSels() { return this.artefatoSels; }
	
    public void setIndSelecao(Boolean newIndSelecao) { this.indSelecao = newIndSelecao; }
    public Boolean getIndSelecao() { return this.indSelecao; }
    
    // --- FileDownload ! 
    
    public void setFileModelX(StreamedContent fileModelX) { this.fileModelX = fileModelX; }
    public StreamedContent getFileModelX() { return fileModelX; }
    
    public void setFileFilterX(StreamedContent fileFilterX) { this.fileFilterX = fileFilterX; }
    public StreamedContent getFileFilterX() { return fileFilterX; }
    
}
