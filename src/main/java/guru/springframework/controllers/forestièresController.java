package guru.springframework.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import guru.springframework.services.ClassificationService;
import guru.springframework.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import guru.springframework.domain.Classification;
import guru.springframework.domain.Connaissance;
import guru.springframework.domain.Localisation;
import guru.springframework.domain.Permission;
import guru.springframework.domain.Ressource;
import guru.springframework.domain.RessourceCaracteristique;
import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.ClassificationRepository;
import guru.springframework.repositories.ConnaissanceRepository;
import guru.springframework.repositories.LocalisationRepository;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RessourceRepository;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;


@Controller

public class forestièresController {
	 
	@Autowired
	    private ClassificationService classificationService;
	 
		@Autowired
		    private LotService lotService;
		@Autowired
	    private ConnaissanceRepository connaissanceRepository;
		@Autowired
	    private LocalisationRepository localisationRepository;
		  @Autowired
	      private RessourceRepository ressourceRepo;

	      @Autowired
	      private ClassificationRepository classificationRepository;
	        	
		
	  @Autowired
	    private void setClassificationService(ClassificationService classificationService) {
		        this.classificationService = classificationService;
		    }
	  @Autowired
      private UtilisateurRepository userRepository;
      @Autowired
      private  RoleRepository  rolerepo;
	  @Autowired
	  PermissionRepo permissionRepo;
	  
	      
		@GetMapping("/Forestières/all")
        public String forestières(Model model,@RequestParam(defaultValue="0") int page,Principal principal) {
        
        	 List<Classification> listesClassifications = new ArrayList<Classification>();
        	 listesClassifications= (List<Classification>) classificationService.findAll();
        	 model.addAttribute("listesClassifications", listesClassifications);
             model.addAttribute("product", lotService.findById(Long.valueOf(2)));
        
        	 model.addAttribute("ListRessources", ressourceRepo.
        			 findAllByLots(lotService.findById(Long.valueOf(2)).get(),new PageRequest(page, 12,Sort.by("id").descending())));
        	
        	 List<Ressource> ListRessources=new ArrayList<Ressource>();
         	 ListRessources=(List<Ressource>) lotService.findById(Long.valueOf(2)).get().getRessources();
         	 int totPage=getTotalPage(ListRessources.size());
         	if(totPage>0) {model.addAttribute("totPage", totPage);}
        	 else { model.addAttribute("totPage", 1);}

        	 model.addAttribute("currentPage",page);
       /*--------------------------*/
           	 
          	 List<Role> listeRole = new ArrayList<>();
             List<Long> listes_lot_valider = new ArrayList<>();
             List<Long> listes_lot_ajouter = new ArrayList<>();

            List<Permission> permissions;
            List<Permission> permissions2=new ArrayList<Permission>();

            String permToFind = "valider"; // nom de la permission qui doit exister
            String permtoFind2="ajouter";// nom2 de la permission qui doit exister
            boolean permissionExists = false;
            boolean permissionExists2 = false;

            
            Long lotRole = null;

        	 if (principal != null) {
         		  String userName = principal.getName();
         		  Utilisateur user = userRepository.findByUsername(userName);
         		  listeRole=rolerepo.findAllByUtilisateurs(user);

       	      Long max_niv_sec=getmax_niv_sec(listeRole);
            	 model.addAttribute("max_niv_sec",max_niv_sec);

         		  }
        	 
        	 int i = 0;
             if(listeRole!=null) {
             while (i < listeRole.size() )
             {
            	 if(listeRole.get(i).getLot()==2)
            	 {
            		  permissions2.addAll(permissionRepo.findByRoles_id(listeRole.get(i).getId())); // Récupérer la liste des permissions d'un rôle
            	 }
                 lotRole = listeRole.get(i).getLot(); //récupérer le lot du role
                 permissions = permissionRepo.findByRoles_id(listeRole.get(i).getId()); // Récupérer la liste des permissions d'un rôle
               

                 // Comparer entre les permissions existantes du rôle et la permission "permToFind"
                 permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
                 permissionExists2 = permissions2.stream().anyMatch(permission -> permtoFind2.equalsIgnoreCase(permission.getNom()));
                 if(permissionExists) {listes_lot_valider.add(lotRole);}
                 if(permissionExists2) {listes_lot_ajouter.add(lotRole);}
                 i++;
             } 
            
             }
             
             
                
            
             if(listes_lot_valider.size()>0) {
            	
            	    model.addAttribute("valide",true);   
             }
             if(listes_lot_ajouter.size()>0) {
             	
         	    model.addAttribute("ajoute",true);   
          }
         	  
            /*----------------------*/
         
            return "ressources genetiques/forestieres.html";
        }
		
	
	    @GetMapping("/Forestières/detaille/{id}")

