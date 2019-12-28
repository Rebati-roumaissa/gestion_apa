package guru.springframework.repositories;
import guru.springframework.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    /**
     * Method for finding files by filename
     *
     * @param nomobj
     * @return Optional containing Document if it exists
     */
    Optional<Document> findByNomobj(String nomobj);

    /**
     * !
     * Method for finding files by filename
     *
     * @param idobj
     * @return Optional containing Document if it exists
     */
    Document findByidobj(int idobj);
/*
    /**
     * Method for searching for files based on upload date
     *
     * @param datecreation
     * @return Collection of Uploads
     */
    //Collection<Document> findByDateUploadedAfter(Timestamp datecreation);

    /**
     * !
     * Method for finding files by categorie
     *
     * @param idcat
     * @return Optional containing Document if it exists
     */
    List<Document> findByidcat(String idcat);

    /**
     * Method to get all uploads in a sincle collection.
     *
     * @return Collection containing uploads
     */
    List<Document> findAll();

    void deleteByIdobj(int id);
}
