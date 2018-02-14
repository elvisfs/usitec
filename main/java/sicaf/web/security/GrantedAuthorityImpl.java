package sicaf.web.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

	public GrantedAuthorityImpl(String role) {
		this.role = role;
	}
	
	
	

}
