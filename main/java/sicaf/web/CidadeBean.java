package sicaf.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import sicaf.cidade.Cidade;
import sicaf.cidade.CidadeRN;
import sicaf.util.RNException;

@ManagedBean(name = "cidadeBean")
public class CidadeBean {
	private Cidade cidade = new Cidade();
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
		CidadeRN cidadeRN = new CidadeRN();
		try {
			cidadeRN.excluir(this.cidade);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
