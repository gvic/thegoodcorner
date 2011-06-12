package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Communaute")
public class Communaute implements Serializable{
	
	private static final long serialVersionUID = 1748423381899940742L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String denomination;
	private String description;
	
	@ManyToMany(mappedBy="communautes")
	private Set<User> users;

	@ManyToMany(mappedBy="communautes", fetch = FetchType.EAGER)
	private Set<Annonce> annonces;
	
	public Communaute() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setAnnonces(Set<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Set<Annonce> getAnnonces() {
		return annonces;
	}	
	
	public String toString(){
		return getDenomination();
	}
}
