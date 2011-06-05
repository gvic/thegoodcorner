package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import entities.Annonce;
import entities.Communaute;
import entities.Departement;

public class AdAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	@Inject AdService service;

	private Annonce annonce;
	private java.util.List<Communaute> departements;
	private java.util.List<Departement> communautes;
	
	public String execute(){
		departements = service.getCommunautes();
		communautes = service.getDepartements();
		return SUCCESS;
	}

	public java.util.List<Communaute> getDepartements() {
		return departements;
	}

	public void setDepartements(java.util.List<Communaute> departements) {
		this.departements = departements;
	}

	public java.util.List<Departement> getCommunautes() {
		return communautes;
	}

	public void setCommunautes(java.util.List<Departement> communautes) {
		this.communautes = communautes;
	}
	
	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	
}
