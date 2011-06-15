package actions;

import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import entities.Annonce;
import entities.User;

public class DeleteAction extends ActionSupport {

	private static final long serialVersionUID = -3862998745885884707L;
	private long annonceId;
	@Inject AdService service;
	
	/**
	 * Verif user qu'est connecté a les droits sur l'id de l'annonce
	 */
	
	@Override
	public String execute() throws Exception {
		Annonce ad = service.getOne(Annonce.class, annonceId);
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User userBean = (User) session.get("user");
		if(userBean != null && ad.getUser().getId() == userBean.getId()){
			service.delete(ad);
		}
		
		return super.execute();
	}
	
	
	public long getAnnonceId() {
		return annonceId;
	}
	public void setAnnonceId(long annonceId) {
		this.annonceId = annonceId;
	}

	public AdService getService() {
		return service;
	}
	public void setService(AdService service) {
		this.service = service;
	}

}
