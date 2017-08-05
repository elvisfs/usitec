package sicaf.pessoa;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class PessoaDAOHibernate implements PessoaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Pessoa pessoa) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.save(pessoa);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}
	
	public void atualizar(Pessoa pessoa) throws DAOException{
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(pessoa);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}
	}

	@Override
	public void excluir(Pessoa pessoa) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(pessoa);
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
	}

	@Override
	public Pessoa carregar(Integer id) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoa = (Pessoa) (this.session.get(Pessoa.class, id));
		this.session.close();
		return pessoa ;
	}

	@Override
	public List<Pessoa> listar() throws DAOException {
		List<Pessoa> pessoas = null;
				
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
			Root<Pessoa> from = criteria.from(Pessoa.class);		
			pessoas = session.createQuery(criteria.orderBy(builder.asc(from.get("nome")))).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return pessoas;
	}

	public List<PessoaSetor> listarSetores() {
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<PessoaSetor> criteria = builder.createQuery(PessoaSetor.class);
		criteria.from(Pessoa.class);
		List<PessoaSetor> pessoas = session.createQuery(criteria).getResultList();

		return pessoas;

	}

}
