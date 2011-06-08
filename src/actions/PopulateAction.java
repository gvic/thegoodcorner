package actions;

import java.util.List;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import dao.PopulateService;
import entities.Communaute;

public class PopulateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject private PopulateService service;
	private List<Communaute> communautes;
	private String communaute;
	private String communauteDesc;
	
	public String adminPopulate(){
		
		return SUCCESS;
	}
	
	public String addCommunaute() throws Exception{
		String ret = super.input();
		System.out.println("===== addCommunaute method called =====");
		System.out.println("===== communaute: "+communaute+", desc:"+communauteDesc+"=====");
		if(communaute != null && communauteDesc != null){
			System.out.println("==== validation OK =====");
			if(service.exists(communaute)){
				System.out.println("the comunity exists");
				addActionError(getText("errors.populate.communaute_exists"));
				ret = ERROR;
			} else{
				Communaute c = new Communaute();
				c.setDenomination(communaute);
				c.setDescription(communauteDesc);
				try{
					service.save(c);
				} catch (Exception e) {
					System.out.println("exception lors de l'enregistrement");
				}
				
				System.out.println("the comunity has been created");
				ret = SUCCESS;
			}
		}
		System.out.println("===== returned value : "+ ret +" =====");
		return ret;
	}

	public String getCommunauteDesc() {
		return communauteDesc;
	}

	public void setCommunauteDesc(String communauteDesc) {
		this.communauteDesc = communauteDesc;
	}

	public List<Communaute> getCommunautes() {
		return communautes;
	}

	public void setCommunautes(List<Communaute> communautes) {
		this.communautes = communautes;
	}

	public String getCommunaute() {
		return communaute;
	}

	public void setCommunaute(String communaute) {
		this.communaute = communaute;
	}
	

	
}
