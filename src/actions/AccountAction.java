package actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import dao.UserService;
import entities.Departement;
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
	private long regionId;
	private long regionIdKey;
	private long departementId;
	private long departementIdKey;
	private List<Region> regions;
	private Region region;
	private List<Departement> departements;
	private Departement departement;
	
	/**
	 * 	Method launched at the start of the form in order to valide the form coherency
	 *  It also validates the criteria in the AccountAction.xml file
	 */
	public void validate() {
		
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			System.out.println("=== validate() method called ===");
			
			String oldEmail = service.getOne((Long) userIdO).getEmail();
			
			HashMap<String,Object> mhm = new HashMap<String,Object>();
			mhm.put("email", email);
			if ( !email.equals("") && service.findByField(mhm)!=null && !email.equals(oldEmail) ) {
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
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			User userBean = service.getOne(userId);
			regions = adService.getRegions();
			regionIdKey = userBean.getRegion().getId();
			region = userBean.getRegion();
			System.out.println(region);
			departements = adService.getDepartements();
			departementIdKey = userBean.getDepartement().getId();
			departement = userBean.getDepartement();
			System.out.println(departement);
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
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			
			User userBean = service.getOne(userId);
			
			userBean.setDepartement(service.getDepartement(departementId));
			userBean.setRegion(service.getRegion(regionId));
			System.out.println(service.getRegion(regionId));
			System.out.println(service.getDepartement(departementId));
			userBean.setCodePostal(codePostal);
			userBean.setNom(name);
			userBean.setPrenom(firstname);
			userBean.setEmail(email);
			userBean.setTelephoneFixe(phone);
			userBean.setTelephonePortable(mobile);
			
			service.updateOne(userBean);
			addActionMessage(getText("account.update.success"));
			// Because the field is disabled it is not processed...
			login = userBean.getLogin();
			return SUCCESS;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
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

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(long departementId) {
		this.departementId = departementId;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
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
	
}
