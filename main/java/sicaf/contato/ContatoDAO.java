package sicaf.contato;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class ContatoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Contato contato) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			this.session.saveOrUpdate(contato);
			this.session.getTransaction().commit();
		} catch (javax.persistence.PersistenceException e) {
			throw new DAOException(e.getMessage());
		} finally {
			if (this.session.getTransaction().isActive())
				this.session.getTransaction().rollback();
			this.session.close();
		}

	}

	public void atualizar(Contato contato) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void excluir(Contato contato) throws DAOException {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
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
	public List<Contato> listarPorIdPessoa(Integer idPessoa) throws DAOException {
		List<Contato> contatos = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			String hql = "select c from Contato c where c.pessoa.id = ?";
			Query<?> consulta = this.session.createQuery(hql);
			consulta.setParameter(0, idPessoa);
			contatos = (List<Contato>) consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.session.close();
		}

		return contatos;
	}
}