        public String detaille_Forestières(@PathVariable("id") long id,Model model,Principal principal) {

       	 List<Classification> listesClassifications = new ArrayList<Classification>();
       	 listesClassifications= (List<Classification>) classificationService.findAll();
       	 model.addAttribute("listesClassifications", listesClassifications);
            model.addAttribute("product", ressourceRepo.findById(id));
          	 List<Connaissance> listesConnaissances = new ArrayList<Connaissance>();
          	listesConnaissances=connaissanceRepository.findAllByRessources(ressourceRepo.findById(id).get());
            model.addAttribute("listesConnaissances", listesConnaissances);
            List<Localisation> listesLocalisations = new ArrayList<Localisation>();
            listesLocalisations=localisationRepository.findAllByRessources2(ressourceRepo.findById(id).get());
            model.addAttribute("listesLocalisations",listesLocalisations);
            List<RessourceCaracteristique> listes_ress_Caracteristiques = new ArrayList<RessourceCaracteristique>();
            listes_ress_Caracteristiques=ressourceRepo.findById(id).get().getRessourceCaracteristiques();
            model.addAttribute("listes_ress_Caracteristiques",listes_ress_Caracteristiques);

            /*-----*/
         	 List<Role> listeRole = new ArrayList<>();
            List<Long> listes_lot_valider = new ArrayList<>();
            List<Long> listes_lot_modifier = new ArrayList<>();
            List<Long> listes_lot_ajouter = new ArrayList<>();

           List<Permission> permissions;
           List<Permission> permissions2=new ArrayList<Permission>();
           List<Permission> permissions3=new ArrayList<Permission>();

           String permToFind = "valider"; // nom de la permission qui doit exister
           String permtoFind2="modifier";// nom2 de la permission qui doit exister
           String permtoFind3="ajouter";// nom2 de la permission qui doit exister

           boolean permissionExists = false;
           boolean permissionExists2 = false;
           boolean permissionExists3 = false;

           
           Long lotRole = null;

       	 if (principal != null) {
        		  String userName = principal.getName();
        		  Utilisateur user = userRepository.findByUsername(userName);
        		  listeRole=rolerepo.findAllByUtilisateurs(user);

      	      Long max_niv_sec=getmax_niv_sec(listeRole);
           	 model.addAttribute("max_niv_sec",max_niv_sec);

        		  }
       	 
       	 int i = 0;
            if(listeRole!=null) {
            while (i < listeRole.size() )
            {
           	 if(listeRole.get(i).getLot()==2)
           	 {
           		  permissions2.addAll(permissionRepo.findByRoles_id(listeRole.get(i).getId())); // Récupérer la liste des permissions d'un rôle
           		  permissions3.addAll(permissionRepo.findByRoles_id(listeRole.get(i).getId())); // Récupérer la liste des permissions d'un rôle

           	 }
                lotRole = listeRole.get(i).getLot(); //récupérer le lot du role
                permissions = permissionRepo.findByRoles_id(listeRole.get(i).getId()); // Récupérer la liste des permissions d'un rôle
              

                // Comparer entre les permissions existantes du rôle et la permission "permToFind"
                permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
                permissionExists2 = permissions2.stream().anyMatch(permission -> permtoFind2.equalsIgnoreCase(permission.getNom()));
                permissionExists3 = permissions3.stream().anyMatch(permission -> permtoFind3.equalsIgnoreCase(permission.getNom()));

                if(permissionExists) {listes_lot_valider.add(lotRole);}
                if(permissionExists2) {listes_lot_modifier.add(lotRole);}
                if(permissionExists3) {listes_lot_ajouter.add(lotRole);}

                i++;
            } 
           
            }
           
            if(listes_lot_valider.size()>0) {
           	
           	    model.addAttribute("valide",true);   
            }
            if(listes_lot_modifier.size()>0) {
            	
        	    model.addAttribute("modife",true);   
            }
            if(listes_lot_ajouter.size()>0) {
             	
         	    model.addAttribute("ajoute",true);   
             }
        	  
           /*-----*/
            return "ressources genetiques/detaill_ressource.html";
        }
	    
      
		
