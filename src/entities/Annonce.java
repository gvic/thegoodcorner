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
	private Integer id;
	
	@ManyToOne
	//@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	// Preferences de l'utilisateur pour une annonce
	private int rayonGeographique;
	private boolean telephoneFixeVisible;
	private boolean telephonePortableVisible;
	private boolean envoiColis;
	
	// Ou est l'objet a vendre?
	@OneToOne
	private Adresse adresse;
	
	@ManyToMany
	// @JoinColumn(name = "categorie_id", nullable = false)
	private Set<Categorie> categories;
	
	@ManyToMany
	private Set<Communaute> communautes;
	
	private String titre;
	private String description;
	private float prix;
	
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

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public float getPrix() {
		return prix;
	}
	
	
}
