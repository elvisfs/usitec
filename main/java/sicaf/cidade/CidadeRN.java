package sicaf.cidade;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class CidadeRN {
	private CidadeDAO cidadeDAO;

	public CidadeRN() {
		this.cidadeDAO = DAOFactory.criarCidadeDAO();
	}
	
	public Cidade carregar(Integer codigo) {
		return this.cidadeDAO.carregar(codigo);
	}
	
	public void salvar(Cidade cidade) throws RNException {
		try {
			cidadeDAO.salvar(cidade);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}
	
	public List<Cidade> listar() throws RNException{
		try {
			return cidadeDAO.listar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
		
	}

	public void excluir(Cidade cidade) throws RNException {
		try {
			cidadeDAO.excluir(cidade);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
		
	}
}
