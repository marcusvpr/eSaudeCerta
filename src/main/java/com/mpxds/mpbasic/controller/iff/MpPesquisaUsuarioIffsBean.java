package com.mpxds.mpbasic.controller.iff;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.mpxds.mpbasic.model.enums.iff.MpSetorIff;
import com.mpxds.mpbasic.model.iff.MpUsuarioIff;
import com.mpxds.mpbasic.repository.iff.MpUsuarioIffs;
import com.mpxds.mpbasic.repository.filter.iff.MpUsuarioIffFilter;

@Named
@ViewScoped
public class MpPesquisaUsuarioIffsBean implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MpUsuarioIffs mpUsuarioIffs;
	
	private MpUsuarioIffFilter mpFiltro;
	private List<MpUsuarioIff> mpUsuarioIffsFiltrados;
	
	private MpUsuarioIff mpUsuarioIffSelecionado;
	
	private LazyDataModel<MpUsuarioIff> model;
	
	//---
	
	public MpPesquisaUsuarioIffsBean() {
		//
		mpFiltro = new MpUsuarioIffFilter();
		
		model = new LazyDataModel<MpUsuarioIff>() {
			//
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<MpUsuarioIff> load(int first, int pageSize, String sortField,
											SortOrder sortOrder, Map<String, Object> filters) {
				mpFiltro.getMpFilterOrdenacao().setPrimeiroRegistro(first);
				mpFiltro.getMpFilterOrdenacao().setQuantidadeRegistros(pageSize);
				mpFiltro.getMpFilterOrdenacao().setPropriedadeOrdenacao(sortField);
				mpFiltro.getMpFilterOrdenacao().setAscendente(SortOrder.DESCENDING.equals(
																					sortOrder));
				setRowCount(mpUsuarioIffs.quantidadeFiltrados(mpFiltro));
				
				return mpUsuarioIffs.filtrados(mpFiltro);
			}
		};
	}
	
	public void pesquisar() {
		//
		mpUsuarioIffsFiltrados = mpUsuarioIffs.filtrados(mpFiltro);
	}

	public void posProcessarXls(Object documento) {
		//
		HSSFWorkbook planilha = (HSSFWorkbook) documento;
		HSSFSheet folha = planilha.getSheetAt(0);
		HSSFRow cabecalho = folha.getRow(0);
		HSSFCellStyle estiloCelula = planilha.createCellStyle();
		Font fonteCabecalho = planilha.createFont();
		
		fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
		fonteCabecalho.setBold(true);
		fonteCabecalho.setFontHeightInPoints((short) 16);
		
		estiloCelula.setFont(fonteCabecalho);
		estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
			cabecalho.getCell(i).setCellStyle(estiloCelula);
		}
	}
	
	// ---

	public MpSetorIff[] getMpSetorIffs() { return MpSetorIff.values(); }
	
	public MpUsuarioIffFilter getMpFiltro() { return mpFiltro; }

	public LazyDataModel<MpUsuarioIff> getModel() { return model; }

	public List<MpUsuarioIff> getMpUsuarioIffsFiltrados() { return mpUsuarioIffsFiltrados; }

	public MpUsuarioIff getMpUsuarioIffSelecionado() { return mpUsuarioIffSelecionado; }
	public void setMpUsuarioIffSelecionado(MpUsuarioIff mpUsuarioIffSelecionado) {
										this.mpUsuarioIffSelecionado = mpUsuarioIffSelecionado; }
	
}