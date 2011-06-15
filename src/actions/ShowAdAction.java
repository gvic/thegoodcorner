package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import entities.Annonce;

public class ShowAdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Inject	AdService service;
	
	private long adId;
	private Annonce annonceBean;
	
	public void validate() {
		setAnnonceBean((Annonce)service.getOne(Annonce.class, adId));
		if (annonceBean == null) {
			addActionError(getText("ad.id.not.found"));
		}
	}
	
	public String execute() throws Exception {
		return super.execute();
	}

	public void setAdId(long adId) {
		this.adId = adId;
	}

	public long getAdId() {
		return adId;
	}

	public void setAnnonceBean(Annonce annonceBean) {
		this.annonceBean = annonceBean;
	}

	public Annonce getAnnonceBean() {
		return annonceBean;
	}

}
