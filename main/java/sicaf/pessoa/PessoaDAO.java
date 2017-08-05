package sicaf.pessoa;

import java.util.List;

import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.DAOException;

public interface PessoaDAO {
	public void salvar(Pessoa pessoa) throws DAOException;

	public void excluir(Pessoa pessoa) throws DAOException;

	public Pessoa carregar(Integer id);

	public List<Pessoa> listar() throws DAOException;

	public List<PessoaSetor> listarSetores();

	public void atualizar(Pessoa pessoa) throws DAOException;
}
