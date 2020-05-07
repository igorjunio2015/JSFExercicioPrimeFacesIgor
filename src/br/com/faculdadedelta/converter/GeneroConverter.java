package br.com.faculdadedelta.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.GeneroDao;
import br.com.faculdadedelta.modelo.Genero;

@FacesConverter(value = "generoConverter")
public class GeneroConverter implements Converter {

	private GeneroDao dao = new GeneroDao();

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
			return String.valueOf(((Genero) valor).getId());
		}
		return null;
	}

}