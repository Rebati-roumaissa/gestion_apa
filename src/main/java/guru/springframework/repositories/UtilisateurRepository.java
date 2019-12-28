package guru.springframework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>
{
     Utilisateur findByEmail(String email);
     Utilisateur findByUsername(String user_name);
     Utilisateur findTopByOrderByIdDesc();
}
