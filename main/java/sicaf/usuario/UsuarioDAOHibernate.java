
package sicaf.usuario;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class UsuarioDAOHibernate implements UsuarioDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();			
			this.session.saveOrUpdate(usuario);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if(this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}


	public void excluir(Usuario usuario) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(usuario);
			this.session.getTransaction().commit();
		} catch (javax.persistence.EntityExistsException e) {
			if(this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}

	public Usuario carregar(Integer id) {
		return (Usuario) this.session.get(Usuario.class, id);
	}
	
	public boolean isLoginExiste(String login) throws DAOException {
		Query<?> consulta = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();//
			String hql = "select u from Usuario u where u.login = ?";
			consulta = session.createQuery(hql);
			consulta.setParameter(0, login);
			return consulta.getResultList().size()>0;
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException(e.getMessage());
		} finally {
			session.close();//
		}
	}
	
	public boolean isLoginExiste(String login, Usuario usuario) throws DAOException {
		Query<?> consulta = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();//
			String hql = "select u from Usuario u where u.login = ? and u = ?";
			consulta = session.createQuery(hql);
			consulta.setParameter(0, login);
			consulta.setParameter(1, usuario);
			return consulta.getResultList().size()>0;
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException(e.getMessage());
		} finally {
			session.close();//
		}
	}
	

	public Usuario buscarPorLogin(String login) throws DAOException {
		Query<?> consulta = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select u from Usuario u where u.login = ?";
			consulta = session.createQuery(hql);
			consulta.setParameter(0, login);
			return (Usuario) consulta.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException("Usuário inexistente");
		} finally {
			session.close();
		}
	}

	public List<Usuario> listar() throws DAOException {
		List<Usuario> usuarios = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
			Root<Usuario> from = criteria.from(Usuario.class); 
			usuarios = session.createQuery(criteria.orderBy(builder.asc(from.get("nome")))).getResultList();
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException("Usuário inexistente");
		} finally {
			session.close();
		}
		return usuarios;
	}

}
