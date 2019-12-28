package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource", name = "Image")
@Entity 
public class Image implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String name;
private String type;
private String lien;

@ManyToOne
@JoinColumn(name="ressource_id", nullable=false)
private Ressource ressource;



public String getLien() {
	return lien;
}

public void setLien(String lien) {
	this.lien = lien;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Ressource getRessource() {
	return ressource;
}

public void setRessource(Ressource ressource) {
	this.ressource = ressource;
}

public Image() {}

}