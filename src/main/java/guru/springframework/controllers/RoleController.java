package guru.springframework.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.domain.Lot;
import guru.springframework.domain.Permission;
import guru.springframework.domain.Role;
import guru.springframework.repositories.LotRepository;
import guru.springframework.repositories.PermissionRepo;
import guru.springframework.repositories.RoleRepository;

@RestController
public class RoleController {
	
	@Autowired
	private RoleRepository role_repository;
	
	@Autowired
	private PermissionRepo permission_repository;

	@Autowired
	private LotRepository lot_repository;
	
	public ModelAndView View(List<Role> l1, String type, int id) {
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("gestion_compte/roles");
		  return modelAndView;
	}
	
	@GetMapping("/roles")
	public ModelAndView viewRolesPage() {
		ModelAndView modelAndView = new ModelAndView();
	    Role role = new Role();
	    List<Permission> permission = permission_repository.findAll();
	    List<Role> roles = role_repository.findAll();
	    modelAndView.addObject("roles", roles);
		modelAndView.addObject("lots", lot_repository.findAll());

	    modelAndView.addObject("permissions", permission);
		modelAndView.setViewName("gestion_compte/roles");
		return modelAndView;
	}
	
	@PostMapping("/roles/save")
	public ModelAndView saveRole(@Valid Role role, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			return modelAndView;
		}
		modelAndView.setViewName("gestion_compte/roles");
		role_repository.save(role);	
		modelAndView.addObject("lots", lot_repository.findAll());
		modelAndView.addObject("roles", role_repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/roles/delete/{id}")
	public ModelAndView deleteRole(@PathVariable("id") Long id) {
		Role role = role_repository.getOne(id);
		role_repository.delete(role);
		ModelAndView modelAndView = new ModelAndView();
		Role new_role = new Role();
		modelAndView.addObject("role", new_role);
		modelAndView.setViewName("gestion_compte/roles");
		modelAndView.addObject("roles", role_repository.findAll());
		return modelAndView;
	} 
	
	@GetMapping("/roles/edit/{id}")
	public ModelAndView getRole(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
	    Role role = role_repository.getOne(id);
	    List<Permission>p=role.getPermissions();
	    for(Permission per:p)
	    System.out.println(per.getNom());
	    modelAndView.addObject("role", role);
	    modelAndView.addObject("permissions", permission_repository.findAll());
	    modelAndView.addObject("lots", lot_repository.findAll());
		modelAndView.setViewName("gestion_compte/edit_role");
		return modelAndView;
	}
	
	@GetMapping("/roles/add")
	public ModelAndView addRole() {
		ModelAndView modelAndView = new ModelAndView();
	    Role role = new Role();
	    modelAndView.addObject("role", role);
	    modelAndView.addObject("permissions", permission_repository.findAll());
	    modelAndView.addObject("lots", lot_repository.findAll());
	    modelAndView.setViewName("gestion_compte/add_role");

return modelAndView;
	}
	
	@PostMapping("/roles/update/{id}")
	public ModelAndView editRole(@Valid Role role, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			return modelAndView;
		}
		role_repository.save(role);
		modelAndView.addObject("role", role);
		modelAndView.setViewName("gestion_compte/roles");
		modelAndView.addObject("permissions", permission_repository.findAll());
	    modelAndView.addObject("lots", lot_repository.findAll());
		modelAndView.addObject("roles", role_repository.findAll());
		return modelAndView;
	}
}
