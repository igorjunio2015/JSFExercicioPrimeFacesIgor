package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.StatusDao;
import br.com.faculdadedelta.modelo.Status;

@FacesConverter(value = "statusConverter")
public class StatusConverter implements Converter {

	private StatusDao dao = new StatusDao();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
			;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Status) valor).getId());
		}
		return null;
	}

}
