package sicaf.setor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import sicaf.pessoaSetor.PessoaSetor;

@Entity
public class Setor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="setorGenerator",sequenceName="SETOR_SEQ",allocationSize=10)
	@GeneratedValue(generator="setorGenerator")
	private Integer id;
	@OneToMany(mappedBy="setor")
	private List<PessoaSetor> pessoas;
	@Column(length=50, nullable=false)
	private String nomeSetor;
	
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String setor) {
		this.nomeSetor = setor;
	}
	public List<PessoaSetor> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaSetor> pessoas) {
		this.pessoas = pessoas;
	}
}
