package guru.springframework.controllers;

import guru.springframework.domain.Categorie;
import guru.springframework.domain.Directory;
import guru.springframework.repositories.CategorieRepository;
import guru.springframework.repositories.DirectoryRepository;
import guru.springframework.services.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Directories")
public class DirectoryController {
    private final DirectoryRepository directoryRepository;
    private final CategorieRepository categorieRepository;
    private final StorageService service;

    @Autowired
    public DirectoryController(DirectoryRepository directoryRepository, CategorieRepository categorieRepository, StorageService service) {
        this.directoryRepository = directoryRepository;
        this.categorieRepository=categorieRepository;
        this.service = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add_directory() {
        List<Directory> directories = directoryRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        params.put("directories", directories);
        return new ModelAndView("ged/Directory_add", params);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RedirectView create(Directory directory) {
        Directory directory_parent = directoryRepository.findByIddirectory(directory.getIdparent());
        directory.setChemin(directory_parent.getChemin() + "\\" + directory.getTitre());
        service.createDirectory(directory);
        directoryRepository.save(directory);
        return new RedirectView("/ged/index");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public List<Directory> remove(@PathVariable String id_directory) {
        directoryRepository.deleteByiddirectory(Integer.parseInt(id_directory));
        return directoryRepository.findAll();
    }
}
