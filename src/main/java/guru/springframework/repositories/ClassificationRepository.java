package guru.springframework.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.domain.Ressource;

public interface ClassificationRepository extends CrudRepository<Classification,Long> {

	List<Classification> findByLot(Lot lot);
	Classification getByNom(String nom);
	Classification findByNom(String string);


}
