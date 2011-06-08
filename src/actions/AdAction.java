package actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import dao.AdService;
import entities.Annonce;
import entities.Communaute;
import entities.Departement;
import entities.Region;

public class AdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject	AdService service;

	private Annonce annonce;
	private List<String> geographicAreas;
	private List<Communaute> communautes;

	public String input() throws Exception {
		List<Region> regions = service.getRegions();
		setCommunautes(service.getCommunautes());
		geographicAreas = new ArrayList<String>();
		Region reg = null;
		Iterator<Region> it_reg = regions.iterator();
		while(it_reg.hasNext()){
			reg = it_reg.next();
			List<Departement> matched_dep = reg.getDepartements();
			geographicAreas.add(reg.getNom());
			Iterator<Departement> it = matched_dep.iterator();
			while (it.hasNext()) {
				geographicAreas.add("- "+it.next().getNom());
			}
		}

		return super.input();
	}

	public String processDatas() {
		System.out.println(annonce.getDescription());
		service.save(annonce);
		return SUCCESS;	
	}

	public List<String> getGeographicAreas() {
		return geographicAreas;
	}

	public void setGeographicAreas(List<String> geographicAreas) {
		this.geographicAreas = geographicAreas;
	}

	public Annonce getAnnonce() {
		return annonce;
	}
	
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public void setCommunautes(List<Communaute> communautes) {
		this.communautes = communautes;
	}

	public List<Communaute> getCommunautes() {
		return communautes;
	}

}
