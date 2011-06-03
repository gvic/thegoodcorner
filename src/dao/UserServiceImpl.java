package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

        // sauvegarder une u
        public User saveOne(User u) {
                em.persist(u);
                return u;
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
