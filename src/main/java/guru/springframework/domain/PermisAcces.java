package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PermisAcces", schema = "GED")
public class PermisAcces {
    @Id
    @Column(name = "codePermis")
    private String codePermis;
    @Column(name = "iddemande")
    private int iddemande;
    @Column(name = "iddemandeur")
    private long iddemandeur;
    @Column(name = "chemin")
    private String chemin;
    @Column(name = "valide")
    private String valide;
    @Column(name = "nature")
    private String nature; //scientifique ou commerciale
    @Column(name = "type")
    private String type;

    public PermisAcces() {
    }

    public PermisAcces(String codePermis, int id_demande, String chemin) {
        this.codePermis = codePermis;
        this.iddemande = id_demande;
        this.chemin = chemin;
    }

    public PermisAcces(String codePermis, int id_demande, String chemin, String valide, String nature, String type) {
        this.codePermis = codePermis;
        this.iddemande = id_demande;
        this.chemin = chemin;
        this.valide = valide;
        this.nature = nature;
        this.type = type;
    }

    public int getIddemande() {
        return iddemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public long getIddemandeur() {
        return iddemandeur;
    }

    public void setIddemandeur(long  iddemandeur) {
        this.iddemandeur = iddemandeur;
    }

    public String getCodePermis() {
        return codePermis;
    }

    public void setCodePermis(String codePermis) {
        this.codePermis = codePermis;
    }

    public int getId_demande() {
        return iddemande;
    }

    public void setId_demande(int id_demande) {
        this.iddemande = id_demande;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getValide() {
        return valide;
    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
