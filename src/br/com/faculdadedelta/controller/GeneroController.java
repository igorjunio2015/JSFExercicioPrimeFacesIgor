package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.GeneroDao;
import br.com.faculdadedelta.modelo.Genero;

@ManagedBean
@SessionScoped
public class GeneroController {
	private Genero genero = new Genero();
	private GeneroDao dao = new GeneroDao();
	private String PAGINA_CADASTRO_GENERO = "cadastroGenero.xhtml";

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void limparCampos() {
		genero = new Genero();
	}

	private void exibirMensagem(Integer opc, Exception e) {
		String mensagem = null;
		if (opc == 1) {
			mensagem = "Incluido com sucesso!";
		} else if (opc == 2) {
			mensagem = "Modificado com sucesso!";
		} else if (opc == 3) {
			mensagem = "Erro ao tentar a operação " + e.getMessage();
		}
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (genero.getId() == null) {
				dao.incluir(genero);
				limparCampos();
				exibirMensagem(1, null);
			} else {
				dao.modificar(genero);
				limparCampos();
				exibirMensagem(2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_GENERO;
	}

	public String editar() {
		return PAGINA_CADASTRO_GENERO;
	}

	public String excluir() {
		try {
			dao.excluir(genero);
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_GENERO;
	}

	public List<Genero> getLista() {
		List<Genero> resp = new ArrayList<>();
		try {
			resp = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return resp;
	}

}
