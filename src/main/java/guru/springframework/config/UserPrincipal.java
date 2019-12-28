package guru.springframework.config;

import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RoleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    //private User user;
    Log log = LogFactory.getLog(UserPrincipal.class);
    private Utilisateur utilisateur;
    RoleRepository roleRepository;
    PermissionRepo permissionRepository;

    /*public UserPrincipal(User user){
        this.user = user;
    }*/
    public UserPrincipal(Utilisateur utilisateur, RoleRepository roleRepository, PermissionRepo permissionRepository) {
        this.utilisateur = utilisateur;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Extract list of permissions (name)
        log.info(utilisateur.getNom());
        roleRepository.findAllByUtilisateurs(this.utilisateur).forEach(r ->
        {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r.getNom());
            authorities.add(authority);
            permissionRepository.findAllByRoles(r).forEach(p -> {
                GrantedAuthority authorityp = new SimpleGrantedAuthority(p.getNom());
                authorities.add(authorityp);
            });
        });
        log.info(authorities.toString());
        return authorities;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String getPassword() {
        return this.utilisateur.getPassword();//this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.utilisateur.getUsername();// this.user.getUsername();
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

        //  return (this.user.getActive() == 1);
        return true;
    }
}
