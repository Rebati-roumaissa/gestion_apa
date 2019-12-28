package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Connaissance;
import guru.springframework.repositories.ConnaissanceRepository;

@Service

public class ConnaissanceService {
    @Autowired
    private ConnaissanceRepository connaissanceRespository;
   
    public List<Connaissance> findAll() {
        return connaissanceRespository.findAll();
    }

    public void save(Connaissance caracteristique) {
        connaissanceRespository.save(caracteristique);
    }

    public Optional<Connaissance> findById(long id){
        return connaissanceRespository.findById(id);
    }


    
}