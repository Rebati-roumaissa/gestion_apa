package guru.springframework.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Dossier", schema = "GED")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddos")
    private int iddos;
    private String typeDemande;
    @ManyToMany
    @JoinTable(
            name = "Exigee",
            schema = "GED",
            joinColumns = @JoinColumn(name = "iddos", referencedColumnName = "iddos"),
            inverseJoinColumns = @JoinColumn(name = "idpreuve", referencedColumnName = "idpreuve"))
    private List<Preuve> preuves;

    public Dossier() {
    }

    public Dossier(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public int getIddos() {
        return iddos;
    }

    public void setIddos(int iddos) {
        this.iddos = iddos;
    }

    public List<Preuve> getPreuves() {
        return preuves;
    }

    public void setPreuves(List<Preuve> preuves) {
        this.preuves = preuves;
    }

    public void addPreuve(Preuve preuve) {
        this.preuves.add(preuve);
    }

}
