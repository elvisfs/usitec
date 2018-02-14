package sicaf.ativoFixo;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import sicaf.centroCusto.CentroCusto;

//teste
@Entity
public class AtivoFixo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(name="ativoFixoGenerator",sequenceName="ATIVO_FIXO_SEQ",allocationSize=10)
	@GeneratedValue(generator="ativoFixoGenerator")
	@Id
	private Integer id;
	private String codigo;
	private String descricao;
	private String identificador;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CentroCusto centroCusto;
	
	@Transient
	private String codigoCompleto;
	
	
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
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public CentroCusto getCentroCusto() {
		return centroCusto;
	}
	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigoCompleto() {
		StringBuilder strCodigo = new StringBuilder();
		if(this.centroCusto !=null)
			strCodigo.append(this.centroCusto.getCodigo());
		if(this.codigo !=null)
			strCodigo.append(this.codigo);
		return strCodigo.toString();
	}
	
	public void setCodigoCompleto(String codigoCompleto) {
		this.codigoCompleto = codigoCompleto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroCusto == null) ? 0 : centroCusto.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtivoFixo other = (AtivoFixo) obj;
		if (centroCusto == null) {
			if (other.centroCusto != null)
				return false;
		} else if (!centroCusto.equals(other.centroCusto))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}
	
	
}
