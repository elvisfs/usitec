package sicaf.perfil;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import sicaf.util.DAOException;
import sicaf.util.HibernateUtil;

public class PerfilDAOHibernate implements PerfilDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Perfil carregar(Integer id) {
		return (Perfil) (this.session.get(Perfil.class, id));
	}

	@Override
	public List<Perfil> listar() throws DAOException {
		List<Perfil> perfis = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Perfil> criteria = builder.createQuery(Perfil.class);
			criteria.from(Perfil.class);
			perfis = session.createQuery(criteria).getResultList();
		} catch (javax.persistence.PersistenceException e) {
			throw (new DAOException(e.getMessage()));
		} finally {
			this.session.close();
		}

		return perfis;

	}

	@SuppressWarnings("unchecked")
	public List<String> listarPerfisByUser(Integer idUsuario) throws DAOException {
		List<?> lista = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			idUsuario = 1;
			String sql = "select p.id_perfil from Perfil p join p.usuarios u where u.id = :idUsuario";
			Query consulta = this.session.createQuery(sql);
			consulta.setParameter("idUsuario", idUsuario);
			consulta.getResultList();
			lista = consulta.getResultList();
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return (List<String>) lista;

	}

	@SuppressWarnings("unchecked")
	public List<Perfil> listarPerfisNaoAtribuidosByUser(Integer idUsuario) throws DAOException {
		List<?> lista = null;
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			String sql = "select p1 from Perfil p1 where p1.id_perfil not in (select p.id_perfil from Perfil p join p.usuarios u where u.id = :idUsuario)";
			Query consulta = this.session.createQuery(sql);
			consulta.setParameter("idUsuario", idUsuario);
			consulta.getResultList();
			lista = consulta.getResultList();
		} catch (javax.persistence.NoResultException e) {
			throw new DAOException(e.getMessage());
		} finally {
			session.close();
		}
		return (List<Perfil>) lista;

	}

}
