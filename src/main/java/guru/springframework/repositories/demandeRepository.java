package guru.springframework.repositories;

import guru.springframework.domain.Utilisateur;
import guru.springframework.domain.demande;
import guru.springframework.domain.demandeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface demandeRepository extends JpaRepository<demande, Long> {
    /**
     * Method for finding files by filename
     *
     * @param etatDemande
     * @return Optional containing Document if it exists
     */
    List<demande> findByEtatDemande(String etatDemande);

    /**
     * @param typeDemande
     * @return Optional containing Document if it exists
     */
    List<demande> findByTypeDemande(String typeDemande);

    /**
     * Method to get all uploads in a sincle collection.
     *
     * @return Collection containing uploads
     */
    List<demande> findAll();

    demande findByIddemande(int id);

    List<demande> findByDemandeurAndEtatDemande(demandeur demandeur, String etatDemande);

    List<demande> findByUtilisateurAndEtatDemande(Utilisateur demandeur, String etatDemande);
}
