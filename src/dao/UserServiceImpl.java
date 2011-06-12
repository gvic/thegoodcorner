package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import entities.Adresse;
import entities.Departement;
import entities.Region;
import entities.User;

@Stateless(mappedName = "dao.UserServiceImpl")
public class UserServiceImpl implements UserService {
	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	// supprimer une u via son identifiant
	public void deleteOne(Long id) {
		User u = em.find(User.class, id);
		if (u == null) {
			throw new DaoException(String.format("User n°[%d] inconnue", id), 2);
		}
		em.remove(u);
	}

	@SuppressWarnings("unchecked")
	// obtenir toutes les users
	public List<User> getAll() {
		return em.createQuery("select p from User p").getResultList();
	}

	@SuppressWarnings("unchecked")
	// obtenir les users dont le nom correspond à un modèle
	public List<User> getAllLike(String modele) {
		return em.createQuery("select p from User p where p.nom like :modele")
				.setParameter("modele", modele).getResultList();
	}

	// obtenir une u via son identifiant
	public User getOne(long id) {
		return em.find(User.class, id);
	}

	// sauvegarder un user
	// (Signup a user)
	public User saveOne(User u) {
		User res = u;
		u.setInscritDepuis(new Date(new java.util.Date().getTime())); // Subscribed
																		// today!
		u.setMd5_mdp(md5Encryption(u.getMd5_mdp()));
		if (res != null) {
			em.persist(u);
		}
		return res;
	}

	static public String md5Encryption(String text) {
		String toEnc = text;
		String res = "";
		MessageDigest mdEnc;
		try {
			mdEnc = MessageDigest.getInstance("MD5");// Encryption algorithm
			mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
			res = new java.math.BigInteger(1, mdEnc.digest()).toString(16); // Encrypted
																			// string
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}

	// Check if a field is already used?
	// throws Exception in case of bad field parameter
	public User findByField(Map<String, Object> fieldValue)
			throws IllegalArgumentException {
		User res = null;
		try {
			Set<String> keys = fieldValue.keySet();
			Iterator<String> iteKeys = keys.iterator();
			Collection<Object> values = fieldValue.values();
			Iterator<Object> iteValues = values.iterator();
			String query = "SELECT p FROM User p WHERE";
			// Iterator on keys
			int i = 0;
			while (iteKeys.hasNext()) {
				if (i != 0) {
					query += " AND";
				}
				query += " p." + iteKeys.next() + "=:value" + i;
				i++;
			}
			TypedQuery<User> q = em.createQuery(query, User.class);
			// Iterator on values
			i = 0;
			while (iteValues.hasNext()) {
				q.setParameter("value" + i, iteValues.next());
				i++;
			}
			User u = q.getSingleResult();
			if (u != null) {
				res = u;
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			// Very Strange if it happens!
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return res;
	}

	// mettre à jour une u
	public User updateOne(User u) {
		User p = em.find(User.class, u.getId());
		if (p == null) {
			throw new DaoException(String.format("User n°[%d] inconnue",
					u.getId()), 2);
		}
		return em.merge(u);
	}

	public boolean isUser(String username, String password) {
		boolean res = false;

		return res;
	}

	@Override
	public Adresse save(Adresse a) {
		if (em.find(Adresse.class, a.getId()) != null)
			em.persist(a);
		return a;
	}

	@Override
	public Departement getDepartement(long departementId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Departement> cq = cb.createQuery(Departement.class);
		Root<Departement> rootDep = cq.from(Departement.class);
		cq.select(rootDep).where(cb.equal(rootDep.get("id"), departementId)).distinct(true);
		TypedQuery<Departement> query = em.createQuery(cq);
		return query.getSingleResult();
	}

	@Override
	public Region getRegion(long regionId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Region> cq = cb.createQuery(Region.class);
		Root<Region> rootReg = cq.from(Region.class);
		cq.select(rootReg).where(cb.equal(rootReg.get("id"), regionId)).distinct(true);
		TypedQuery<Region> query = em.createQuery(cq);
		return query.getSingleResult();
	}


}
