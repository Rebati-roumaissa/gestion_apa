package guru.springframework.controllers;


import guru.springframework.domain.*;
import guru.springframework.repositories.*;
import guru.springframework.services.storage.StorageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Entry point into the web service.
 * <p>
 * Using @RestController, so no need to autowire anything.
 */
@RestController
@RequestMapping(value = "")
public class UploadController {

    private final Log log = LogFactory.getLog(UploadController.class);
    private final DocumentRepository repository;
    private final demandeRepository demandeRepository;
    private final StorageService service;
    private final motCleRepository motCleRepository;
    private final DirectoryRepository directoryRepository;
    private final ConsultationRepository consultationRepository;
    private final PermisRepository permisRepository;

    /**
     * Required if I want the UploadRespository to be autowired.
     *
     * @param repository
     * @param service
     * @param directoryRepository
     */
    public UploadController(DocumentRepository repository, motCleRepository motCleRepository,
                            StorageService service, demandeRepository demandeRepository,
                            DirectoryRepository directoryRepository, PermisRepository permisRepository,
                            ConsultationRepository consultationRepository) {
        this.repository = repository;
        this.motCleRepository = motCleRepository;
        this.service = service;
        this.demandeRepository = demandeRepository;
        this.directoryRepository = directoryRepository;
        this.consultationRepository = consultationRepository;
        this.permisRepository = permisRepository;
    }

//public UploadController(){}

    /**
     * Landing Page/ Welcome Message
     *
     * @return String greeting
     */
    @RequestMapping(method = GET, produces = "application/json")
    public Resource<String> greeting() {
        log.info("greeting has been accessed");
        return new Resource<>("Hello There! Welcome to the Upload Management System.\n\tBuilt By Jaya Kasa.");
    }

    /**
     * Method for getting a list of all files
     *
     * @return Collection containing record of all documents
     */
    @RequestMapping(value = "/all", method = GET)
    public Resources<Document> findAll() {
        log.info("findAll");
        Collection<Document> out = (Collection<Document>) repository.findAll();
        return new Resources<>(out);
    }

    /**
     * Method for uploading a file
     */
    @RequestMapping(value = "/document", method = POST)
    public @ResponseBody
    RedirectView documentSubmit(Document document, mot_cle motCle, Categorie categorie,
                          @RequestParam(value = "file") MultipartFile file, @RequestParam(name = "CategorieSelection", required = false) String CategorieSelection) throws IOException {
        service.store(directoryRepository.findByIddirectory(document.getDoss_parent()).getChemin(), file);
        document.setIdcat(CategorieSelection);
        document.setNomobj(file.getOriginalFilename());
        document.setId_type(file.getContentType());
        String[] decoupe = motCle.getMotcle().split(" ");
        List<mot_cle> motcles = new ArrayList<>();
        for (int i = 0; i < decoupe.length; i++) {
            mot_cle motcle1 = new mot_cle();
            int newId;
            if (!motCleRepository.existsByMotcle(decoupe[i])) {
                motcle1 = new mot_cle(decoupe[i]);
                mot_cle motcle2 = motCleRepository.save(motcle1);
                motcles.add(motcle2);
            } else {
                motcle1 = motCleRepository.findBymotcle(decoupe[i]);
                motcles.add(motcle1);
            }
            document.setMots_cles(motcles);
            Document out = repository.save(document);
        }
        return new RedirectView( "/Documents/all");
    }
   
    @RequestMapping(value = "/documentup", method = POST)
    public @ResponseBody
    RedirectView documentupdate(Document document, mot_cle motCle, Categorie categorie,
    @RequestParam(name = "CategorieSelection", required = false) String CategorieSelection) throws IOException {
    if (document.getIdobj()!=0)
    {
    Document document1= repository.findByidobj(document.getIdobj());
    document1.setIdcat(CategorieSelection);
    String[] decoupe = motCle.getMotcle().split(" ");
    List<mot_cle> motcles = new ArrayList<>();
    for (int i = 0; i < decoupe.length; i++) {
    mot_cle motcle1 = new mot_cle();
    int newId;
    if (!motCleRepository.existsByMotcle(decoupe[i])) {
    motcle1 = new mot_cle(decoupe[i]);
    mot_cle motcle2 = motCleRepository.save(motcle1);
    motcles.add(motcle2);
    } else {
    motcle1 = motCleRepository.findBymotcle(decoupe[i]);
    motcles.add(motcle1);
    }
    document1.setMots_cles(motcles);
    Document out = repository.save(document1);
    }

    }
    return new RedirectView( "/Documents/all");
    } 
    
    //  @RequestMapping(value = "/uploadMultipleFiles", method =RequestMethod.POST)
    public String uploadMultipleFiles(String codedemande, ArrayList<String> descriptions, ArrayList<MultipartFile> files) {
        if (files.size() != descriptions.size())
            return "Mismatching no of files are equal to description";
        String status = "";
        for (int i = 0; i < files.size(); i++) {
            String UPLOADED_FOLDER = "uploads/demandes/" + codedemande;
            MultipartFile file = files.get(i);
            String description = descriptions.get(i);
            UPLOADED_FOLDER += "/" + description;
            File dir = new File(UPLOADED_FOLDER);
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
        }
        File dir = new File("uploads/demandes/" + codedemande);
        if (!dir.exists())
            dir.mkdirs();
        return dir.getPath();
    }

