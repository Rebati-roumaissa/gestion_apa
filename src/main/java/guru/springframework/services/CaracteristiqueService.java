package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Caracteristique;
import guru.springframework.repositories.CaracteristiqueRepository;
@Service

public class CaracteristiqueService {


	  @Autowired
	    private CaracteristiqueRepository caracteristiqueRespository;
	   
	    public List<Caracteristique> findAll() {
	        return caracteristiqueRespository.findAll();
	    }

	    public void save(Caracteristique caracteristique) {
	        caracteristiqueRespository.save(caracteristique);
	    }

	    public Optional<Caracteristique> findById(long id){
	        return caracteristiqueRespository.findById(id);
	    }

}
