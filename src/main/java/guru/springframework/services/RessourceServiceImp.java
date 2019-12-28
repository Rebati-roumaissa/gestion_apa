package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.domain.Ressource;
import guru.springframework.repositories.RessourceRepository;

@Repository
public class RessourceServiceImp extends RessourceService {
	
	@Autowired
	 private RessourceRepository ressourceRepository;

	  public List<Ressource> listAll() {
	        List<Ressource> ressources = new ArrayList<>();
	        ressources = (List<Ressource>) ressourceRepository.findAll();
	      //  classificationRepository.findAll().forEach(classifications::add); //fun with Java 8
	        return ressources;
	    }

	public Ressource getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	 

}
