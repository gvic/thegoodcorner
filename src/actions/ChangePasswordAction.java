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
		if (login != null && newPassword != null) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("login", login);
			if (service.findByField(hm) == null) {
				addActionError(getText("errors.login"));
			}
			hm.put("md5_mdp", UserServiceImpl.md5Encryption(newPassword));
			if (service.findByField(hm) == null) {
				addActionError(getText("errors.password"));
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
			
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("login", login);
			hm.put("md5_mdp", UserServiceImpl.md5Encryption(newPassword));
			User u = service.findByField(hm);
			
			userBean.setMd5_mdp(newPassword);
			
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

}
