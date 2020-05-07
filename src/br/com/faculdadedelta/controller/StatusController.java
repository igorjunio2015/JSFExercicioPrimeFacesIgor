package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.StatusDao;
import br.com.faculdadedelta.modelo.Status;

@ManagedBean
@SessionScoped
public class StatusController {
	private Status status = new Status();
	private StatusDao dao = new StatusDao();
	private String PAGINA_CADASTRO_STATUS = "cadastroStatus.xhtml";

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void limparCampos() {
		status = new Status();
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
			if (status.getId() == null) {
				dao.incluir(status);
				limparCampos();
				exibirMensagem(1, null);
			} else {
				dao.modificar(status);
				limparCampos();
				exibirMensagem(2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_STATUS;
	}

	public String editar() {
		return PAGINA_CADASTRO_STATUS;
	}

	public String excluir() {
		try {
			dao.excluir(status);
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return PAGINA_CADASTRO_STATUS;
	}

	public List<Status> getLista() {
		List<Status> resp = new ArrayList<>();
		try {
			resp = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem(3, e);
		}
		return resp;
	}

}
