package dao;

import java.util.List;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface UserService {
        // obtenir une personne via son identifiant
        public User getOne(Integer id);

        // obtenir toutes les personnes
        public List<User> getAll();

        // sauvegarder une personne
        public User saveOne(User u);

        // mettre à jour une personne
        public User updateOne(User u);

        // supprimer une personne via son identifiant
        public void deleteOne(Integer id);

        // obtenir les personnes dont le nom correspond àun modèle
        public List<User> getAllLike(String modele);

		public User findByUserNameAndPassword(String username, String password);

		public boolean createUser(String username, String password);

}
