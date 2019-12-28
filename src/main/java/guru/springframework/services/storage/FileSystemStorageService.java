package guru.springframework.services.storage;

import guru.springframework.domain.Directory;
import guru.springframework.repositories.DirectoryRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path root;
    private final Log log;
    private final DirectoryRepository directoryRepository;

    //private Path subdirectory;

    /**
     * Constructor to get the service started
     */
    @Autowired
    public FileSystemStorageService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
        root = Paths.get("");
        //subdirectory= Paths.get(root.toString()+"\\"+"first");
        log = LogFactory.getLog(getClass());
        log.info("All files to be stored in: " + root.toString());
        File dir = new File("uploads");
        if (!dir.exists()) {
            dir.mkdirs();

        }
    }

    public Path getRoot() {
        return root;
    }

    @Override
    public void init() throws IOException {
        log.info("init");
        Files.createDirectory(root);


    }

    @Override
    public void storeSubDirectory(String nameDirectory) throws IOException {
        log.info("init new directory" + nameDirectory);
        // subdirectory = Paths.get(root.toString()+"\\"+nameDirectory);
        //    Files.createDirectory(subdirectory);
    }

    @Override
    public void store(String parent, MultipartFile file) {

        if (file.isEmpty()) throw new StorageServiceException("Failed to store empty file: " + file.getName());
        if (Files.exists(Paths.get(parent + File.separator + file.getOriginalFilename())))
            throw new StorageServiceException("File Already Exists");
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            byte[] bytes = file.getBytes();
            File uploadFile = new File(dir.getAbsolutePath()
                    + File.separator + file.getOriginalFilename());
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(uploadFile));
            outputStream.write(bytes);
            outputStream.close();
            //  Files.copy(file.getInputStream(), root.resolve(file.getName()/*filename*/));
        } catch (IOException e) {
            throw new StorageServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String createDirectory(Directory directory) {
        String status = "";
        File dir = new File(directory.getChemin());
        log.info(dir.exists() + " " + dir.getName());
        if (!dir.exists()) {
            log.info("not existed");
            dir.mkdirs();
        }
        log.info(dir.getPath());
        return dir.getPath();
    }

    @Override
    public Stream<Path> loadAll() throws IOException {
        log.info("loadAll");
        return Files.walk(root).filter(p -> !p.equals(root)).map(p -> root.relativize(p));
    }

    @Override
    public Path load(String filename) {
        log.info("load");
        return root.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        log.info("loadAsResource");
        Path path = root.resolve(filename);
        // log.info(path.toUri());
        Resource resource = null;
        try {
            log.info(path.toUri());
            resource = new UrlResource(path.toUri());
            // log.info(resource.getFilename());
        } catch (MalformedURLException e) {
            throw new StorageServiceException("Could not read file " + filename, e);
        }
        if (resource.exists() || resource.isReadable())
            return resource;
        else
            throw new StorageServiceException("Could not read file " + filename +
                    " exists: " + resource.exists() + " isreadable: " + resource.isReadable());
    }

    @Override
    public void deleteAll() {
        log.info("deleteAll");
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public List<File> listFiles(String directoryName, List<File> flist) {
        log.info(directoryName);
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File file : files) {
            // log.info(file);
            if (file.isFile()) flist.add(file);
            else listFiles(file.getAbsolutePath(), flist);
        }
        return flist;
    }

    public List<File> listDirectories(String parent, List<File> listdirectory) {
        File directory = new File(parent);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listdirectory.add(file);
                listDirectories(file.getAbsolutePath(), listdirectory);
            }
        }
        return listdirectory;
    }
}
