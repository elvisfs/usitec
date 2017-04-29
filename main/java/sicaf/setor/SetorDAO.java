package sicaf.setor;

import java.util.List;

import sicaf.util.DAOException;

public interface SetorDAO {
	public void salvar(Setor setor) throws DAOException;

	public void atualizar(Setor setor) throws DAOException;
	
	public void excluir(Setor setor) throws DAOException;

	public Setor carregar(Integer codigo) throws DAOException;
	
	public Setor buscarPorDescricao(String setor);

	public List<Setor> listar() throws DAOException;
	
}
