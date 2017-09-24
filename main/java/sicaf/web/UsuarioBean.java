package sicaf.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.util.DigestUtils;

import sicaf.perfil.Perfil;
import sicaf.perfil.PerfilRN;
import sicaf.usuario.Usuario;
import sicaf.usuario.UsuarioRN;
import sicaf.util.RNException;

@ManagedBean(name = "usuarioBean")


public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private String email;
	private String senhaCriptografada;
	private List<Usuario> lista;
	private boolean modoEditar;
	private DualListModel<Perfil> dLPerfis;


	public DualListModel<Perfil> getdLPerfis() {
		return dLPerfis;
	}

	public void setdLPerfis(DualListModel<Perfil> dLPerfis) {
		this.dLPerfis = dLPerfis;
	}

	@PostConstruct
	public void init() {
			PerfilRN perfilRN = new PerfilRN();
			List<Perfil> themesSource = null;
			try {
			themesSource = perfilRN.listar();
			
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			List<Perfil> themesTarget = new ArrayList<Perfil>();
			dLPerfis = new DualListModel<Perfil>(themesSource, themesTarget);
		

	}

	
	private String destinoSalvar;

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String novo() {
		this.usuario = new Usuario();
		this.init();
		this.usuario.setAtivo(true);
		return "/admin/usuario";
	}

	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		this.senhaCriptografada = this.usuario.getSenha();
		return "/admin/usuario";
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		this.usuario.setPerfis(this.getdLPerfis().getTarget());
		
		String senha = this.usuario.getSenha();
		
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
		}
		if (senha != null && senha.trim().length() == 0) {
			this.usuario.setSenha(this.senhaCriptografada);
		} else {
			String senhaCripto = DigestUtils.md5DigestAsHex(senha.getBytes());
			this.usuario.setSenha(senhaCripto);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		try {
			usuarioRN.salvar(this.usuario);
			context.addMessage(null, (new FacesMessage("Registro salvo com sucesso")));
		} catch (RNException e) {
			context.addMessage(null, (new FacesMessage(e.getMessage())));
		}
	//	this.usuario = new Usuario();
	}

	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		try {
			usuarioRN.excluir(this.usuario);
		} catch (RNException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lista = null;
		return null;
	}

	public String ativar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (this.usuario.isAtivo()) {
			this.usuario.setAtivo(false);
		} else {
			this.usuario.setAtivo(true);
		}
		UsuarioRN usuarioRN = new UsuarioRN();
		try {
			usuarioRN.salvar(this.usuario);
		} catch (RNException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public List<Usuario> getLista() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			try {
				this.lista = usuarioRN.listar();
			} catch (Exception e) {
				context.addMessage(null, new FacesMessage(e.getMessage()));
			}
		}
		return this.lista;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaCriptografada() {
		return senhaCriptografada;
	}

	public void setSenhaCriptografada(String senhaCriptografada) {
		this.senhaCriptografada = senhaCriptografada;
	}

	

	public boolean isModoEditar() {
		return modoEditar;
	}

	public void setModoEditar(boolean modoEditar) {
		if(modoEditar){
			for(Perfil p:this.usuario.getPerfis()){
				this.dLPerfis.getTarget().add(p);
			}
			PerfilRN perfilRN = new PerfilRN();
			try {
				this.dLPerfis.setSource(perfilRN.listarPerfilsNaoAtribuidos(this.usuario.getIdUsuario()));
			} catch (RNException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		this.modoEditar = modoEditar;
	}

}
