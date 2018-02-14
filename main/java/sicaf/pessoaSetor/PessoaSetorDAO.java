package sicaf.pessoaSetor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.contato.Contato;
import sicaf.pessoa.Pessoa;
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
	
	public List<Contato> listarContatos(Integer idPessoa){
		List<Contato> contatos = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			Query<Contato> query = this.session.createNativeQuery("select distinct on (c.id) * from Contato as c inner join pessoaSetor as p on p.contato_id = c.id where p.pessoa_id = :idPessoa",Contato.class);
			 query.setParameter("idPessoa",idPessoa);
			 
			 contatos = query.getResultList();
			 
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.session.close();
		}

		return contatos;
		
	}
	
	public List<PessoaSetor> listarSetores(Pessoa pessoa) throws DAOException{
		List<PessoaSetor> setores = null;
		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<PessoaSetor> query = builder.createQuery(PessoaSetor.class);
			Root<PessoaSetor> root = query.from(PessoaSetor.class);			
			Predicate pess =builder.equal(root.get("pessoa"), pessoa);
			query.where(builder.isNull(root.get("contato")),pess); 
			setores = session.createQuery(query).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return setores;
		
	}

}
