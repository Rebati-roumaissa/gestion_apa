package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorie", schema = "GED")
public class Categorie {
    @Id
    private String idcat;
    private String libcat;

    public Categorie() {
    }

    public Categorie(String Categorie) {
        this.libcat = Categorie;
    }

    public Categorie(String id_cat, String Categorie) {
        this.idcat = id_cat;
        this.libcat = Categorie;
    }

    public String getIdcat() {
        return idcat;
    }

    public String getLibcat() {
        return libcat;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }

    public void setLibcat(String libcat) {
        this.libcat = libcat;
    }
}
