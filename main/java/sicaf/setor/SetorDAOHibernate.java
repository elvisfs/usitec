package sicaf.setor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import sicaf.util.DAOException;

public class SetorDAOHibernate implements SetorDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Setor setor) throws DAOException {
		String hql = "select s.nomeSetor from Setor s where s.nomeSetor = :nomeSetor";
		String nomeSetor = setor.getNomeSetor();
		Query<?> consulta = this.session.createQuery(hql);
		consulta.setParameter("nomeSetor", nomeSetor);
		if (consulta.getResultList().size()>0) {
			throw new DAOException("Setor já existente");
		}
		this.session.saveOrUpdate(setor);

	}

	@Override
	public void excluir(Setor setor) {
		this.session.delete(setor);

	}

	@Override
	public Setor carregar(Integer codigo) {

		return (Setor) this.session.get(Setor.class, codigo);
	}

	@Override
	public List<Setor> listar() {

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Setor> criteria = builder.createQuery(Setor.class);
		criteria.from(Setor.class);
		List<Setor> setores = session.createQuery(criteria).getResultList();
		return setores;

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
	public void atualizar(Setor setor)  throws DAOException{
		String hql = "select s.nomeSetor from Setor s where s.nomeSetor = :nomeSetor and s.id != :idSetor";
		String nomeSetor = setor.getNomeSetor();
		Integer idSetor = setor.getId();
		Query<?> consulta = this.session.createQuery(hql);
		consulta.setParameter("nomeSetor", nomeSetor);
		consulta.setParameter("idSetor", idSetor);
		if (consulta.getResultList().size() >0) {
			throw new DAOException("Setor já existente");
		}
		this.session.update(setor);
		
	}

}
