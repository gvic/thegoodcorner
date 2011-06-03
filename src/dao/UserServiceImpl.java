package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless(mappedName="dao.UserServiceImpl")
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
        public void deleteOne(Integer id) {
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
        public User getOne(Integer id) {
        	return em.find(User.class, id);
        }

        // sauvegarder un user
        public User saveOne(User u) {
            em.persist(u);
            return u;
        }
        
        // Check if a field is already used?
        // throws Exception in case of bad field parameter
        public boolean fieldAlreadyUsed(String field, String value) throws IllegalArgumentException {
        	boolean res = false;
        	try{
        		User u = (User) em.createQuery("SELECT p FROM User p WHERE p.:field=:value")
        							.setParameter("field", field)
        							.setParameter("value", value).getSingleResult();
        		if (u != null) {
        			res = true;
        		}
        	} catch(NoResultException e) {
        		e.printStackTrace();
        	} catch(NonUniqueResultException e) {
        		e.printStackTrace();
        		// Very Strange if it happens!
        	}
        	return res;
        }

        // mettre à jour une u
        public User updateOne(User u) {
        	User p = em.find(User.class, u.getId());
        	if (p == null) {
                    throw new DaoException(String.format("User n°[%d] inconnue", u.getId()), 2);
            }
        	return em.merge(u);
        }


		@Override
		public boolean find(User u) {
			return em.contains(u);	
		}

}
