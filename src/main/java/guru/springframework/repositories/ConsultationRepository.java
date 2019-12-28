package guru.springframework.repositories;

import guru.springframework.domain.Consultaion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultaion, Long> {
    @Override
    List<Consultaion> findAll();

    List<ConsultationRepository> findAllByIdobjAndTypeconsultationAndDateconsultationBetween
            (int idobj, String typeconsultation, Date dateD, Date dateF);

}
