package guru.springframework.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource", name = "Lot")

@Entity 
public class Lot implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String Description;
@ManyToOne(targetEntity=Lot.class)
private Lot lot;
@ManyToMany(targetEntity=Ressource.class,mappedBy="lots")
private List<Ressource> ressources;


public List<Ressource> getRessources() {
	return ressources;
}


public void setRessources(List<Ressource> ressources) {
	this.ressources = ressources;
}


public Lot() {
}


public Lot(String string) {
this.id=Long.parseLong(string);
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getDescription() {
	return Description;
}


public void setDescription(String description) {
	Description = description;
}


public List<Classification> getClassifications() {
	return classifications;
}


public void setClassifications(List<Classification> classifications) {
	this.classifications = classifications;
}


@OneToMany(mappedBy="lot") 
private List<Classification> classifications;
}