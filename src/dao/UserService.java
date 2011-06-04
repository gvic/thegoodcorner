package dao;

import java.util.List;
import java.util.Map;

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

        public boolean isUser(String username, String password);
		
        // Find user by map of fields-values
		public User findByField(Map<String,String> fieldValue) throws IllegalArgumentException; 

}
