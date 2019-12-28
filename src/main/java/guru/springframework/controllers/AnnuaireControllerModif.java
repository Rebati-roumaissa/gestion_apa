package guru.springframework.controllers;

import java.util.List;
import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.domain.Utilisateur;
import guru.springframework.domain.cat_inst;
import guru.springframework.domain.cat_rh;
import guru.springframework.domain.detail_inst;
import guru.springframework.domain.detail_rh;
import guru.springframework.repositories.*;


@RestController
public class AnnuaireControllerModif {

	@Autowired
	cat_rh_repository cat_rh_repository;
	@Autowired
	cat_inst_repository cat_inst_repository;
	
	@Autowired
	detail_inst_repository detail_inst_repository;
	@Autowired
	detail_rh_repository detail_rh_repository;
	
	int risque=0;
	
	public ModelAndView modifView()
	{
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
		  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
		  modelAndView.setViewName("pages_annuaire/modif");
		  return modelAndView;
	}
	
	  
	  @GetMapping("/modif")   //afficher la page de modif
	  public ModelAndView Modif(){
	  ModelAndView modelAndView = modifView();
	  return modelAndView;
	  }
	  
	  @GetMapping("modif/inst/{id}")    //afficher detail institution
	   public List<detail_inst> getRessource1(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   return detail_inst_repository.findByCat(D);
	   }
	   
	   @GetMapping("modif/rh/{id}")  //afficher detail  rh
	   public List<detail_rh> getRessource2(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   return detail_rh_repository.findByCat(D);
	   }
	   
	   @GetMapping("delete_inst/{id}")     //supp  categorie institution
	   public void  deleteRessource1(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   cat_inst_repository.deleteById(D);
	   }
	   
	   @GetMapping("delete_rh/{id}")     //supp  categorie rh
	   public void  deleteRessource2(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   cat_rh_repository.deleteById(D);
	   }
	   
	   
	   
	   @GetMapping("/cat_inst_form")   //ajouter  categorie institution
		public ModelAndView Modif3(){
		  ModelAndView modelAndView = new ModelAndView();
		  cat_inst cat=new cat_inst();
		  modelAndView.addObject("cat",cat);
		  modelAndView.setViewName("pages_annuaire/add_inst_form");
		  return modelAndView;
		}
	   
	   
	   @PostMapping("/modif/inst")  //ajouter categorie institutions
	 		public ModelAndView Modif2(@Valid cat_inst cat, BindingResult result){
		 
	 		   cat_inst_repository.save(cat);
	 		  cat_inst_repository.flush();
	 		   risque=cat.getId_cat_inst();
	 		   ModelAndView modelAndView = modifView();
	 			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
	 			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
	 			  modelAndView.setViewName("pages_annuaire/modif");
	 			  return modelAndView;
	 		   
	 		   
	 		   
	 			}
	 	   
	   
	   @GetMapping("/cat_rh_form")     //ajouter  categorie rh
		public ModelAndView Modif4(){
		  ModelAndView modelAndView = new ModelAndView();
		  cat_rh cat=new cat_rh();
		 modelAndView.addObject("cat",cat);
		  modelAndView.setViewName("pages_annuaire/add_rh_form");
		  return modelAndView;
		}
	   
	   
	  
	 
	   @PostMapping("/modif/rh")  //ajouter categorie rh
		public ModelAndView Modif5(@Valid cat_rh cat, BindingResult result){
		   cat_rh_repository.save(cat);
		   ModelAndView modelAndView = modifView();
			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
			  modelAndView.setViewName("pages_annuaire/modif");
			  return modelAndView;
		   
			}
	  
	   
	   ;
	   
	   @GetMapping("modif/{id}/cat_inst")     //update categorie institution
	   public ModelAndView modifCatInst(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   ModelAndView modelAndView = new ModelAndView();
		   
		   modelAndView.addObject("cat",cat_inst_repository.getOne(D));
		   modelAndView.setViewName("pages_annuaire/update_inst_form");
		   return modelAndView;
	   }
	   
		@PostMapping("/cat_inst/update/{id}")   //update categorie institution
		public ModelAndView UpdateCatInst(@Valid cat_inst cat, BindingResult result) {
			ModelAndView modelAndView = new ModelAndView();
			if (result.hasErrors()) {
				return modelAndView;
			}
			  cat_inst_repository.save(cat);
			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
			  modelAndView.setViewName("pages_annuaire/modif");
			  return modelAndView;
		}
	   
	  
		@GetMapping("modif/{id}/cat_rh")  //update categorie rh
	   public ModelAndView modifCatRH(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   ModelAndView modelAndView = new ModelAndView();
		   modelAndView.addObject("cat",cat_rh_repository.getOne(D));
		   modelAndView.setViewName("pages_annuaire/update_rh_form");
		   return modelAndView;
	   }
	   
	   
	   
	     
	   @PostMapping("/cat_rh/update/{id}")   //update categorie rh
	   public ModelAndView UpdateCatRH(@Valid cat_rh cat, BindingResult result) {
			ModelAndView modelAndView = new ModelAndView();
			if (result.hasErrors()) {
				return modelAndView;
			}
			  cat_rh_repository.save(cat);
			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
			  modelAndView.setViewName("pages_annuaire/modif");
			  return modelAndView;
		}
	   
	   
	   
	   
	   @GetMapping("/add_inst_get")   //ajouter  institution
		public ModelAndView AjoutInst(){
		  ModelAndView modelAndView = new ModelAndView();
		  detail_inst inst=new detail_inst();
		  modelAndView.addObject("inst",inst);
		  modelAndView.setViewName("pages_annuaire/add_inst");
		  return modelAndView;
		}
	   
	   
	   @PostMapping("/add_inst_post")  //ajouter  institutions
	 		public ModelAndView AjoutInstPost(@Valid detail_inst inst, BindingResult result){
		     inst.setCat(risque);
		     inst.setUrl("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d102375.20613804815!2d2.958657969009851!3d36.693126170821614!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x128fad6931ebb18b%3A0xb6278c20161ce32d!2sR%C3%A9sidence%20AFAK%20OUED%20EL%20KERMA!5e0!3m2!1sfr!2sdz!4v1569440520053!5m2!1sfr!2sdz");
		    
	 		   detail_inst_repository.save(inst);
	 		   ModelAndView modelAndView = modifView();
	 			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
	 			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
	 			  modelAndView.setViewName("pages_annuaire/modif");
	 			  return modelAndView;
	 		   
	 		   
	 		   
	 			}
	   
	   
	   
	   @GetMapping("modif/{id}/inst")     //update institution
	   public ModelAndView updateInstGet(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   ModelAndView modelAndView = new ModelAndView();
		   
		   modelAndView.addObject("inst",detail_inst_repository.getOne(D));
		   modelAndView.setViewName("pages_annuaire/update_inst");
		   return modelAndView;
	   }
	   
		@PostMapping("/inst/update/{id}")   //update  institution
		public ModelAndView updateInstPost(@Valid detail_inst inst, BindingResult result) {
			ModelAndView modelAndView = new ModelAndView();
			if (result.hasErrors()) {
				return modelAndView;
			}
			  detail_inst_repository.save(inst);
			  modelAndView.addObject("cat_inst",cat_inst_repository.findAll());
			  modelAndView.addObject("cat_rh",cat_rh_repository.findAll());
			  modelAndView.setViewName("pages_annuaire/modif");
			  return modelAndView;
		}
	   
	   
	   
	   
	   @GetMapping("delete_detail/inst/{id}")   //supp institution
	   public void  deleteDetailInst(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   cat_rh_repository.deleteById(D);
	   }
}

