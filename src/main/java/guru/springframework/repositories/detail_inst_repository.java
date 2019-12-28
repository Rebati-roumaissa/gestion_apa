package guru.springframework.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.detail_inst;
import guru.springframework.domain.detail_rh;

@Repository
public interface detail_inst_repository extends JpaRepository <detail_inst,Integer> {
	List<detail_inst>  findByCat(int id);
	List<detail_inst>  findAll();
	detail_inst findByIdDetail(int id);

}
