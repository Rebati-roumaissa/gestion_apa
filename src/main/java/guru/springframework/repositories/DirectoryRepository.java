package guru.springframework.repositories;

import guru.springframework.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    @Override
    List<Directory> findAll();

    Directory findByTitre(String titre);

    Directory findByIddirectory(int id);

    void deleteByiddirectory(int id_directory);
}
