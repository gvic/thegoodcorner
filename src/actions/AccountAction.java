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
	
	private User userBean;
	private long regionId;
	private long regionIdKey = -1;
	private long departementId;
	private long departementIdKey = -1;
	private List<Region> regions;
	
	/**
	 * 	Method launched at the start of the form in order to valide the form coherency
	 *  It also validates the criteria in the AccountAction.xml file
	 */
	public void validate() {
		System.out.println("=== validate() method called ===");
				
		Map<String,Object>  session = ActionContext.getContext().getSession();
		userBean = (User) session.get("user");
		if (userBean != null) {			
			HashMap<String,Object> mhm = new HashMap<String,Object>();
			mhm.put("email", userBean.getEmail());
			if ( !userBean.getEmail().equals("") && service.getByField(mhm)!=null) {
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
		userBean = (User) session.get("user");
		if (userBean != null) {
			// Compulsary for the Double Select
			regions = adService.getRegions();
			if(userBean.getRegion() != null)
				regionIdKey = userBean.getRegion().getId();
			if (userBean.getDepartement() != null)
				departementIdKey = userBean.getDepartement().getId();
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
		userBean = (User) session.get("user");
		if (userBean != null) {
			service.updateOne(userBean);
			addActionMessage(getText("account.update.success"));
			return SUCCESS;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
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
