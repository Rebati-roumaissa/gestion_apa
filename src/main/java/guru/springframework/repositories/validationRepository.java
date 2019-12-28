package guru.springframework.repositories;
import guru.springframework.domain.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface validationRepository extends JpaRepository<Validation,Long> {

}
