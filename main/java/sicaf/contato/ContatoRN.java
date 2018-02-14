package sicaf.contato;

import java.util.List;

import sicaf.pessoa.Pessoa;
import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class ContatoRN {
	private ContatoDAO contatoDAO;

	public ContatoRN() {
		this.contatoDAO = DAOFactory.criarContatoDAO();
	}

	public Contato carregar(Integer codigo) {
		return this.contatoDAO.carregar(codigo);
	}

	public Contato salvar(Contato contato) throws RNException {
		try {
			Contato merge = null;
			if(contato.getId()==null)
				merge = contatoDAO.salvar(contato);
			else
				contatoDAO.atualizar(contato);
			return merge;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	public List<Contato> listarPorPessoa(Pessoa pessoa) {
		try {
			return this.contatoDAO.listarPorPessoa(pessoa);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void excluir(Contato contato) throws RNException {
		try {
			contatoDAO.excluir(contato);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}

	}
}
