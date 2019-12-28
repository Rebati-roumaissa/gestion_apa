package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource")
@Entity 
public class Caracteristique implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nom;
private String detail;
public Caracteristique() {
}

public Caracteristique(String nom2, String description) {
this.nom=nom2;
this.detail=description;
}

@OneToMany(mappedBy="caracteristique", cascade = CascadeType.ALL)
private List<RessourceCaracteristique> ressourcecaracteristique;



public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getDetail() {
	return detail;
}

public void setDetail(String detail) {
	this.detail = detail;
}

public List<RessourceCaracteristique> getRessourcecaracteristique() {
	return ressourcecaracteristique;
}

public void setRessourcecaracteristique(List<RessourceCaracteristique> ressourcecaracteristique) {
	this.ressourcecaracteristique = ressourcecaracteristique;
}



}