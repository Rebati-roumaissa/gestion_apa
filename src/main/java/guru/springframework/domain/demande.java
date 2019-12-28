package guru.springframework.domain;

import javax.persistence.*;

@Entity
@Table(name = "Demande", schema = "GED")
public class demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddemande")
    private int iddemande;
    private String etatDemande;
    private String typeDemande;
    private String descriptionActivites;
    private String Objectifs;
    private String applicationsEnvisagees;
    private String commentaire;
    private String cheminDossierDemande;
    @ManyToOne
    @JoinColumn
    private demandeur demandeur;
    @ManyToOne
    @JoinColumn
    private Utilisateur utilisateur;
    public demande() {
    }

    public demande(String etatDemande, String typeDemande) {
        this.etatDemande = etatDemande;
        this.typeDemande = typeDemande;
    }

    public demande(String descriptionActivites, String Objectifs, String applicationsEnvisagees) {
        this.descriptionActivites = descriptionActivites;
        this.Objectifs = Objectifs;
        this.applicationsEnvisagees = applicationsEnvisagees;

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(String etatDemande) {
        this.etatDemande = etatDemande;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public int getIddemande() {
        return iddemande;
    }

    public String getApplicationsEnvisagees() {
        return applicationsEnvisagees;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getDescriptionActivites() {
        return descriptionActivites;
    }

    public String getObjectifs() {
        return Objectifs;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public void setApplicationsEnvisagees(String applicationsEnvisagees) {
        this.applicationsEnvisagees = applicationsEnvisagees;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDescriptionActivites(String descriptionActivites) {
        this.descriptionActivites = descriptionActivites;
    }

    public void setObjectifs(String objectifs) {
        Objectifs = objectifs;
    }

    public String getCheminDossierDemande() {
        return cheminDossierDemande;
    }

    public void setCheminDossierDemande(String cheminDossierDemande) {
        this.cheminDossierDemande = cheminDossierDemande;
    }

    public demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur( demandeur demandeur) {
        this.demandeur = demandeur;
    }
}