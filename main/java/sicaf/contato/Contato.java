package sicaf.contato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import sicaf.pessoa.Pessoa;
import sicaf.pessoaSetor.PessoaSetor;

@Entity
public class Contato implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@SequenceGenerator(name="contatoGenerator",sequenceName="CONTATO_SEQ",allocationSize=10)
	@GeneratedValue(generator="contatoGenerator")
	@Id
	private Integer id;	
	private String nome;
	private String telefone;
	private String email;
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy="contato",cascade = CascadeType.MERGE)
	private List<PessoaSetor> setores = new ArrayList<PessoaSetor>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pessoa pessoa;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<PessoaSetor> getSetores() {
		return setores;
	}
	public void setSetores(List<PessoaSetor> setores) {
		this.setores = setores;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
