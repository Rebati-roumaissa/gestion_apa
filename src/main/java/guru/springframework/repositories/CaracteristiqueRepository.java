package guru.springframework.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Caracteristique;

public interface CaracteristiqueRepository extends JpaRepository<Caracteristique,Long> {
}