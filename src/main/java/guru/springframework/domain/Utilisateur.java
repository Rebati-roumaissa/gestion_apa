package guru.springframework.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
@Table(schema = "ressource", name = "Utilisateur")

@Entity 
public class Utilisateur implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
private String nom;
private String prenom;
private String password;
private String username;
private String profession;
private String image;
private String email;
private String telephone;
private Long Nss;
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	private List<demande> demandes = new ArrayList<>();
    public String getTelephone() {
	return telephone;
}



public void setTelephone(String telephone) {
	this.telephone = telephone;
}


public String getEmail() {
	return email;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public void setEmail(String email) {
	this.email = email;
}


public Utilisateur() {
}



@OneToMany(mappedBy="ressource", cascade = CascadeType.ALL)
private List<RessourceUtilisateur> ressourceutilisateur;


@ManyToMany
@JoinTable(
		name = "utilisateur_roles",
		schema = "ressource",
		joinColumns = @JoinColumn(name = "utilisateurs_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
private List<Role> roles;



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


public String getPrenom() {
	return prenom;
}


public void setPrenom(String prenom) {
	this.prenom = prenom;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getUser_name() {
	return username;
}


public void setUser_name(String user_name) {
	this.username = user_name;
}


public String getProfession() {
	return profession;
}


public void setProfession(String profession) {
	this.profession = profession;
}


public String getImage() {
	return image;
}


public void setImage(String image) {
	this.image = image;
}


public List<RessourceUtilisateur> getRessourceutilisateur() {
	return ressourceutilisateur;
}


public void setRessourceutilisateur(List<RessourceUtilisateur> ressourceutilisateur) {
	this.ressourceutilisateur = ressourceutilisateur;
}


public List<Role> getRoles() {
	return roles;
}


public void setRoles(List<Role> roles) {
	this.roles = roles;
}


public void setRoles(HashSet<Role> hashSet) {
	// TODO Auto-generated method stub
	this.roles = roles;

}

	public Utilisateur(String nom, String prenom, String password, String username) {
		this.password=password;
		this.username=username;
		this.nom=nom;
		this.prenom=prenom;
	}

	public List<demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<demande> demandes) {
		this.demandes = demandes;
	}

	public void addDemande(demande demande) {
		this.demandes.add(demande);
	}

/*
@OneToMany(mappedBy="ressource", cascade = CascadeType.ALL)
private List<RessourceUtilisateur> ressourceutilisateur;*/

	public Utilisateur(String nom, String prenom, String password, String username, List<Role> roles) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.username = username;
		this.roles = roles;
	}

	public Utilisateur(String nom, String prenom, String password, String username, String profession, String telephone, String email, Long nss, List<Role> roles) {
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.username = username;
		this.profession = profession;
		this.telephone = telephone;
		this.email = email;
		Nss = nss;
		this.roles = roles;
	}



	public Long getNss() {
		return Nss;
	}

	public void setNss(Long nss) {
		Nss = nss;
	}



}

