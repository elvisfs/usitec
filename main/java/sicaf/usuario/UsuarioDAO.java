package sicaf.usuario;

import java.util.List;

import sicaf.util.DAOException;

public interface UsuarioDAO {
	public void salvar(Usuario usuario) throws DAOException;

	public void excluir(Usuario usuario) throws DAOException;

	public Usuario carregar(Integer id);

	public boolean isLoginExiste(String login) throws DAOException;

	public boolean isLoginExiste(String login, Usuario usuario) throws DAOException;
	
	public Usuario buscarPorLogin(String login) throws DAOException;

	public List<Usuario> listar() throws DAOException;
	
}
