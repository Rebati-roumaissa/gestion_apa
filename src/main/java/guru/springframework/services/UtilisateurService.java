package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.UtilisateurRepository;

@Service

public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRespository;
       
       
        
    
    public List<Utilisateur> findAll() {
        return utilisateurRespository.findAll();
    }

    public void save(Utilisateur utilisateur) {
        utilisateurRespository.save(utilisateur);
    }

    public Optional<Utilisateur> findById(long id){
        return utilisateurRespository.findById(id);
    }
    

    

}