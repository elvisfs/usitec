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
	
}
