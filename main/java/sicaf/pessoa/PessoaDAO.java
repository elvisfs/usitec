package sicaf.pessoa;

import java.util.List;

import sicaf.pessoaSetor.PessoaSetor;

public interface PessoaDAO {
	public void salvar(Pessoa pessoa);

	public void excluir(Pessoa pessoa);

	public Pessoa carregar(Integer id);

	public List<Pessoa> listar();

	public List<PessoaSetor> listarSetores();
}
