package sicaf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "zerosEsquerdaConverter")
public class ZeroEsquerdaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
            return value;
        }
        return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		return zerosEsquerda((String) value);
	}

	public String zerosEsquerda(String valor) {
        while (valor.length() < 3) {
            valor = "0" + valor;
        }
        return valor;
    }
}


