package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Table;
@Table(schema = "ressource")
@Entity 
public class Localisation implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nom;
private Double altitude;
private Double longitude;

private String url_google_maps;
private String brevet;


@ManyToMany(targetEntity=Ressource.class,mappedBy="localisations")
   private List<Ressource> ressources2;

public Localisation() {

}

public Localisation(String coord_geo,String  Latitude,String url_google_maps) {
	   
    this.altitude=Double.valueOf(Latitude);
    this.longitude=Double.valueOf(coord_geo);
    this.nom=url_google_maps;

}
public Localisation(String string) {
this.nom=string;
}


public String getNom() {
	return nom;
}



public void setNom(String nom) {
	this.nom = nom;
}



public Double getAltitude() {
	return altitude;
}



public void setAltitude(Double altitude) {
	this.altitude = altitude;
}



public Double getLongitude() {
	return longitude;
}



public void setLongitude(Double longitude) {
	this.longitude = longitude;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}



public String getUrl_google_maps() {
	return url_google_maps;
}

public void setUrl_google_maps(String url_google_maps) {
	this.url_google_maps = url_google_maps;
}

public String getBrevet() {
	return brevet;
}

public void setBrevet(String brevet) {
	this.brevet = brevet;
}

public List<Ressource> getRessources() {
	return ressources2;
}

public void setRessources(List<Ressource> ressources) {
	ressources2 = ressources;
}

}