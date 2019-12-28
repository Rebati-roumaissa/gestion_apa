package guru.springframework.controllers;

import guru.springframework.domain.Categorie;
import guru.springframework.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping(value = "/Categories")
public class CategorieController {
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @RequestMapping(value = "/Categories/all", method = RequestMethod.GET)
    //description = "Get all categories information from the database"
    public ModelAndView showCategories_Choice() {
        List<Categorie> categories = categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        return new ModelAndView("ged/Documents_list", params);
    }


    @RequestMapping(value = "/Categories/create", method = RequestMethod.POST)
//    description = "Create a categorie and save it to the database"
    public RedirectView create(Categorie categorie) {
        categorieRepository.save(categorie);
        return new RedirectView("/ged/index");
    }

    @RequestMapping(value = "/Categories/update", method = RequestMethod.POST)
//    description = "Create a categorie and save it to the database"
    public String update(Categorie categorie) {
        categorieRepository.save(categorie);
        return "succès";
    }

    @GetMapping("/Categories/modifier_categorie/{id}")
    public ModelAndView categorieupdate(@PathVariable String id_cat) {
        Categorie categorie = categorieRepository.findByidcat(id_cat);
        Map<String, Object> params = new HashMap<>();
        params.put("categorie", categorie);
        return new ModelAndView("ged/Categorie_update", params);
    }

    @RequestMapping(value = "/Categories/delete/{id}", method = RequestMethod.POST)
    //description = "Remove the categorie with the provided ID from the database"
    public List<Categorie> remove(@PathVariable String id_cat) {
        categorieRepository.deleteByidcat(id_cat);
        return categorieRepository.findAll();
    }

}