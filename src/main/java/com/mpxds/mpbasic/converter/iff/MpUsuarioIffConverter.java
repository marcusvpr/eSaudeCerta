package com.mpxds.mpbasic.converter.iff;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.convert.ClientConverter;

import com.mpxds.mpbasic.model.iff.MpUsuarioIff;
import com.mpxds.mpbasic.repository.iff.MpUsuarioIffs;

@FacesConverter(forClass = MpUsuarioIff.class)
public class MpUsuarioIffConverter implements Converter, ClientConverter {

	@Inject
	private MpUsuarioIffs mpUsuarioIffss;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		//
		MpUsuarioIff retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = mpUsuarioIffss.porId(id);
		}
		//
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		//
		if (value != null) {
			if (null == ((MpUsuarioIff) value).getId())
				return "";
			else
				return ((MpUsuarioIff) value).getId().toString();
		}
		//
		return "";
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getConverterId() {
		return "com.mpxds.MpUsuarioIffs";
	}

}