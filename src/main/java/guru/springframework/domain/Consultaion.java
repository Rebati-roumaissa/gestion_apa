package guru.springframework.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Consultation", schema = "GED")
public class Consultaion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idconsultation")
   private int idconsultation;
    private int idobj;
    private String typeconsultation;
    private Date dateconsultation;

   public Consultaion(){}
    public Consultaion(int idobj, String typeconsultation, Date dateconsultation) {
        this.idobj = idobj;
        this.typeconsultation = typeconsultation;
        this.dateconsultation = dateconsultation;
    }
    public int getIdconsultation() {
        return idconsultation;
    }

    public void setIdconsultation(int idconsultation) {
        this.idconsultation = idconsultation;
    }

    public int getIdobj() {
        return idobj;
    }

    public void setIdobj(int idobj) {
        this.idobj = idobj;
    }

    public String getTypeconsultation() {
        return typeconsultation;
    }

    public void setTypeconsultation(String typeconsultation) {
        this.typeconsultation = typeconsultation;
    }

    public Date getDateconsultation() {
        return dateconsultation;
    }

    public void setDateconsultation(Date dateconsultation) {
        this.dateconsultation = dateconsultation;
    }

}