		@GetMapping("/Forestières/{nom}/{id}")
        public String getClassification_Forestières(Principal principal,@PathVariable("id") long id,@PathVariable("nom") String nom,Model model,@RequestParam(defaultValue="0") int page) {
        
        	 List<Classification> listesClassifications = new ArrayList<Classification>();
        	 listesClassifications= (List<Classification>) classificationService.findAll();
        	 model.addAttribute("listesClassifications", listesClassifications);
             model.addAttribute("product", classificationRepository.findById(id));
             
             model.addAttribute("ListRessources", ressourceRepo.findAllByClassifications(classificationService.findById(id).get(), new PageRequest(page, 12,Sort.by("id").descending())));
        	
        	 List<Ressource> ListRessources=new ArrayList<Ressource>();
         	 ListRessources= classificationService.findById(id).get().getRessources();
         	 int totPage=getTotalPage(ListRessources.size());
         	 if(totPage>0) {model.addAttribute("totPage", totPage);}
         	 else { model.addAttribute("totPage", 1);}

        	 model.addAttribute("currentPage",page);
        	 int x=(int) id;
        	 model.addAttribute("iddeclasse",x);
        	 model.addAttribute("nom_lot","Forestières");
       /*--------------------------*/
           	 
          	 List<Role> listeRole = new ArrayList<>();
             List<Long> listes_lot_valider = new ArrayList<>();
             List<Long> listes_lot_ajouter = new ArrayList<>();

            List<Permission> permissions;
            List<Permission> permissions2=new ArrayList<Permission>();

            String permToFind = "valider"; // nom de la permission qui doit exister
            String permtoFind2="ajouter";// nom2 de la permission qui doit exister
            boolean permissionExists = false;
            boolean permissionExists2 = false;

            
            Long lotRole = null;

        	 if (principal != null) {
         		  String userName = principal.getName();
         		  Utilisateur user = userRepository.findByUsername(userName);
         		  listeRole=rolerepo.findAllByUtilisateurs(user);

       	      Long max_niv_sec=getmax_niv_sec(listeRole);
            	 model.addAttribute("max_niv_sec",max_niv_sec);

         		  }
        	 
        	 int i = 0;
             if(listeRole!=null) {
             while (i < listeRole.size() )
             {
            	 if(listeRole.get(i).getLot()==2)
            	 {
            		  permissions2.addAll(permissionRepo.findByRoles_id(listeRole.get(i).getId())); // Récupérer la liste des permissions d'un rôle
            	 }
                 lotRole = listeRole.get(i).getLot(); //récupérer le lot du role
                 permissions = permissionRepo.findByRoles_id(listeRole.get(i).getId()); // Récupérer la liste des permissions d'un rôle
               

                 // Comparer entre les permissions existantes du rôle et la permission "permToFind"
                 permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
                 permissionExists2 = permissions2.stream().anyMatch(permission -> permtoFind2.equalsIgnoreCase(permission.getNom()));
                 if(permissionExists) {listes_lot_valider.add(lotRole);}
                 if(permissionExists2) {listes_lot_ajouter.add(lotRole);}
                 i++;
             } 
            
             }
             
             
                
            
             if(listes_lot_valider.size()>0) {
            	
            	    model.addAttribute("valide",true);   
             }
             if(listes_lot_ajouter.size()>0) {
             	
         	    model.addAttribute("ajoute",true);   
          }
         	  
            /*----------------------*/
         
            return "ressources genetiques/par_classification.html";
        }
		 
		
		 
		
private int getTotalPage(int var){
	int x,y;
	x=var / 12;
	y=var %12; 
	if(y==0) {
		return x;
	}else {
		return x+1;
	}
	
}

private List<Role> filtrerParRole(List<Role> listeRole) {
List<Role> liste = new ArrayList<Role>() ;

for ( Role r:listeRole) {
	
    if(r.getLot()==2) {

    		liste.add(r);
    		
    }
}
return liste;
	}

private Long getmax_niv_sec(List<Role> listeRole) {
	long max_niv_sec=0;
	 
	for ( Role role:listeRole) {
	    if(role.getLot()==2) {
	    	 if(max_niv_sec<role.getNiv_sec()) {
	    		 max_niv_sec=role.getNiv_sec(); 
	     }
	    }
	}
return max_niv_sec;
}
}
