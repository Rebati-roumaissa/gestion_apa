package guru.springframework.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Table(schema = "ressource")

@Entity 
public class RessourceUtilisateur implements Serializable {

 

    @Id
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
 
    @Id
    @ManyToOne
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    private Long modifiateur;
    
    private Long validateur;


      public RessourceUtilisateur(){
        }

 
    private Date date_Modif;

 
    private Date date_valid;
    private Date date_ajout;
    

    public void setModifiateur(Long modif){
       this.modifiateur=modif;
    }

    public Long getModifiateur(){
        return this.modifiateur;
    }
    
    public void setDate_ajout(Date date_ajout){
       this.date_ajout=date_ajout;
    }

     public Date getDate_ajout(){
     return this.date_ajout;
    }
     
     public void setDate_valid(Date date_valid){
            this.date_valid=date_valid;

     }

     public void setDate_modif(Date date_modif){

        this.date_Modif=date_modif;

      }

     /**
      * @return the date_Modif
      */
     public Date getDate_Modif() {
         return date_Modif;
     }
     
     /**
      * @return the date_valid
      */
     public Date getDate_valid() {
         return date_valid;
     }

    
     public RessourceUtilisateur(Ressource ressource,Utilisateur utilisateur,Date date_ajout){
         this.ressource=ressource;
         this.utilisateur=utilisateur;
         this.date_ajout=date_ajout;
        }


  
     public Long getValidateur(){
               return this.validateur;
     }

     /**
      * @param validateur the validateur to set
      */
     public void setValidateur(Long validateur) {
         this.validateur = validateur;
     }
     

}