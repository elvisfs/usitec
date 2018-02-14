package sicaf.centroCusto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class CentroCustoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void salvar(CentroCusto centroCusto) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.merge(centroCusto);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}

	public void atualizar(CentroCusto centroCusto) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.saveOrUpdate(centroCusto);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}
	}

	public void excluir(CentroCusto centroCusto) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(centroCusto);
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
				throw (new DAOException("Erro ao excluir o centro de custo. Verifique se ele está associado a algum um ativo fixo"));
		} finally {
			this.session.close();
		}
	}

	public CentroCusto carregar(Integer id) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		CentroCusto centroCusto = (CentroCusto) (this.session.get(CentroCusto.class, id));
		this.session.close();
		return centroCusto ;
	}

	List<CentroCusto> listar() throws DAOException {
		List<CentroCusto> centroCusto = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CentroCusto> query = builder.createQuery(CentroCusto.class);
			Root<CentroCusto> root = query.from(CentroCusto.class);
			centroCusto = session.createQuery(query.orderBy(builder.asc(root.get("descricao")))).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return centroCusto;
	}
}
