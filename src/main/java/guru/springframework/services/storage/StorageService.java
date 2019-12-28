package guru.springframework.services.storage;

import guru.springframework.domain.Directory;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Interface to abstract away implementation details of storage service.
 */
public interface StorageService {

    /**
     * Method to create root directory
     *
     * @throws IOException
     */
    void init() throws IOException;

    /**
     * Method to store file
     *
     * @param filename
     * @param file
     */
    void store(String filename, MultipartFile file);

    /**
     * Method to get all files
     *
     * @return Stream of Path objects
     * @throws IOException
     */
    Stream<Path> loadAll() throws IOException;

    /**
     * Method to load file
     *
     * @param filename
     * @return Path object for file
     */
    Path load(String filename);

    /**
     * Method to load a file into memory
     *
     * @param filename
     * @return Resource object containing file data
     */
    Resource loadAsResource(String filename);

    /**
     * Method to delete all files in storage root directory only
     */
    void deleteAll();

    /**
     * Method to create subDirectory
     *
     * @param nameDirectory
     */
    public void storeSubDirectory(String nameDirectory) throws IOException;

    public Path getRoot();

    public List<File> listFiles(String directoryName, List<File> flist);

    List<File> listDirectories(String parent, List<File> listdirectory);

    public String createDirectory(Directory directory);

}
