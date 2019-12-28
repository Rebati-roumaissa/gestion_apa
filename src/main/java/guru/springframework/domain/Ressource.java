package guru.springframework.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;


@Table(schema = "ressource")
@Entity 
@Indexed

public class Ressource implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@NotNull
private String nomcommun;
@NotNull
private String nom_scientifique;
public EtatValidation getEtat() {
	return etat;
}




public void setEtat(EtatValidation etat) {
	this.etat = etat;
}




public List<RessourceCaracteristique> getRessourcecaracteristique() {
	return ressourcecaracteristique;
}




public void setRessourcecaracteristique(List<RessourceCaracteristique> ressourcecaracteristique) {
	this.ressourcecaracteristique = ressourcecaracteristique;
}




public int getNiv_sec_communes() {
	return niv_sec_communes;
}




public void setNiv_sec_communes(int niv_sec_communes) {
	this.niv_sec_communes = niv_sec_communes;
}




private Boolean voie_disparition;
private int population;
private String couleur;
private Double taille;
@Enumerated(EnumType.STRING)
private EtatValidation etat;

private String utilisation;
private String detaille_prelevement;
private String detaille;
private Long parent_id;
public Long getParent_id() {
	return parent_id;
}




@OneToMany(mappedBy="ressource")
private List<Image> images;

private String image;



private Boolean visible;  //?

private int nbr_consultation;

@ManyToMany(targetEntity=Classification.class)
private List<Classification> classifications;

@ManyToMany(targetEntity=Lot.class)
private List<Lot> lots;

@ManyToMany(targetEntity=Connaissance.class)
private List<Connaissance> connaissances;

@ManyToMany(targetEntity=Localisation.class)
private List<Localisation> localisations;

@ManyToMany(targetEntity=Commune.class)
private List<Commune> communes;

@OneToMany(mappedBy="ressource", cascade = CascadeType.ALL)
private List<RessourceCaracteristique> ressourcecaracteristique;

@OneToMany(mappedBy="utilisateur", cascade = CascadeType.ALL)
private List<RessourceUtilisateur> ressourceUtilisateur;


private int niv_sec_nom_commun;
private int niv_sec_nom_scientifique;
private int niv_sec_voie_disparition;
private int niv_sec_population;
private int niv_sec_couleur;
private int niv_sec_taille;
private int niv_sec_utilisation;
private int niv_sec_detaille_prelevement;
private int niv_sec_detaille;
private int niv_sec_visible;
private int niv_sec_nbr_consultation;
private int niv_sec_connaissances;
private int niv_sec_localisations;
private int niv_sec_communes;
private int niv_sec_ressourceCaracteristiques;
private int niv_sec_ressourceUtilisateur;

private int niv_sec_classifications;

public List<Image> getImages() {
	return images;
}




public Ressource(String nom_commun, String nom_scientifique, Boolean voie_disparition, int population, String couleur,
		Double taille, String utilisation, String detaille_prelevement, String detaille, List<Image> images,
		Boolean visible, int nbr_consultation, List<Classification> classifications, List<Lot> lots,
		List<Connaissance> connaissances, List<Localisation> localisations, List<Commune> communes,
		List<RessourceCaracteristique> ressourceCaracteristiques, List<RessourceUtilisateur> ressourceUtilisateur,
		int niv_sec_nom_commun, int niv_sec_nom_scientifique, int niv_sec_voie_disparition, int niv_sec_population,
		int niv_sec_couleur, int niv_sec_taille, int niv_sec_utilisation, int niv_sec_detaille_prelevement,
		int niv_sec_detaille, int niv_sec_visible, int niv_sec_nbr_consultation, int niv_sec_connaissances,
		int niv_sec_localisations, int niv_sec_ommunes, int niv_sec_ressourceCaracteristiques,
		int niv_sec_ressourceUtilisateur) {
	super();
	this.nomcommun = nom_commun;
	this.nom_scientifique = nom_scientifique;
	this.voie_disparition = voie_disparition;
	this.population = population;
	this.couleur = couleur;
	this.taille = taille;
	this.utilisation = utilisation;
	this.detaille_prelevement = detaille_prelevement;
	this.detaille = detaille;
	this.images = images;
	this.visible = visible;
	this.nbr_consultation = nbr_consultation;
	this.classifications = classifications;
	this.lots = lots;
	this.connaissances = connaissances;
	this.localisations = localisations;
	this.communes = communes;
	this.ressourcecaracteristique = ressourceCaracteristiques;
	this.ressourceUtilisateur = ressourceUtilisateur;
	this.niv_sec_nom_commun = niv_sec_nom_commun;
	this.niv_sec_nom_scientifique = niv_sec_nom_scientifique;
	this.niv_sec_voie_disparition = niv_sec_voie_disparition;
	this.niv_sec_population = niv_sec_population;
	this.niv_sec_couleur = niv_sec_couleur;
	this.niv_sec_taille = niv_sec_taille;
	this.niv_sec_utilisation = niv_sec_utilisation;
	this.niv_sec_detaille_prelevement = niv_sec_detaille_prelevement;
	this.niv_sec_detaille = niv_sec_detaille;
	this.niv_sec_visible = niv_sec_visible;
	this.niv_sec_nbr_consultation = niv_sec_nbr_consultation;
	this.niv_sec_connaissances = niv_sec_connaissances;
	this.niv_sec_localisations = niv_sec_localisations;
	this.niv_sec_communes = niv_sec_ommunes;
	this.niv_sec_ressourceCaracteristiques = niv_sec_ressourceCaracteristiques;
	this.niv_sec_ressourceUtilisateur = niv_sec_ressourceUtilisateur;
}




