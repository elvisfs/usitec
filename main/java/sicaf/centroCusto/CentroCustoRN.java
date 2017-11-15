package sicaf.centroCusto;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class CentroCustoRN {
	private CentroCustoDAO centroCustoDAO;

	public CentroCustoRN() {
		this.centroCustoDAO = DAOFactory.criarCentroCustoDAO();
	}
	
	public CentroCusto carregar(Integer id) {
		return this.centroCustoDAO.carregar(id);
	}
	
	public void salvar(CentroCusto centroCusto) throws RNException {
		try {
			centroCustoDAO.salvar(centroCusto);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}
	
	public List<CentroCusto> listar() throws RNException{
		try {
			return centroCustoDAO.listar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
		
	}

	public void excluir(CentroCusto centroCusto) throws RNException {
		try {
			centroCustoDAO.excluir(centroCusto);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
		
	}
}
