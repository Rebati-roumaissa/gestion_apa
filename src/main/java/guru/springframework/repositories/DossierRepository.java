package guru.springframework.repositories;

import guru.springframework.domain.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierRepository extends JpaRepository<Dossier, Long> {
    @Override
    List<Dossier> findAll();

    Dossier findByTypeDemande(String typeDemande);

}
