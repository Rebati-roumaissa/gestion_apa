package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.cat_inst;
@Repository
public interface cat_inst_repository extends JpaRepository<cat_inst,Integer> {

}
