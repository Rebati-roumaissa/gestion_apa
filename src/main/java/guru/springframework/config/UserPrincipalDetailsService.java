package guru.springframework.config;

import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    //private UserRepository userRepository;
    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PermissionRepo permissionRepository;

    public UserPrincipalDetailsService(/*UserRepository userRepository,*/
                                       RoleRepository roleRepository,
                                       UtilisateurRepository utilisateurRepository,
                                       PermissionRepo permissionRepository) {
        this.roleRepository = roleRepository;
      //  this.userRepository = userRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.permissionRepository = permissionRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByUsername(s);// this.userRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(user, roleRepository, permissionRepository);
        return userPrincipal;
    }

}
