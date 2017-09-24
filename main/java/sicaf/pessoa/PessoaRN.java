package sicaf.pessoa;

import java.util.List;

import sicaf.contato.Contato;
import sicaf.pessoaSetor.PessoaSetor;
import sicaf.pessoaSetor.PessoaSetorRN;
import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;
import sicaf.util.TipoPessoa;

public class PessoaRN {
	private PessoaDAO pessoaDAO;

	public PessoaRN() {
		this.pessoaDAO = DAOFactory.criarPessoaDAO();
	}

	public Pessoa carregar(Integer codigo) {
		return this.pessoaDAO.carregar(codigo);
	}

	public void salvar(Pessoa pessoa) throws RNException {
		Integer id = pessoa.getId();
		Pessoa pessoaMerge = null;
		
		try {
			if(pessoa.getTipo() == TipoPessoa.FISICA ){
				pessoa.setCnpj(null);
				pessoa.setInscricaoEstadual(null);
			}
			else if(pessoa.getTipo() == TipoPessoa.JURIDICA){
				pessoa.setCpf(null);
				pessoa.setIdentidade(null);
			}
			
			if(id == null || id== 0){
				pessoaDAO.salvar(pessoa);
			}
			else{
				pessoaDAO.atualizar(pessoa);
			}
			
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
			
		}

	}

	public void excluir(Pessoa pessoa) throws RNException {
		try {
			this.pessoaDAO.excluir(pessoa);
		} catch (DAOException e) {
			throw (new RNException(e.getMessage()));
		}
	}
	
	public List<Pessoa> listar(String relacionamento) {
		try {
			return this.pessoaDAO.listar(relacionamento);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Contato> listarSetores() {
		return this.pessoaDAO.listarSetores();
	}
}
