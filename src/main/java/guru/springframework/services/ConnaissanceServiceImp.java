package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.domain.Connaissance;
import guru.springframework.repositories.ConnaissanceRepository;

public class ConnaissanceServiceImp extends ConnaissanceService{
	
	
	 private ConnaissanceRepository connaissanceRepository;

	    @Autowired
	    public ConnaissanceServiceImp(ConnaissanceRepository connaissanceRepository) {
	        this.connaissanceRepository = connaissanceRepository;
	    }


	    public List<Connaissance> listAll() {
	        List<Connaissance> connaissances = new ArrayList<>();
	        connaissanceRepository.findAll().forEach(connaissances::add); //fun with Java 8
	        return connaissances;
	    }

	    public Connaissance getById(Long id) {
	        return connaissanceRepository.findById(id).orElse(null);
	    }



}
