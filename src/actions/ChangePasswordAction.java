package actions;

import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import dao.UserServiceImpl;

import entities.User;

/**
 * 
 * Actions for an user to change his password
 *
 */
public class ChangePasswordAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	
	private String login, oldPassword, newPassword, confirmPassword;
	
	/**
	 *  Method launched at the start of the form in order to valide the form coherency
	 *  It also validates the criteria in the ChangePasswordAction.xml file
	 */
	public void validate() {
		System.out.println("=== validate() method called ===");
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user != null) {
			if (!user.getMd5_mdp().equals(UserServiceImpl.md5Encryption(oldPassword))) {
				addFieldError("oldPassword",getText("errors.oldPassword"));	
			}		
		}
		else {
			addActionError(getText("error.notloggedin"));
		}		
	}
	
	/**
	 * Method used to instanciate the form
	 */
	public String input() throws Exception {
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user != null) {
			login = user.getLogin();
			return INPUT;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}	
	
	/**
	 * Method used when submitting the form. It changes the password.
	 * @return Action result
	 */
	public String changePassword(){
		Map<String,Object>  session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user != null) {			
			service.changePassword(user, newPassword);
			addActionMessage(getText("account.update.success"));
			// Because the field is disabled it is not processed...
			login = user.getLogin();
			return SUCCESS;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
