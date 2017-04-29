package sicaf.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sicaf.setor.Setor;
import sicaf.setor.SetorRN;
import sicaf.util.DAOException;
import sicaf.util.RNException;

@ManagedBean(name = "setorBean")
@RequestScoped
public class SetorBean implements Serializable {
	
	private Setor setor = new Setor();
	private List<Setor> lista;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getLista() {
		if (this.lista == null) {
			SetorRN setorRN = new SetorRN();
			try {
				this.lista = setorRN.listar();
			} catch (RNException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.lista;
	}

	public void setLista(List<Setor> lista) {
		this.lista = lista;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();	
		SetorRN setorRN = new SetorRN();
		try {
			setorRN.salvar(this.setor);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
			this.setor = new Setor();
		} catch (DAOException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));;
		} 
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		SetorRN setorRN = new SetorRN();
		try {
			setorRN.excluir(this.setor);
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
		this.lista = null;
	}
	
	public String editar() {
		
		return "/restrito/setor";
	}

	public String novo() {

		return "/restrito/setor";
	}
}
