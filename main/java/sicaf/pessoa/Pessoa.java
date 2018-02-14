package sicaf.pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import sicaf.cidade.Cidade;
import sicaf.contato.Contato;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.TipoPessoa;

@Entity

public class Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="pessoaGenerator",sequenceName="PESSOA_SEQ",allocationSize=10)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator="pessoaGenerator")
	
	private Integer id;
	@Column(nullable=false)
	private String nome;
	public Pessoa(String nome) {
		super();
		this.nome = nome;
	}
	public Pessoa(){
		
	}

	@Enumerated(EnumType.STRING)
	private TipoPessoa tipo;
	private String endereco;
	private String bairro;
	private String cep;
	
	@ManyToOne
	private Cidade cidade;

	private String telefone;
	private String site;
	@Column(name="FLGCLIENTE")
	private boolean cliente;
	@Column(name="FLGFORNECEDOR")
	private boolean fornecedor;
	@Column(name="FLGFUNCIONARIO")
	private boolean funcionario;
	@Transient
	private String tipoContato;
	private String cpf;
	private String identidade;
	
	private String cnpj;
	private String inscricaoEstadual;
	
	@Transient
	private Boolean isPessoaFisica;
	@Transient
	private Boolean isPessoaJuridica;
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy="pessoa", cascade=CascadeType.MERGE, orphanRemoval = true)
	private List<PessoaSetor> setores = new ArrayList<PessoaSetor>();
	
	@OneToMany (fetch = FetchType.EAGER, mappedBy="pessoa")
	private List<Contato> contatos =new ArrayList<Contato>();
		
	public Boolean getIsPessoaFisica() {
		return (TipoPessoa.FISICA.equals(this.tipo));
	}
	
	public Boolean getIsPessoaJuridica() {
		return (TipoPessoa.JURIDICA.equals(this.tipo));
	}
	
	public boolean isCliente() {
		return cliente;
	}
	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
	public boolean isFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TipoPessoa getTipo() {
		return tipo;
	}
	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public boolean isFuncionario() {
		return funcionario;
	}
	public void setFuncionario(boolean funcionario) {
		this.funcionario = funcionario;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getIdentidade() {
		return identidade;
	}
	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getTipoContato() {
		StringBuilder tipo = new StringBuilder();
		if(this.isFornecedor()){
			if(tipo.length()>0) tipo.append(", ");				
			tipo.append("Fornecedor");
		}
		if(this.isCliente()){
			if(tipo.length()>0) tipo.append(", ");				
			tipo.append("Cliente");
		}
		if(this.isFuncionario()){
			if(tipo.length()>0) tipo.append(", ");				
			tipo.append("Funcionario");
		}
		
		return tipo.toString();
	}
	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
	
	public List<PessoaSetor> getSetores() {
		return setores;
	}

	public void setSetores(List<PessoaSetor> setores) {
		this.setores = setores;
	}
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + (cliente ? 1231 : 1237);
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((contatos == null) ? 0 : contatos.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + (fornecedor ? 1231 : 1237);
		result = prime * result + (funcionario ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identidade == null) ? 0 : identidade.hashCode());
		result = prime * result + ((inscricaoEstadual == null) ? 0 : inscricaoEstadual.hashCode());
		result = prime * result + ((isPessoaFisica == null) ? 0 : isPessoaFisica.hashCode());
		result = prime * result + ((isPessoaJuridica == null) ? 0 : isPessoaJuridica.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((setores == null) ? 0 : setores.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((tipoContato == null) ? 0 : tipoContato.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cliente != other.cliente)
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (contatos == null) {
			if (other.contatos != null)
				return false;
		} else if (!contatos.equals(other.contatos))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (fornecedor != other.fornecedor)
			return false;
		if (funcionario != other.funcionario)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identidade == null) {
			if (other.identidade != null)
				return false;
		} else if (!identidade.equals(other.identidade))
			return false;
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		if (isPessoaFisica == null) {
			if (other.isPessoaFisica != null)
				return false;
		} else if (!isPessoaFisica.equals(other.isPessoaFisica))
			return false;
		if (isPessoaJuridica == null) {
			if (other.isPessoaJuridica != null)
				return false;
		} else if (!isPessoaJuridica.equals(other.isPessoaJuridica))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (setores == null) {
			if (other.setores != null)
				return false;
		} else if (!setores.equals(other.setores))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoContato == null) {
			if (other.tipoContato != null)
				return false;
		} else if (!tipoContato.equals(other.tipoContato))
			return false;
		return true;
	}
	
}
