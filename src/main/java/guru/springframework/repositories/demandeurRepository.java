package guru.springframework.repositories;

import guru.springframework.domain.demandeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface demandeurRepository extends JpaRepository<demandeur, Long> {

    /**
     * Method to get all demandeurs in a single collection.
     *
     * @return Collection containing demandeurs
     */
    List<demandeur> findAll();

    demandeur findByIddemandeur(int iddemandeur);

    demandeur findByUsername(String username);
}