public int getNiv_sec_nom_commun() {
	return niv_sec_nom_commun;
}




public void setNiv_sec_nom_commun(int niv_sec_nom_commun) {
	this.niv_sec_nom_commun = niv_sec_nom_commun;
}




public int getNiv_sec_nom_scientifique() {
	return niv_sec_nom_scientifique;
}




public void setNiv_sec_nom_scientifique(int niv_sec_nom_scientifique) {
	this.niv_sec_nom_scientifique = niv_sec_nom_scientifique;
}




public int getNiv_sec_voie_disparition() {
	return niv_sec_voie_disparition;
}




public void setNiv_sec_voie_disparition(int niv_sec_voie_disparition) {
	this.niv_sec_voie_disparition = niv_sec_voie_disparition;
}




public int getNiv_sec_population() {
	return niv_sec_population;
}




public void setNiv_sec_population(int niv_sec_population) {
	this.niv_sec_population = niv_sec_population;
}




public int getNiv_sec_couleur() {
	return niv_sec_couleur;
}




public void setNiv_sec_couleur(int niv_sec_couleur) {
	this.niv_sec_couleur = niv_sec_couleur;
}




public int getNiv_sec_taille() {
	return niv_sec_taille;
}




public void setNiv_sec_taille(int niv_sec_taille) {
	this.niv_sec_taille = niv_sec_taille;
}




public int getNiv_sec_utilisation() {
	return niv_sec_utilisation;
}




public void setNiv_sec_utilisation(int niv_sec_utilisation) {
	this.niv_sec_utilisation = niv_sec_utilisation;
}




public int getNiv_sec_detaille_prelevement() {
	return niv_sec_detaille_prelevement;
}




public void setNiv_sec_detaille_prelevement(int niv_sec_detaille_prelevement) {
	this.niv_sec_detaille_prelevement = niv_sec_detaille_prelevement;
}




public int getNiv_sec_detaille() {
	return niv_sec_detaille;
}




public void setNiv_sec_detaille(int niv_sec_detaille) {
	this.niv_sec_detaille = niv_sec_detaille;
}




public int getNiv_sec_visible() {
	return niv_sec_visible;
}




public void setNiv_sec_visible(int niv_sec_visible) {
	this.niv_sec_visible = niv_sec_visible;
}




public int getNiv_sec_nbr_consultation() {
	return niv_sec_nbr_consultation;
}




public void setNiv_sec_nbr_consultation(int niv_sec_nbr_consultation) {
	this.niv_sec_nbr_consultation = niv_sec_nbr_consultation;
}




public int getNiv_sec_connaissances() {
	return niv_sec_connaissances;
}




public void setNiv_sec_connaissances(int niv_sec_connaissances) {
	this.niv_sec_connaissances = niv_sec_connaissances;
}




public int getNiv_sec_localisations() {
	return niv_sec_localisations;
}




public void setNiv_sec_localisations(int niv_sec_localisations) {
	this.niv_sec_localisations = niv_sec_localisations;
}




