package sicaf.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import sicaf.ativoFixo.AtivoFixo;
import sicaf.ativoFixo.AtivoFixoRN;
import sicaf.centroCusto.CentroCusto;
import sicaf.centroCusto.CentroCustoRN;
import sicaf.util.RNException;

@ManagedBean(name="ativoFixoBean")

public class AtivoFixoBean {
	private AtivoFixo ativoFixo = new AtivoFixo();
	private List<AtivoFixo> lista;
	private List<CentroCusto> centrosCusto;
	
	@PostConstruct
	public void init() {
		CentroCustoRN centroCustoRN = new CentroCustoRN();
		try {
			this.centrosCusto = centroCustoRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public AtivoFixo getAtivoFixo() {
		return ativoFixo;
	}
	
	public void setAtivoFixo(AtivoFixo ativoFixo) {
		this.ativoFixo = ativoFixo;
	}
	
	public List<AtivoFixo> getLista() {
		AtivoFixoRN ativoFixoRN = new AtivoFixoRN();
		try {
			this.lista = ativoFixoRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public void setLista(List<AtivoFixo> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.ativoFixo = new AtivoFixo();
		return "/restrito/ativoFixo";
	}
	
	public void excluir(){
		AtivoFixoRN ativoFixoRN = new AtivoFixoRN();
		try {
			ativoFixoRN.excluir(this.ativoFixo);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String editar(){
		
		return "/restrito/ativoFixo";
		
	}
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();

		AtivoFixoRN ativoFixoRN = new AtivoFixoRN();
		try {
			ativoFixoRN.salvar(this.ativoFixo);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
		
	}

	public List<CentroCusto> getCentrosCusto() {
		return centrosCusto;
	}

	
}
