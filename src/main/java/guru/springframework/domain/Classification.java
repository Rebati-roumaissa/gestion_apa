package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource")
@Entity 
public class Classification implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nom;
private String description;

@ManyToOne() 
private  Lot lot;
@ManyToMany(targetEntity=Ressource.class,mappedBy="classifications")
private List<Ressource> ressources;
public Classification(String nom,String description,Lot lot){
	  this.nom=nom;
	  this.description=description;
	  this.lot=lot;
	}


public Classification() {
	// TODO Auto-generated constructor stub
}


public void setId(String string) {
	this.id=Long.parseLong(string);	
}
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
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Lot getLot() {
	return lot;
}
public void setLot(Lot lot) {
	this.lot = lot;
}
public List<Ressource> getRessources() {
	return ressources;
}
public void setRessources(List<Ressource> ressources) {
	ressources = ressources;
}




}