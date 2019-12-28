package guru.springframework.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.domain.Permission;
import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.domain.cat_inst;
import guru.springframework.domain.cat_rh;
import guru.springframework.domain.detail_inst;
import guru.springframework.domain.detail_rh;
import guru.springframework.repositories.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
public class AnnuaireController {
	
	@Autowired
	cat_rh_repository cat_rh_repository;
	@Autowired
	cat_inst_repository cat_inst_repository;
	
	@Autowired
	detail_inst_repository detail_inst_repository;
	@Autowired
	detail_rh_repository detail_rh_repository;
    
	 @Autowired
     private UtilisateurRepository userRepository;
     @Autowired
     private  RoleRepository  rolerepo;
     @Autowired
	  PermissionRepo permissionRepo;
	

	public ModelAndView View(HttpServletRequest request,List<cat_inst> l1, List<cat_rh> l2, String type, int id) {
		Principal principal = request.getUserPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		  modelAndView.addObject("cat_inst",l1);
		  modelAndView.addObject("cat_rh",l2);
	      modelAndView.addObject("ID",id);
	      modelAndView.addObject("type",type);
		  modelAndView.setViewName("pages_annuaire/index");

		/*-----*/
		List<Role> listeRole = new ArrayList<>();
		List<Long> listes_lot_ajouter = new ArrayList<>();

		List<Permission> permissions;
		List<Permission> permissions2=new ArrayList<Permission>();

		String permtoFind3="ajouter";// nom2 de la permission qui doit exister

		boolean permissionExists = false;
		boolean permissionExists2 = false;
		boolean permissionExists3 = false;


		Long lotRole = null;

		if (principal != null) {
			String userName = principal.getName();
			Utilisateur user = userRepository.findByUsername(userName);
			listeRole=rolerepo.findAllByUtilisateurs(user);
System.out.println("user "+user.getId());

		}

		int i = 0;
		if(listeRole!=null) {
			while (i < listeRole.size() )
			{
				if(listeRole.get(i).getLot()==5)
				{
					permissions2.addAll(permissionRepo.findByRoles_id(listeRole.get(i).getId())); // Récupérer la liste des permissions d'un rôle

				}
				lotRole = listeRole.get(i).getLot(); //récupérer le lot du role
				permissions = permissionRepo.findByRoles_id(listeRole.get(i).getId()); // Récupérer la liste des permissions d'un rôle


				// Comparer entre les permissions existantes du rôle et la permission "permToFind"
				permissionExists3 = permissions2.stream().anyMatch(permission -> permtoFind3.equalsIgnoreCase(permission.getNom()));

				if(permissionExists3) {listes_lot_ajouter.add(lotRole);}

				i++;
			}

		}


		if(listes_lot_ajouter.size()>0) {

			modelAndView.addObject("ajoute",true);
		}

		/*-----*/
          return modelAndView;
	}
	
	@GetMapping("/")
    public ModelAndView home(){ 
       
       return    new ModelAndView("pages_annuaire/home");
    }
	
	
	@GetMapping("/annuaire")
    public ModelAndView Annuaire(HttpServletRequest request){
       ModelAndView view= View(request,cat_inst_repository.findAll(),cat_rh_repository.findAll(),"inst",0);
       view.addObject("cat",cat_inst_repository.findById(10));
       view.addObject("detail",detail_inst_repository.findByCat(10));
       return view;
    }
	
	
	   @GetMapping("/detail/{id}/inst")
	   public ModelAndView Annuaire2(HttpServletRequest request,@PathVariable String id){
		   int Id = Integer.parseInt(id);
		   ModelAndView view= View(request,cat_inst_repository.findAll(),cat_rh_repository.findAll(),"inst",Id-1);
	       view.addObject("cat",cat_inst_repository.findById(Id));
	       view.addObject("detail",detail_inst_repository.findByCat(Id));
	       return view;
		   
	    }
	   
	   @GetMapping("/detail/{id}/rh")
	   public ModelAndView Annuaire3(HttpServletRequest request,@PathVariable String id){
		  int Id = Integer.parseInt(id);
		   ModelAndView view= View(request,cat_inst_repository.findAll(),cat_rh_repository.findAll(),"rh",Id-1);
	       view.addObject("cat",cat_rh_repository.findById(Id));
	       view.addObject("detail",detail_rh_repository.findByCat(Id));
	       return view;
	    }
	    
	   
	   @GetMapping("/inst/{id}")
	   public detail_inst getRessource1(@PathVariable String id )
	   {
		   int D = Integer.parseInt(id);
		   return detail_inst_repository.findByIdDetail(D);
	   }
	   
	   @GetMapping("/rh/{id}")
	   public detail_rh getRessource2(@PathVariable String id)
	   {
		   int D = Integer.parseInt(id);
		   return detail_rh_repository.findByIdDetail(D);
	   }
	   
	   private Long getmax_niv_sec(List<Role> listeRole) {
			long max_niv_sec=0;
			 
			for ( Role role:listeRole) {
			    if(role.getLot()==1) {
			    	 if(max_niv_sec<role.getNiv_sec()) {
			    		 max_niv_sec=role.getNiv_sec(); 
			     }
			    }
			}
		return max_niv_sec;
		}
}

