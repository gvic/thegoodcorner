package actions;

import java.util.List;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;

import entities.Categorie;
import entities.Communaute;
import entities.Region;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Inject AdService service;
	
	private List<Region> regions;
	private List<Categorie> categories;
	private List<Communaute> communities;
	
	public IndexAction() {
		// TODO Auto-generated constructor stub
	}
	
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public List<Region> getRegions() {
		return service.getRegions();
	}
	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}
	public List<Categorie> getCategories() {
		return service.getCategories();
	}
	public void setCommunities(List<Communaute> communities) {
		this.communities = communities;
	}
	public List<Communaute> getCommunities() {
		return service.getCommunautes();
	}

}
