package com.mpxds.mpbasic.model.sisJuri;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import com.mpxds.mpbasic.model.MpAuditoriaObjetoX;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@FilterDef(name="tenantFilter", parameters=@ParamDef(name="tenantId", type="string"))
@Filters(@Filter(name="tenantFilter", condition="tenant_id=:tenantId"))
@EqualsAndHashCode(of = "id")
public abstract class MpBaseEntitySJ implements Serializable, Cloneable {
	//
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
    private Long id;

	@Version
	@Column(columnDefinition = "integer DEFAULT 0", nullable = false)
	@Getter @Setter
	private Long version = 0L;

	@Column(name = "tenant_id", nullable = false) //, updatable=false)
	@Getter @Setter
	private String tenantId = "0";

	@Column(name = "ind_ativo", nullable = true)
	@Getter @Setter
    private Boolean indAtivo = true;

	@Column(name = "ind_exclusao", nullable = true)
	@Getter @Setter
	private Boolean indExclusao = false;
	
	@Embedded
	@Getter @Setter
	private MpAuditoriaObjetoX mpAuditoriaObjetoX;

	@Column(name="id_carga", nullable = true)
	@Getter @Setter
    private Long idCarga;
	
	// --- 
	
	public Object clone() throws CloneNotSupportedException { 
		return super.clone();
	}
	
}