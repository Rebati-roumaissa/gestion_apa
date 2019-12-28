package guru.springframework.repositories;

import guru.springframework.domain.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * UploadRepository interface represents the entry point into the database.
 * All database related queries and operations will go through this interface.
 */
public interface UploadRepository extends CrudRepository<Document, Long> {

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
    Optional<Document> findByidobj(Long idobj);
/*
    /**
     * Method for searching for files based on upload date
     *
     * @param datecreation
     * @return Collection of Uploads
     */
    //Collection<Document> findByDateUploadedAfter(Timestamp datecreation);

    /**
     * Method to get all uploads in a sincle collection.
     *
     * @return Collection containing uploads
     */
    //  Collection<Document> findAll();
}
