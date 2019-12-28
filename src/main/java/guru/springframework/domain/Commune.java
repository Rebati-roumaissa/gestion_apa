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
@Table(schema = "ressource", name = "Commune")
@Entity 
public class Commune implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String procedure;
private String brevet;
private String ingrediants;
private String region;
private String niv_securite;
public Commune() {
}

@ManyToMany(targetEntity=Ressource.class,mappedBy="communes")
private List<Ressource> Ressources;

}