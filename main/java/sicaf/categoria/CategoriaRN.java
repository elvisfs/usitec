package sicaf.categoria;

import java.util.List;

import sicaf.util.DAOFactory;

public class CategoriaRN {
	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}

	public List<Categoria> listar() {
		return this.categoriaDAO.listar();
	}

	public Categoria salvar(Categoria categoria) {

		Categoria pai = categoria.getPai();
		if (pai == null) {
			String msg = "A Categoria " + categoria.getDescricao() + " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
		}

		boolean mudouFator = pai.getFator() != categoria.getFator();
		categoria.setFator(pai.getFator());
		categoria = this.categoriaDAO.salvar(categoria);

		if (mudouFator) {
			categoria = this.carregar(categoria.getCodigo());
			this.replicarFator(categoria, categoria.getFator());
		}
		return categoria;
	}

	private void replicarFator(Categoria categoria, int fator) {
		if (categoria.getFilhos() != null) {
			for (Categoria filho : categoria.getFilhos()) {
				filho.setFator(fator);
				this.categoriaDAO.salvar(filho);
				this.replicarFator(filho, fator);
			}

		}
	}

	public void excluir(Categoria categoria){
		this.categoriaDAO.excluir(categoria);
		
	}
	
	
	public Categoria carregar(Integer categoria) {

		return this.categoriaDAO.carregar(categoria);

	}
}
