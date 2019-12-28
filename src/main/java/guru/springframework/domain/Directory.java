package guru.springframework.domain;

import javax.persistence.*;

@Entity
@Table(name = "Directory", schema = "GED")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddirectory")
    private int iddirectory;
    private String titre;
    private String chemin;
    private int idparent;

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }
/* @OneToMany(mappedBy = "Directory", cascade = CascadeType.ALL)
    private List<Document> documents = new ArrayList<>();
*/

    public Directory() {
    }

    public Directory(String titre, String chemin) {
        this.titre = titre;
        this.chemin = chemin;
    }

    public int getIddirectory() {
        return iddirectory;
    }

    public void setIddirectory(int iddirectory) {
        this.iddirectory = iddirectory;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

  /*  public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> directories) {
        this.documents = directories;
    }*/
}
