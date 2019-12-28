package guru.springframework.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource")

@Entity 
public class RessourceCaracteristique implements Serializable {
    private String valeur;
    private  int niv_sec;
@Id
@ManyToOne
@JoinColumn(name = "caracteristique_id")
private Caracteristique caracteristique;

@Id
@ManyToOne
@JoinColumn(name = "ressource_id")
private Ressource ressource;


public String getValeur() {
	return valeur;
}

public void setValeur(String valeur) {
	this.valeur = valeur;
}

public Caracteristique getCaracteristique() {
	return caracteristique;
}

public void setCaracteristique(Caracteristique caracteristique) {
	this.caracteristique = caracteristique;
}

public Ressource getRessource() {
	return ressource;
}

public void setRessource(Ressource ressource) {
	this.ressource = ressource;
}

public int getNiv_sec() {
	return niv_sec;
}

public void setNiv_sec(int niv_sec) {
	this.niv_sec = niv_sec;
}

public  RessourceCaracteristique() {
}

public RessourceCaracteristique(String valeur, Caracteristique caracteristique, Ressource ressource, int niv_sec) {
	this.valeur = valeur;
	this.caracteristique = caracteristique;
	this.ressource = ressource;
	this.niv_sec = niv_sec;
}



}