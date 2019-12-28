package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Lot;


public interface LotRepository extends JpaRepository<Lot,Long> {
	
}