package sicaf.setor;

import java.util.ArrayList;
import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class SetorRN {
	private SetorDAO setorDAO;

	public SetorRN() {
		this.setorDAO = DAOFactory.criarSetorDAO();
	}

	public Setor carregar(Integer codigo) throws RNException {
		try {
			return this.setorDAO.carregar(codigo);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	public Setor buscarPorDescricao(String setor) {
		return this.setorDAO.buscarPorDescricao(setor);
	}

	public void salvar(Setor setor) throws RNException {
		try{
			if(setor.getIdSetor()==null || setor.getIdSetor() == 0){
				setorDAO.salvar(setor);
			}else{
				setorDAO.atualizar(setor);
			}
		}catch(DAOException e){
			throw new RNException (e.getMessage());
		}
		
	}

	public void excluir(Setor setor) throws RNException {
		try {
			this.setorDAO.excluir(setor);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	public ArrayList<Setor> listar() throws RNException {
		try {
			return (ArrayList<Setor>) this.setorDAO.listar();
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}
}
