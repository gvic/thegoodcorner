package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departement")
public class Departement {
	
	@Id
	private long id;
	
	private String nom;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "departement")
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
}
