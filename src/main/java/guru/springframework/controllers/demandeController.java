package guru.springframework.controllers;

import guru.springframework.domain.*;
import guru.springframework.repositories.*;
import guru.springframework.services.storage.StorageService;
import guru.springframework.util.GeneratePdfReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/demandes")
public class demandeController {
    private final demandeRepository demandeRepository;
    private final demandeurRepository demandeurRepository;
    private final DossierRepository dossierRepository;
    private final preuveRepository preuveRepository1;
    private final DocumentRepository documentRepository;
    private final motCleRepository motCleRepository;
    private final StorageService service;
    private final DirectoryRepository directoryRepository;
    private final ConsultationRepository consultationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PermisRepository permisRepository;
    private final InfoPermisRepository infoPermisRepository;
    private final CategorieRepository categorieRepository;
    private final validationRepository validationRepository;

    @Autowired
    public demandeController(demandeRepository demandeRepository, demandeurRepository demandeurRepository,
                             StorageService service, DossierRepository dossierRepository,
                             preuveRepository preuveRepository1,
                             DocumentRepository documentRepository, motCleRepository motCleRepository,
                             DirectoryRepository directoryRepository, UtilisateurRepository utilisateurRepository,
                             ConsultationRepository consultationRepository,
                             PermisRepository permisRepository,
                             InfoPermisRepository infoPermisRepository,
                             CategorieRepository categorieRepository,
                             validationRepository validationRepository) {
        this.demandeRepository = demandeRepository;
        this.demandeurRepository = demandeurRepository;
        this.dossierRepository = dossierRepository;
        this.preuveRepository1 = preuveRepository1;
        this.service = service;
        this.documentRepository = documentRepository;
        this.motCleRepository = motCleRepository;
        this.directoryRepository = directoryRepository;
        this.consultationRepository = consultationRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.permisRepository = permisRepository;
        this.infoPermisRepository = infoPermisRepository;
        this.categorieRepository=categorieRepository;
        this.validationRepository=validationRepository;
    }

