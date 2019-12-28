package guru.springframework.controllers;
import guru.springframework.services.ClassificationService;
import guru.springframework.services.LotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import guru.springframework.domain.Classification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@Controller 

public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;
    
    @Autowired
    private LotService lotService;

    @RequestMapping("/classification/create")
    public String Create(
      @RequestParam (value="lot", defaultValue="0") Long lot ,
      @RequestParam (value="nom", defaultValue="World") String nom ,
      @RequestParam(value = "description", defaultValue = "World") String description,
      Model model
      ) {
      if(lotService.findById(lot).isPresent()){
        classificationService.save(new Classification(nom,description,lotService.findById(lot).get()) )  ;
      }
   
      model.addAttribute("name",nom);
       return "BackOffice/Classification/create";
     //  return "index";
    }
    @RequestMapping("/classification/create/form")
    public String CreateForm(@RequestParam (value="name", defaultValue="World") String name,Model model) {
      model.addAttribute("lots",lotService.findAll());
       return "BackOffice/Classification/create";
     //  return "index";
    }

    @RequestMapping("/classification/update/form/{id}")
    public String UpdateForm(@PathVariable("id") Integer id,Model model) {
     // model.addAttribute("name",name);
     if(classificationService.findById(id).isPresent()){
      model.addAttribute("classification",classificationService.findById(id).get());
      return "BackOffice/Classification/update";
     }
       return "BackOffice/Classification/create";
    }

    @RequestMapping("/classification/update")
    public String Update(
      @RequestParam (value="id", defaultValue="") Long id ,
      @RequestParam (value="nom", defaultValue="") String nom ,
      @RequestParam( value = "description", defaultValue = "") String description,
     Model model) {
     // model.addAttribute("name",name);
     if(classificationService.findById(id).isPresent()){
       Classification classification=classificationService.findById(id).get();
       classification.setDescription(description);
       classification.setNom(nom);
       classificationService.save(classification);
      model.addAttribute("classification",classificationService.findById(id).get());
      return "BackOffice/Classification/update";
     }
       return "BackOffice/Classification/create";
    }


    @RequestMapping("/classifications")
    public String classifications(@RequestParam (value="name", defaultValue="World") String name,Model model) {
       model.addAttribute("classifications",classificationService.findAll());
       return "BackOffice/Classification/list";
     //  return "index";
    }
    @RequestMapping("/khroto")
    public String khroto(Model model){
      
      model.addAttribute("users", classificationService.findAll());
         return "display";
    }


}
