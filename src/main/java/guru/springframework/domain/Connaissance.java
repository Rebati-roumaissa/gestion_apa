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
public class Connaissance implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String procedure;
private String brevet;
private String ingrediants;
private String region;
private int niv_securite;

@ManyToMany(targetEntity=Ressource.class,mappedBy="connaissances")
private List<Ressource> ressources;

public Connaissance() {
}
public Connaissance(String procedure,String brevet,String ingrediants,String region,String niv_securite) {
this.procedure=procedure;
this.brevet=brevet;
this.ingrediants=ingrediants;
this.region=region;
this.niv_securite=Integer.valueOf(niv_securite);



}

public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getProcedure() {
	return procedure;
}


public void setProcedure(String procedure) {
	this.procedure = procedure;
}


public String getBrevet() {
	return brevet;
}


public void setBrevet(String brevet) {
	this.brevet = brevet;
}


public String getIngrediants() {
	return ingrediants;
}


public void setIngrediants(String ingrediants) {
	this.ingrediants = ingrediants;
}


public String getRegion() {
	return region;
}


public void setRegion(String region) {
	this.region = region;
}


public int getNiv_securite() {
	return niv_securite;
}


public void setNiv_securite(int niv_securite) {
	this.niv_securite = niv_securite;
}


public List<Ressource> getRessources() {
	return ressources;
}


public void setRessources(List<Ressource> ressources) {
	ressources = ressources;
}

}


