package sicaf.pessoaSetor;

import java.util.List;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class PessoaSetorDAO {
	private Session session;
	
	public void salvar(PessoaSetor pessoaSetor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();			
			this.session.beginTransaction();
			this.session.saveOrUpdate(pessoaSetor);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}

	
	public void excluir(PessoaSetor pessoaSetor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.delete(pessoaSetor);
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

	}

	
	public PessoaSetor carregar(Integer id) {
		return (PessoaSetor) this.session.get(PessoaSetor.class, id);
	}

	
	public List<PessoaSetor> listar() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void atualizar(PessoaSetor pessoaSetor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(pessoaSetor);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}

	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	
	public void excluir(Integer idPessoaSetor) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			
			this.session.delete((PessoaSetor) this.session.get(PessoaSetor.class, idPessoaSetor));
			this.session.getTransaction().commit();

		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		
	}

}
