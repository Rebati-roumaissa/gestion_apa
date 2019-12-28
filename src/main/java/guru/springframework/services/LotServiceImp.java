package guru.springframework.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;
import guru.springframework.repositories.LotRepository;


@Repository
public class LotServiceImp extends LotService{
	@Autowired
	 private LotRepository lotRepository ;


	    public List<Lot> listAll() {
	        List<Lot> lots = new ArrayList<>();
	         lots = (List<Lot>) lotRepository.findAll();
	        return lots;
	    }
	
	public Lot getById(Long id) {
        return lotRepository.findById(id).orElse(null);

	}
	 

}
