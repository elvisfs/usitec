package sicaf.categoria;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class CategoriaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Categoria salvar(Categoria categoria) throws DAOException {
		Categoria merged = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			merged = (Categoria) this.session.merge(categoria);
			this.session.flush();
			this.session.clear();
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive()) {
				this.session.getTransaction().rollback();
			}
			this.session.close();
		}
		return merged;
	}

	public void excluir(Categoria categoria) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(categoria);
			this.session.flush();
			this.session.clear();
			this.session.getTransaction().commit();
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Categoria não encontrada");
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive()) {
				this.session.getTransaction().rollback();
			}
			this.session.close();
		}
	}
	
	public Categoria carregar(Integer categoria) throws DAOException {
		Categoria cat = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			cat = (Categoria) this.session.get(Categoria.class, categoria);
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Categoria não encontrada");
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			this.session.close();
		}
		return cat;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listar() throws DAOException {
		List<Categoria> lista = null;
		String hql = "select c from Categoria c where c.pai is null";
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			Query<Categoria> query = this.session.createQuery(hql);

			lista = query.getResultList();
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Categoria não encontrada");
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			this.session.close();
		}
		return lista;
	}
}
