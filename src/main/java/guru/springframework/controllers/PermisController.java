package guru.springframework.controllers;

import guru.springframework.domain.Categorie;
import guru.springframework.domain.InfoPermis;
import guru.springframework.domain.PermisAcces;
import guru.springframework.domain.Utilisateur;
import guru.springframework.repositories.*;
import guru.springframework.util.GeneratePdfReport;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/PermisAcces")
public class PermisController {
    private final PermisRepository permisRepository;
    private final demandeRepository demandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CategorieRepository categorieRepository;


    public PermisController(PermisRepository permisRepository,
                            UtilisateurRepository utilisateurRepository,
                            guru.springframework.repositories.demandeRepository demandeRepository,
                            CategorieRepository categorieRepository) {
        this.permisRepository = permisRepository;
        this.demandeRepository = demandeRepository;
        this.utilisateurRepository=utilisateurRepository;
        this.categorieRepository=categorieRepository;
    }

    private MultipartFile permisReport(InfoPermis infoPermis) throws IOException {
        byte[] bis;
        bis = GeneratePdfReport.permisReport(infoPermis);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=permis" + infoPermis.getCodePermis());
        MultipartFile multipartFile1 = new MockMultipartFile("file",
                "permis" + infoPermis.getCodePermis() + ".pdf", "application/pdf", bis);
        return multipartFile1;
    }

    @RequestMapping(value = "/gestionnaire/create/{iddemande}", method = POST)
    public RedirectView createPermis(PermisAcces permisAcces, InfoPermis infoPermis, @PathVariable int iddemande) throws IOException {
        String UPLOADED_FOLDER = "uploads/permisAcces/" + infoPermis.getCodePermis();
        MultipartFile file = permisReport(infoPermis);
        File dir = new File(UPLOADED_FOLDER);
        String status = "";
        try {
            byte[] bytes = file.getBytes();
            if (!dir.exists())
                dir.mkdirs();
            File uploadFile = new File(dir.getAbsolutePath()
                    + File.separator + file.getOriginalFilename());
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(uploadFile));
            outputStream.write(bytes);
            outputStream.close();
            status = status + "You successfully uploaded file=" + file.getOriginalFilename();
        } catch (Exception e) {
            status = status + "Failed to upload " + file.getOriginalFilename() + " " + e.getMessage();
        }
        permisAcces.setChemin(UPLOADED_FOLDER
                + File.separator + file.getOriginalFilename());
        permisAcces.setId_demande(iddemande);
        permisAcces.setIddemandeur(demandeRepository.findByIddemande(iddemande).getUtilisateur().getId());
        permisRepository.save(permisAcces);
        return new RedirectView("/demandes/gestionnaire/all");
    }
    @RequestMapping(value = "/gestionnaire/all", method = GET)
    public ModelAndView showPermis()
    {
        List<PermisAcces> permisAcces = permisRepository.findAll();
        List<Categorie> categories= categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("permisAcces", permisAcces);    params.put("categories", categories);
        return new ModelAndView("ged/Permis_list", params);
    }
    @RequestMapping(value = "/demandeur/all", method = GET)
    @ResponseBody
    public ModelAndView showPermisU(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        List<PermisAcces> permisAcces = permisRepository.findByIddemandeur(utilisateur.getId());
        List<Categorie> categories= categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        params.put("permisAcces", permisAcces);
        return new ModelAndView("ged/Permis_list", params);
    }

}
