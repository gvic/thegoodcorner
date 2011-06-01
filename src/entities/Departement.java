package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Departement")
public class Departement {
	
	@Id
	private long id;
	
	private String nom;

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
}
