package guru.springframework.repositories;

import guru.springframework.domain.Preuve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface preuveRepository extends JpaRepository<Preuve, Long> {
    @Override
    <S extends Preuve> List<S> saveAll(Iterable<S> iterable);

    @Override
    <S extends Preuve> S save(S s);

    @Override
    List<Preuve> findAll();
}
