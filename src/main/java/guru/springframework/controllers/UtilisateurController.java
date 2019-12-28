package guru.springframework.controllers;


import guru.springframework.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.domain.Classification;
import guru.springframework.domain.EtatValidation;
import guru.springframework.domain.Permission;
import guru.springframework.domain.Ressource;
import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RessourceRepository;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UtilisateurController {

    @Autowired
    RoleRepository roleRepo;
    @Autowired
    PermissionRepo permissionRepo;
    @Autowired
    RessourceRepository ressourceRepo;
    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private  RoleRepository  rolerepo;
    
    @Autowired
    private ClassificationService classificationService;
 
    ModelAndView mv;
    List<Ressource> RessourcesEnAttente=new ArrayList<Ressource>();

    List<Ressource> RessourcesValidees=new ArrayList<Ressource>();
    List<Ressource> RessourcesNonValidees=new ArrayList<Ressource>();
    int nbrEnAttente;
    int nbrValidees;
    int nbrNonValidees;


    Utilisateur user = new Utilisateur(); // TODO: Remplacer par l'instance de l'utilisateur

    @RequestMapping("/etat_ressources")
    public String verifierPermission(Model model,Principal principal) { //Vérifier si il a la permission de valider une ressource
        List<Role> roles = new ArrayList<>();
        List<Long> listes_lot = new ArrayList<>();

        List<Permission> permissions;
        String permToFind = "valider"; // nom de la permission qui doit exister
        boolean permissionExists = false;
        Long lotRole = null;
      
        if (principal != null) {
    		  String userName = principal.getName();
    		  Utilisateur user = userRepository.findByUsername(userName);
    	  	 roles=rolerepo.findAllByUtilisateurs(user);  	    
    		  }

        //roles = roleRepo.findByUtilisateurs_id(user.getId()); //Récuperer la liste des rôles de l'utilisateur
        int i = 0;
        if(roles!=null) {
        while (i < roles.size() )
        {
            lotRole = roles.get(i).getLot(); //récupérer le lot du role
            permissions = permissionRepo.findByRoles_id(roles.get(i).getId()); // Récupérer la liste des permissions d'un rôle
            // Comparer entre les permissions existantes du rôle et la permission "permToFind"
            permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
            if(permissionExists) {listes_lot.add(lotRole);}
            i++;
        } 
       
        }
        int j=0;
        RessourcesEnAttente.clear();
        RessourcesValidees.clear();
        RessourcesNonValidees.clear();
        while (j<listes_lot.size()) { //Si permission existe alors appeler la fonction d'affichage

            //Récuperer les ressources appartenant au lot auquel à accès l'utilisateur
            RessourcesEnAttente.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.enAttente));
            RessourcesValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.validee));
            RessourcesNonValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.nonValidee));
            //Récupérer nombres de ressources
         j++;  
        }
        if(listes_lot.size()>0) {
        nbrEnAttente = RessourcesEnAttente.size();
        nbrValidees = RessourcesValidees.size();
        nbrNonValidees = RessourcesNonValidees.size();
        //Ajout au modèle
        model.addAttribute("attente",nbrEnAttente);
        model.addAttribute("validee",nbrValidees);
        model.addAttribute("nonValidee",nbrNonValidees);
        model.addAttribute("listeRessources",RessourcesEnAttente);
        model.addAttribute("valide",true);
        model.addAttribute("lotId",listes_lot);
        model.addAttribute("type","en attente de validation");
   	 List<Classification> listesClassifications = new ArrayList<Classification>();
   	 listesClassifications= (List<Classification>) classificationService.findAll();
   	 model.addAttribute("listesClassifications", listesClassifications);
     

        return  "dashboard/generalDash.html";
        }
        //Sinon page d'erreur
        else return "dashboard/generalDashNoAccess.html";
    }
   
    
    @RequestMapping("/ressourcesEnAttente") // TODO : Enlever requestMapping (/x)
    public String getRessourcesValidation(Model model,Principal principal) {
    	 List<Role> roles = new ArrayList<>();
         List<Long> listes_lot = new ArrayList<>();

         List<Permission> permissions;
         String permToFind = "valider"; // nom de la permission qui doit exister
         boolean permissionExists = false;
         Long lotRole = null;
       
         if (principal != null) {
     		  String userName = principal.getName();
     		  Utilisateur user = userRepository.findByUsername(userName);
     	  	 roles=rolerepo.findAllByUtilisateurs(user);  	    
     		  }

         //roles = roleRepo.findByUtilisateurs_id(user.getId()); //Récuperer la liste des rôles de l'utilisateur
         int i = 0;
         if(roles!=null) {
         while (i < roles.size() )
         {
             lotRole = roles.get(i).getLot(); //récupérer le lot du role
             permissions = permissionRepo.findByRoles_id(roles.get(i).getId()); // Récupérer la liste des permissions d'un rôle
             // Comparer entre les permissions existantes du rôle et la permission "permToFind"
             permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
             if(permissionExists) {listes_lot.add(lotRole);}
             i++;
         } 
        
         }
         int j=0;
         RessourcesEnAttente.clear();
         RessourcesValidees.clear();
         RessourcesNonValidees.clear();
         while (j<listes_lot.size()) { //Si permission existe alors appeler la fonction d'affichage

             //Récuperer les ressources appartenant au lot auquel à accès l'utilisateur
             RessourcesEnAttente.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.enAttente));
             RessourcesValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.validee));
             RessourcesNonValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.nonValidee));
             //Récupérer nombres de ressources
          j++;  
         }
         if(listes_lot.size()>0) {
        	//Récupérer nombres de ressources
             nbrEnAttente = RessourcesEnAttente.size();
             nbrValidees = RessourcesValidees.size();
             nbrNonValidees = RessourcesNonValidees.size();
             //Ajout au modèle
             model.addAttribute("attente",nbrEnAttente);
             model.addAttribute("validee",nbrValidees);
             model.addAttribute("nonValidee",nbrNonValidees);
             model.addAttribute("listeRessources",RessourcesEnAttente);
             model.addAttribute("type","en attente de validation");
             model.addAttribute("valide",true);
        	 List<Classification> listesClassifications = new ArrayList<Classification>();
        	 listesClassifications= (List<Classification>) classificationService.findAll();
        	 model.addAttribute("listesClassifications", listesClassifications);
          

             return  "dashboard/generalDash.html";
        
              }
         
         //Sinon page d'erreur
         else return "dashboard/generalDashNoAccess.html";
     }
       

    @GetMapping(value="/ressourcesValidees")
    public String getRessourcesValidees(Model model,Principal principal)
    {        
    	 List<Role> roles = new ArrayList<>();
         List<Long> listes_lot = new ArrayList<>();

         List<Permission> permissions;
         String permToFind = "valider"; // nom de la permission qui doit exister
         boolean permissionExists = false;
         Long lotRole = null;
       
         if (principal != null) {
     		  String userName = principal.getName();
     		  Utilisateur user = userRepository.findByUsername(userName);
     	  	 roles=rolerepo.findAllByUtilisateurs(user);  	    
     		  }

         //roles = roleRepo.findByUtilisateurs_id(user.getId()); //Récuperer la liste des rôles de l'utilisateur
         int i = 0;
         if(roles!=null) {
         while (i < roles.size() )
         {
             lotRole = roles.get(i).getLot(); //récupérer le lot du role
             permissions = permissionRepo.findByRoles_id(roles.get(i).getId()); // Récupérer la liste des permissions d'un rôle
             // Comparer entre les permissions existantes du rôle et la permission "permToFind"
             permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
             if(permissionExists) {listes_lot.add(lotRole);}
             i++;
         } 
        
         }
         int j=0;
         RessourcesEnAttente.clear();
         RessourcesValidees.clear();
         RessourcesNonValidees.clear();
         while (j<listes_lot.size()) { //Si permission existe alors appeler la fonction d'affichage

             //Récuperer les ressources appartenant au lot auquel à accès l'utilisateur
             RessourcesEnAttente.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.enAttente));
             RessourcesValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.validee));
             RessourcesNonValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.nonValidee));
             //Récupérer nombres de ressources
          j++;  
         }
         if(listes_lot.size()>0) {
        	 //Récupérer nombres de ressources
             nbrEnAttente = RessourcesEnAttente.size();
             nbrValidees = RessourcesValidees.size();
             nbrNonValidees = RessourcesNonValidees.size();
             //Ajout au modèle
             model.addAttribute("attente",nbrEnAttente);
             model.addAttribute("validee",nbrValidees);
             model.addAttribute("nonValidee",nbrNonValidees);
             model.addAttribute("listeRessources",RessourcesValidees);
             model.addAttribute("type","validées");
             model.addAttribute("valide",true);
        	 List<Classification> listesClassifications = new ArrayList<Classification>();
        	 listesClassifications= (List<Classification>) classificationService.findAll();
        	 model.addAttribute("listesClassifications", listesClassifications);
          

             return  "dashboard/generalDash.html";
         }
         
         //Sinon page d'erreur
         else return "dashboard/generalDashNoAccess.html";
     }
    
     //Sinon page d'erreur
   
    @GetMapping(value="/ressourcesNonValidees")
    public String getRessourcesNonValidees(Model model,Principal principal)
    {
    	 List<Role> roles = new ArrayList<>();
         List<Long> listes_lot = new ArrayList<>();

         List<Permission> permissions;
         String permToFind = "valider"; // nom de la permission qui doit exister
         boolean permissionExists = false;
         Long lotRole = null;
       
         if (principal != null) {
     		  String userName = principal.getName();
     		  Utilisateur user = userRepository.findByUsername(userName);
     	  	 roles=rolerepo.findAllByUtilisateurs(user);  	    
     		  }

         //roles = roleRepo.findByUtilisateurs_id(user.getId()); //Récuperer la liste des rôles de l'utilisateur
         int i = 0;
         if(roles!=null) {
         while (i < roles.size() )
         {
             lotRole = roles.get(i).getLot(); //récupérer le lot du role
             permissions = permissionRepo.findByRoles_id(roles.get(i).getId()); // Récupérer la liste des permissions d'un rôle
             // Comparer entre les permissions existantes du rôle et la permission "permToFind"
             permissionExists = permissions.stream().anyMatch(permission -> permToFind.equalsIgnoreCase(permission.getNom()));
             if(permissionExists) {listes_lot.add(lotRole);}
             i++;
         } 
        
         }
         int j=0;
         RessourcesEnAttente.clear();
         RessourcesValidees.clear();
         RessourcesNonValidees.clear();
         while (j<listes_lot.size()) { //Si permission existe alors appeler la fonction d'affichage

             //Récuperer les ressources appartenant au lot auquel à accès l'utilisateur
             RessourcesEnAttente.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.enAttente));
             RessourcesValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.validee));
             RessourcesNonValidees.addAll(ressourceRepo.findByClassifications_Lot_idAndEtat(listes_lot.get(j),EtatValidation.nonValidee));
             //Récupérer nombres de ressources
          j++;  
         }
         if(listes_lot.size()>0) {
        	//Récupérer nombres de ressources
             nbrEnAttente = RessourcesEnAttente.size();
             nbrValidees = RessourcesValidees.size();
             nbrNonValidees = RessourcesNonValidees.size();
             //Ajout au modèle
             model.addAttribute("attente",nbrEnAttente);
             model.addAttribute("validee",nbrValidees);
             model.addAttribute("nonValidee",nbrNonValidees);
             model.addAttribute("listeRessources",RessourcesNonValidees);
             model.addAttribute("type","non validées/refusées");
             model.addAttribute("valide",true);
        	 List<Classification> listesClassifications = new ArrayList<Classification>();
        	 listesClassifications= (List<Classification>) classificationService.findAll();
        	 model.addAttribute("listesClassifications", listesClassifications);
          

             return  "dashboard/generalDash.html";
         }
         //Sinon page d'erreur
         else return "dashboard/generalDashNoAccess.html";
     }
             
        
   


    private Long getmax_niv_sec(List<Role> listeRole) {
    	long max_niv_sec=0;
    	 
    	for ( Role role:listeRole) {
    	    if(role.getLot()==0) {
    	    	 if(max_niv_sec<role.getNiv_sec()) {
    	    		 max_niv_sec=role.getNiv_sec(); 
    	     }
    	    }
    	}
    return max_niv_sec;
    }

}
