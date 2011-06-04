package actions;

import java.util.HashMap;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.User;


public class SignUpAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Inject UserService service;

	// Signup form
	private User userBean;
	private String confirmPassword;

	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
		// Validate SignUp form
		if (userBean != null) {
			System.out.println(userBean.toString());
			HashMap<String,String> mhm = new HashMap<String,String>();
			mhm.put("login", userBean.getLogin());
			if (!userBean.getLogin().equals("") && service.findByField(mhm)!=null) {
				addFieldError("userBean.login", getText("username.used"));
			}
			mhm.clear();
			mhm.put("email", userBean.getEmail());
			if (!userBean.getEmail().equals("") && service.findByField(mhm)!=null) {
				addFieldError("userBean.email", getText("email.used"));
			}
		}
	}

	public String signUp() throws Exception {
		System.out.println("=== signUp() method called ===");
		if (service.saveOne(userBean) != null) {
			return SUCCESS;
		} else {
			addActionError(getText("signup.impossible"));
			return ERROR;
		}	
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