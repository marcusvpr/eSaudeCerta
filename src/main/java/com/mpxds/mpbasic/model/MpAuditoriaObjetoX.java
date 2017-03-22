package com.mpxds.mpbasic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class MpAuditoriaObjetoX implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dthr_alt", nullable = true)
	@Getter @Setter
	private Date dtHrAlt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dthr_inc", nullable = true)
	@Getter @Setter
	private Date dtHrInc;

	@Column(name = "user_alt", nullable = true, length = 100)
	@Getter @Setter
	private String userAlt;

	@Column(name = "user_inc", nullable = true, length = 100)
	@Getter @Setter
	private String userInc;

}
