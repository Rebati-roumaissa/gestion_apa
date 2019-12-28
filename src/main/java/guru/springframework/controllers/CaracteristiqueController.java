package guru.springframework.controllers;
import guru.springframework.services.CaracteristiqueService;
import guru.springframework.services.LotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import guru.springframework.domain.Caracteristique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;


@Controller 

public class CaracteristiqueController {
    @Autowired
    private CaracteristiqueService caracteristiqueService;
    
    @Autowired
    private LotService lotService;

    @RequestMapping("/caracteristique/create")
    public String Create(
      @RequestParam (value="nom", defaultValue="World") String nom ,
      @RequestParam(value = "description", defaultValue = "World") String description,
      Model model
      ) {
      if(lotService.findById(1).isPresent()){
        caracteristiqueService.save(new Caracteristique(nom,description) )  ;
      }
   
      model.addAttribute("name",nom);
       return "BackOffice/Caracteristique/create";
     //  return "index";
    }
    @RequestMapping("/caracteristique/create/form")
    public String CreateForm(@RequestParam (value="name", defaultValue="World") String name,Model model) {
      model.addAttribute("name",name);
       return "BackOffice/Caracteristique/create";
     //  return "index";
    }

    @RequestMapping("/caracteristiques")
    public String Caracteristiques(@RequestParam (value="name", defaultValue="World") String name,Model model) {
       model.addAttribute("caracteristiques",caracteristiqueService.findAll());
       return "BackOffice/Caracteristique/list";
     //  return "index";
    }

    @RequestMapping("/caracteristique/update")
    public String Update(
      @RequestParam (value="id", defaultValue="") Long id ,
      @RequestParam (value="nom", defaultValue="") String nom ,
      @RequestParam( value = "detail", defaultValue = "") String detail,
     Model model) {
     // model.addAttribute("name",name);
     if(caracteristiqueService.findById(id).isPresent()){
        Caracteristique caracteristique=caracteristiqueService.findById(id).get();
        caracteristique.setDetail(detail);
        caracteristique.setNom(nom);
        caracteristiqueService.save(caracteristique);
      model.addAttribute("classification",caracteristiqueService.findById(id).get());
      return "BackOffice/Caracteristique/update";
     }
       return "BackOffice/Caracteristique/create";
    }



    @RequestMapping("/caracteristique/update/form/{id}")
    public String UpdateForm(@PathVariable("id") Integer id,Model model) {
     // model.addAttribute("name",name);
     if(caracteristiqueService.findById(id).isPresent()){
      model.addAttribute("classification",caracteristiqueService.findById(id).get());
      return "BackOffice/Caracteristique/update";
     }
       return "BackOffice/Caracteristique/create";
    }







}
