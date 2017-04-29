package sicaf.pessoa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

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
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipo;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String telefone;
	private String site;
	private boolean flgCliente;
	private boolean flgFornecedor;
	
	
	private boolean flgFuncionario;
	
	private String cpf;
	private String identidade;
	
	private String cnpj;
	private String inscricaoEstadual;
	
	@OneToMany(mappedBy="pessoa")
	private List<PessoaSetor> setores;
	
		
	public boolean isFlgCliente() {
		return flgCliente;
	}
	public void setFlgCliente(boolean flgCliente) {
		this.flgCliente = flgCliente;
	}
	public boolean isFlgFornecedor() {
		return flgFornecedor;
	}
	public void setFlgFornecedor(boolean flgFornecedor) {
		this.flgFornecedor = flgFornecedor;
	}
	public Integer getCodigo() {
		return id;
	}
	public void setCodigo(Integer codigo) {
		this.id = codigo;
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
	public boolean isFlgFuncionario() {
		return flgFuncionario;
	}
	public void setFlgFuncionario(boolean flgFuncionario) {
		this.flgFuncionario = flgFuncionario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<PessoaSetor> getSetores() {
		return setores;
	}
	public void setSetores(List<PessoaSetor> setores) {
		this.setores = setores;
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
	
}
