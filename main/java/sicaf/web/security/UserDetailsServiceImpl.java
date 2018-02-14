package sicaf.web.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sicaf.perfil.Perfil;
import sicaf.usuario.Usuario;
import sicaf.usuario.UsuarioDAO;
import sicaf.usuario.UsuarioRN;


public class UserDetailsServiceImpl implements UserDetailsService {

	  // Put user credentials from a DB, RemeberMe,..
	UsuarioDAO usuarioDAO ;
 
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UsuarioRN usuarioRN = new UsuarioRN(); 
    	
    	 Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
         Usuario usuario=null;
		try {
			usuario = usuarioRN.buscarPorLogin(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         for(Perfil p:usuario.getPerfis()){
         	authorities.add(new GrantedAuthorityImpl(p.getAuthority()));
         }
        
         UserDetails matchingUser = new UserDetailsImpl(usuario.getNome(),usuario.getSenha(),authorities);
        return matchingUser;
    }

}
