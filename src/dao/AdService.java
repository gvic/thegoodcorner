package dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.ImagePath;
import entities.Region;
import entities.User;

@Remote
public interface AdService {

	public <T> T getOne(Class<T> arg, long id);
	
    public <T> Set<T> getByIds(Class<T> arg, Set<Long> ids);
	
	public List<Departement> getDepartements();
	public List<Communaute> getCommunautes();
	public List<Region> getRegions();
	public List<Categorie> getCategories();
	public List<Categorie> getCategoriesLevel(long id);
	public List<Categorie> getSecondLevelCategories();
	public List<Categorie> getMasterCategory();
	public List<Categorie> getSonsCategories(Categorie c);	
	public void save(Annonce annonce);
	public void save(ImagePath ip);

	public List<String> getOrderedCategories();

	public List<String> getAreas();

	public Object findAd(String title, String description,
			String geographicAreaSubmitted, String categorySubmitted);
	
	public List<Annonce> findByJointure(Map<String,Map<String,Object>> joins);
	
	public List<Annonce> findAd(User u, String title);
	
	public List<Annonce> search(String text);

	public <T> List<T> getAll(Class<T> class1);

	public void merge(Annonce ad);

//	public Map getRegionsWithDeparts();
}