public int getNiv_sec_ommunes() {
	return niv_sec_communes;
}




public void setNiv_sec_ommunes(int niv_sec_ommunes) {
	this.niv_sec_communes = niv_sec_ommunes;
}




public int getNiv_sec_ressourceCaracteristiques() {
	return niv_sec_ressourceCaracteristiques;
}




public void setNiv_sec_ressourceCaracteristiques(int niv_sec_ressourceCaracteristiques) {
	this.niv_sec_ressourceCaracteristiques = niv_sec_ressourceCaracteristiques;
}




public int getNiv_sec_ressourceUtilisateur() {
	return niv_sec_ressourceUtilisateur;
}




public void setNiv_sec_ressourceUtilisateur(int niv_sec_ressourceUtilisateur) {
	this.niv_sec_ressourceUtilisateur = niv_sec_ressourceUtilisateur;
}




public void setImages(List<Image> images) {
	this.images = images;
}




public String getDetaille_prelevement() {
	return detaille_prelevement;
}




public void setDetaille_prelevement(String detaille_prelevement) {
	this.detaille_prelevement = detaille_prelevement;
}




public String getDetaille() {
	return detaille;
}




public void setDetaille(String detaille) {
	this.detaille = detaille;
}




public List<Lot> getLots() {
	return lots;
}




public void setLots(List<Lot> lots) {
	this.lots = lots;
}




public Ressource(Long id, String nom_commun, String nom_scientifique, Boolean voie_disparition, int population,
		String couleur, Double taille, String utilisation, String detaille_prelevement, String detaille,
		List<Image> images, Boolean visible, int nbr_consultation, List<Classification> classifications,
		List<Lot> lots, List<Connaissance> connaissances, List<Localisation> localisations,
		List<Commune> communes, List<RessourceCaracteristique> ressourceCaracteristiques,
		List<RessourceUtilisateur> ressourceUtilisateur) {
	this.id = id;
	this.nomcommun = nom_commun;
	this.nom_scientifique = nom_scientifique;
	this.voie_disparition = voie_disparition;
	this.population = population;
	this.couleur = couleur;
	this.taille = taille;
	this.utilisation = utilisation;
	this.detaille_prelevement = detaille_prelevement;
	this.detaille = detaille;
	this.images = images;
	this.visible = visible;
	this.nbr_consultation = nbr_consultation;
	this.classifications = classifications;
	this.lots = lots;
	this.connaissances = connaissances;
	this.localisations = localisations;
	this.communes = communes;
	this.ressourcecaracteristique = ressourceCaracteristiques;
	this.ressourceUtilisateur = ressourceUtilisateur;
}






public Long getId() {
	return id;
}




public void setId(Long id) {
	this.id = id;
}




public String getNom_commun() {
	return nomcommun;
}




public void setNom_commun(String nom_commun) {
	this.nomcommun = nom_commun;
}




public String getNomcommun() {
	return nomcommun;
}




public void setNomcommun(String nomcommun) {
	this.nomcommun = nomcommun;
}




public String getNom_scientifique() {
	return nom_scientifique;
}




public void setNom_scientifique(String nom_scientifique) {
	this.nom_scientifique = nom_scientifique;
}




public Boolean getVoie_disparition() {
	return voie_disparition;
}




public void setVoie_disparition(Boolean voie_disparition) {
	this.voie_disparition = voie_disparition;
}




public int getPopulation() {
	return population;
}




public void setPopulation(int population) {
	this.population = population;
}




public String getCouleur() {
	return couleur;
}




public void setCouleur(String couleur) {
	this.couleur = couleur;
}




public Double getTaille() {
	return taille;
}




public void setTaille(Double taille) {
	this.taille = taille;
}




public String getUtilisation() {
	return utilisation;
}




public void setUtilisation(String utilisation) {
	this.utilisation = utilisation;
}




public Boolean getVisible() {
	return visible;
}




public void setVisible(Boolean visible) {
	this.visible = visible;
}




public int getNbr_consultation() {
	return nbr_consultation;
}




public void setNbr_consultation(int nbr_consultation) {
	this.nbr_consultation = nbr_consultation;
}




public List<Classification> getClassifications() {
	return classifications;
}




public void setClassifications(List<Classification> classifications) {
	this.classifications = classifications;
}




public List<Connaissance> getConnaissances() {
	return connaissances;
}




