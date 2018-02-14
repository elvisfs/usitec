package sicaf.lancamento;

import java.util.Date;
import java.util.List;

import sicaf.util.DAOException;
import sicaf.util.DAOFactory;
import sicaf.util.RNException;

public class LancamentoRN {
	private LancamentoDAO lancamentoDAO;

	public LancamentoRN(){
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}

	public void salvar(Lancamento lancamento) throws RNException{
		try {
			this.lancamentoDAO.salvar(lancamento);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}
	
	public void excluir(Lancamento lancamento) throws RNException{
		try {
			this.lancamentoDAO.excluir(lancamento);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}
	
	public Lancamento carregar(Integer id){
		return this.lancamentoDAO.carregar(id);
	}
	
	public List<Lancamento> listar(Date dataInicio, Date dataFim) throws RNException{
		try {
			return this.lancamentoDAO.listar(dataInicio, dataFim);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}
}
