package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.domain.Commune;
import guru.springframework.repositories.CommuneRepository;

public class CommuneServiceImp implements CommuneService {
	
	 private CommuneRepository communeRepository;

	    @Autowired
	    public CommuneServiceImp(CommuneRepository communeRepository) {
	        this.communeRepository = communeRepository;
	    }


	    @Override
	    public List<Commune> listAll() {
	        List<Commune> communes = new ArrayList<>();
	        communeRepository.findAll().forEach(communes::add); //fun with Java 8
	        return communes;
	    }

	    @Override
	    public Commune getById(Long id) {
	        return communeRepository.findById(id).orElse(null);
	    }

}
