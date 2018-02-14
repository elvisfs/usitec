package sicaf.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import sicaf.ativoFixo.AtivoFixo;
import sicaf.categoria.Categoria;
import sicaf.pessoa.Pessoa;
import sicaf.usuario.Usuario;

@Entity
public class Lancamento implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Pessoa favorecido;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private AtivoFixo ativoFixo;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	private String descricao;
	@Column(precision = 10, scale = 2)
	private BigDecimal valor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Pessoa getFavorecido() {
		return favorecido;
	}
	public void setFavorecido(Pessoa favorecido) {
		this.favorecido = favorecido;
	}
	
	public AtivoFixo getAtivoFixo() {
		return ativoFixo;
	}
	public void setAtivoFixo(AtivoFixo ativoFixo) {
		this.ativoFixo = ativoFixo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativoFixo == null) ? 0 : ativoFixo.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((favorecido == null) ? 0 : favorecido.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Lancamento other = (Lancamento) obj;
		if (ativoFixo == null) {
			if (other.ativoFixo != null)
				return false;
		} else if (!ativoFixo.equals(other.ativoFixo))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;		
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (favorecido == null) {
			if (other.favorecido != null)
				return false;
		} else if (!favorecido.equals(other.favorecido))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}	
	
	
}
