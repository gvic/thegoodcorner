package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Region")
public class Region {
	
	@Id
	private long id;
	private String nom;
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "region")
	private Set<Departement> departements;
	
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

	public void setDepartements(Set<Departement> departements) {
		this.departements = departements;
	}

	public Set<Departement> getDepartements() {
		return departements;
	}

}
