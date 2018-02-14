package sicaf.perfil;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class PerfilRN {

	private PerfilDAO perfilDAO;
	
	public PerfilRN(){
		this.perfilDAO = DAOFactory.criarPerfilDAO();
	}
	
	public List<Perfil> listar() throws RNException {
		List<Perfil> perfis=null;
		try {
			perfis = this.perfilDAO.listar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new RNException(e.getMessage());
		}
		return perfis;
	}
	public Perfil carregar(Integer perfilSelecionado) {
	
		return perfilDAO.carregar(perfilSelecionado);
	}
	public List<Perfil> listarPerfisbyIdUsuario(Integer idUsuario) throws RNException{
		try {
			return perfilDAO.listarPerfisByUser(idUsuario);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}
	
	public List<Perfil> listarPerfilsNaoAtribuidos(Integer idUsuario) throws RNException{
		try{
		return perfilDAO.listarPerfisNaoAtribuidosByUser(idUsuario);
		} catch(DAOException e){
			throw new RNException(e.getMessage());
		}
	}

}
