package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departement")
public class Departement implements Comparable<Departement> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// numero en string pour la corse par exemple...
	private String numero;
	private String nom;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "departement")
	private Set<Ville> villes;
	
	@ManyToOne
	private Region region;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}

	public Set<Ville> getVilles() {
		return villes;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

	@Override
	public int compareTo(Departement o) {
		return nom.compareTo(o.getNom());
	}
}
