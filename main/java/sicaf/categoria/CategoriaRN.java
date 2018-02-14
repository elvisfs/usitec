package sicaf.categoria;

import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class CategoriaRN {
	private CategoriaDAO categoriaDAO;

	public CategoriaRN() {
		this.categoriaDAO = DAOFactory.criarCategoriaDAO();
	}

	public List<Categoria> listar() throws RNException {
		try {
			return this.categoriaDAO.listar();
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	public Categoria salvar(Categoria categoria) throws RNException {

		Categoria pai = categoria.getPai();
		if (pai == null) {
			String msg = "A Categoria " + categoria.getDescricao() + " deve ter um pai definido";
			throw new IllegalArgumentException(msg);
		}

		boolean mudouFator = pai.getFator() != categoria.getFator();
		categoria.setFator(pai.getFator());
		try {
			categoria = this.categoriaDAO.salvar(categoria);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}

		if (mudouFator) {
			categoria = this.carregar(categoria.getCodigo());
			this.replicarFator(categoria, categoria.getFator());
		}
		return categoria;
	}

	private void replicarFator(Categoria categoria, int fator) throws RNException {
		if (categoria.getFilhos() != null) {
			for (Categoria filho : categoria.getFilhos()) {
				filho.setFator(fator);
				try {
					this.categoriaDAO.salvar(filho);
				} catch (DAOException e) {
					throw new RNException(e.getMessage());
				}
				this.replicarFator(filho, fator);
			}

		}
	}

	public void excluir(Categoria categoria) throws RNException {
		try {
			this.categoriaDAO.excluir(categoria);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}

	}

	public Categoria carregar(Integer categoria) throws RNException {

		try {
			return this.categoriaDAO.carregar(categoria);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}

	}
}
