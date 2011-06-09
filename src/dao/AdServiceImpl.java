package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    
    @Override
	public List<Departement> getDepartements() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Departement> cq = cb.createQuery(Departement.class);
		Root<Departement> rootDep= cq.from(Departement.class);
		TypedQuery<Departement> query = em.createQuery(cq.select(rootDep).distinct(true));
		return query.getResultList();
	}

	@Override
	public List<Communaute> getCommunautes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootComm= cq.from(Communaute.class);
		TypedQuery<Communaute> query = em.createQuery(cq.select(rootComm).distinct(true));
		return query.getResultList();
	}

	@Override
	public void save(Annonce annonce) {
		em.persist(annonce);		
	}

	@Override
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

	@Override
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

	@Override
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
	
	@Override
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

	@Override
	public List<Categorie> getSecondLevelCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat= cq.from(Categorie.class);
		cq.select(rootCat);//.where(cb.greaterThan(rootCat.get("parente_id").as(Integer.class),1));
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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
	
}
