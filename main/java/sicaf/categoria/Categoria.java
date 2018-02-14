package sicaf.categoria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "categoria_pai", nullable = true, foreignKey = @ForeignKey(name = "fk_categoria_categoria"))
	private Categoria pai;

	private String descricao;
	private int fator;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "categoria_pai", updatable = false)
	@org.hibernate.annotations.OrderBy(clause = "descricao asc")
	private List<Categoria> filhos;

	public Categoria() {
	}

	public Categoria(Categoria pai, String descricao, int fator) {
		this.pai = pai;
		this.descricao = descricao;
		this.fator = fator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Categoria other = (Categoria) obj;
		if(id == null){
			if(other.id != null) return false;
		}else if(!id.equals(other.id)) return false;
		return true;
	}

	public Integer getCodigo() {
		return id;
	}

	public void setCodigo(Integer codigo) {
		this.id = codigo;
	}

	public Categoria getPai() {
		return pai;
	}

	public void setPai(Categoria pai) {
		this.pai = pai;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getFator() {
		return fator;
	}

	public void setFator(int fator) {
		this.fator = fator;
	}

	public List<Categoria> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Categoria> filhos) {
		this.filhos = filhos;
	}
}
