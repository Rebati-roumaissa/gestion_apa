package guru.springframework.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 

public class HomeController {

  

    @RequestMapping("/")
    public String index(@RequestParam (value="name", defaultValue="World") String name,Model model) {
      model.addAttribute("name",name);
       return "BackOffice/index";
     //  return "index";
    }


}
