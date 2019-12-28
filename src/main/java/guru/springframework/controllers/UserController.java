package guru.springframework.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import guru.springframework.domain.Role;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.repositories.UtilisateurRepository;

@RestController
public class UserController {

	@Autowired
	private UtilisateurRepository user_repository;
	
	@Autowired
	private RoleRepository role_repository;
	private PasswordEncoder passwordEncoder;

	public UserController( PasswordEncoder passwordEncoder)
	{
		this.passwordEncoder=passwordEncoder;
	}
	public ModelAndView View(List<Utilisateur> l1, String type, int id) {
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("gestion_compte/comptes");
		  return modelAndView;
	}
	
	@GetMapping("/comptes")
	public ModelAndView viewComptesPages() {
		ModelAndView modelAndView = new ModelAndView();
		List<Utilisateur> users = user_repository.findAll();
	    List<Role> roles = role_repository.findAll();
	    modelAndView.addObject("users", users);
	    modelAndView.addObject("roles", roles);
		modelAndView.setViewName("gestion_compte/comptes");
		return modelAndView;
	}
	
	@PostMapping("/comptes/save")
	public ModelAndView saveRole(@Valid Utilisateur user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("gestion_compte/comptes");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		user_repository.save(user);
		modelAndView.addObject("roles", role_repository.findAll());
		modelAndView.addObject("users", user_repository.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/comptes/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") Long id) {
		Utilisateur  user = user_repository.getOne(id);
		user_repository.delete(user);
		ModelAndView modelAndView = new ModelAndView();
		Utilisateur new_user = new Utilisateur();
		modelAndView.addObject("user", new_user);
		modelAndView.setViewName("gestion_compte/comptes");
		modelAndView.addObject("users", user_repository.findAll());
		modelAndView.addObject("roles", role_repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/comptes/add")
	public ModelAndView addCompte() {
		ModelAndView modelAndView = new ModelAndView();
		Utilisateur user = new Utilisateur();
	    modelAndView.addObject("user", user);
	    modelAndView.addObject("LastId",user_repository.findTopByOrderByIdDesc().getId()+1);
	    modelAndView.addObject("roles", role_repository.findAll());
		modelAndView.setViewName("gestion_compte/add_user");
		return modelAndView;
	}
	
	@GetMapping("/comptes/edit/{id}")
	public ModelAndView getCompte(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Utilisateur user = user_repository.getOne(id);
	    modelAndView.addObject("user", user);
	    modelAndView.addObject("roles", role_repository.findAll());
		modelAndView.setViewName("gestion_compte/edit_user");
		return modelAndView;
	}
	
	
	@GetMapping("/comptes/affecter_role/{id}")
	public ModelAndView affecter_role(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Utilisateur user = user_repository.getOne(id);
	    modelAndView.addObject("user", user);
	    modelAndView.addObject("roles", role_repository.findAll());
		modelAndView.setViewName("gestion_compte/affecter_role");
		return modelAndView;
	}
	
	
	@PostMapping("/comptes/update/{id}")
	public ModelAndView editUser(@Valid Utilisateur user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			return modelAndView;
		}
		user_repository.save(user);

		modelAndView.addObject("user", user);
		modelAndView.setViewName("gestion_compte/comptes");
		modelAndView.addObject("users", user_repository.findAll());
		modelAndView.addObject("roles", role_repository.findAll());
		return modelAndView;
	}
}
