package sicaf.categoria;

import java.util.List;

import sicaf.util.DAOException;

public interface CategoriaDAO {
	public Categoria salvar(Categoria categoria) throws DAOException;

	public void excluir(Categoria categoria) throws DAOException;

	public Categoria carregar(Integer categoria) throws DAOException;

	public List<Categoria> listar() throws DAOException;
}
