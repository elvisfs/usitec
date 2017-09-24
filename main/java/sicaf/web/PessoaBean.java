package sicaf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sicaf.cidade.Cidade;
import sicaf.cidade.CidadeRN;
import sicaf.pessoa.Pessoa;
import sicaf.pessoa.PessoaRN;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.pessoaSetor.PessoaSetorRN;
import sicaf.setor.Setor;
import sicaf.setor.SetorRN;
import sicaf.util.RNException;
import sicaf.util.TipoPessoa;

@ManagedBean(name = "pessoaBean")
@ViewScoped

public class PessoaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoContato;
	private Map<String, String> tiposContato;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> lista;
	private List<TipoPessoa> tipos;
	private Boolean btnContatoDisable;
	private List<Setor> setoresSelecionados;
	private List<PessoaSetor> setores = new ArrayList<PessoaSetor>();

	private PessoaSetor pessoaSetor;
	private List<PessoaSetor> setoresExcluir = new ArrayList<PessoaSetor>();

	private Boolean selecionado;
	private ArrayList<Setor> listaSetores;

	@PostConstruct
	public void init() {
		tipos = Arrays.asList(TipoPessoa.values());
		tiposContato = new HashMap<String, String>();
		tiposContato.put("Cliente", "cliente");
		tiposContato.put("Fornecedor", "fornecedor");
		tiposContato.put("Funcionário", "funcionario");

		SetorRN setorRN = new SetorRN();
		try {
			this.listaSetores = setorRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getLista() {
		PessoaRN pessoaRN = new PessoaRN();
		this.lista = pessoaRN.listar(tipoContato);
		return this.lista;
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

	public String editar() {
		return "/restrito/pessoa";
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		PessoaRN pessoaRN = new PessoaRN();
		PessoaSetorRN pessoaSetorRN = new PessoaSetorRN();
		try {
			for(PessoaSetor pessoaSetor:this.setoresExcluir)
				pessoaSetorRN.excluirSetor(pessoaSetor);
			pessoaRN.salvar(this.pessoa);			
			context.addMessage(null, (new FacesMessage("Registro atualizado com sucesso")));
		} catch (RNException e) {
			// TODO Auto-generated catch block
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}

	}

	public void excluirSetor() {
		if(this.pessoaSetor.getId()!=null)
			this.setoresExcluir.add(this.pessoaSetor);
		this.pessoa.getSetores().remove(this.pessoaSetor);
		
	}

	public String voltar() {
		pessoa = new Pessoa();
		return "pessoas";
	}

	public String excluir() {
		PessoaRN pessoaRN = new PessoaRN();
		try {
			pessoaRN.excluir(this.pessoa);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lista = null;
		return null;
	}

	public void incluirSetor() {
		boolean setorEncontrado = false;
		for (Setor set : this.setoresSelecionados) {
			PessoaSetor pessoaSetor = new PessoaSetor();
			pessoaSetor.setSetor(set);
			pessoaSetor.setPessoa(pessoa);
			for(PessoaSetor p:this.pessoa.getSetores()){
				if(pessoaSetor.getPessoa().equals(p.getPessoa()) 
						&& pessoaSetor.getSetor().equals(p.getSetor()))
					setorEncontrado = true;
					
			}
			if(setorEncontrado == false)
				this.pessoa.getSetores().add(pessoaSetor);
		}
	}
	
	public void onRelacionamentoChange() {
		this.getLista();
	}

	public void onPessoaSetoresChange() {

		// this.getPessoaSetores();
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Map<String, String> getTiposContato() {
		return tiposContato;
	}

	public void setTiposContato(Map<String, String> tiposContato) {
		this.tiposContato = tiposContato;
	}

	public Boolean getBtnContatoDisable() {
		if (this.pessoa == null)
			return true;
		else
			return false;
	}

	public void setBtnContatoDisable(Boolean btnContatoDisable) {
		this.btnContatoDisable = btnContatoDisable;
	}

	public List<Setor> getSetoresSelecionados() {
		return setoresSelecionados;
	}

	public void setSetoresSelecionados(List<Setor> setoresSelecionados) {
		this.setoresSelecionados = setoresSelecionados;
	}

	public ArrayList<Setor> getListaSetores() {

		return listaSetores;
	}

	public void setListaSetores(ArrayList<Setor> listaSetores) {
		this.listaSetores = listaSetores;
	}

	public PessoaSetor getPessoaSetor() {
		return pessoaSetor;
	}

	public void setPessoaSetor(PessoaSetor pessoaSetor) {
		this.pessoaSetor = pessoaSetor;
	}

	public List<PessoaSetor> getSetores() {
		return setores;
	}

	public void setSetores(List<PessoaSetor> setores) {
		this.setores = setores;
	}
	
	public List<Cidade> completeCidade(String query) {
		CidadeRN cidadeRN = new CidadeRN();
        List<Cidade> listaCidades = null;
		try {
			listaCidades = cidadeRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Cidade> filtroCidades = new ArrayList<Cidade>();
         
        for (int i = 0; i < listaCidades.size(); i++) {
            Cidade skin = listaCidades.get(i);
            if(skin.getNome().toLowerCase().startsWith(query)) {
            	filtroCidades.add(skin);
            }
        }
         
        return filtroCidades;
    }

}
