package guru.springframework.services;

import java.util.List;

import guru.springframework.domain.Commune;

public interface CommuneService {
	List<Commune> listAll();

	 Commune getById(Long id);

}
