package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;
import entities.User;
import entities.Ville;

@Remote
public interface AdService {

	public List<Departement> getDepartements();
	public List<Communaute> getCommunautes();
	public List<Region> getRegions();
	public List<Categorie> getCategories();
	public List<Categorie> getFirstLevelCategories();
	public List<Categorie> getSecondLevelCategories();
	public List<Categorie> getMasterCategory();
	public List<Categorie> getSonsCategories(long id);




	public ArrayList<Annonce> listAnnoncesFrom(Categorie c);
	
	public ArrayList<Annonce> listAnnoncesFrom(Communaute c);
	
	public ArrayList<Annonce> listAnnoncesFrom(User u);
	
	public ArrayList<Annonce> listAnnoncesFrom(Ville v);
	
	public ArrayList<Annonce> listAnnoncesFrom(Departement d);
	
	public ArrayList<Annonce> listAnnoncesFrom(Region r);
	
	public void save(Annonce annonce);
	
}
