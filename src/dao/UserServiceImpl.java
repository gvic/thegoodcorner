package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserServiceImpl implements UserService {

		@PersistenceContext(unitName="thegoodcorner-pu") 
		private EntityManager em;
        
        public UserServiceImpl() {
			super();
		}
        
        public void setEntityManager(EntityManager em) {
            this.em = em;
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

		public User findByUserNameAndPassword(String username,
				String password) {
			User ret = null;
			User user = new User();
			user.setLogin(username);
			user.setPassword(password);
			if(em.contains(user)){
				ret = user;
			}
			return ret;
		}

		@Override
		public boolean createUser(String username, String password) {
			User u = new User();
			u.setLogin(username);
			u.setPassword(password);
			u = saveOne(u);
			return (u != null);
		}

}
