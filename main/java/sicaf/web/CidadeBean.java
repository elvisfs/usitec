package sicaf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sicaf.cidade.Cidade;
import sicaf.cidade.CidadeRN;
import sicaf.util.RNException;

@ManagedBean(name = "cidadeBean")
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade = new Cidade();
	private Cidade cidExcluir;
	private List<Cidade> lista;
	private static String[] estados = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};


	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getLista() {
		CidadeRN cidadeRN = new CidadeRN();
		try {
			this.lista = cidadeRN.listar();
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public void setLista(List<Cidade> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.cidade = new Cidade();
		return "/restrito/cidade";
	}
	
	public void excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		CidadeRN cidadeRN = new CidadeRN();
		
		try {
			cidadeRN.excluir(this.cidade);
		} catch (RNException e) {
			context.addMessage("teste", (new FacesMessage(e.getMessage())));
		}
	}
	
	   
    
	public String editar(){
		return "/restrito/cidade";
		
	}
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();

		CidadeRN cidadeRN = new CidadeRN();
		try {
			cidadeRN.salvar(this.cidade);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
		
	}
	public List<String> completeText(String query) {
		       
        List<String> filtroEstados = new ArrayList<String>();
       
        for(int i = 0; i < estados.length; i++) {
        	if(estados[i].toLowerCase().startsWith(query)){
        		filtroEstados.add(estados[i]);
        	}          
         
        }
         
        return filtroEstados;
    }
	
	public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public Cidade getCidExcluir() {
		return cidExcluir;
	}

	public void setCidExcluir(Cidade cidExcluir) {
		this.cidExcluir = cidExcluir;
	}
	
}
