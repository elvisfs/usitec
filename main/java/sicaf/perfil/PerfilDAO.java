package sicaf.perfil;

import java.util.List;

import sicaf.util.DAOException;

public interface PerfilDAO {
	public Perfil carregar(Integer id);
	public List<Perfil> listar() throws DAOException;
	public List<String> listarPerfisByUser(Integer idUsuario) throws DAOException;
	public List<Perfil> listarPerfisNaoAtribuidosByUser(Integer idUsuario) throws DAOException;
}
