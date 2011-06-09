package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;
import entities.User;
import entities.Ville;

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
	public ArrayList<Annonce> listAnnoncesFrom(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annonce> listAnnoncesFrom(Communaute c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annonce> listAnnoncesFrom(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annonce> listAnnoncesFrom(Ville v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annonce> listAnnoncesFrom(Departement d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Annonce> listAnnoncesFrom(Region r) {
		// TODO Auto-generated method stub
		return null;
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
		System.out.println(query.getResultList().size());
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
		System.out.println(query.getResultList().size());
		return query.getResultList();
	}

}
