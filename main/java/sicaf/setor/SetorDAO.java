package sicaf.setor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.omg.CORBA.SetOverrideType;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class SetorDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	
	public void salvar(Setor setor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			//this.session.save(setor);
			this.session.persist(setor);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}
	}

	
	public void excluir(Setor setor) throws DAOException {
		try{
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(setor);
			this.session.getTransaction().commit();
		}catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException)
				throw (new DAOException("Erro ao excluir o setor. Verifique se ele está associada a algum registro de Cliente/Fornecedor"));
		} finally{
			if(this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}
	}

	
	public Setor carregar(Integer codigo) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			return (Setor) this.session.get(Setor.class, codigo);
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Setor não cadastrado");
		} finally {
			this.session.close();
		}
	}

	
	public List<Setor> listar() throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Setor> criteria = builder.createQuery(Setor.class); 
			Root<Setor> from = criteria.from(Setor.class);
			List<Setor> setores = session.createQuery(criteria.orderBy(builder.asc(from.get("nomeSetor")))).getResultList();
			return setores;
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Não há setores cadastrados");
		} finally {
			this.session.close();
		}

	}

	
	public Setor buscarPorDescricao(String setor) {
		String hql = "select s from Setor s where s.nomeSetor like ?";
		Query<?> consulta = this.session.createQuery(hql);
		consulta.setParameter(0, setor);
		Setor setorEncontrado = null;
		if (consulta.getSingleResult() != null) {
			setorEncontrado = (Setor) consulta.getSingleResult();
		}

		return setorEncontrado;
	}

	
	public void atualizar(Setor setor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(setor);
			this.session.getTransaction().commit();
		}catch (javax.persistence.PersistenceException e){
			throw new DAOException(e.getMessage());
		}finally{
			if(this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}

	}
}
