package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.MapUtils;

import core.SimpleMail;

import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.ImagePath;
import entities.Region;
import entities.User;

@Stateless(mappedName = "dao.AdServiceImpl")
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
		Root<Departement> rootDep = cq.from(Departement.class);
		TypedQuery<Departement> query = em.createQuery(cq.select(rootDep)
				.distinct(true));
		return query.getResultList();
	}

	public List<Communaute> getCommunautes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootComm = cq.from(Communaute.class);
		TypedQuery<Communaute> query = em.createQuery(cq.select(rootComm)
				.distinct(true));
		return query.getResultList();
	}

	public void save(Annonce annonce, User user) {
		em.persist(annonce);
		List<Annonce> adSaved = findAd(user,
			annonce.getTitle());
		System.out.println(adSaved);
		SimpleMail sm = new SimpleMail();
		try {
			sm.sendValidationAdMessage(user.getLogin(), adSaved.get(0).getId(),
				user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println(annonce.getId());
		em.persist(annonce);
	}

	public List<Region> getRegions() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Region> cq = cb.createQuery(Region.class);
		Root<Region> rootReg = cq.from(Region.class);
		cq.select(rootReg);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootReg.get("nom")));
		TypedQuery<Region> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Categorie> getCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat = cq.from(Categorie.class);
		cq.select(rootCat);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Categorie> getMasterCategory() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat = cq.from(Categorie.class);
		cq.select(rootCat);
		long id = 1;
		cq.where(cb.equal(rootCat.get("id"), id));
		cq.distinct(true);
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Categorie> getCategoriesLevel(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat = cq.from(Categorie.class);
		cq.select(rootCat);
		Expression<Long> get = rootCat.get("id").as(Long.class);
		Predicate wh_clause = cb.equal(get, id);
		cq.where(wh_clause);
		cq.distinct(true);
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Categorie> getSecondLevelCategories() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat = cq.from(Categorie.class);
		cq.select(rootCat);// .where(cb.greaterThan(rootCat.get("parente_id").as(Integer.class),1));
		cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Categorie> getSonsCategories(Categorie p) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Categorie> cq = cb.createQuery(Categorie.class);
		Root<Categorie> rootCat = cq.from(Categorie.class);
		cq.select(rootCat);
		cq.where(cb.equal(rootCat.get("parente"), p));
		// cq.orderBy(cb.asc(rootCat.get("nom")));
		TypedQuery<Categorie> query = em.createQuery(cq);
		return query.getResultList();
	}

	// Get one entity bean thanks to its ID
	public <T> T getOne(Class<T> arg, long id) {
		return em.find(arg, id);
	}

    // Get list entity bean thanks to their IDs
    public <T> Set<T> getByIds(Class<T> arg, Set<Long> ids) {
    	Set<T> res = new HashSet<T>();
    	for (Long long1 : ids) {
			res.add(getOne(arg, long1));
		}
		return res;
	}

	public List<String> getOrderedCategories() {
		List<String> categories = new ArrayList<String>();
		List<Categorie> highest_cat = getCategoriesLevel(1L);
		Iterator<Categorie> it_cat = highest_cat.iterator();

		while (it_cat.hasNext()) {
			Categorie p = it_cat.next();
			categories.add(p.getNom());
			for (Categorie son : getSonsCategories(p)) {
				categories.add(son.getNom());
				for (Categorie son2 : getSonsCategories(son)) {
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
				areas.add(" - " + it.next().getNom());
			}
		}
		return areas;
	}

	public List<Annonce> findAd(String title, String description,
			String geographicAreaSubmitted, String categorySubmitted) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
		Root<Annonce> rootAd = cq.from(Annonce.class);
		cq.select(rootAd);
		cq.where(cb.equal(rootAd.get("title"), title));
		cq.where(cb.equal(rootAd.get("description"), description));
		TypedQuery<Annonce> query = em.createQuery(cq);
		return query.getResultList();
	}

	/**
	 * Find ads by joins with other entities.
	 * 
	 * I.E: parameter : [ "departement" -> ["nom" -> "Ain"]]
	 * 
	 * Dirty method.. No comments!
	 * 
	 * @return
	 */
	public List<Annonce> findByJointure(Map<String, Map<String, Object>> joins) {
		System.out.println("findByJointure() called");
		// SELECT DISTINCT a FROM Annonce a JOIN a.departement d WHERE
		// d.nom=:value
		List<Annonce> res = null;
		try {
			Set<String> keys = joins.keySet();
			Iterator<String> iteKeys = keys.iterator();
			Collection<Map<String, Object>> values = joins.values();
			Iterator<Map<String, Object>> iteValues = values.iterator();
			String query = "SELECT DISTINCT a FROM Annonce a";
			// Iterator on keys
			int i = 0;
			while (iteKeys.hasNext()) {
				query += " JOIN";
				query += " a." + iteKeys.next() + " v" + i;
				i++;
			}

			i = 0;
			int j = 0;
			Set<Object> oValues = new HashSet<Object>();
			while (iteValues.hasNext()) {
				Map<String, Object> map = iteValues.next();
				Iterator<String> iteEntry = map.keySet().iterator();
				oValues.addAll(map.values());
				while (iteEntry.hasNext()) {
					String key = iteEntry.next();
					if (i == 0) {
						query += " WHERE";
					}
					query += " v" + i + "." + key + "=:value" + j;
					j++;
				}
				i++;
			}

			TypedQuery<Annonce> q = em.createQuery(query, Annonce.class);
			j = 0;
			Iterator<Object> iteoValues = oValues.iterator();
			while (iteoValues.hasNext()) {
				q.setParameter("value" + j, iteoValues.next());
				j++;
			}
			List<Annonce> u = q.getResultList();
			if (u != null) {
				res = u;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Searches and returns a list of Annonce which contain in their title one
	 * of the key words in the text entered in parameter. -------------(Method
	 * not tested yet)--------------------
	 */
	public List<Annonce> search(String text) {
		List<Annonce> results = new ArrayList<Annonce>();

		if (text != null && !text.isEmpty()) {
			String[] keyWords = text.split(" ");
			String query = "SELECT DISTINCT * FROM Annonce ";
			int i = 0;
			for (String key : keyWords) {
				if (i == 0)
					query += "WHERE title LIKE '%" + key + "%' ";
				else
					query += "OR title LIKE '%" + key + "%' ";
				i++;
			}
			query += ";";

		}
		return results;
	}

	// sauvegarder une annonce
	public Annonce saveOne(Annonce a) {
		Annonce res = a;
		a.setDate_de_publication(new java.util.Date());
		a.setValidee(false);
		if (res != null) {
			try {
				em.persist(a);
				res = em.merge(a);
			} catch (org.hibernate.exception.ConstraintViolationException e) {
				System.out.println("Ad already submit");
			}
		}
        return res;
    }
	
	

	public <T> List<T> getAll(Class<T> class1) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(class1);
		Root<T> rootReg= cq.from(class1);
		cq.select(rootReg);
		cq.distinct(true);
		TypedQuery<T> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Annonce> findAd(User u, String title) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
		Root<Annonce> rootAd = cq.from(Annonce.class);
		cq.select(rootAd);
		Predicate p1 = cb.equal(rootAd.get("title"), title);
		Predicate p2 = cb.equal(rootAd.get("user"), u); 
		cq.where(cb.and(p1, p2));
		TypedQuery<Annonce> query = em.createQuery(cq);
		return query.getResultList();
	}

	public void merge(Annonce ad) {
		em.merge(ad);
	}

	@Override
	public void save(ImagePath ip) {
		em.persist(ip);
		
	}

//	public Map getRegionsWithDeparts() {
//		Map areas = new HashMap();
//		List<Region> regions = getRegions();
//		Region reg = null;
//		Iterator<Region> it_reg = regions.iterator();
//		while (it_reg.hasNext()) {
//			reg = it_reg.next();
//			List<Departement> matched_dep = reg.getDepartements();
//			Map key_value_dep = new HashMap();
//			for (Departement departement : matched_dep) {
//				key_value_dep.put(departement.getId(), departement.getNom());
//			}
//			areas.put(reg.getId(),key_value_dep);
//		}
//		return areas;
//	}
}
