package guru.springframework.domain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Document", schema = "GED")
public class Document extends ResourceSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idobj")
    private int idobj;
    @ManyToMany
    @JoinTable(
            name = "indexe",
            schema = "GED",
            joinColumns = @JoinColumn(name = "idobj", referencedColumnName = "idobj"),
            inverseJoinColumns = @JoinColumn(name = "idmotcle", referencedColumnName = "idmotcle"))
    private List<mot_cle> mots_cles;
    private String nomobj;
    private String titreobj;
    private boolean modified;
    private boolean deleted;
    private String datecreation;
    private int doss_parent;
    private int taille;
    private int num_version;
    private String id_lang;
    private String id_type;
    private String idcat;
    private String resume;
    @Column(name = "nbTelechargements")
    private int nbTelechargements;
    @Column(name = "nbLectures")
    private int nbLectures;
    @Column(name = "doclie")
    private int doclie;

    public Document() {
    }

    public Document(int idobj, String nomobj, String titreobj) {
        this.idobj = idobj;
        this.nomobj = nomobj;
        this.titreobj = titreobj;
    }

    public Document(int idobj, String nomobj, String titreobj, boolean modified, boolean deleted, String datecreation, int doss_parent,
                    int taille, int num_version, String id_lang, String id_type) {
        this.idobj = idobj;
        this.nomobj = nomobj;
        this.titreobj = titreobj;
        this.modified = modified;
        this.deleted = deleted;
        this.datecreation = datecreation;
        this.doss_parent = doss_parent;
        this.taille = taille;
        this.num_version = num_version;
        this.id_lang = id_lang;
        this.id_type = id_type;
    }

    public Document(int idobj, String nomobj, String titreobj, String datecreation, int doss_parent,
                    int taille, int num_version, String id_lang, String id_type) {
        this.idobj = idobj;
        this.nomobj = nomobj;
        this.titreobj = titreobj;
        this.datecreation = datecreation;
        this.doss_parent = doss_parent;
        this.taille = taille;
        this.num_version = num_version;
        this.id_lang = id_lang;
        this.id_type = id_type;
    }

    public Document(int idobj, String nomobj, String titreobj, String datecreation, int doss_parent,
                    int taille, int num_version, String id_lang, String id_type, String resume) {
        this.idobj = idobj;
        this.nomobj = nomobj;
        this.titreobj = titreobj;
        this.datecreation = datecreation;
        this.doss_parent = doss_parent;
        this.taille = taille;
        this.num_version = num_version;
        this.id_lang = id_lang;
        this.id_type = id_type;
        this.resume = resume;
    }

    public int getDoclie() {
        return doclie;
    }

    public void setDoclie(int doclie) {
        this.doclie = doclie;
    }

    public String getFilename() {
        return this.nomobj;
    }

    public int getIdobj() {
        return idobj;
    }

    public String getNomobj() {
        return nomobj;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isModified() {
        return modified;
    }

    public String getTitreobj() {
        return titreobj;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public int getDoss_parent() {
        return doss_parent;
    }

    public String getId_lang() {
        return id_lang;
    }

    public int getNum_version() {
        return num_version;
    }

    public int getTaille() {
        return taille;
    }

    public String getId_type() {
        return id_type;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public void setIdobj(int idobj) {
        this.idobj = idobj;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setId_lang(String id_lang) {
        this.id_lang = id_lang;
    }

    public void setNomobj(String nomobj) {
        this.nomobj = nomobj;
    }

    public void setDoss_parent(int doss_parent) {
        this.doss_parent = doss_parent;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public void setTitreobj(String titreobj) {
        this.titreobj = titreobj;
    }

    public void setNum_version(int num_version) {
        this.num_version = num_version;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public String getIdcat() {
        return idcat;
    }

    public List<mot_cle> getMots_cles() {
        return mots_cles;
    }

    public void setMots_cles(List<mot_cle> mots_cles) {
        this.mots_cles = mots_cles;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getNbLectures() {
        return nbLectures;
    }

    public int getNbTelechargements() {
        return nbTelechargements;
    }

    public void setNbTelechargements(int nbTelechargements) {
        this.nbTelechargements = nbTelechargements;
    }

    public void setNbLectures(int nbLectures) {
        this.nbLectures = nbLectures;
    }
}
