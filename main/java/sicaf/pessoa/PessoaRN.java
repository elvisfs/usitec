package sicaf.pessoa;

import java.util.List;

import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class PessoaRN {
	private PessoaDAO pessoaDAO;

	public PessoaRN() {
		this.pessoaDAO = DAOFactory.criarPessoaDAO();
	}

	public Pessoa carregar(Integer codigo) {
		return this.pessoaDAO.carregar(codigo);
	}

	public void salvar(Pessoa pessoa) {
		Integer id = pessoa.getId();
		try {
			if(id == null || id== 0){
			pessoaDAO.salvar(pessoa);
			}
			else{
				pessoaDAO.atualizar(pessoa);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir(Pessoa pessoa) throws RNException {
		try {
			this.pessoaDAO.excluir(pessoa);
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
	}

	public List<Pessoa> listar() {
		try {
			return this.pessoaDAO.listar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PessoaSetor> listarSetores() {
		return this.pessoaDAO.listarSetores();
	}
}
