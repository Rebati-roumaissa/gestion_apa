package guru.springframework.repositories;

import guru.springframework.domain.PermisAcces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisRepository extends JpaRepository<PermisAcces, String> {
    @Override
    List<PermisAcces> findAll();

    PermisAcces findByCodePermis(String code);

    PermisAcces findByIddemande(int id_demande);

    List<PermisAcces> findByIddemandeur(long id_demandeur);
}
