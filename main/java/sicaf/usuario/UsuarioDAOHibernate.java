
package sicaf.usuario;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sicaf.util.HibernateUtil;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) {
		this.session.save(usuario);
	}

	public void atualizar(Usuario usuario) {
		if (usuario.getPermissao() == null || usuario.getPermissao().size() == 0) {
			Usuario usuarioPermissao = this.carregar(usuario.getCodigo());
			usuario.setPermissao(usuarioPermissao.getPermissao());
			this.session.evict(usuarioPermissao);
		}
		this.session.update(usuario);
	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);
	}

	public Usuario carregar(Integer id) {
		return (Usuario) this.session.get(Usuario.class, id);
	}

	public Usuario buscarPorLogin(String login) {
		Session sessao = null;
		Transaction transacao = null;
		Query<?> consulta = null;
		try {
			sessao = HibernateUtil.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			String hql = "select u from Usuario u where u.login = ?";
			consulta = sessao.createQuery(hql);
			consulta.setParameter(0, login);
			transacao.commit();
			return (Usuario) consulta.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			throw new javax.persistence.NoResultException("Usuário inexistente");
		} finally {
			sessao.close();
		}
	}

	public List<Usuario> listar() {
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		criteria.from(Usuario.class);
		List<Usuario> usuarios = session.createQuery(criteria).getResultList();

		return usuarios;
	}

	
}
