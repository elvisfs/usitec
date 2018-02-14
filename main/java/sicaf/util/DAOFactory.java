package sicaf.util;

import sicaf.ativoFixo.AtivoFixoDAO;
import sicaf.categoria.CategoriaDAO;
import sicaf.centroCusto.CentroCustoDAO;
import sicaf.cidade.CidadeDAO;
import sicaf.contato.ContatoDAO;
import sicaf.lancamento.LancamentoDAO;
import sicaf.perfil.PerfilDAO;
import sicaf.pessoa.PessoaDAO;
import sicaf.pessoaSetor.PessoaSetorDAO;
import sicaf.setor.SetorDAO;
import sicaf.usuario.UsuarioDAO;

public class DAOFactory {
	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	public static CategoriaDAO criarCategoriaDAO(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}
	public static SetorDAO criarSetorDAO(){
		SetorDAO setorDAO = new SetorDAO();
		setorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return setorDAO;
	}
	public static PessoaDAO criarPessoaDAO() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (PessoaDAO) pessoaDAO;
	}
	public static PerfilDAO criarPerfilDAO() {
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (PerfilDAO) perfilDAO;
	}
	public static ContatoDAO criarContatoDAO() {
		ContatoDAO contatoDAO = new ContatoDAO();
		contatoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return (ContatoDAO) contatoDAO;
	}
	public static PessoaSetorDAO criarPessoaSetorDAO(){
		PessoaSetorDAO pessoaSetorDAO = new PessoaSetorDAO();
		pessoaSetorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return pessoaSetorDAO;
	}
	public static CidadeDAO criarCidadeDAO(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return cidadeDAO;	
	}
	public static CentroCustoDAO criarCentroCustoDAO(){
		CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
		centroCustoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return centroCustoDAO;
	}
	public static AtivoFixoDAO criarAtivoFixoDAO() {
		AtivoFixoDAO ativoFixoDAO = new AtivoFixoDAO();
		ativoFixoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return ativoFixoDAO;
	}
	public static LancamentoDAO criarLancamentoDAO(){
		LancamentoDAO lancamentoDAO = new LancamentoDAO();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}
}
