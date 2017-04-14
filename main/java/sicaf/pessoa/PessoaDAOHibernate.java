package sicaf.pessoa;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import sicaf.pessoaSetor.PessoaSetor;

public class PessoaDAOHibernate implements PessoaDAO{
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	@Override
	public void salvar(Pessoa pessoa) {
		this.session.save(pessoa);
	}

	@Override
	public void excluir(Pessoa pessoa) {
		this.session.delete(pessoa);
		
	}

	@Override
	public Pessoa carregar(Integer id) {
		
		return (Pessoa) (this.session.get(Pessoa.class, id));
	}

	@Override
	public List<Pessoa> listar() {
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		criteria.from(Pessoa.class);
		List<Pessoa> pessoas = session.createQuery(criteria).getResultList();

		return pessoas;
	}
	
	public List<PessoaSetor> listarSetores(){
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<PessoaSetor> criteria = builder.createQuery(PessoaSetor.class);
		criteria.from(Pessoa.class);
		List<PessoaSetor> pessoas = session.createQuery(criteria).getResultList();

		return pessoas;
		
	}


}
