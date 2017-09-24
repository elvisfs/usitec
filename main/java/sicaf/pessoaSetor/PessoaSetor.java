package sicaf.pessoaSetor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import sicaf.pessoa.Pessoa;
import sicaf.setor.Setor;

@Entity
//@IdClass(PessoaSetorId.class)
public class PessoaSetor {

	@Id
	@GeneratedValue
	private Integer id;

	//@Id
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	//@Id
	@ManyToOne
	@JoinColumn(name="setor_id")
	private Setor setor;
	
	private String telefone;

	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
