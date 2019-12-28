package guru.springframework.controllers;

import guru.springframework.domain.Categorie;
import guru.springframework.domain.Directory;
import guru.springframework.domain.Document;
import guru.springframework.domain.mot_cle;
import guru.springframework.repositories.CategorieRepository;
import guru.springframework.repositories.DirectoryRepository;
import guru.springframework.repositories.DocumentRepository;
import guru.springframework.services.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping(value = "/Documents")
public class DocumentController {
    private final DocumentRepository documentRepository;
    private final CategorieRepository categorieRepository;
    private final StorageService service;

    @PersistenceContext
    private EntityManager em;
    private final DirectoryRepository directoryRepository;

    @Autowired
    public DocumentController(DocumentRepository documentRepository, CategorieRepository categorieRepository, StorageService service, DirectoryRepository directoryRepository) {

        this.documentRepository = documentRepository;
        this.categorieRepository = categorieRepository;
        this.service = service;
        this.directoryRepository = directoryRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    //description = "Get all documents information from the database"
    public ModelAndView showDocuments() {
        List<Document> documents = documentRepository.findAll();
        return (showDocs(documents));
    }

    @RequestMapping(value = "/gestionnaire/create", method = RequestMethod.GET)
    //description = "Get all categories information from the database"
    public ModelAndView documentForm() {
        List<Categorie> categories = categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        Log log = LogFactory.getLog(UploadController.class);
        params.put("categories", categories);
        List<Directory> directories = directoryRepository.findAll();
        params.put("directories1", directories);
        List<Document> documents = documentRepository.findAll();
        params.put("anciensdocs", documents);
        return new ModelAndView("ged/document_formulaire", params);
    }

    /*  @RequestMapping(value = "/create", method = RequestMethod.POST)
  //    description = "Create a document and save it to the database"
      public List<Document> create(@RequestBody Document document) {
          documentRepository.save(document);
          return documentRepository.findAll();
      }
  */
    @RequestMapping(value = "/gestionnaire/delete/{id}", method = RequestMethod.GET)
    public RedirectView remove(@PathVariable int id) {
        documentRepository.delete(documentRepository.findByidobj(id));
        return new RedirectView( "/Documents/all");
    }

    @RequestMapping(value = "/gestionnaire/directories", method = RequestMethod.GET)
    public ModelAndView listerReps() {
        Map<String, Object> params = new HashMap<>();
        List<File> files = service.listDirectories(service.getRoot().toString(), new ArrayList<>());
        params.put("directories", files);
        return new ModelAndView("document_formulaire", params);
    }

    @RequestMapping(value = "/byCategorie", method = RequestMethod.POST)
    //description = "search documents with the provided idcat from the database"
    public ModelAndView SearchDocsByCat(@RequestParam(name = "CategorieSelection", required = false) String CategorieSelection) {
        List<Document> documents = documentRepository.findByidcat(CategorieSelection);
        return (showDocs(documents));
    }

    @RequestMapping(value = "/byCategorieL/{CategorieSelection}", method = RequestMethod.GET)
    //description = "search documents with the provided idcat from the database"
    public ModelAndView ShowDocsByCat(@PathVariable String CategorieSelection) {
        List<Document> documents = documentRepository.findByidcat(CategorieSelection);
        return (showDocsL(documents));
    }


    @RequestMapping(value = "/byKeywords", method = RequestMethod.POST)
    public ModelAndView test(@RequestParam(name = "keywords", required = false) String keywords) {
        String[] decoupe = keywords.split(" ");
        List<String> keywordsL = new ArrayList<>();
        for (int i = 0; i < decoupe.length; i++) {
            keywordsL.add(decoupe[i]);
        }
        String varreq = keywordsL.get(0) + "";
        int i;
        if (keywordsL.size() > 2) {
            for (i = 1; i < keywordsL.size() - 1; i++) {
                varreq = varreq + "', '" + keywordsL.get(i) + "";
            }
        }
        if (keywordsL.size() != 1) {
            varreq = varreq + "', '" + keywordsL.get(keywordsL.size() - 1) + "";
        }
        Query q = em.createNativeQuery("select d.* as document from ged.document d join" +
                " ged.indexe p on d.idobj = p.idobj join ged.mot_cle mc" +
                " on mc.idmotcle=p.idmotcle" +
                " where mc.motcle in ('" + varreq + "')" +
                "group by d.idobj order by count(*) desc;", Document.class);
        List<Document> documents = new ArrayList<>();
        if (q.getResultList() != null)
            documents = (List<Document>) q.getResultList();
        return (showDocs(documents));
    }

    @RequestMapping(value = "/gestionnaire/statistics", method = RequestMethod.GET)
    public ModelAndView statistiques() {
        Map<String, Object> params = new HashMap<>();
        Map<String, Integer> surveyMapj = new LinkedHashMap<>();
        surveyMapj = resultStats("Consultation", "jour");
        Map<String, Integer> surveyMaps = resultStats("Consultation", "semaine");
        Map<String, Integer> surveyMapm = resultStats("Consultation", "mois");
        Map<String, Integer> surveyMapa = resultStats("Consultation", "annee");
        params.put("surveyMapj", surveyMapj);
        params.put("surveyMaps", surveyMaps);
        params.put("surveyMapm", surveyMapm);
        params.put("surveyMapa", surveyMapa);
        List<Categorie> categories=categorieRepository.findAll();
        params.put("categories",categories);
        return new ModelAndView("ged/barGraph", params);
    }

    private Map<String, Integer> resultStats(String type, String delai) {
        Date date1 = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        GregorianCalendar calendar1 = new GregorianCalendar();
        if (delai.equals("jour")) {
            calendar1 = new GregorianCalendar(
                    date1.getYear() + 1900,
                    date1.getMonth(),
                    date1.getDate()
            );
        }
        if (delai.equals("semaine")) {
            calendar1 = new GregorianCalendar(
                    date1.getYear() + 1900,
                    date1.getMonth(),
                    date1.getDate() - 7
            );

        }
        if (delai.equals("mois")) {
            calendar1 = new GregorianCalendar(
                    date1.getYear() + 1900,
                    date1.getMonth() - 1,
                    date1.getDate()
            );
        }
        if ((delai.equals("annee"))) {
            calendar1 = new GregorianCalendar(
                    date1.getYear() + 1900 - 1,
                    date1.getMonth(),
                    date1.getDate()
            );
        }
        Date date2 = calendar1.getTime();
        String typeconsultation = type;
        Query q = em.createNativeQuery("select d.idobj, count(d.idobj) from ged.Consultation c join ged.document d on" +
                " d.idobj = c.idobj where c.dateconsultation between '" + date2 + "' and '" + date1 + "'" +
                "and c.typeconsultation = '" + typeconsultation + "'" +
                "group by d.idobj order by count(d.idobj) desc;");

        List<Object[]> list = q.getResultList();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<String, Integer> surveyMap = new LinkedHashMap<>();
        for (Object[] result : list) {
            surveyMap.put(result[0].toString(), Integer.parseInt(result[1].toString()));
        }
        return surveyMap;
    }

    private ModelAndView showDocs(List<Document> documents) {

        List<Categorie> categories1 = categorieRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("documents", documents);
        params.put("categories1", categories1);
        return new ModelAndView("ged/Documents_list", params);
    }

    private ModelAndView showDocsL(List<Document> documents) {

        Map<String, Object> params = new HashMap<>();
        params.put("documents", documents);
        params.put("categories1", null);
        return new ModelAndView("ged/Documents_list", params);
    }

    @GetMapping("/gestionnaire/modifier/{idobj}")
    public ModelAndView updateDoc(@PathVariable int idobj) {
        List<Categorie> categories = categorieRepository.findAll();
        Document doc = documentRepository.findByidobj(idobj);
        Map<String, Object> params = new HashMap<>();
        Log log = LogFactory.getLog(UploadController.class);
        params.put("categories", categories);
        List<mot_cle> mot_cles = doc.getMots_cles();
        StringBuilder mot_clesS = new StringBuilder();
        for (int i = 0; i < mot_cles.size(); i++) {
            mot_clesS.append(mot_cles.get(i).getMotcle()).append(" ");
        }
        params.put("document", doc);
        params.put("mot_cles", mot_clesS);
        List<Directory> directories = directoryRepository.findAll();
        params.put("directories1", directories);
        return new ModelAndView("ged/Document_update", params);
    }
}