    @RequestMapping(value = "/demandeur/pdfreport", method = GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public /*ResponseEntity<InputStreamResource>*/ MultipartFile demandeReport(InfoPermis infoPermis
            , demande demande, demandeur demandeur) throws IOException {
        /*List<demande> demandes = (List<demande>) demandeRepository.findAll();
        byte[] bis = GeneratePdfReport.demandesReport(demandes);*/
        byte[] bis;
        bis = GeneratePdfReport.demandeReport(infoPermis);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=demande" + infoPermis.getNom() + demande.getIddemande());
        MultipartFile multipartFile1 = new MockMultipartFile("file",
                "demande" + infoPermis.getNom() + demande.getIddemande() + ".pdf", "application/pdf", bis);
          /* service.store("demande"+demandeur.getNom()+demande.getIddemande(), multipartFile);
             return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(new ByteArrayInputStream(bis)));*/
        return multipartFile1;
    }

    public /*ResponseEntity<InputStreamResource>*/ MultipartFile infoDemandeReport(InfoPermis infoPermis) throws IOException {
        /*List<demande> demandes = (List<demande>) demandeRepository.findAll();
        byte[] bis = GeneratePdfReport.demandesReport(demandes);*/
        byte[] bis;
        bis = GeneratePdfReport.demandeReport(infoPermis);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=demande" + infoPermis.getNom() + infoPermis.getNss());
        MultipartFile multipartFile1 = new MockMultipartFile("file",
                "demande" + infoPermis.getNom() + infoPermis.getNss() + ".pdf", "application/pdf", bis);
          /* service.store("demande"+demandeur.getNom()+demande.getIddemande(), multipartFile);
             return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(new ByteArrayInputStream(bis)));*/
        return multipartFile1;
    }

    @RequestMapping(value = "/demandeur/create", method = POST)
    public @ResponseBody
    RedirectView demandeSubmit(HttpServletRequest request, InfoPermis infoPermis, demande demande, demandeur demandeur, @RequestParam("action") String action,
                         @RequestParam("description") ArrayList<String> descriptions, @RequestParam("file") ArrayList<MultipartFile> files) throws IOException {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        if (action.equals("soumettre")) {
            demande.setEtatDemande("en attente");
        } else {
            if (action.equals("enregistrer")) {
                demande.setEtatDemande("brouillon");
            }
        }

        sauvegardeDemande(infoPermis, demande, utilisateur, demandeur, descriptions, files);
        return new RedirectView("/demandes/demandeur/all");
    }

    private void sauvegardeDemande(InfoPermis infoPermis, demande demande, Utilisateur utilisateur, demandeur demandeur,
                                   @RequestParam("description") ArrayList<String> descriptions, @RequestParam("file") ArrayList<MultipartFile> files) throws IOException {
        UploadController uploadController = new UploadController(documentRepository, motCleRepository, service, demandeRepository, directoryRepository, permisRepository, consultationRepository);
        Log log = LogFactory.getLog(UploadController.class);
        // à completer les deux cas
        if(demande.getIddemande()!=0) {
            log.info(demande.getIddemande());
            demande demande2 = demandeRepository.findByIddemande(demande.getIddemande());
            demande2.setEtatDemande(demande.getEtatDemande());
            demande2.setCommentaire(demande.getCommentaire());
            demande2.setApplicationsEnvisagees(demande.getApplicationsEnvisagees());
            demande2.setDescriptionActivites(demande.getDescriptionActivites());
            demande2.setObjectifs(demande.getObjectifs());
            demande2.setTypeDemande(demande.getTypeDemande());
            //demande2.setDemandeur(demande.getDemandeur());
            //demande2.setUtilisateur(demande.getUtilisateur());
            files.add(demandeReport(infoPermis, demande, demandeur));
            String codedemande;
            if (demandeur.getTypeDemandeur().equals("Personne morale")) {
                codedemande = demandeur.getRaisonSociale() + utilisateur.getId();
            } else {
                codedemande = utilisateur.getNom() + utilisateur.getPrenom() + utilisateur.getId();
            }
            descriptions.add("demande" + codedemande);
            String chemin = uploadController.uploadMultipleFiles(codedemande, descriptions, files);
            demande2.setCheminDossierDemande(chemin);
            demandeRepository.save(demande2);
         //   demandeRepository.save(demande2);
            if (demande.getEtatDemande().equals("brouillon")) {
                infoPermis.setIddemande(demande.getIddemande());
                infoPermisRepository.save(infoPermis);
            }
            //utilisateur.addDemande(demande);
        }
        else
        {
            sauvegardeDemandeNB(infoPermis,demande,utilisateur,demandeur,descriptions,files);
        }

        //

        /***
         * Ajouter  une condition avant la création, ida yexisti nchofo wch ndiro
         */
        //
        //if (demandeur.getTypeDemandeur().equals("Personne morale")) {
        /*
        try {
            //demandeur.addDemande(demande);
            //demande.setDemandeur(demandeur);
           // demandeurRepository.save(demandeur);
        }
        catch(Exception ex){
            //demandeurRepository.save(demandeur);
            //demande.setDemandeur(demandeur);
        }
        //}
        try {
            utilisateurRepository.save(utilisateur);
            demande.setUtilisateur(utilisateur);
        }
        catch(Exception ex ){
            demande.setUtilisateur(utilisateur);
        }*/

    }
    private void sauvegardeDemandeNB(InfoPermis infoPermis, demande demande, Utilisateur utilisateur, demandeur demandeur,
                                     @RequestParam("description") ArrayList<String> descriptions, @RequestParam("file") ArrayList<MultipartFile> files) throws IOException {
        UploadController uploadController = new UploadController(documentRepository, motCleRepository, service, demandeRepository, directoryRepository, permisRepository,consultationRepository);

        files.add(demandeReport(infoPermis,demande,demandeur));
        String codedemande;
        if (demandeur.getTypeDemandeur().equals("Personne morale"))
        {
            codedemande= demandeur.getRaisonSociale()+ utilisateur.getId(); }
        else {
            codedemande = utilisateur.getNom()+ utilisateur.getPrenom() + utilisateur.getId() ;
        }
        descriptions.add("demande" + codedemande);
        String chemin = uploadController.uploadMultipleFiles(codedemande,descriptions, files);
        demande.setCheminDossierDemande(chemin);
        demande out = demandeRepository.save(demande);
        //
        utilisateur.addDemande(out);
        /***
         * Ajouter  une condition avant la création, ida yexisti nchofo wch ndiro
         */
        //
       /* if (demandeur.getTypeDemandeur().equals("Personne morale"))
        {*/
            demandeur.addDemande(out);
            demande.setDemandeur(demandeur);
            demandeurRepository.save(demandeur);
        //}
        if (demande.getEtatDemande().equals("brouillon")) {
            infoPermis.setIddemande(out.getIddemande());
            infoPermisRepository.save(infoPermis);
        }
        utilisateurRepository.save(utilisateur);
        demande.setUtilisateur(utilisateur);
        demandeRepository.save(out);
    }
    @RequestMapping(value = "/demandeur/Savedemande", method = POST)
    public void demandeSave(HttpServletRequest request, InfoPermis infoPermis, demande demande, demandeur demandeur,
                            @RequestParam("description") ArrayList<String> descriptions, @RequestParam("file") ArrayList<MultipartFile> files) throws IOException {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        demande.setEtatDemande("brouillon");
        sauvegardeDemande(infoPermis, demande, utilisateur, demandeur, descriptions, files);
    }

    @RequestMapping(value = "/demandeur/remplirForm", method = GET)
    public ModelAndView showForm(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        Dossier dossier = dossierRepository.findByTypeDemande("scientifique");
        List<Preuve> preuves = new ArrayList<>();
        if (dossier != null) {
            preuves = dossier.getPreuves();
        }
        List<Categorie> categories=categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("preuves", preuves);
        params.put("demandeur", utilisateur);
        params.put("categories", categories);
        return new ModelAndView("ged/Demande_Formulaire", params);
    }

    @RequestMapping(value = "/demandeur/remplirForm/{brouillon}", method = GET)
    //description = "Get all documents information from the database"
    public ModelAndView showFormRemplis(HttpServletRequest request, @PathVariable(value = "brouillon") int iddemande) {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        Dossier dossier = dossierRepository.findByTypeDemande("scientifique");
        demande demande= demandeRepository.findByIddemande(iddemande);
        InfoPermis infoPermis = infoPermisRepository.findByIddemande(iddemande);
        Map<String, Object> params = new HashMap<>();
        List<Preuve> preuves = new ArrayList<>();
        if (dossier != null) {
            preuves = dossier.getPreuves();
        }
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories", categories);
        params.put("preuves", preuves);
        params.put("infoDemande", infoPermis);
        params.put("demandeur", utilisateur);
        params.put("demande", demande);
        return new ModelAndView("ged/Demande_Formulaire_sauvegarde", params);
    }

    @RequestMapping(value = "/gestionnaire/all", method = GET)
    //description = "Get all documents information from the database"
    public ModelAndView showDemande() {
        List<Dossier> types = dossierRepository.findAll();
        List<demande> demandes = demandeRepository.findAll();
        List<List<demande>> demandesClass = demandeDashboard();
        Map<String, Object> params = new HashMap<>();
        params.put("demandes", demandes);
        params.put("demandesAttente", demandesClass.get(0));
        params.put("demandesCours", demandesClass.get(1));
        params.put("demandesValides", demandesClass.get(2));
        params.put("demandesRefusees", demandesClass.get(3));
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories", categories);
        params.put("types", types);
        return new ModelAndView("ged/Demandes_list", params);
    }

    @RequestMapping(value = "/gestionnaire/dossierpreuves", method = POST)
    public String AddDossierPreuves(@RequestBody ArrayList<String> data) {
        Log log = LogFactory.getLog(UploadController.class);
        log.info(data.get(0));
        Dossier dossier = new Dossier(data.get(0));
        List<Preuve> preuves = new ArrayList<>();
        for (int j = 1; j < data.size(); j++) {
            preuves.add(new Preuve(data.get(j)));
            log.info(data.get(j));
        }
        preuveRepository1.saveAll(preuves);
        dossier.setPreuves(preuves);
        dossierRepository.save(dossier);
        return "/ged/index";
    }

    @RequestMapping(value = "/demandeur/all", method = GET)
    @ResponseBody
    public ModelAndView showDemande(HttpServletRequest request) {
        List<Dossier> types = dossierRepository.findAll();
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        List<demande> demandes = utilisateur.getDemandes();
        List<List<demande>> demandesClass = demandeDashboard(utilisateur);
        List<Categorie> categories=categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("demandes", demandes);
        params.put("categories",categories);
        params.put("demandesAttente", demandesClass.get(0));
        params.put("demandesCours", demandesClass.get(1));
        params.put("demandesValides", demandesClass.get(2));
        params.put("demandesRefusees", demandesClass.get(3));
        params.put("demandesDrafts", demandesClass.get(4));
        params.put("types", types);
        return new ModelAndView("ged/Mes_demandes", params);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    private ModelAndView showDemande(List<demande> demandes) {
        List<Dossier> types = dossierRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("demandes", demandes);
        params.put("types", types);
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        return new ModelAndView("ged/Demandes_list", params);
    }

    private ModelAndView showDemandeU(List<demande> demandes) {
        List<Dossier> types = dossierRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        params.put("demandes", demandes);
        params.put("types", types);
        return new ModelAndView("ged/Mes_demandes", params);
    }

    @RequestMapping(value = "/gestionnaire/Type", method = GET)
    //description = "Get all documents information from the database"
    public ModelAndView showDemandeByType() {
        List<demande> demandes = demandeRepository.findByTypeDemande("scientifique");
        Map<String, Object> params = new HashMap<>();
        params.put("demandes", demandes);
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        return new ModelAndView("ged/Demandes_list", params);
    }

    @RequestMapping(value = "gestionnaire/Files/{demande}", method = GET)
    public ModelAndView showFilesByDemande(@PathVariable(value = "demande") int iddemande) {
        demande demande1 = demandeRepository.findByIddemande(iddemande);
        demande1.setEtatDemande("en cours");
        return afficher_fichiers(demande1);
    }

    @RequestMapping(value = "demandeur/Files/{demande}", method = GET)
    public ModelAndView showFilesByDemandeD(@PathVariable(value = "demande") int iddemande) {
        demande demande1 = demandeRepository.findByIddemande(iddemande);
        return afficher_fichiers(demande1);
    }

    private ModelAndView afficher_fichiers(demande demande) {
        demandeRepository.save(demande);
        List<File> files = service.listFiles(demande.getCheminDossierDemande(), new ArrayList<>());
        PermisAcces permisAcces = permisRepository.findByIddemande(demande.getIddemande());
        if (permisAcces != null)
            files.add(new File(permisAcces.getChemin()));
        Map<String, Object> params = new HashMap<>();
        params.put("files", files);
        params.put("iddemande", demande.getIddemande());
        params.put("demande", demande);
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        return new ModelAndView("ged/files_list", params);

    }

    @RequestMapping(value = "/gestionnaire/validation/{id}/{decision}", method = GET)
    public ModelAndView decisionDemande(HttpServletRequest request, @PathVariable(value = "decision") String decision, @PathVariable(value = "id") String idDemande) {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        demande demande = demandeRepository.findByIddemande(Integer.parseInt(idDemande));
        demande.setEtatDemande(decision);
        demandeRepository.save(demande);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Validation validation = new Validation(demande.getIddemande(),utilisateur.getId(),date,decision);
        validationRepository.save(validation);
        Map<String, Object> params = new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        if (decision.equals("validee"))
        {
            params.put("demande", demande);
            return new ModelAndView("ged/Permis_acces", params);
        }
        else{
            return showDemande();
        }
    }
    @RequestMapping(value = "/informations",method = GET)
    public ModelAndView showInfo()
    {
        Map<String, Object> params = new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        return new ModelAndView("ged/informationsdemandes",params);
    }

    private List<List<demande>> demandeDashboard(Utilisateur demandeur) {
        List<List<demande>> demandes = new ArrayList<>();
        List<demande> demandesAttente = demandeRepository.findByUtilisateurAndEtatDemande(demandeur, "en attente");
        List<demande> demandesCours = demandeRepository.findByUtilisateurAndEtatDemande(demandeur, "en cours");
        List<demande> demandesValides = demandeRepository.findByUtilisateurAndEtatDemande(demandeur, "validee");
        List<demande> demandesRefusees = demandeRepository.findByUtilisateurAndEtatDemande(demandeur, "refusee");
        List<demande> demandesDrafts = demandeRepository.findByUtilisateurAndEtatDemande(demandeur, "brouillon");
        demandes.add(demandesAttente);
        demandes.add(demandesCours);
        demandes.add(demandesValides);
        demandes.add(demandesRefusees);
        demandes.add(demandesDrafts);
        return demandes;
    }

    private List<List<demande>> demandeDashboard() {
        List<List<demande>> demandes = new ArrayList<>();
        List<demande> demandesAttente = demandeRepository.findByEtatDemande("en attente");
        List<demande> demandesCours = demandeRepository.findByEtatDemande("en cours");
        List<demande> demandesValides = demandeRepository.findByEtatDemande("validee");
        List<demande> demandesRefusees = demandeRepository.findByEtatDemande("refusee");
        demandes.add(demandesAttente);
        demandes.add(demandesCours);
        demandes.add(demandesValides);
        demandes.add(demandesRefusees);
        return demandes;
    }

    @RequestMapping(value = "/gestionnaire/byStatut/{Statut}", method = RequestMethod.GET)
    //description = "search documents with the provided idcat from the database"
    public ModelAndView SearchDocsByCat(@PathVariable(value = "Statut") String etatDemande) {
        List<demande> demandes = demandeRepository.findByEtatDemande(etatDemande);
        return showDemande(demandes);
    }

    @RequestMapping(value = "/demandeur/byStatut/{Statut}", method = RequestMethod.GET)
    //description = "search documents with the provided idcat from the database"
    public ModelAndView SearchDocsByCatUser(HttpServletRequest request, @PathVariable(value = "Statut") String etatDemande) {
        Principal principal = request.getUserPrincipal();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(principal.getName());
        List<demande> demandes = demandeRepository.findByUtilisateurAndEtatDemande(utilisateur, etatDemande);
        return showDemandeU(demandes);
    }

}
