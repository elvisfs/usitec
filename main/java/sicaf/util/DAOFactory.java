package sicaf.util;

import sicaf.categoria.CategoriaDAO;
import sicaf.categoria.CategoriaDAOHibernate;
import sicaf.perfil.PerfilDAO;
import sicaf.perfil.PerfilDAOHibernate;
import sicaf.pessoa.PessoaDAO;
import sicaf.pessoa.PessoaDAOHibernate;
import sicaf.setor.SetorDAO;
import sicaf.setor.SetorDAOHibernate;
import sicaf.usuario.UsuarioDAO;
import sicaf.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	public static CategoriaDAO criarCategoriaDAO(){
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}
	public static SetorDAO criarSetorDAO(){
		SetorDAOHibernate setorDAO = new SetorDAOHibernate();
		setorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return setorDAO;
	}
	public static PessoaDAO criarPessoaDAO() {
		PessoaDAOHibernate pessoaDAO = new PessoaDAOHibernate();
		pessoaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (PessoaDAO) pessoaDAO;
	}
	public static PerfilDAO criarPerfilDAO() {
		PerfilDAOHibernate perfilDAO = new PerfilDAOHibernate();
		perfilDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (PerfilDAO) perfilDAO;
	}
}
