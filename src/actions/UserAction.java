package actions;

import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;


public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	private String username;
	private String password;
	
	public String execute() throws Exception {
	    return SUCCESS;
	}
	
	public String login() throws Exception {
		if (service.findByUserNameAndPassword(getUsername(), getPassword()) != null) {
			Map session = ActionContext.getContext().getSession();
			session.put("logged-in", "true");
			return SUCCESS;
		} else {
			return LOGIN;
		}
	}

	public String signUp() {
		if (getUsername().equals("") && getPassword().equals("")) {
			if (service.createUser(username, password)) {
				return SUCCESS;
			} else {
				return ERROR;
			}	
		} else {
			return INPUT;
		}
	}
	
	public String logout() throws Exception {
		Map<?, ?> session = ActionContext.getContext().getSession();
		session.remove("logged-in");
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}