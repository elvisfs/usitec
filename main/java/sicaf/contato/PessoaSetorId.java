package sicaf.pessoaSetor;

import java.io.Serializable;

public class PessoaSetorId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pessoa;
	private int setor;

	public int getPessoa() {
		return pessoa;
	}

	public void setPessoa(int pessoa) {
		this.pessoa = pessoa;
	}

	public int getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		return pessoa + setor;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PessoaSetorId) {
			PessoaSetorId pessoaSetorId = (PessoaSetorId) obj;
			return pessoaSetorId.pessoa == pessoa && pessoaSetorId.setor == setor;
		}
		return false;
	}

}
