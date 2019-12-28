package guru.springframework.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {


    private String path=System.getProperty("user.dir")+"/src/main/resources/static/images/";

    public void uploadFile(MultipartFile file) {



        try {
            String fileName = file.getOriginalFilename();

            Files.copy(file.getInputStream(), Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            //var msg = String.format("Failed to store file", file.getName());

            //throw new StorageException(msg, e);
        }

    }
}
