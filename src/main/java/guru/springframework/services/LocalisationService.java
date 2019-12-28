package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Localisation;
import guru.springframework.repositories.LocalisationRepository;

@Service

public class LocalisationService {
    @Autowired
    private LocalisationRepository localisationRespository;
   
    public List<Localisation> findAll() {
        return localisationRespository.findAll();
    }

    public void save(Localisation caracteristique) {
        localisationRespository.save(caracteristique);
    }

    public Optional<Localisation> findById(long id){
        return localisationRespository.findById(id);
    }


    
}
