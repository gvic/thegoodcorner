package dao;

import java.util.List;
import javax.ejb.Remote;

import entities.Communaute;


@Remote
public interface PopulateService {

	List<Communaute> getCommunautes();
	
	public boolean exists(String nom, String desc);
	
	void save(Communaute c);
	
}
