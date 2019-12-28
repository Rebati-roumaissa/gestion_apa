package guru.springframework.domain;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Table(schema = "ressource", name = "Permission")
@Entity 
public class Permission implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nom;
private String Description;

public Permission() {
}

	public Permission(String nom, String description) {
		this.nom = nom;
		Description = description;
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
	return Description;
}


public void setDescription(String description) {
	Description = description;
}


public List<Role> getRoles() {
	return roles;
}


public void setRoles(List<Role> roles) {
	this.roles = roles;
}


@ManyToMany
@JoinTable(
		name = "role_permessions",
		schema = "ressource",
		joinColumns = @JoinColumn(name = "permessions_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
private List<Role> roles;
}