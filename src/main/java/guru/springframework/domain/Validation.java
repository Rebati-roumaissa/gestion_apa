package guru.springframework.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Validation", schema = "GED")
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvalidation")
    private int idvalidation;
    private int iddemande;
    private long iduser;
    private Date datevalidation;
    private String decision;
    public Validation(){}

    public Validation(int iddemande, long iduser, Date datevalidation, String decision) {
        this.iddemande = iddemande;
        this.iduser = iduser;
        this.datevalidation = datevalidation;
        this.decision=decision;
    }

    public int getIdvalidation() {
        return idvalidation;
    }

    public void setIdvalidation(int idvalidation) {
        this.idvalidation = idvalidation;
    }

    public int getIddemande() {
        return iddemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public Date getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Date datevalidation) {
        this.datevalidation = datevalidation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
