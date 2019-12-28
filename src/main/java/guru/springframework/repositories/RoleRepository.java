package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Localisation;
import guru.springframework.domain.Ressource;
import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;

public interface RoleRepository  extends JpaRepository<Role, Long>{
    Role findByNom(String role);
    List<Role> findAllByUtilisateurs(Utilisateur id);
    List<Role> findByUtilisateurs_id(Long id);


}
