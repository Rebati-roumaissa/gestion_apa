package guru.springframework.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.repositories.LotRepository;

@Service

public class LotService {
    @Autowired
    private LotRepository LotRespository;
   
    public List<Lot> findAll() {
        return LotRespository.findAll();
    }

    public Optional<Lot> findById(long id){
        return LotRespository.findById(id);
    }

    public void save(Lot Lot) {
        LotRespository.save(Lot);
    }


}