package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Categorie")
public class Categorie implements Serializable, Comparable<Categorie>{

	private static final long serialVersionUID = -404538643185867920L;

	// champs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30)
	private String nom;
	
	@ManyToOne
	private Categorie parente;
	
	// relation inverse Categorie (one) -> Annonce (many) de la relation Annonce
	// (many) -> Categorie(one)
	// cascade insertion Categorie -> insertion Annonces
	// cascade maj Categorie -> maj Annonces
	// cascade suppression Categorie -> suppression Annonces
	@ManyToMany(mappedBy = "categories")
	private Set<Annonce> annonces = new HashSet<Annonce>();
	
	// Arbres des categories
//	@ManyToMany
//	private Set<Categorie> sscategories;

	public Categorie() {
	}

	public String toString() {
		return String.format("Categorie[%d,%d,%s]", id, nom);
	}

//	public void setSscategories(Set<Categorie> sscategories) {
//		this.sscategories = sscategories;
//	}
//
//	public Set<Categorie> getSscategories() {
//		return sscategories;
//	}

	public void setAnnonces(Set<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Set<Annonce> getAnnonces() {
		return annonces;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getParente() {
		return parente;
	}

	public void setParente(Categorie parente) {
		this.parente = parente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Categorie o) {
		return nom.compareTo(o.getNom());
	}
	
	
}
