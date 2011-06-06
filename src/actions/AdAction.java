package actions;

import java.util.List;
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
//	private List<Departement> departements;
//	private List<Communaute> communautes;
	private String[] departements = {"Isere","Loire"};
	private String[] communautes = {"Geek","Sportif"};
	
	public String submitAd(){
//		departements = service.getDepartements();
//		communautes = service.getCommunautes();
		service.save(annonce);
		return SUCCESS;
	}

//	public List<Departement> getDepartements() {
//		return departements;
//	}
//
//	public void setDepartements(java.util.List<Departement> departements) {
//		this.departements = departements;
//	}
//
//	public List<Communaute> getCommunautes() {
//		return communautes;
//	}
//
//	public void setCommunautes(java.util.List<Communaute> communautes) {
//		this.communautes = communautes;
//	}
	
	public Annonce getAnnonce() {
		return annonce;
	}

	public String[] getDepartements() {
		return departements;
	}

	public void setDepartements(String[] departements) {
		this.departements = departements;
	}

	public String[] getCommunautes() {
		return communautes;
	}

	public void setCommunautes(String[] communautes) {
		this.communautes = communautes;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	
}
