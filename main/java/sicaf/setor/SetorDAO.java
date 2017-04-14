package sicaf.setor;

import java.util.List;

import sicaf.util.DAOException;

public interface SetorDAO {
	public void salvar(Setor setor) throws DAOException;

	public void atualizar(Setor setor) throws DAOException;
	
	public void excluir(Setor setor);

	public Setor carregar(Integer codigo);
	
	public Setor buscarPorDescricao(String setor);

	public List<Setor> listar();
	
}
