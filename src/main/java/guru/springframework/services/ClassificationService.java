package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.repositories.ClassificationRepository;

@Service

public class ClassificationService {
    @Autowired
    private ClassificationRepository classificationRespository;
   
    public List<Classification> findAll() {
        return (List<Classification>) classificationRespository.findAll();
    }

    public void save(Classification classification) {
        classificationRespository.save(classification);
    }

    public Optional<Classification> findById(long id){
        return classificationRespository.findById(id);
    }

    public void update(Classification classification) {
       
    }


    
    

}
