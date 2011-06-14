package actions;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import dao.UserServiceImpl;
import entities.User;


public class LogInAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Inject UserService service;

	// Login form
	private String login;
	private String password;
	
	
	public String login() throws Exception {
		System.out.println("=== login() method called ===");
		addActionMessage(getText("welcome")+" "+login);
		// Find User
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("login", login);
		hm.put("md5_mdp", UserServiceImpl.md5Encryption(password));
		User u = service.getByField(hm);
		// Add session infos
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("loggedin","true");
		session.put("userId",u.getId());
		// Update some stuff on user..
		u.setDerniereConnexion(new Date(new java.util.Date().getTime()));
		service.updateOne(u);
		
		return SUCCESS;
	}
	
	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
		// Validate LogIn form
		if (login != null && password != null) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("login", login);
			if (service.getByField(hm) == null) {
				addFieldError("login", getText("errors.login"));
			}
			hm.put("md5_mdp", UserServiceImpl.md5Encryption(password));
			if (service.getByField(hm) == null) {
				addFieldError("password", getText("errors.password"));
			}
		}
	}
	
	@SkipValidation
	public String logout() throws Exception {
		System.out.println("=== logout() method called ===");
		Map<String,Object>  session = ActionContext.getContext().getSession();
		session.remove("loggedin");
		session.remove("userId");
		return SUCCESS;
  	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}