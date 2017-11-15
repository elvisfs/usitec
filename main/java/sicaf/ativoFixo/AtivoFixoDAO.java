package sicaf.ativoFixo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class AtivoFixoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public void salvar(AtivoFixo ativoFixo) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.merge(ativoFixo);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}

	public void atualizar(AtivoFixo ativoFixo) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.saveOrUpdate(ativoFixo);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}
	}

	public void excluir(AtivoFixo ativoFixo) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(ativoFixo);
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}

	public AtivoFixo carregar(Integer id) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		AtivoFixo ativoFixo = (AtivoFixo) (this.session.get(AtivoFixo.class, id));
		this.session.close();
		return ativoFixo ;
	}

	List<AtivoFixo> listar() throws DAOException {
		List<AtivoFixo> ativoFixo = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<AtivoFixo> query = builder.createQuery(AtivoFixo.class);
			Root<AtivoFixo> root = query.from(AtivoFixo.class);
			ativoFixo = session.createQuery(query.orderBy(builder.asc(root.get("descricao")))).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return ativoFixo;
	}
}
