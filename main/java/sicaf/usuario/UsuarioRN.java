package sicaf.usuario;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class UsuarioRN {
	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public Usuario carregar(Integer codigo) {
		return this.usuarioDAO.carregar(codigo);
	}

	public Usuario buscarPorLogin(String login) throws RNException {
		try {
			return this.usuarioDAO.buscarPorLogin(login);
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
	}

	public void salvar(Usuario usuario) throws RNException {
		Integer codigo = usuario.getCodigo();
		
		try {	
			
			if (codigo == null || codigo == 0) {
				this.usuarioDAO.isLoginExiste(usuario.getLogin());
				this.usuarioDAO.salvar(usuario);
			} else {
				this.usuarioDAO.isLoginExiste(usuario.getLogin(),usuario);
				this.usuarioDAO.salvar(usuario);
			}
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}

	}

	public void excluir(Usuario usuario) throws RNException {
		try {
			this.usuarioDAO.excluir(usuario);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	public List<Usuario> listar() throws Exception {
		try {
			return this.usuarioDAO.listar();
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
	}
}
