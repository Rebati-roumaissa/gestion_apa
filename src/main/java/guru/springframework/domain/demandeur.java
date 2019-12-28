package guru.springframework.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demandeur", schema = "GED")
public class demandeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddemandeur")
    private int iddemandeur;
 //   private String nom;
    @Column(name = "typedemandeur")
    private String typeDemandeur;
  /*  private String prenom;
    private String telephone;
    private String email;*/
    private String raisonSociale;
    private String formeJuridique;
    private String adresse;
    @Column(name = "username")
    private String username;
   /* @Column(name = "Nss")
    private long Nss;*/
    @Column(name = "Nif")
    private long Nif;
    @OneToMany(mappedBy = "demandeur", cascade = CascadeType.ALL)
    private List<demande> demandes = new ArrayList<>();

    public demandeur() {

    }

   /* public demandeur(String nom, String prenom, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
    }*/

    public demandeur(String raisonSociale, String formeJuridique, String adresse) {
        this.formeJuridique = formeJuridique;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
/*
    public long getNss() {
        return Nss;
    }

    public void setNss(long nss) {
        Nss = nss;
    }
*/
    public long getNif() {
        return Nif;
    }

    public void setNif(long nif) {
        Nif = nif;
    }

    public String getTypeDemandeur() {
        return typeDemandeur;
    }

    public void setTypeDemandeur(String typeDemandeur) {
        this.typeDemandeur = typeDemandeur;
    }

    public List<demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<demande> demandes) {
        this.demandes = demandes;
    }

    public void addDemande(demande demande) {
        this.demandes.add(demande);
    }

    public int getIddemandeur() {
        return iddemandeur;
    }

  /*  public String getEmail() {
        return email;
    }
*/
    public String getAdresse() {
        return adresse;
    }

  /*  public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
*/
    public String getFormeJuridique() {
        return formeJuridique;
    }

    public void setIddemandeur(int iddemandeur) {
        this.iddemandeur = iddemandeur;
    }

  /*  public String getTelephone() {
        return telephone;
    }*/

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
/*
    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
*/
    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

  /*  public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
*/
    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

  /*  public void setTelephone(String telephone) {
        this.telephone = telephone;
    }*/
}
