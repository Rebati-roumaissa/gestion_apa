package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "GED")
public class InfoPermis {
    @Id
    @Column(name = "iddemande")
    private int iddemande;
    private String nom;
    private String prenom;
    private String typeDemandeur;
    private long nss;
    private String email;
    private String telephone;
    private String raisonSociale;
    private String formeJuridique;
    private long nif;
    private String adresse;
    private String scientifiqueNom;
    private String zones;
    private String cta;
    private String quantite;
    private String periode;
    private String typeAcces;
    private String descriptionActivites;
    private String Objectifs;
    private String applicationsEnvisagees;
    private String moyensTransport;
    private String itineraires;
    private String entSort;
    private String commentaire;
    private String datedebut;
    private String datefin;
    private String codePermis;
    public InfoPermis() {

    }

    public String getCodePermis() {
        return codePermis;
    }

    public void setCodePermis(String codePermis) {
        this.codePermis = codePermis;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public void setObjectifs(String objectifs) {
        Objectifs = objectifs;
    }

    public void setDescriptionActivites(String descriptionActivites) {
        this.descriptionActivites = descriptionActivites;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public void setNss(long nss) {
        this.nss = nss;
    }

    public void setTypeDemandeur(String typeDemandeur) {
        this.typeDemandeur = typeDemandeur;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public void setScientifiqueNom(String scientifiqueNom) {
        this.scientifiqueNom = scientifiqueNom;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public void setApplicationsEnvisagees(String applicationsEnvisagees) {
        this.applicationsEnvisagees = applicationsEnvisagees;
    }

    public void setEntSort(String entSort) {
        this.entSort = entSort;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setTypeAcces(String typeAcces) {
        this.typeAcces = typeAcces;
    }

    public void setMoyensTransport(String moyensTransport) {
        this.moyensTransport = moyensTransport;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTypeDemandeur() {
        return typeDemandeur;
    }

    public long getNss() {
        return nss;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public String getFormeJuridique() {
        return formeJuridique;
    }

    public long getNif() {
        return nif;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getScientifiqueNom() {
        return scientifiqueNom;
    }

    public String getZones() {
        return zones;
    }

    public String getCta() {
        return cta;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getPeriode() {
        return periode;
    }

    public String getTypeAcces() {
        return typeAcces;
    }

    public String getDescriptionActivites() {
        return descriptionActivites;
    }

    public String getObjectifs() {
        return Objectifs;
    }

    public String getApplicationsEnvisagees() {
        return applicationsEnvisagees;
    }

    public String getMoyensTransport() {
        return moyensTransport;
    }

    public String getItineraires() {
        return itineraires;
    }

    public String getEntSort() {
        return entSort;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setItineraires(String itineraires) {
        this.itineraires = itineraires;
    }

    public int getIddemande() {
        return iddemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public InfoPermis(String nom, String prenom, String typeDemandeur, int nss, String email, String telephone, String raisonSociale, String formeJuridique, int nif, String adresse, String scientifiqueNom, String zones, String cta, String quantite, String periode, String typeAcces, String descriptionActivites, String objectifs, String applicationsEnvisagees, String moyensTransport, String itineraires, String entSort, String commentaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.typeDemandeur = typeDemandeur;
        this.nss = nss;
        this.email = email;
        this.telephone = telephone;
        this.raisonSociale = raisonSociale;
        this.formeJuridique = formeJuridique;
        this.nif = nif;
        this.adresse = adresse;
        this.scientifiqueNom = scientifiqueNom;
        this.zones = zones;
        this.cta = cta;
        this.quantite = quantite;
        this.periode = periode;
        this.typeAcces = typeAcces;
        this.descriptionActivites = descriptionActivites;
        Objectifs = objectifs;
        this.applicationsEnvisagees = applicationsEnvisagees;
        this.moyensTransport = moyensTransport;
        this.itineraires = itineraires;
        this.entSort = entSort;
        this.commentaire = commentaire;
    }
}
