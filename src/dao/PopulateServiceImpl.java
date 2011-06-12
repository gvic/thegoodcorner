package dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Communaute;

@Stateless(mappedName = "dao.PopulateServiceImpl")
public class PopulateServiceImpl implements PopulateService {

	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public List<Communaute> getCommunautes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootCom = cq.from(Communaute.class);
		TypedQuery<Communaute> query = em.createQuery(cq.select(rootCom)
				.distinct(true));
		return query.getResultList();
	}

	// Check if a field is already used?
	// throws Exception in case of bad field parameter
	public Communaute getByField(Map<String, Object> fieldValue) {
		Communaute c = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootReg = cq.from(Communaute.class);
		for (Entry<String, Object> entry : fieldValue.entrySet()) {
			cq.select(rootReg).where(cb.equal(rootReg.get(entry.getKey()), entry.getValue()))
				.distinct(true);
		}
		TypedQuery<Communaute> query = em.createQuery(cq);
		try {
			c = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return c;
	}

	public void save(Communaute c) {
		em.persist(c);
	}

	public Communaute getOne(long id) {
		return em.find(Communaute.class, id);
	}

}
