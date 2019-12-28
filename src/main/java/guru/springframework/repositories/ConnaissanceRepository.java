package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Connaissance;
import guru.springframework.domain.Ressource;

public interface ConnaissanceRepository extends JpaRepository<Connaissance,Long> {
	List<Connaissance> findAllByRessources(Ressource id);


}
