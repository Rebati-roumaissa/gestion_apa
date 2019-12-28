package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.repositories.ClassificationRepository;

@Repository
public class ClassificationServiceImp extends ClassificationService {
	
	@Autowired
	 private ClassificationRepository classificationRepository;


	    public List<Classification> listAll() {
	        List<Classification> classifications = new ArrayList<>();
	         classifications = (List<Classification>) classificationRepository.findAll();
	      //  classificationRepository.findAll().forEach(classifications::add); //fun with Java 8
	        return classifications;
	    }
	    
	    public List<Classification> lis_classification_idlot(Lot lot) {
	        List<Classification> classifications = new ArrayList<>();
	         classifications = (List<Classification>) classificationRepository.findByLot(lot);
	        return classifications;
	    }

	    public Classification getById(Long id) {
	        return classificationRepository.findById(id).orElse(null);

		}
	   

	




}
