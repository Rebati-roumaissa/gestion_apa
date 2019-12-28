package guru.springframework.domain;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Table(schema = "ressource", name = "Role")

@Entity 
public class Role implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

private String nom;
private String Description;
private Long lot;
private long niv_sec;

public Role() {
}


@ManyToMany
@JoinTable(
		name = "utilisateur_roles",
		schema = "ressource",
		joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "utilisateurs_id", referencedColumnName = "id"))
private List<Utilisateur> utilisateurs;

@ManyToMany
@JoinTable(
		name = "role_permessions",
		schema = "ressource",
		joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "permessions_id", referencedColumnName = "id"))
private List<Permission> permissions;
	


public Role(String nom, String description, int niv_sec) {
		this.nom = nom;
		Description = description;
		this.niv_sec=niv_sec;
	}

public Role(String nom, String description, int niv_sec,long lot) {
	this.nom = nom;
	Description = description;
	this.niv_sec=niv_sec;
	this.lot=lot;
}
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}
	public Role(String nom, String description) {
		this.nom = nom;
		Description = description;
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

public Long getLot() {
	return lot;
}

public void setLot(Long lot) {
	this.lot = lot;
}


public long getNiv_sec() {
	return niv_sec;
}

public void setNiv_sec(long niv_sec) {
	this.niv_sec = niv_sec;
}

public List<Utilisateur> getUtilisateurs() {
	return utilisateurs;
}

public void setUtilisateurs(List<Utilisateur> utilisateurs) {
	this.utilisateurs = utilisateurs;
}

public List<Permission> getPermissions() {
	return permissions;
}

public void setPermissions(List<Permission> permissions) {
	this.permissions = permissions;
}





}