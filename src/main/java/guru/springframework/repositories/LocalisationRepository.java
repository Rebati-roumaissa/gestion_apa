package guru.springframework.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import guru.springframework.domain.Localisation;
import guru.springframework.domain.Ressource;


public interface LocalisationRepository extends  JpaRepository<Localisation,Long> {
	List<Localisation> findAllByRessources2(Ressource id);

}