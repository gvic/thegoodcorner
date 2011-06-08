package dao;

import java.util.List;
import javax.ejb.Remote;

import entities.Communaute;


@Remote
public interface PopulateService {

	List<Communaute> getCommunautes();
	
	boolean exists(String nom);

	void save(Communaute c);
	
}
