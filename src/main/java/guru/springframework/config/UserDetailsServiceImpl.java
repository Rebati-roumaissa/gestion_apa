package guru.springframework.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	    
    @Autowired
    private UtilisateurRepository userRepository;
    
    /*
    @Override    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
      //  log.info("loadUserByUsername() : {}", username);
        return new PdfUserDetails(user);
    }*/
    
   
    @Autowired
    private  RoleRepository  rolerepo;
    
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	Utilisateur user = userRepository.findByUsername(userName);
    	List<Role> listeRole=rolerepo.findAllByUtilisateurs(user);
    	 UserDetails userDetails = (UserDetails) new User(user.getUsername(), user.getPassword(),
         getAuthorities(listeRole));
         return  userDetails;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role> listeRole) {
        String[] userRoles = listeRole.stream().map((role) -> role.getNom()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);

        return authorities;
    }
}
