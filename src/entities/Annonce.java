package entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Annonce")
public class Annonce {
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	//@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	// Preferences de l'utilisateur pour une annonce
//	@Column(nullable=true)
	private int rayonGeographique;
//	@Column(nullable=true)
	private boolean telephoneFixeVisible;
//	@Column(nullable=true)
	private boolean telephonePortableVisible;
//	@Column(nullable=true)
	private boolean envoiColis;
//	@Column(nullable=false)
	private boolean validee;
	
	// Ou est l'objet a vendre?
	@OneToOne
//	@Column(nullable=true)
	private Adresse adresse;
	
	@OneToOne
	private Departement departement;
	@OneToOne
	private Region region;
	
	
	@ManyToMany
	// @JoinColumn(name = "categorie_id", nullable = false)
	private Set<Categorie> categories;
	
	@ManyToMany
	private Set<Communaute> communautes;
	
	private String title;
	private String description;
//	@Column(nullable=true)
	private float price;
	
	@Basic()
	@Temporal(TemporalType.DATE)
	private Date date_de_publication;
	
	
	public Annonce(){
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titre) {
		this.title = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate_de_publication(Date date_de_publication) {
		this.date_de_publication = date_de_publication;
	}

	public Date getDate_de_publication() {
		return date_de_publication;
	}

	public void setCommunautes(Set<Communaute> communautes) {
		this.communautes = communautes;
	}

	public Set<Communaute> getCommunautes() {
		return communautes;
	}

	public int getRayonGeographique() {
		return rayonGeographique;
	}

	public void setRayonGeographique(int rayonGeographique) {
		this.rayonGeographique = rayonGeographique;
	}

	public boolean isTelephoneFixeVisible() {
		return telephoneFixeVisible;
	}

	public void setTelephoneFixeVisible(boolean telephoneFixeVisible) {
		this.telephoneFixeVisible = telephoneFixeVisible;
	}

	public boolean isTelephonePortableVisible() {
		return telephonePortableVisible;
	}

	public void setTelephonePortableVisible(boolean telephonePortableVisible) {
		this.telephonePortableVisible = telephonePortableVisible;
	}

	public boolean isEnvoiColis() {
		return envoiColis;
	}

	public void setEnvoiColis(boolean envoiColis) {
		this.envoiColis = envoiColis;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public boolean isValidee() {
		return validee;
	}

	public void setValidee(boolean validee) {
		this.validee = validee;
	}
	
	
}
