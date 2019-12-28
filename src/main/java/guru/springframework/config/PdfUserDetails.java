package guru.springframework.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.RoleRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PdfUserDetails implements UserDetails {

	
	private Utilisateur user;
	@Autowired
    private RoleRepository rolerepo;
    
	//List<Role> listeRole=rolerepo.findAllByUtilisateurs(user);

    public PdfUserDetails(Utilisateur user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
       // return listeRole.stream().map(authority -> new SimpleGrantedAuthority(authority.getNom())).collect(Collectors.toList());
       return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
    public Long getId() {
        return user.getId();	
    }
    public List<Role> getRole() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUser_name();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public Utilisateur getUserDetails() {
        return user;
    }
}
