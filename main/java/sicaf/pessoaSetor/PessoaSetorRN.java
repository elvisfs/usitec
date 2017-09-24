package sicaf.pessoaSetor;

import sicaf.pessoa.Pessoa;
import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class PessoaSetorRN {
	private PessoaSetorDAO pessoaSetorDAO;
	
	public PessoaSetorRN(){
		this.pessoaSetorDAO = DAOFactory.criarPessoaSetorDAO();
	}
	
	public void salvar(PessoaSetor pessoaSetor) throws RNException{
		try {
			pessoaSetorDAO.salvar(pessoaSetor);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw (new RNException(e.getMessage()));
		}
	}
	
	public void excluirSetor(PessoaSetor pessoaSetor) throws RNException {
		try {
			this.pessoaSetorDAO.excluir(pessoaSetor);
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
	}

	public void excluirSetor(Integer idPessoaSetor) throws RNException {
		try {
			this.pessoaSetorDAO.excluir(idPessoaSetor);
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
		
	}
	
}
