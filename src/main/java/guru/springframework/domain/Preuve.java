package guru.springframework.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Preuve", schema = "GED")
public class Preuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpreuve")
    int idpreuve;
    String libpreuve;
    @ManyToMany
    @JoinTable(
            name = "Exigee",
            schema = "GED",
            joinColumns = @JoinColumn(name = "idpreuve", referencedColumnName = "idpreuve"),
            inverseJoinColumns = @JoinColumn(name = "iddos", referencedColumnName = "iddos"))
    private List<Dossier> dossiers;

    public Preuve() {
    }

    public Preuve(String libpreuve) {
        this.libpreuve = libpreuve;
    }

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public int getIdpreuve() {
        return idpreuve;
    }

    public String getLibpreuve() {
        return libpreuve;
    }

    public void setIdpreuve(int idpreuve) {
        this.idpreuve = idpreuve;
    }

    public void setLibpreuve(String libpreuve) {
        this.libpreuve = libpreuve;
    }
}
