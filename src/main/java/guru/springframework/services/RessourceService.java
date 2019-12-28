package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.domain.Ressource;
import guru.springframework.repositories.RessourceRepository;
@Service

public class RessourceService {
    @Autowired
    private RessourceRepository ressourceRespository;
       
       
        
    
    public List<Ressource> findAll() {
        return ressourceRespository.findAll();
    }

    public void save(Ressource ressource) {
        ressourceRespository.save(ressource);
    }

    public Optional<Ressource> findById(long id){
        return ressourceRespository.findById(id);
    }
    

    

}
