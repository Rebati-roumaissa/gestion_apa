package guru.springframework;

import guru.springframework.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class ViewController {
    private String appMode;
    private final CategorieRepository CategorieRepository;

    @Autowired
    public ViewController(Environment environment, CategorieRepository categorieRepository) {
        appMode = environment.getProperty("app-mode");
        CategorieRepository = categorieRepository;
    }

    @RequestMapping("/ged/index")
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Romi");
        model.addAttribute("mode", appMode);
        model.addAttribute("categories", CategorieRepository.findAll());
        return "ged/index";
    }
    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView( "login");
    }

    @GetMapping("/ajouter_categorie")
    public ModelAndView categorie() {
        return new ModelAndView("ged/Categorie_add");
    }

    @GetMapping("/ajouter_directory")
    public ModelAndView directory() {
        return new ModelAndView("ged/Directory_add");
    }

    @GetMapping("/permisAcces")
    public ModelAndView permis() {
        return new ModelAndView("ged/Permis_acces");
    }

    @GetMapping("/displayBarGraph")
    public ModelAndView barGraph(Model model) {
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        surveyMap.put("Java", 40);
        surveyMap.put("Dev oops", 25);
        surveyMap.put("Python", 20);
        surveyMap.put(".Net", 15);
        model.addAttribute("surveyMap", surveyMap);
        return new ModelAndView("ged/barGraph");
    }

    @GetMapping("/demandes/gestionnaire/addDossier")
    public ModelAndView dossier_preuves() {
        return new  ModelAndView("ged/Dossier_preuves");
    }
}