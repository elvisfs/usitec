package sicaf.cidade;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class CidadeDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void salvar(Cidade cidade) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.merge(cidade);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}

	public void atualizar(Cidade cidade) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.saveOrUpdate(cidade);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}
	}

	public void excluir(Cidade cidade) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(cidade);
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
				throw (new DAOException("Erro ao excluir a cidade. Verifique se ela está associada a algum registro de Cliente/Fornecedor"));
		} finally {
			this.session.close();
		}
	}

	public Cidade carregar(Integer codigo) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		Cidade cidade = (Cidade) (this.session.get(Cidade.class, codigo));
		this.session.close();
		return cidade ;
	}

	List<Cidade> listar() throws DAOException {
		List<Cidade> cidades = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Cidade> query = builder.createQuery(Cidade.class);
			Root<Cidade> root = query.from(Cidade.class);
			cidades = session.createQuery(query.orderBy(builder.asc(root.get("nome")))).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return cidades;
	}

	
}
