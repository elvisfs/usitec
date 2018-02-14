package sicaf.web;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import sicaf.util.TipoPessoa;

@ManagedBean
@ApplicationScoped
public class TipoPessoaBean {
	private TipoPessoa tipo ;

	public TipoPessoa getTipo() {
		
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}
	

   
}

