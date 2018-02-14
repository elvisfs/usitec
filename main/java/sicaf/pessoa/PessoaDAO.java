package sicaf.pessoa;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import sicaf.pessoaSetor.PessoaSetor;
import sicaf.pessoaSetor.PessoaSetorDAO;
import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.HibernateUtil;

public class PessoaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	
	public Pessoa salvar(Pessoa pessoa) throws DAOException {
		Pessoa merge = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			merge = (Pessoa) this.session.merge(pessoa);
			this.session.flush();
			this.session.clear();
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
		return merge;
	}
	
	public void atualizar(Pessoa pessoa) throws DAOException{
		try {
			PessoaSetorDAO pessoaSetorDAO = DAOFactory.criarPessoaSetorDAO();
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			for(PessoaSetor p:pessoa.getSetores())
				pessoaSetorDAO.salvar(p);
			this.session.saveOrUpdate(pessoa);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			throw (new DAOException(e.getMessage()));
		} finally{
			this.session.close();
		}
	}

	
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

	
	public Pessoa carregar(Integer id) {
		this.session = HibernateUtil.getSessionFactory().openSession();
		Pessoa pessoa = (Pessoa) (this.session.get(Pessoa.class, id));
		this.session.close();
		return pessoa ;
	}

	
	public List<Pessoa> listar() throws DAOException {
		List<Pessoa> pessoas = null;
				
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Pessoa> query = builder.createQuery(Pessoa.class);
			Root<Pessoa> root = query.from(Pessoa.class);
			Path<String> nomePath = root.get("nome");
			query.multiselect(nomePath);
			
			pessoas = session.createQuery(query.orderBy(builder.asc(root.get("nome")))).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return pessoas;
	}
	
	
	public List<Pessoa> listar(String tipoContato) throws DAOException {
		List<Pessoa> pessoas = null;
				
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			Predicate predicate = builder.and();
			CriteriaQuery<Pessoa> query = builder.createQuery(Pessoa.class);
			Root<Pessoa> root = query.from(Pessoa.class);
			if (tipoContato != null){
				predicate = builder.equal(root.<String>get(tipoContato), true);
			}
			pessoas = session.createQuery(query.orderBy(builder.asc(root.get("nome"))).where(predicate)).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return pessoas;
	}

}
