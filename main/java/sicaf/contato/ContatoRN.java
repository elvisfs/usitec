package sicaf.contato;

import java.util.List;

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
	
	public void salvar(Contato contato) throws RNException {
		try {
			contatoDAO.salvar(contato);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}
	
	public List<Contato> listarPorIdPessoa(Integer idPessoa) {
		try {
			return this.contatoDAO.listarPorIdPessoa(idPessoa);
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
