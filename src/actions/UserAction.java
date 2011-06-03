package actions;

import java.sql.Date;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.User;


public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Inject UserService service;

	private User userBean;
	private String confirmPassword;
	
	public String login() throws Exception {
		System.out.println("=== login() method called ===");
		if (service.find(userBean)) {
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String signUp() throws Exception {
		System.out.println("=== signUp() method called ===");
		System.out.println(userBean.toString());
		if (service.fieldAlreadyUsed("login", userBean.getLogin())) {
			addActionError(getText("username.used"));
			return ERROR;
		}
		if (service.fieldAlreadyUsed("email", userBean.getEmail())) {
			addActionError(getText("email.used"));
			return ERROR;
		}
		userBean.setInscritDepuis(new Date(new java.util.Date().getTime())); // Subscribed today!
		if (service.saveOne(userBean) != null) {
			return SUCCESS;
		} else {
			return ERROR;
		}	
	}
	
	public String logout() throws Exception {
		Map<?, ?> session = ActionContext.getContext().getSession();
		session.remove("logged-in");
		return SUCCESS;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public User getUserBean() {
		return userBean;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

}