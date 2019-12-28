package guru.springframework.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mot_cle", schema = "GED")
public class mot_cle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idmotcle")
    private int idmotcle;
    @ManyToMany
    @JoinTable(
            name = "indexe",
            schema = "GED",
            joinColumns = @JoinColumn(name = "idmotcle", referencedColumnName = "idmotcle"),
            inverseJoinColumns = @JoinColumn(name = "idobj", referencedColumnName = "idobj"))
    private List<Document> documents;
    private String motcle;

    public mot_cle() {
    }

    public mot_cle(String motcle) {
        this.motcle = motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public void setIdmotcle(int idmotcle) {
        this.idmotcle = idmotcle;
    }

    public String getMotcle() {
        return motcle;
    }

    public int getIdmotcle() {
        return idmotcle;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
