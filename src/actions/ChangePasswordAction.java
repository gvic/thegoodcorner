package actions;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import dao.UserServiceImpl;

import entities.User;

public class ChangePasswordAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	
	private String login, oldPassword, newPassword;
	
	public void validate() {
		System.out.println("=== validate() method called ===");
		if (login != null && oldPassword != null) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("login", login);
			if (service.findByField(hm) == null) {
				addActionError(getText("errors.login"));
			}
			hm.put("md5_mdp", UserServiceImpl.md5Encryption(oldPassword));
			if (service.findByField(hm) == null) {
				addActionError(getText("errors.oldPassword"));
			}
		}
	}
	
	public String input() throws Exception {
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			User userBean = service.getOne(userId);
			login = userBean.getLogin();
			return INPUT;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}	
	
	public String changePassword(){
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			
			User userBean = service.getOne(userId);			
			userBean.setMd5_mdp(UserServiceImpl.md5Encryption(newPassword));
			
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

}
