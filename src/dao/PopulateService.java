package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.Communaute;

@Remote
public interface PopulateService {

	List<Communaute> getCommunautes();
	
	public Communaute getByField(Map<String, Object> fieldValue);
	
	void save(Communaute c);
	
	Communaute getOne(long id);
}
