package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Caracteristique;
import guru.springframework.repositories.CaracteristiqueRepository;

public class CaracteristiqueServiceImp extends CaracteristiqueService {

	 private CaracteristiqueRepository caracteristiqueRepository;

	    @Autowired
	    public CaracteristiqueServiceImp(CaracteristiqueRepository caracteristiqueRepository) {
	        this.caracteristiqueRepository = caracteristiqueRepository;
	    }


	    public List<Caracteristique> listAll() {
	        List<Caracteristique> caracteristiques = new ArrayList<>();
	        caracteristiqueRepository.findAll().forEach(caracteristiques::add); //fun with Java 8
	        return caracteristiques;
	    }

	    public Caracteristique getById(Long id) {
	        return caracteristiqueRepository.findById(id).orElse(null);
	    }


}
