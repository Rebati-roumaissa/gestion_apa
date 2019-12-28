package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.domain.Localisation;
import guru.springframework.repositories.LocalisationRepository;

public class LocalisationServiceImp extends LocalisationService {

	
		private LocalisationRepository localisationRepository;

	    @Autowired
	    public LocalisationServiceImp(LocalisationRepository localisationRepository) {
	        this.localisationRepository = localisationRepository;
	    }


	    public List<Localisation> listAll() {
	        List<Localisation> localisations = new ArrayList<>();
	        localisationRepository.findAll().forEach(localisations::add); //fun with Java 8
	        return localisations;
	    }

	    public Localisation getById(Long id) {
	        return localisationRepository.findById(id).orElse(null);
	    }


}
