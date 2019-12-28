package guru.springframework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.websocket.Decoder.Text;

@Entity
@Table (name="detail_inst", schema="annuaire")
public class detail_inst {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_inst")
     private int idDetail;
	
	@Column(name="nom")
     private String nom;
	
	@Column(name="description")
	 private String description;
	
	@Column(name="activite")
	 private String activite;
	
	@Column(name="adresse")
	 private String adresse;
	
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
    
	
	@Column(name="url")
	 private  String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}
	
	
	
}
