package sicaf.pessoaSetor;

import java.io.Serializable;

import sicaf.pessoa.Pessoa;
import sicaf.setor.Setor;

public class PessoaSetorId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private Setor setor;
	
	public PessoaSetorId(){
		
	}
	
	public PessoaSetorId(Pessoa pessoa, Setor setor){
		this.pessoa = pessoa;
		this.setor = setor;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	@Override
	public int hashCode() {
		
		final int prime = 31;
        int result = 1;
        result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
        result = prime * result + ((setor == null) ? 0 : setor.hashCode());
        return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PessoaSetorId){
			PessoaSetorId pessoaSetorId = (PessoaSetorId) obj;
			return pessoaSetorId.setor == setor && pessoaSetorId.pessoa == pessoa;
		}
		return false;
	}
	
}
