package guru.springframework.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import java.security.Principal;
import org.springframework.security.core.userdetails.User;

//import guru.springframework.config.UserDetailsServiceImpl;
import guru.springframework.domain.Utilisateur;

@Controller

public class LoginController {
	
/*	public UserDetailsServiceImpl userDetailsServiceImpl;

	  @GetMapping("/login")
	    public String login(Model model, Principal principal) {  
		//  User loginedUser = (User) ((Authentication) principal).getPrincipal();
		  //System.out.println(loginedUser.getUsername());
	       // model.addAttribute("userInfo", userInfo);
	        return "ressources genetiques/login.html";
	    }
	  

	  @GetMapping("/logout")
	  public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      if (auth != null){    
	          new SecurityContextLogoutHandler().logout(request, response, auth);
	      }
	      return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	  }
	*/

}
