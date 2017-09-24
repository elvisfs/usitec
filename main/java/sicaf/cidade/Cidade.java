package sicaf.cidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class Cidade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="cidadeGenerator",sequenceName="CIDADE_SEQ",allocationSize=10)
	@GeneratedValue(generator="cidadeGenerator")
	@Id
	private Integer id;
	private String nome;
	private String estado;
	@Transient
	private String nomeCompleto;
	
	public String getNomeCompleto() {
		this.nomeCompleto = this.nome.concat("-").concat(this.estado);
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
