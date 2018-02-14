package sicaf.centroCusto;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=CentroCusto.class)
public class CentroCustoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	CentroCustoRN centroCustoRN = new CentroCustoRN();
                return centroCustoRN.carregar(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na conversão", "Classe inválida"));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null) {
			return String.valueOf(((CentroCusto) object).getId());
        }
        else {
            return null;
        }
	}
}



