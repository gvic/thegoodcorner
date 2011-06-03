package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Utilisateur")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "user")
	private Set<Annonce> annonces;
	
	@ManyToMany
	private Set<Communaute> communautes;

	private String prenom;
	private String nom;
	private String login;
	private String md5_mdp;
	
	@OneToOne
	private Adresse adresse;
	
	private String email;
	private String telephoneFixe;
	private String telephonePortable;
	
	// Pour savoir quels utilisateurs sont 
	// connectés
	private Date derniereConnexion;

	public User(){
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String nickName) {
		this.login = nickName;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	public String getTelephonePortable() {
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}

	public Date getDerniereConnexion() {
		return derniereConnexion;
	}

	public void setDerniereConnexion(Date derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	public Set<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(Set<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Set<Communaute> getCommunautes() {
		return communautes;
	}

	public void setCommunautes(Set<Communaute> communautes) {
		this.communautes = communautes;
	}	
	
	public String toString() {
		return "User : "+getLogin()+", "+getNom()+" "+getPrenom();
	}

	public void setMd5_mdp(String md5_mdp) {
		this.md5_mdp = md5_mdp;
	}

	public String getMd5_mdp() {
		return md5_mdp;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
}
