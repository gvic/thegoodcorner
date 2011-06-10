package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;

@Stateless(mappedName="dao.AdServiceImpl")
public class AdServiceImpl implements AdService {

	@PersistenceContext
	private EntityManager em;
   
    public void setEntityManager(EntityManager em) {
    	this.em = em;
    }
    
    public EntityManager getEntityManager() {
    	return em;
    }
    
    
	public List<Departement> getDepartements() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Departement> cq = cb.createQuery(Departement.class);
		Root<Departement> rootDep= cq.from(Departement.class);
		TypedQuery<Departement> query = em.createQuery(cq.select(rootDep).distinct(true));
		return query.getResultList();
	}

	
	public List<Communaute> getCommunautes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootComm= cq.from(Communaute.class);
		TypedQuery<Communaute> query = em.createQuery(cq.select(rootComm).distinct(true));
		return query.getResultList();
	}

	
	public void save(Annonce annonce) {
		em.persist(annonce);		
	}

	
	public List<Region> getRegions() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Region> cq = cb.createQuery(Region.class);
		Root<Region> rootReg= cq.from(Region.class);
		cq.select(rootReg);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootReg.get("nom")));
		TypedQuery<Region> query = em.createQuery(cq);
		return query.getResultList();
	}

	
	public List<Categorie> getCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	
	public List<Categorie> getMasterCategory() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);
		long id = 1;
		cq.where(cb.equal(rootCat.get("id"),id));
		cq.distinct(true);
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}
	
	
	public List<Categorie> getCategoriesLevel(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);
		Expression<Long> get = rootCat.get("id").as(Long.class);
		Predicate wh_clause = cb.equal(get,id);
		cq.where(wh_clause);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	
	public List<Categorie> getSecondLevelCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);//.where(cb.greaterThan(rootCat.get("parente_id").as(Integer.class),1));
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	
	public List<Categorie> getSonsCategories(Categorie p) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);
		cq.where(cb.equal(rootCat.get("parente"),p));
//		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

    // obtenir une annonce via son identifiant
    public Annonce getOne(long id) {
    	return em.find(Annonce.class, id);
    }

	
	public List<String> getOrderedCategories() {
		List<String> categories = new ArrayList<String>(); 
		List<Categorie> highest_cat = getCategoriesLevel(1L);
		Iterator<Categorie> it_cat = highest_cat.iterator();
		
		while (it_cat.hasNext()) {
			Categorie p = it_cat.next();
			categories.add(p.getNom());
			for(Categorie son : getSonsCategories(p)){
				categories.add(son.getNom());
				for(Categorie son2 : getSonsCategories(son)){
					categories.add(son2.getNom());
				}
			}
		}
		return categories;
	}

	
	public List<String> getAreas() {
		List<String> areas = new ArrayList<String>();
		List<Region> regions = getRegions();
		Region reg = null;
		Iterator<Region> it_reg = regions.iterator();
		while (it_reg.hasNext()) {
			reg = it_reg.next();
			List<Departement> matched_dep = reg.getDepartements();
			areas.add(reg.getNom());
			Iterator<Departement> it = matched_dep.iterator();
			while (it.hasNext()) {
				areas.add(it.next().getNom());
			}
		}
		return areas;
	}

	
	public List<Annonce> findAd(String title, String description,
			String geographicAreaSubmitted, String categorySubmitted) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
		Root<Annonce> rootAd= cq.from(Annonce.class);
		cq.select(rootAd);
		cq.where(cb.equal(rootAd.get("title"),title));
		cq.where(cb.equal(rootAd.get("description"),description));
		TypedQuery<Annonce> query = em.createQuery(cq);
		return query.getResultList();
	}
	
	/**
	 * Find ads by joins with other entities.
	 * 
	 * I.E:
	 * parameter : [ "departement" -> ["nom" -> "Ain"]]
	 * 
	 * Dirty method.. No comments!
	 * 
	 * @return
	 */
	public List<Annonce> findByJointure(Map<String,Map<String,Object>> joins) {
		
//		SELECT DISTINCT a FROM Annonce a JOIN a.departement d WHERE d.nom=:value
		List<Annonce> res = null;
    	try{
    		Set<String> keys = joins.keySet();
    		Iterator<String> iteKeys = keys.iterator();
    		Collection<Map<String,Object>> values = joins.values();
    		Iterator<Map<String,Object>> iteValues = values.iterator();
    		String query = "SELECT DISTINCT a FROM Annonce a";
    		// Iterator on keys
    		int i = 0;
    		while(iteKeys.hasNext()) {
				query += " JOIN";
    			query += " a."+iteKeys.next() + " v"+i;
    			i++;
    		}
    		
    		i = 0;
    		int j = 0;
    		Set<Object> oValues = new HashSet<Object>();
    		while(iteValues.hasNext()) {
    			Map<String,Object> map = iteValues.next();
    			Iterator<String> iteEntry = map.keySet().iterator();
    			oValues.addAll(map.values());
    			while (iteEntry.hasNext()) {
    				String key = iteEntry.next();
    				if (i==0){
        	    		query += " WHERE";
        			}
    				query += " v"+i+"."+key+"=:value"+j;
    				j++;
    			}
    			i++;
    		}
    		
    		TypedQuery<Annonce> q = em.createQuery(query,Annonce.class);
    		j = 0;
    		Iterator<Object> iteoValues = oValues.iterator();
    		while(iteoValues.hasNext()) {
    			q.setParameter("value"+j, iteoValues.next());
    			j++;
    		}
    		List<Annonce> u = q.getResultList();
    		if (u != null) {
    			res = u;
    		}
    	} catch(NoResultException e) {
    		e.printStackTrace();
    	} catch(NullPointerException e) {
    		e.printStackTrace();
    	}
    	return res;
	}
}