    /**
     * Method for searching for file information by fileid
     *
     * @param id
     * @return Upload object, else HttpStatus.NotFound
     */
    @RequestMapping(value = "/details/{id}", method = GET)
    public Resource<?> findByID(@PathVariable(value = "id") int id) {
        log.info("findByID " + id);
        Document out = repository.findByidobj(id);
        if (out == null) return new Resource<>(HttpStatus.NOT_FOUND);
        return new Resource<>(out);
    }

    /**
     * Method to get file details by filename
     *
     * @param filename
     * @return upload object, else HttpStatus.NotFound
     */
    @RequestMapping(value = "/details/filename/{filename}/", method = GET)
    public Resource<?> findByFilename(@PathVariable(value = "filename") String filename) {
        log.info("findByFilename: " + filename);
        Document out = repository.findByNomobj(filename).orElse(null);
        if (out == null) return new Resource<>(HttpStatus.NOT_FOUND);
        return new Resource<>(out);
    }

    /**
     * Method for getting file stream by fileid
     *
     * @param id
     * @return InputStream if file exists
     */
    @RequestMapping(value = "/stream/{id}", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> streamByID(@PathVariable(value = "id") int id) {
        Document out = repository.findByidobj(id);
        if (out == null) return (ResponseEntity<org.springframework.core.io.Resource>) ResponseEntity.notFound();
        Directory d = directoryRepository.findByIddirectory(out.getDoss_parent());
        org.springframework.core.io.Resource file = service.loadAsResource(/*out.getFilename()*/d.getChemin() + File.separator + out.getNomobj());
        return ResponseEntity.
                ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + out.getFilename() + "\"").
                body(file);
    }

    /**
     * Method for getting file stream by fileid
     *
     * @param fileid
     * @return InputStream if file exists
     */
    @RequestMapping(value = "/stream/fileid/{fileid}/", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> streamByFilename(@PathVariable(value = "fileid") int fileid) {
        // Document out = repository.findByNomobj(filename).get();
        Document out = repository.findByidobj(fileid);
        if (out == null) return (ResponseEntity<org.springframework.core.io.Resource>) ResponseEntity.notFound();
        Directory d = directoryRepository.findByIddirectory(out.getDoss_parent());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Consultaion consultaion = new Consultaion(fileid, "Telechargements", date/*formatter.format(date)*/);
        consultationRepository.save(consultaion);
        out.setNbTelechargements(out.getNbTelechargements() + 1);
        repository.save(out);
        org.springframework.core.io.Resource file = service.loadAsResource(/*out.getFilename()*/d.getChemin() + File.separator + out.getNomobj());
        //   org.springframework.core.io.Resource file = service.loadAsResource(out.getFilename());

        return ResponseEntity.
                ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + out.getFilename() + "\"").
                body(file);
    }

    @RequestMapping(value = "/stream/open/{fileid}/", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> streamOpenByFilename(@PathVariable(value = "fileid") int fileid) {
        //  log.info("streamByFilename " + filename);
        // Document out = repository.findByNomobj(filename).get();
        Document out = repository.findByidobj(fileid);
        if (out == null) return (ResponseEntity<org.springframework.core.io.Resource>) ResponseEntity.notFound();
        File file1 = new File(out.getFilename());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Consultaion consultaion = new Consultaion(fileid, "Consultation", date/*formatter.format(date)*/);
        consultationRepository.save(consultaion);
        out.setNbLectures(out.getNbLectures() + 1);
        repository.save(out);
        Directory d = directoryRepository.findByIddirectory(out.getDoss_parent());
        org.springframework.core.io.Resource file = service.loadAsResource(/*out.getFilename()*/d.getChemin() + File.separator + out.getNomobj());
//        org.springframework.core.io.Resource file = service.loadAsResource(out.getFilename());
        return ResponseEntity.
                ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + out.getFilename() + "\"")
                //       .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file1)))
                .body(file);
    }
    @RequestMapping(value = "/stream/openPermis/{permisid}/", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> streamOpenByPermisId(@PathVariable(value = "permisid") String permis) {
        PermisAcces permisAcces= permisRepository.findByCodePermis(permis);
        if (permisAcces == null) return (ResponseEntity<org.springframework.core.io.Resource>) ResponseEntity.notFound();
        File file1 = new File(permisAcces.getChemin());
        org.springframework.core.io.Resource file = service.loadAsResource(permisAcces.getChemin());
        return ResponseEntity.
                ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + permisAcces.getChemin() + "\"")
                //       .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file1)))
                .body(file);
    }

    @RequestMapping(value = "/open/{position}/{demande}", method = GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<org.springframework.core.io.Resource> streamOpenFile(@PathVariable(value = "position") int position,
                                                                               @PathVariable(value = "demande") int demande) {
        demande demande1 = demandeRepository.findByIddemande(demande);
        List<File> files = service.listFiles(demande1.getCheminDossierDemande(), new ArrayList<>());
        PermisAcces permisAcces = permisRepository.findByIddemande(demande1.getIddemande());
        if ((permisAcces != null)) files.add(new File(permisAcces.getChemin()));
        org.springframework.core.io.Resource file = service.loadAsResource(files.get(position).getAbsolutePath());
        return ResponseEntity.
                ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + files.get(position).getName() + "\"")
                //.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType((File) file)))
                .body(file);
    }

    /**
     * This bean is necessary for the entire program to run.
     * It creates the uploads directory where the files will be stored
     * It also deletes any existing files in the directory on start. SO BE CAREFUL
     *
     * @param service
     * @return
     */
    @Bean
    CommandLineRunner init(StorageService service) {
        return (args -> {
            //   service.deleteAll();
//            service.init();
        });
    }
}

