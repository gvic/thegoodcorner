package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

	@Override
	public List<Communaute> getCommunautes() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootCom = cq.from(Communaute.class);
		TypedQuery<Communaute> query = em.createQuery(cq.select(rootCom)
				.distinct(true));
		return query.getResultList();
	}

	@Override
	public boolean exists(String nom) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Communaute> cq = cb.createQuery(Communaute.class);
		Root<Communaute> rootCom = cq.from(Communaute.class);
		cq.select(rootCom).where(cb.equal(rootCom.get("denomination"),nom));
		TypedQuery<Communaute> query = em.createQuery(cq);
		return (query.getResultList() != null);
	}

	@Override
	public void save(Communaute c) {
		em.persist(c);
	}

}
