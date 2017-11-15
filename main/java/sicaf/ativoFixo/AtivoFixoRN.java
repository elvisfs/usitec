package sicaf.ativoFixo;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class AtivoFixoRN {
	private AtivoFixoDAO ativoFixoDAO;

	public AtivoFixoRN() {
		this.ativoFixoDAO = DAOFactory.criarAtivoFixoDAO();
	}
	
	public AtivoFixo carregar(Integer id) {
		return this.ativoFixoDAO.carregar(id);
	}
	
	public void salvar(AtivoFixo ativoFixo) throws RNException {
		try {
			ativoFixoDAO.salvar(ativoFixo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}
	
	public List<AtivoFixo> listar() throws RNException{
		try {
			return ativoFixoDAO.listar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
		
	}

	public void excluir(AtivoFixo ativoFixo) throws RNException {
		try {
			ativoFixoDAO.excluir(ativoFixo);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
		
	}
}
