package sicaf.setor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class SetorDAOHibernate implements SetorDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Setor setor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.saveOrUpdate(setor);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}
	}

	@Override
	public void excluir(Setor setor) throws DAOException {
		try{
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(setor);
			this.session.getTransaction().commit();
		}catch(javax.persistence.EntityNotFoundException e){
			throw new DAOException("Setor não cadastrado");
		} finally{
			if(this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}
	}

	@Override
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

	@Override
	public List<Setor> listar() throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Setor> criteria = builder.createQuery(Setor.class);
			criteria.from(Setor.class);
			List<Setor> setores = session.createQuery(criteria).getResultList();
			this.session.getTransaction().commit();
			return setores;
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Não há setores cadastrados");
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}

	}

	@Override
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

	@Override
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
	/*
	String hql = "select s.nomeSetor from Setor s where s.nomeSetor = :nomeSetor and s.id != :idSetor";
	String nomeSetor = setor.getNomeSetor();
	Integer idSetor = setor.getId();
	Query<?> consulta = this.session.createQuery(hql);
	consulta.setParameter("nomeSetor", nomeSetor);
	consulta.setParameter("idSetor", idSetor);
	if (consulta.getResultList().size() > 0) {
		throw new DAOException("Setor já existente");
	}*/

}
