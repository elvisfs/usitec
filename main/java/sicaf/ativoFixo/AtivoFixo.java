package sicaf.ativoFixo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import sicaf.centroCusto.CentroCusto;


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
	private String descricao;
	private String identificador;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CentroCusto centroCusto;
	
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
	
	
	
}
