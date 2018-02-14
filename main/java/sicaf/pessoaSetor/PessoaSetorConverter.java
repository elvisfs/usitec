package sicaf.pessoaSetor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(forClass=PessoaSetor.class, value="pessoaSetorConverter")
public class PessoaSetorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
			 	Object ret = null;
		   
		        Object dualList = ((PickList) component).getValue();
		        DualListModel<?> dl = (DualListModel<?>) dualList;
		        for (Object o : dl.getSource()) {
		            String id = "" + ((PessoaSetor) o).getId();
		            if (value.equals(id)) {
		                ret = o;
		                break;
		            }
		        }
		        if (ret == null)
		            for (Object o : dl.getTarget()) {
		                String id = "" + ((PessoaSetor) o).getId();
		                if (value.equals(id)) {
		                    ret = o;
		                    break;
		                }
		            }
		        return ret;
		   
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (component instanceof PickList) {
			 String str = "";
			 if (value instanceof PessoaSetor) {
		        str = "" + ((PessoaSetor) value).getId();
			 }
		    return str;
		 }
		 else{
			 PessoaSetor setor = (PessoaSetor) value;
			 if(setor == null || setor.getId() == null){
				 return null;
			 }
			 return String.valueOf(((PessoaSetor) setor).getId());
		 }
		
	}

}


/*
@FacesConverter(forClass=Setor.class, value="setorConverter")
public class SetorConverter implements Converter{
	
		   
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		 else{
		    
		  //
			 if(value == null || value.isEmpty()){
				 return null;
			 }
		
			 SetorDAO setorDAO = DAOFactory.criarSetorDAO();
			 Setor setor=null;
			 try {
				 setor = setorDAO.carregar(Integer.parseInt(value));
			 } catch (NumberFormatException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 } catch (DAOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		
			 return setor ;
		 }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		 
	}

}

*/