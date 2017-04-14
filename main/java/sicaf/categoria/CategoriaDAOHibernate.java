package sicaf.categoria;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

public class CategoriaDAOHibernate implements CategoriaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		Categoria merged = (Categoria) this.session.merge(categoria);
		this.session.flush();
		this.session.clear();
		return merged;
	}

	@Override
	public void excluir(Categoria categoria) {
		categoria = (Categoria) this.carregar(categoria.getCodigo());
		this.session.delete(categoria);
		this.session.flush();
		this.session.clear();
		
	}

	@Override
	public Categoria carregar(Integer categoria) {
		return (Categoria) this.session.get(Categoria.class, categoria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listar() {
		String hql = "select c from Categoria c where c.pai is null";
		Query<Categoria> query = this.session.createQuery(hql);
	
		List<Categoria> lista =  query.getResultList();
		return lista;
	}
}
