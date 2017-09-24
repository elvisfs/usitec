package sicaf.contato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import sicaf.pessoa.Pessoa;
import sicaf.setor.Setor;

@Entity
//@IdClass(PessoaSetorId.class)
public class Contato implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@SequenceGenerator(name="contatoGenerator",sequenceName="CONTATO_SEQ",allocationSize=10)
	@GeneratedValue(generator="contatoGenerator")
	@Id
	private Integer idContato;	
	private String nome;
	private String telefone;
	private String email;
	@ManyToOne
	private Pessoa pessoa;
	@ManyToMany
	@JoinTable(name="contato_setor", joinColumns={@JoinColumn(name="idContato")},inverseJoinColumns={@JoinColumn(name="idSetor")})
	private List<Setor> setores = new ArrayList<Setor>();

	
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Integer getIdContato() {
		return idContato;
	}
	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
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

}
