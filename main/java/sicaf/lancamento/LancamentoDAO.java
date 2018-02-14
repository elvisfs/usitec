package sicaf.lancamento;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class LancamentoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Lancamento lancamento) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.merge(lancamento);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}

	public void excluir(Lancamento lancamento) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(lancamento);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}

	public Lancamento carregar(Integer id) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		Lancamento lancamento = (Lancamento) (this.session.get(Lancamento.class, id));
		this.session.close();
		return lancamento;
	}

	public List<Lancamento> listar(Date dataInicio, Date dataFim) throws DAOException {
		List<Lancamento> lista = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Lancamento> query = builder.createQuery(Lancamento.class);
			Root<Lancamento> root = query.from(Lancamento.class);
			Predicate predicate = null;
			if(dataInicio !=null && dataFim != null){
				predicate=builder.between(root.get("data"), dataInicio, dataFim);
			} else if (dataInicio != null){
				predicate= builder.equal(root.<Date>get("data"), dataInicio);
			} else if(dataFim != null){
				predicate= builder.equal(root.<Date>get("data"), dataInicio);
			}
			
			lista = session.createQuery(query.orderBy(builder.asc(root.get("nome"))).where(predicate)).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
		return lista;
	}
	
	
}
