package sicaf.pessoa;

import java.util.List;

import sicaf.pessoaSetor.PessoaSetor;
import sicaf.util.DAOFactory;

public class PessoaRN {
	private PessoaDAO pessoaDAO;

	public PessoaRN() {
		this.pessoaDAO = DAOFactory.criarPessoaDAO();
	}

	public Pessoa carregar(Integer codigo) {
		return this.pessoaDAO.carregar(codigo);
	}

	public void salvar(Pessoa pessoa) {		
	
		if(pessoa.getId()==null || pessoa.getId() == 0){
			pessoaDAO.salvar( pessoa);	
		}else{
	//		pessoaDAO.atualizar(pessoa);
		}
	}

	public void excluir(Pessoa pessoa) {
		this.pessoaDAO.excluir(pessoa);
	}

	public List<Pessoa> listar() {
		return this.pessoaDAO.listar();
	}
	
	public List<PessoaSetor> listarSetores(){
		return this.pessoaDAO.listarSetores();
	}
}
