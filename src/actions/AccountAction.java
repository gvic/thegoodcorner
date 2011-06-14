package actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import dao.UserService;
import entities.Region;
import entities.User;

/**
 * Actions for an user to update his account data (except his password).
 *
 */
public class AccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	@Inject AdService adService;
	
	private String name, firstname, email, login, phone, mobile;
    private int codePostal; //XML Validation fails...
	private long regionIdKey = -1;
	private long departementIdKey = -1;
	private Region region;
	private Region departement;	
	private List<Region> regions;
	
	/**
	 * 	Method launched at the start of the form in order to valide the form coherency
	 *  It also validates the criteria in the AccountAction.xml file
	 */
	public void validate() {
		System.out.println("=== validate() method called ===");
				
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User userBean = (User) session.get("user");
		if (userBean != null) {			
			String oldEmail = userBean.getEmail();
            HashMap<String,Object> mhm = new HashMap<String,Object>();
            mhm.put("email", email);
            if ( !email.equals("") && service.getByField(mhm)!=null && !email.equals(oldEmail) ) {
            	addFieldError("email", getText("email.used"));
            }
		}
		else{
			addActionError(getText("error.notloggedin"));
		}
	}
	
	/**
	 * Method used to instanciate the form
	 */
	public String input() throws Exception {
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User userBean = (User) session.get("user");
		// Compulsary for the Double Select
		regions = adService.getRegions();
		if (userBean != null) {
			System.out.println(userBean);
			if(userBean.getRegion() != null)
				regionIdKey = userBean.getRegion().getId();
			if (userBean.getDepartement() != null)
				departementIdKey = userBean.getDepartement().getId();
			codePostal = userBean.getCodePostal();
            name = userBean.getNom();
            firstname = userBean.getPrenom();
            email = userBean.getEmail();
            login = userBean.getLogin();
            phone = userBean.getTelephoneFixe();
            mobile = userBean.getTelephonePortable();
			return INPUT;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}	
	
	/**
	 * Method used when submitting the form. It updates the account.
	 * @return Action result
	 */
	public String updateAccount(){
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User userBean = (User) session.get("user");

		if (userBean != null) {
            if (departementIdKey != -1)
                userBean.setDepartement(service.getDepartement(departementIdKey));
            if (regionIdKey != -1)
                userBean.setRegion(service.getRegion(regionIdKey));
            userBean.setCodePostal(codePostal);
            userBean.setNom(name);
            userBean.setPrenom(firstname);
            userBean.setEmail(email);
            userBean.setTelephoneFixe(phone);
            userBean.setTelephonePortable(mobile);
			service.updateOne(userBean);
			session.remove("user");
			session.put("user",userBean);
			addActionMessage(getText("account.update.success"));
			return SUCCESS;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public void setRegionIdKey(long regionIdKey) {
		this.regionIdKey = regionIdKey;
	}

	public long getRegionIdKey() {
		return regionIdKey;
	}

	public void setDepartementIdKey(long departementIdKey) {
		this.departementIdKey = departementIdKey;
	}

	public long getDepartementIdKey() {
		return departementIdKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

	public void setDepartement(Region departement) {
		this.departement = departement;
	}

	public Region getDepartement() {
		return departement;
	}
	
}
