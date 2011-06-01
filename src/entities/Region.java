package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Region")
public class Region {
	
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
