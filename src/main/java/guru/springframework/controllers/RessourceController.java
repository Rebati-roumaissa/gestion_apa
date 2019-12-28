package guru.springframework.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Connaissance;
import guru.springframework.domain.EtatValidation;
import guru.springframework.domain.Image;
import guru.springframework.domain.Localisation;
import guru.springframework.domain.Lot;
import guru.springframework.domain.Ressource;
import guru.springframework.domain.RessourceUtilisateur;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.UtilisateurRepository;
import guru.springframework.services.ClassificationService;
import guru.springframework.services.ConnaissanceService;
import guru.springframework.services.LocalisationService;
import guru.springframework.services.RessourceService;
import guru.springframework.services.StorageService;
import guru.springframework.services.UtilisateurService;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller 
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;
    @Autowired
    private ConnaissanceService connaissanceService;
    @Autowired 
    private LocalisationService localisationService;  
    @Autowired
    private  ClassificationService classificationService;
    @Autowired
    private  UtilisateurService utilisateurService;
    @Autowired
    private StorageService storageService;

    @Autowired
    private UtilisateurRepository userRepository;
    
  
    @RequestMapping(value ="ressource/create" ,consumes = {"multipart/form-data"})
    public String Create(
        @RequestParam (value="niv_sec_nom_commun", defaultValue="1") int niv_sec_nom_commun ,
        @RequestParam (value="nom_commun", defaultValue="") String nom_commun ,
        @RequestParam(value = "niv_sec_nom_scientifique", defaultValue = "1") int niv_sec_nom_scientifique,
        @RequestParam(value = "nom_scientifique",defaultValue = "") String nom_scientifique,
        @RequestParam (value="niv_sec_population",defaultValue="1") int niv_sec_population ,
        @RequestParam (value="population",defaultValue="0") int population ,
        @RequestParam(value = "couleur",defaultValue = "") String couleur,
        @RequestParam(value = "niv_sec_couleur", defaultValue = "1") int niv_sec_couleur,
        @RequestParam(value = "niv_sec_utilisation", defaultValue = "1") int niv_sec_utilisation,
        @RequestParam(value = "utilisation", defaultValue = "") String utilisation,
        @RequestParam(value = "niv_sec_classifications", defaultValue = "1") int niv_sec_classifications,
        @RequestParam(value = "classifications",defaultValue = "1") Long classifications,
        @RequestParam(value = "niv_sec_taille",defaultValue = "1") int niv_sec_taille,
        @RequestParam(value = "taille",defaultValue = "0") Double taille,   
        @RequestParam(value = "niv_sec_voie_disparition",defaultValue = "1") int niv_sec_voie_disparition,
        @RequestParam(value = "voie_disparition", defaultValue = "0") Boolean voie_disparition,   
        @RequestParam(value = "procedure", defaultValue = "") String procedure,  
        @RequestParam(value = "brevet", defaultValue = "") String brevet,  
        @RequestParam(value = "ingrediants", defaultValue = "") String ingrediants,  
        @RequestParam(value = "region", defaultValue = "") String region,  
        @RequestParam(value = "niv_securite", defaultValue = "1") String niv_securite,
        @RequestParam(value = "longitude", defaultValue = "") String longitude,  
        @RequestParam(value = "latitude", defaultValue = "") String latitude,  
        @RequestParam(value = "adressgmap", defaultValue = "") String adressgmap, 
        @RequestParam(value = "classification", defaultValue = "") Long classification,   
        @RequestParam(value="image") MultipartFile file,
        Model model,
        Principal principal
    ) {
        
        Connaissance connaissance=new Connaissance( procedure, brevet, ingrediants, region,niv_securite);
        connaissanceService.save(connaissance);
        List<Connaissance> connaissances=new ArrayList<>();
        connaissances.add(connaissance);
        List<Localisation>  localisations= new ArrayList<>();
        List< Classification>   listclassifications= new ArrayList<>();
         List<RessourceUtilisateur> ressourceUtilisateurs=new ArrayList<>();
        Localisation localisation=new Localisation(longitude,latitude,adressgmap);
        localisationService.save(localisation);
        localisations.add(localisation);
        if(classificationService.findById(classification).isPresent()){
            Classification classifica=classificationService.findById(classification).get();
            listclassifications.add(classifica);
        }
        
        Ressource ressource=new Ressource(nom_commun,nom_scientifique,voie_disparition,population,couleur,taille, utilisation,connaissances,localisations,listclassifications,niv_sec_nom_commun,niv_sec_nom_scientifique,niv_sec_voie_disparition,niv_sec_population,
        niv_sec_couleur,niv_sec_taille,niv_sec_utilisation,niv_sec_classifications);
        ressource.setParent_id(Long.valueOf(0));
        ressource.setEtat(EtatValidation.enAttente);
       
        
        storageService.uploadFile(file);
    


       // imagesRepository.save(img);
        ressource.setImage(file.getOriginalFilename());
        Utilisateur user=new Utilisateur();
        if (principal != null) {
   		  String userName = principal.getName();
   		  user = userRepository.findByUsername(userName);
   		 

   		  }
        RessourceUtilisateur ressourceUtilisateur=new RessourceUtilisateur(ressource,user,new Date());
        ressourceUtilisateurs.add(ressourceUtilisateur);
        ressource.setRessourceUtilisateur(ressourceUtilisateurs);
        List<Lot> lots=new ArrayList<Lot>();
        lots.add(ressource.getClassifications().get(0).getLot());
        ressource.setLots(lots);
        System.out.println("ressource"+ressource.getId());
        ressourceService.save(ressource);
    
        model.addAttribute("users", ressourceService.findAll());
        String redirectUrl = "/etat_ressources";
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/ressource/create/form")
    public String CreateForm(Model model) {
       model.addAttribute("ressource",new Ressource());
       
       model.addAttribute("classifications",classificationService.findAll());
 
       return "BackOffice/Ressource/ajouterressource";
    }

    @RequestMapping("ressource/update/form/{id}")
    public String UpdateForm(@PathVariable("id") Integer id,Model model) {
        if(ressourceService.findById(id).isPresent()){
            model.addAttribute("ressource",ressourceService.findById(id).get());
            model.addAttribute("classifications",classificationService.findAll());
            return "BackOffice/Ressource/modifierressource";
        }

        return "khroto";
    
    }


    @RequestMapping(value="ressource/update")
    public String Update(
        @RequestParam (value="niv_sec_nom_commun", defaultValue="1") int niv_sec_nom_commun ,
        @RequestParam (value="nom_commun", defaultValue="") String nom_commun ,
        @RequestParam(value = "niv_sec_nom_scientifique", defaultValue = "1") int niv_sec_nom_scientifique,
        @RequestParam(value = "nom_scientifique", defaultValue = "") String nom_scientifique,
        @RequestParam (value="niv_sec_population", defaultValue="1") int niv_sec_population ,
        @RequestParam (value="population", defaultValue="0") int population ,
        @RequestParam(value = "couleur", defaultValue = "") String couleur,
        @RequestParam(value = "niv_sec_couleur", defaultValue = "1") int niv_sec_couleur,
        @RequestParam(value = "niv_sec_utilisation", defaultValue = "1") int niv_sec_utilisation,
        @RequestParam(value = "utilisation", defaultValue = "") String utilisation,
        @RequestParam(value = "niv_sec_classifications", defaultValue = "1") int niv_sec_classifications,
        @RequestParam(value = "classifications", defaultValue = "1") Long classifications,
        @RequestParam(value = "niv_sec_taille", defaultValue = "1") int niv_sec_taille,
        @RequestParam(value = "taille", defaultValue = "0") Double taille,   
        @RequestParam(value = "niv_sec_voie_disparition", defaultValue = "1") int niv_sec_voie_disparition,
        @RequestParam(value = "voie_disparition", defaultValue = "0") Boolean voie_disparition,   
        @RequestParam(value = "procedure", defaultValue = "") String procedure,  
        @RequestParam(value = "brevet", defaultValue = "") String brevet,  
        @RequestParam(value = "ingrediants", defaultValue = "") String ingrediants,  
        @RequestParam(value = "region", defaultValue = "") String region,  
        @RequestParam(value = "niv_securite", defaultValue = "1") String niv_securite,
        @RequestParam(value = "longitude", defaultValue = "0") String longitude,  
        @RequestParam(value = "altitude", defaultValue = "0") String latitude,  
        @RequestParam(value = "adressgmap", defaultValue = "") String adressgmap, 
        @RequestParam(value = "classification", defaultValue = "") Long classification,  
        @RequestParam(value = "id", defaultValue = "") Long parent_id, 
        Model model,
        Principal principal
    ) {
    	Utilisateur user=new Utilisateur();
       
                if (principal != null) {
                   String userName = principal.getName();
                   user = userRepository.findByUsername(userName);
    
                   }
        
        Connaissance connaissance=new Connaissance( procedure, brevet, ingrediants, region,niv_securite);
//        System.out.print(connaissance.getBrevet());
        connaissanceService.save(connaissance);
        List<Connaissance> connaissances=new ArrayList<>();
        connaissances.add(connaissance);
        List<Localisation>  localisations= new ArrayList<>();
        List<RessourceUtilisateur> ressourceUtilisateurs=new ArrayList<>();
        List< Classification>   listclassifications= new ArrayList<>();
        Localisation localisation=new Localisation(longitude,latitude,adressgmap);
        localisationService.save(localisation);
        localisations.add(localisation);
        if(classificationService.findById(classification).isPresent()){
            Classification classifica=classificationService.findById(classification).get();
            listclassifications.add(classifica);
        }
 
    
        Ressource ressource=new Ressource(nom_commun,nom_scientifique,voie_disparition,population,couleur,taille, utilisation,connaissances,localisations,listclassifications,niv_sec_nom_commun,niv_sec_nom_scientifique,niv_sec_voie_disparition,niv_sec_population,
        niv_sec_couleur,niv_sec_taille,niv_sec_utilisation,niv_sec_classifications);
      //  if (principal != null) {
           // String userName = principal.getName();
           // Utilisateur user = userRepository.findByUsername(userName);
                

            

       // }

        RessourceUtilisateur ressourceUtilisateur=new RessourceUtilisateur(ressource,user,new Date());
        ressourceUtilisateur.setDate_modif(new Date());
        ressourceUtilisateur.setModifiateur(user.getId());
        ressourceUtilisateurs.add(ressourceUtilisateur);
        ressource.setRessourceUtilisateur(ressourceUtilisateurs);

   
        ressource.setParent_id(parent_id);
        ressource.setEtat(EtatValidation.enAttente);
        ressourceService.save(ressource);
        model.addAttribute("users", ressourceService.findAll());
        String redirectUrl = "/etat_ressources";
        return "redirect:" + redirectUrl;
    }

   
    @RequestMapping("ressource/validate/form/{id}")
    public String ValidateForm(@PathVariable("id") Integer id,Model model) {
        if(ressourceService.findById(id).isPresent()){
            model.addAttribute("ressource",ressourceService.findById(id).get());
            model.addAttribute("classifications",classificationService.findAll());
            return "BackOffice/Ressource/validerressource";
        }
        return "khroto";
    }

    @RequestMapping("ressource/validate")
    public String Validate(      
        @RequestParam( value = "id" ) Long id, 
        Model model,
        Principal principal
    ) {
    	 Utilisateur user =new Utilisateur();
            if (principal != null) {
     		   String userName = principal.getName();
     		    user = userRepository.findByUsername(userName);
     		  }
        
        List< Classification>   listclassifications= new ArrayList<>();
        List<Connaissance> listconnaissances=new ArrayList<>();
        List<Localisation>  listlocalisations= new ArrayList<>();
        List<RessourceUtilisateur> ressourceUtilisateurs=new ArrayList<>();
        if(ressourceService.findById(id).isPresent()){
            Ressource ressource=ressourceService.findById(id).get();
            if(ressource.getParent_id() !=0){
            ressourceUtilisateurs=ressource.getRessourceUtilisateur();
             
            if(!ressource.getRessourceUtilisateur().isEmpty()){
                ressource.getRessourceUtilisateur().get(0).setValidateur(user.getId());    
                ressource.getRessourceUtilisateur().get(0).setDate_valid(new Date());
            }
            
            Ressource parent_ressource=ressourceService.findById(ressource.getParent_id()).get();
              // ressource.setParent_id(parent_ressource.getParent_id());
              if(!parent_ressource.getClassifications().isEmpty()){
                if(classificationService.findById(parent_ressource.getClassifications().get(0).getId()).isPresent()){
                    Classification classifica=classificationService.findById(parent_ressource.getClassifications().get(0).getId()).get();
                    listclassifications.add(classifica);
                }
              }
              if(!parent_ressource.getConnaissances().isEmpty()){
                if(connaissanceService.findById(parent_ressource.getConnaissances().get(0).getId()).isPresent()){
                    Connaissance classifica=connaissanceService.findById(parent_ressource.getConnaissances().get(0).getId()).get();
                    listconnaissances.add(classifica);
                }
              }
              if(!parent_ressource.getLocalisations().isEmpty()){
                if(localisationService.findById(parent_ressource.getLocalisations().get(0).getId()).isPresent()){
                    Localisation classifica=localisationService.findById(parent_ressource.getLocalisations().get(0).getId()).get();
                    listlocalisations.add(classifica);
                }
              }
              
              parent_ressource.alter( ressource.getNom_commun(), ressource.getNom_scientifique(), ressource.getVoie_disparition(), ressource.getPopulation(), ressource.getCouleur(), ressource.getTaille(), ressource.getUtilisation(), listconnaissances,listlocalisations, listclassifications, ressource.getNiv_sec_nom_commun(), ressource.getNiv_sec_nom_scientifique(), ressource.getNiv_sec_voie_disparition(), ressource.getNiv_sec_population(), ressource.getNiv_sec_couleur(), ressource.getNiv_sec_taille(), ressource.getNiv_sec_utilisation(), ressource.getNiv_sec_classifications());
             
              parent_ressource.setVisible(true);
              ressource.setEtat(EtatValidation.validee);
              ressourceService.save(ressource);
              ressourceService.save(parent_ressource);
            
            } else {
                ressource.setEtat(EtatValidation.validee);
                ressource.setVisible(true);
                ressourceService.save(ressource);

            }
        }

        model.addAttribute("users", ressourceService.findAll());
        String redirectUrl = "/etat_ressources";
        return "redirect:" + redirectUrl;
      
    }

    @RequestMapping("ressource/nonvalide/{id}")
    public String NoValidate(      
        @PathVariable("id") Integer id, 
        Model model,
        Principal principal
    ) {
       
        if(ressourceService.findById(id).isPresent()){
            Ressource ressource=ressourceService.findById(id).get();
            ressource.setEtat(EtatValidation.nonValidee);
            ressourceService.save(ressource);
        }

        String redirectUrl = "/ressource/update/form/"+id;
        return "redirect:" + redirectUrl;
      

    }



        
}