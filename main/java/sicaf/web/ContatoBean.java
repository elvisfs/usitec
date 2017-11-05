package sicaf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import sicaf.contato.Contato;
import sicaf.contato.ContatoRN;
import sicaf.pessoa.Pessoa;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.pessoaSetor.PessoaSetorRN;
import sicaf.setor.Setor;
import sicaf.util.RNException;

@ManagedBean(name = "contatoBean")
@ViewScoped
public class ContatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contato contato = new Contato();
	private Pessoa pessoa;
	private List<Contato> lista;
	private ArrayList<Setor> listaSetores = null;
	private DualListModel<PessoaSetor> dLSetores;
	private List<PessoaSetor> themesSource = new ArrayList<PessoaSetor>();
	private List<PessoaSetor> themesTarget = new ArrayList<PessoaSetor>();

	@PostConstruct
	public void init() {
		PessoaSetorRN pessoaSetorRN = new PessoaSetorRN();
		try {
			this.themesSource.addAll(pessoaSetorRN.listaSetoresPorPessoa(this.pessoa));
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dLSetores = new DualListModel<PessoaSetor>(this.themesSource, this.themesTarget);

	}

	public List<PessoaSetor> getThemesSource() {
		PessoaSetorRN pessoaSetorRN = new PessoaSetorRN();
		try {
			this.themesSource = pessoaSetorRN.listaSetoresPorPessoa(this.pessoa);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return themesSource;
	}

	public void setThemesSource(List<PessoaSetor> themesSource) {
		this.themesSource = themesSource;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getLista() {
		ContatoRN contatoRN = new ContatoRN();
		this.lista = contatoRN.listarPorPessoa(this.pessoa);
		return lista;
	}

	public String editar() {
		return "/restrito/contato";
	}

	public String novo() {
		 return "contato?faces-redirect=true&includeViewParams=true&pessoa="+pessoa;
	}

	public String excluir() {
		ContatoRN contatoRN = new ContatoRN();
		try {
			contatoRN.excluir(this.contato);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "contato?faces-redirect=true&includeViewParams=true&pessoa="+pessoa;
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ContatoRN contatoRN = new ContatoRN();
		PessoaSetorRN pessoaSetorRN = new PessoaSetorRN();
		try {

			this.contato.setPessoa(this.pessoa);
			if (this.contato.getId() != null) {
				for (PessoaSetor p : this.contato.getSetores()) {
					pessoaSetorRN.salvar(p);
				}
			}
			this.contato = contatoRN.salvar(this.contato);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
	}

	public void before(ComponentSystemEvent event) {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			PessoaSetorRN pessoaSetorRN = new PessoaSetorRN();

			try {
				this.themesSource.addAll(pessoaSetorRN.listaSetoresPorPessoa(this.pessoa));
			} catch (RNException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void onTransfer(TransferEvent event) {
		PessoaSetor pessoaSetor = null;
		for (Object item : event.getItems()) {
			if (event.isAdd()) {
				pessoaSetor = (PessoaSetor) item;
				pessoaSetor.setContato(this.contato);
				this.contato.getSetores().add(pessoaSetor);

			} else if (event.isRemove()) {
				pessoaSetor = (PessoaSetor) item;
				pessoaSetor.setContato(null);
			}
		}

	}

	public List<Setor> getListaSetores() {
		return this.listaSetores;
	}

	public DualListModel<PessoaSetor> getdLSetores() {
		return dLSetores;
	}

	public void setdLSetores(DualListModel<PessoaSetor> dLSetores) {
		this.dLSetores = dLSetores;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;
	}

	public List<PessoaSetor> getThemesTarget() {

		return themesTarget;
	}

	public void setThemesTarget(List<PessoaSetor> themesTarget) {
		this.themesTarget = themesTarget;
	}

}
