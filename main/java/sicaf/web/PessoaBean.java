package sicaf.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sicaf.pessoa.Pessoa;
import sicaf.pessoa.PessoaRN;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.TipoPessoa;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable{

	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> lista;
	private List<PessoaSetor> listaSetores;
	private List<TipoPessoa> tipos;
		
	
	
	@PostConstruct
	public void init(){
		tipos = Arrays.asList(TipoPessoa.values());
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getLista() {
		if (this.lista == null) {
			PessoaRN pessoaRN = new PessoaRN();
			this.lista = pessoaRN.listar();
		}
		return this.lista;
	}
	
	public List<PessoaSetor> getListaSetores() {
		return listaSetores;
	}
	
	public void setListaSetores(List<PessoaSetor> listaSetores) {
		this.listaSetores = listaSetores;
	}
	
	public List<TipoPessoa> getTipos() {
		return tipos;
	}
	public void setTipos(List<TipoPessoa> tipos) {
		this.tipos = tipos;
	}
	
	public String novo() {

		return "/restrito/pessoa";
	}
	
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();	
		PessoaRN pessoaRN = new PessoaRN();
		
		pessoaRN.salvar(this.pessoa);
		context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
//			this.pessoa = new Pessoa(); 
	}
	
	public String excluir(){
		PessoaRN pessoaRN = new PessoaRN();
		pessoaRN.excluir(this.pessoa);
		this.lista = null;
		return null;
	}
	
}
