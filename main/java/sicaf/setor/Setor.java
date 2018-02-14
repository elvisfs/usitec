package sicaf.setor;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Integer idSetor;

	@Column(length=50, nullable=true)
	private String nomeSetor;
	
	@OneToMany(mappedBy="setor")
	private List<PessoaSetor> pessoas = new ArrayList<PessoaSetor>();
	
	public List<PessoaSetor> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<PessoaSetor> pessoas) {
		this.pessoas = pessoas;
	}
	public Integer getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String setor) {
		this.nomeSetor = setor;
	}
	
	@Override
    public String toString() {
        return this.nomeSetor;
    }
	
	@Override
    public boolean equals(Object object) {
        return (object instanceof Setor) && (idSetor != null) 
             ? idSetor.equals(((Setor) object).idSetor) 
             : (object == this);
    }

}
