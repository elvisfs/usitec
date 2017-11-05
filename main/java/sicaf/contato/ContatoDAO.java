package sicaf.contato;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.pessoa.Pessoa;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class ContatoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Contato salvar(Contato contato) throws DAOException {
		Contato merge=null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.merge(contato);
			this.session.flush();
			this.session.clear();
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}
		return merge;
	}

	public void atualizar(Contato contato) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.update(contato);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}

	}

	public void excluir(Contato contato) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			for(PessoaSetor p:contato.getSetores()){
				p.setContato(null);
				this.session.update(p);
			}
			this.session.delete(contato);
			this.session.getTransaction().commit();
		} catch (javax.persistence.EntityNotFoundException e) {
			throw new DAOException("Contato não cadastrado");
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}

	}

	public Contato carregar(Integer codigo) {

		this.session = HibernateUtil.getSessionFactory().openSession();
		Contato contato;

		contato = this.session.get(Contato.class, codigo);

		this.session.close();
		return contato;
	}

	@SuppressWarnings("unchecked")
	public List<Contato> buscarPorIdPessoa(String idPessoa) {
		String hql = "select p from Contato p where p.pessa.id like ?";
		Query<?> consulta = this.session.createQuery(hql);
		consulta.setParameter(0, idPessoa);
		List<Contato> setores;
		setores = (List<Contato>) consulta.getResultList();
		return setores;
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listarPorPessoa(Pessoa pessoa) throws DAOException {
		List<Contato> contatos = null;
		this.session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
			Root<Contato> root = query.from(Contato.class);			
			query.distinct(true).where(builder.equal(root.get("pessoa"), pessoa)); 
			contatos = session.createQuery(query.orderBy(builder.asc(root.get("nome")))).getResultList();
	 
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}
		
		return contatos;
	}
}
