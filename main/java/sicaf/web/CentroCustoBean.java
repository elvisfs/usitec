package sicaf.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import sicaf.centroCusto.CentroCusto;
import sicaf.centroCusto.CentroCustoRN;
import sicaf.util.RNException;

@ManagedBean(name="centroCustoBean")
public class CentroCustoBean {
	private CentroCusto centroCusto = new CentroCusto();
	private List<CentroCusto> lista;
	
	public CentroCusto getCentroCusto() {
		return centroCusto;
	}
	
	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}
	
	public List<CentroCusto> getLista() {
		CentroCustoRN centroCustoRN = new CentroCustoRN();
		try {
			this.lista = centroCustoRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public void setLista(List<CentroCusto> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.centroCusto = new CentroCusto();
		return "/restrito/centroCusto";
	}
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		CentroCustoRN centroCustoRN = new CentroCustoRN();
		try {
			centroCustoRN.excluir(this.centroCusto);
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
	}
	
	public String editar(){
		
		return "/restrito/centroCusto";
		
	}
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();

		CentroCustoRN centroCustoRN = new CentroCustoRN();
		try {
			centroCustoRN.salvar(this.centroCusto);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
		
	}

}
