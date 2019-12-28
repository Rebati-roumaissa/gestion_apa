package guru.springframework.repositories;

import guru.springframework.domain.mot_cle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface motCleRepository extends JpaRepository<mot_cle, Long> {
    mot_cle findBymotcle(String motcle);

    Optional<mot_cle> findByidmotcle(Long idmotcle);

    boolean existsByMotcle(String motcle);

    @Override
    List<mot_cle> findAllById(Iterable<Long> iterable);
}
