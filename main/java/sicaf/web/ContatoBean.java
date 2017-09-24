package sicaf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import sicaf.contato.Contato;
import sicaf.contato.ContatoRN;
import sicaf.pessoa.Pessoa;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.setor.Setor;
import sicaf.setor.SetorRN;
import sicaf.util.RNException;

@ManagedBean(name ="contatoBean")
@ViewScoped
public class ContatoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contato contato = new Contato();
	private Pessoa pessoa;
	private Setor set;
	private  List<Contato> lista;
	private ArrayList<Setor> listaSetores;
	private DualListModel<Setor> dLSetores;
	private List<Setor> themesSource = new ArrayList<Setor>();
	
	@PostConstruct
    public void init() {
//		List<Setor> themesSource = null;
		/*for(PessoaSetor pe:this.pessoa.getSetores()){
			themesSource.add(pe.getSetor());
		}*/
		
/*		SetorRN setorRN = new SetorRN();
		
		try {
			themesSource = setorRN.listar();
		
		} catch (RNException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List<Setor> themesTarget = new ArrayList<Setor>();
		dLSetores = new DualListModel<Setor>(themesSource, themesTarget);
		
	}
	
	public List<Setor> getThemesSource() {
		
		return themesSource;
	}

	public void setThemesSource(List<Setor> themesSource) {
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
		this.lista = contatoRN.listarPorIdPessoa(this.pessoa.getId());
		return lista;
	}
	
	public String editar(){
		return "/restrito/contato";
	}
	
	public String novo(){
		return "/restrito/contato";
	}
		
	public void excluir(){
		ContatoRN contatoRN = new ContatoRN();
		try {
			contatoRN.excluir(this.contato);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		this.contato.setPessoa(pessoa);
		this.contato.setSetores(this.getdLSetores().getTarget());
		
		ContatoRN contatoRN = new ContatoRN();
		try {
			contatoRN.salvar(this.contato);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		for(PessoaSetor pe:pessoa.getSetores()){
			themesSource.add(pe.getSetor());
		}
		this.pessoa = pessoa;
	}
	public List<Setor> getListaSetores() {
		return this.listaSetores;
	}
	
	public Setor getSet() {
		return set;
	}
	public void setSet(Setor set) {
		this.set = set;
	}
	public DualListModel<Setor> getdLSetores() {
		return dLSetores;
	}
	public void setdLSetores(DualListModel<Setor> dLSetores) {
		this.dLSetores = dLSetores;
	}
}
