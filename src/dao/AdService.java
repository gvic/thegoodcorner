package dao;

import java.util.List;

import javax.ejb.Remote;

import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;

@Remote
public interface AdService {

    // obtenir une annonce via son identifiant
    public Annonce getOne(long id);
	
	public List<Departement> getDepartements();
	public List<Communaute> getCommunautes();
	public List<Region> getRegions();
	public List<Categorie> getCategories();
	public List<Categorie> getCategoriesLevel(long id);
	public List<Categorie> getSecondLevelCategories();
	public List<Categorie> getMasterCategory();
	public List<Categorie> getSonsCategories(Categorie c);	
	public void save(Annonce annonce);

	public List<String> getOrderedCategories();

	public List<String> getAreas();

	public Object findAd(String title, String description,
			String geographicAreaSubmitted, String categorySubmitted);
	
}
