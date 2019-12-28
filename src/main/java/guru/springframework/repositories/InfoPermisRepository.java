package guru.springframework.repositories;

import guru.springframework.domain.InfoPermis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoPermisRepository extends JpaRepository<InfoPermis, Long> {
    @Override
    List<InfoPermis> findAll();

    InfoPermis findByCodePermis(String code);

    InfoPermis findByNss(long nss);

    InfoPermis findByNif(long nif);

    InfoPermis findByIddemande(int iddemande);
}