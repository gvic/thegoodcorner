package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.Adresse;
import entities.Departement;
import entities.Region;
import entities.User;

@Remote
public interface UserService {
        // obtenir une personne via son identifiant
        public User getOne(long id);

        // obtenir toutes les personnes
        public List<User> getAll();

        // sauvegarder une personne
        public User saveOne(User u);

        // mettre à jour une personne
        public User updateOne(User u);

        // supprimer une personne via son identifiant
        public void deleteOne(Long id);

        public boolean isUser(String username, String password);
		
        // Find user by map of fields-values
		public User getByField(Map<String,Object> fieldValue); 
		
		public Adresse save(Adresse a);

		public Departement getDepartement(long departementId);

		public Region getRegion(long regionId);

		public void changePassword(User user, String newPassword);
		
}
