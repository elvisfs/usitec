package sicaf.perfil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter(value = "perfilConverter")
public class PerfilConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
	    Object ret = null;
	    if (arg1 instanceof PickList) {
	        Object dualList = ((PickList) arg1).getValue();
	        DualListModel<?> dl = (DualListModel<?>) dualList;
	        for (Object o : dl.getSource()) {
	            String id = "" + ((Perfil) o).getIdPerfil();
	            if (arg2.equals(id)) {
	                ret = o;
	                break;
	            }
	        }
	        if (ret == null)
	            for (Object o : dl.getTarget()) {
	                String id = "" + ((Perfil) o).getIdPerfil();
	                if (arg2.equals(id)) {
	                    ret = o;
	                    break;
	                }
	            }
	    }
	    return ret;
	}
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	    String str = "";
	    if (arg2 instanceof Perfil) {
	        str = "" + ((Perfil) arg2).getIdPerfil();
	    }
	    return str;
	}
}
