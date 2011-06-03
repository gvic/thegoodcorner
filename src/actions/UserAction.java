package actions;

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
	
	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
		if (userBean != null) {
			System.out.println(userBean.toString());
			if (!userBean.getLogin().equals("") && service.fieldAlreadyUsed("login", userBean.getLogin())) {
				addFieldError("userBean.login", getText("username.used"));
			}
			if (!userBean.getEmail().equals("") && service.fieldAlreadyUsed("email", userBean.getEmail())) {
				addFieldError("userBean.email", getText("email.used"));
			}
		}
	}

	public String signUp() throws Exception {
		if (service.saveOne(userBean) != null) {
			return SUCCESS;
		} else {
			addActionError(getText("signup.impossible"));
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