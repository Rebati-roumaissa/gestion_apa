package guru.springframework.repositories;


import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Commune;

public interface CommuneRepository extends CrudRepository<Commune,Long> {
}