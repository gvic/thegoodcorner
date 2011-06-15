package actions;

import java.util.HashMap;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import core.SimpleMail;
import dao.AdService;
import dao.UserService;
import entities.Annonce;
import entities.User;
public class ConfirmAd extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject	AdService service;
	@Inject UserService uService;
	private String key;	
	
	public String execute() throws Exception {
		String s = SimpleMail.hexToStr(key);
		String[] a = s.split("_"); //0:login 1:idAd
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("login", a[0]);
		User user = uService.getByField(hm);
		Annonce ad = service.getOne(Annonce.class,Long.parseLong(a[1]));
		System.out.println(a[1]);
		System.out.println(a[0]);
		if(ad != null && user != null){
			ad.setValidee(true);
			service.merge(ad);
			addActionMessage(getText("ad.validated"));
		} else{
			addActionError(getText("errors.ad.non.validated"));
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
	

}
