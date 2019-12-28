package guru.springframework.repositories;

import guru.springframework.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    /**
     * Method for finding files by filename
     *
     * @param categorie
     * @return Optional containing Document if it exists
     */
    Categorie findBylibcat(String categorie);

    /**
     * !
     * Method for finding files by filename
     *
     * @param id_cat
     * @return Optional containing Document if it exists
     */
    Categorie findByidcat(String id_cat);

    /**
     * Method to get all uploads in a sincle collection.
     *
     * @return Collection containing uploads
     */
    List<Categorie> findAll();

    void deleteByidcat(String id_cat);
}