public void setConnaissances(List<Connaissance> connaissances) {
	this.connaissances = connaissances;
}




public List<Localisation> getLocalisations() {
	return localisations;
}




public void setLocalisations(List<Localisation> localisations) {
	this.localisations = localisations;
}




public List<Commune> getCommunes() {
	return communes;
}




public void setCommunes(List<Commune> communes) {
	this.communes = communes;
}




public List<RessourceCaracteristique> getRessourceCaracteristiques() {
	return ressourcecaracteristique;
}




public void setRessourceCaracteristiques(List<RessourceCaracteristique> ressourceCaracteristiques) {
	this.ressourcecaracteristique = ressourceCaracteristiques;
}




public List<RessourceUtilisateur> getRessourceUtilisateur() {
	return ressourceUtilisateur;
}




public void setRessourceUtilisateur(List<RessourceUtilisateur> ressourceUtilisateur) {
	this.ressourceUtilisateur = ressourceUtilisateur;
}




public int getNiv_sec_classifications() {
	return niv_sec_classifications;
}




public void setNiv_sec_classifications(int niv_sec_classifications) {
	this.niv_sec_classifications = niv_sec_classifications;
}

public void setParent_id(Long detaille) {
	this.parent_id = detaille;
}



public Ressource() {

}




public Ressource(String nom_commun, String nom_scientifique2, Boolean voie_disparition2, int population2,
		String couleur2, Double taille2, String utilisation2, List<Connaissance> connaissances2,
		List<Localisation> localisations2, List<Classification> listclassifications, int niv_sec_nom_commun2,
		int niv_sec_nom_scientifique2, int niv_sec_voie_disparition2, int niv_sec_population2, int niv_sec_couleur2,
		int niv_sec_taille2, int niv_sec_utilisation2, int niv_sec_classifications) {
	this.nomcommun=nom_commun;
	this.nom_scientifique=nom_scientifique2;
	this.voie_disparition=voie_disparition2;
	this.population=population2;
	this.couleur=couleur2;
	this.taille=taille2;
	this.utilisation=utilisation2;
	this.connaissances=connaissances2;
	this.localisations=localisations2;
	this.classifications=listclassifications;
	this.niv_sec_nom_commun=niv_sec_nom_commun2;
	this.niv_sec_nom_scientifique=niv_sec_nom_scientifique2;
	this.niv_sec_voie_disparition=niv_sec_voie_disparition2;
	this.niv_sec_population=niv_sec_population2;
	this.niv_sec_couleur=niv_sec_couleur2;
	this.niv_sec_taille=niv_sec_taille2;
	this.niv_sec_utilisation=niv_sec_utilisation2;
	this.niv_sec_classifications=niv_sec_classifications;
	
	// TODO Auto-generated constructor stub
}




public void alter (String nom_commun, String nom_scientifique, Boolean voie_disparition, int population, String couleur,
Double taille, String utilisation,List<Connaissance> connaissances,List<Localisation> localisations,List<Classification> classifications,int niv_sec_nom_commun, int niv_sec_nom_scientifique, int niv_sec_voie_disparition, int niv_sec_population,
int niv_sec_couleur, int niv_sec_taille, int niv_sec_utilisation,int niv_sec_classifications){
	this.nomcommun = nom_commun;
	this.nom_scientifique = nom_scientifique;
	this.voie_disparition = voie_disparition;
	this.population = population;
	this.couleur = couleur;
	this.taille = taille;
	this.utilisation = utilisation;
	this.connaissances = connaissances;
	this.localisations=localisations;
	this.classifications = classifications;
	this.visible = false;
	this.niv_sec_nom_commun = niv_sec_nom_commun;
	this.niv_sec_nom_scientifique = niv_sec_nom_scientifique;
	this.niv_sec_voie_disparition = niv_sec_voie_disparition;
	this.niv_sec_population = niv_sec_population;
	this.niv_sec_couleur = niv_sec_couleur;
	this.niv_sec_taille = niv_sec_taille;
	this.niv_sec_utilisation = niv_sec_utilisation;
    this.niv_sec_classifications=niv_sec_classifications;
    this.niv_sec_ressourceUtilisateur=0;
    this.detaille_prelevement="";
}




public String getImage() {
	return image;
}




public void setImage(String image) {
	this.image = image;
}



}