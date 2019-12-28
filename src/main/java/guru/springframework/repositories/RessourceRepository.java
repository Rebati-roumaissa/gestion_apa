package guru.springframework.repositories;



import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.EtatValidation;

import java.util.List;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.domain.Ressource;

public interface RessourceRepository extends JpaRepository<Ressource,Long> {

	List<Ressource> findAllByLots(Lot id, Pageable pageable);
	List<Ressource> findAllByLots(Lot id);

	List<Ressource> findAllByClassifications(Classification id, Pageable pageable);
	List<Ressource> findAllByNomcommun(String surname);

    List<Ressource> findByClassifications_Lot_idAndEtat(Long id, EtatValidation etat);


}