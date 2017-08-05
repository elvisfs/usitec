package sicaf.pessoa;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sicaf.util.DAOFactory;
import sicaf.util.HibernateUtil;
@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(value == null || value.isEmpty()){
			return null;
		}
		
		PessoaDAO pessoaDAO = DAOFactory.criarPessoaDAO();
			return pessoaDAO.carregar(Integer.parseInt(value)) ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Pessoa pessoa = (Pessoa) value;
		if(pessoa == null || pessoa.getId() == null){
			return null;
		}
		return String.valueOf(pessoa.getId());
	}

}



