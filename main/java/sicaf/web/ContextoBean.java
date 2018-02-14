package sicaf.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import sicaf.usuario.Usuario;
import sicaf.usuario.UsuarioRN;

@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {
	private Usuario usuarioLogado = null;

	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if (this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())) {
			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				try {
					this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}

}
