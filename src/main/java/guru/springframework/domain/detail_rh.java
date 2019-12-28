package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="detail_rh", schema="annuaire")
public class detail_rh {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_rh")
    private int idDetail;
	
	@Column(name="nom")
     private String nom;
	
	@Column(name="prenom")
	 private String prenom;
	
	@Column(name="metier")
	 private String metier;
	
	@Column(name="contribution")
	 private String contribution;
	
	@Column(name="site_web")
	 private String site_web;
	
	@Column(name="tel")
	 private String tel;
	
	@Column(name="fax")
	 private String fax;
	
	@Column(name="email")
	 private String email;
 
	
	@Column(name="cat")
	private int cat;
	
	

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}
	

	

	public int getId_detail() {
		return idDetail;
	}

	public void setId_detail(int id_detail) {
		this.idDetail = id_detail;
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

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getSite_web() {
		return site_web;
	}

	public void setSite_web(String site_web) {
		this.site_web = site_web;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
