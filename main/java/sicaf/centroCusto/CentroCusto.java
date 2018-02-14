package sicaf.centroCusto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import sicaf.ativoFixo.AtivoFixo;

@Entity
public class CentroCusto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name = "centroCustoGenerator", sequenceName = "CENTRO_CUSTO_SEQ", allocationSize = 10)
	@GeneratedValue(generator = "centroCustoGenerator")
	@Id
	private Integer id;
	private String codigo;
	private String descricao;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "centroCusto", cascade = CascadeType.MERGE)
	private List<AtivoFixo> ativosFixo = new ArrayList<AtivoFixo>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<AtivoFixo> getAtivosFixo() {
		return ativosFixo;
	}

	public void setAtivosFixo(List<AtivoFixo> ativosFixo) {
		this.ativosFixo = ativosFixo;
	}

	@Override
	public String toString() {
		return descricao;
	}

	public CentroCusto() {
	}

	public CentroCusto(Integer id, String descricao, List<AtivoFixo> ativosFixo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.ativosFixo = ativosFixo;
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
		CentroCusto other = (CentroCusto) obj;
		if(id == null){
			if(other.id != null) return false;
		}else if(!id.equals(other.id)) return false;
		return true;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
