package sicaf.setor;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;

public class SetorRN {
	private SetorDAO setorDAO;

	public SetorRN() {
		this.setorDAO = DAOFactory.criarSetorDAO();
	}

	public Setor carregar(Integer codigo) {
		return this.setorDAO.carregar(codigo);
	}

	public Setor buscarPorDescricao(String setor) {
		return this.setorDAO.buscarPorDescricao(setor);
	}

	public void salvar(Setor setor) throws DAOException {
		if(setor.getId()==null || setor.getId() == 0){
			setorDAO.salvar(setor);
		}else{
			setorDAO.atualizar(setor);
		}
	}

	public void excluir(Setor setor) {
		this.setorDAO.excluir(setor);
	}

	public List<Setor> listar() {
		return this.setorDAO.listar();
	}
}
