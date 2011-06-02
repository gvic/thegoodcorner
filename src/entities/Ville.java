package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ville")
public class Ville {
	
	@Id
	private long id;
	
	private String nom;
	
	@ManyToOne
	private Departement departement;

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

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Departement getDepartement() {
		return departement;
	}
}
