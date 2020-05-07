package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.SerieDao;
import br.com.faculdadedelta.modelo.Genero;
import br.com.faculdadedelta.modelo.Status;
import br.com.faculdadedelta.modelo.Serie;

@ManagedBean
@SessionScoped
public class SerieController {

	private Serie serie = new Serie();
	private SerieDao dao = new SerieDao();
	private Genero generoSelecionado = new Genero();
	private Status statusSelecionado = new Status();
	private String PAGINA_CADASTRO_SERIE = "cadastroSerie.xhtml";

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Genero getGeneroSelecionado() {
		return generoSelecionado;
	}

	public void setGeneroSelecionado(Genero generoSelecionado) {
		this.generoSelecionado = generoSelecionado;
	}

	public Status getStatusSelecionado() {
		return statusSelecionado;
	}

	public void setStatusSelecionado(Status statusSelecionado) {
		this.statusSelecionado = statusSelecionado;
	}

	public void limparCampos() {
		generoSelecionado = new Genero();
		statusSelecionado = new Status();
		serie = new Serie();
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
			if (serie.getId() == null) {
				serie.setGenero(generoSelecionado);
				serie.setStatus(statusSelecionado);
				dao.incluir(serie);
				limparCampos();
				exibirMensagem(1, null);
			} else {
				serie.setGenero(generoSelecionado);
				serie.setStatus(statusSelecionado);
				dao.modificar(serie);
				limparCampos();
				exibirMensagem(2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_SERIE;
	}

	public String editar() {
		generoSelecionado = serie.getGenero();
		statusSelecionado = serie.getStatus();
		return PAGINA_CADASTRO_SERIE;
	}

	public String excluir() {
		try {
			serie.setGenero(generoSelecionado);
			serie.setStatus(statusSelecionado);
			dao.excluir(serie);
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_SERIE;
	}

	public List<Serie> getLista() {
		List<Serie> resp = new ArrayList<>();
		try {
			resp = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return resp;
	}

}
