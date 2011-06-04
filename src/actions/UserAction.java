package actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.User;


public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Inject UserService service;

	// Signup form
	private User userBean;
	private String confirmPassword;
	
	// Login form
	private String login;
	private String password;
	
	public String login() throws Exception {
		System.out.println("=== login() method called ===");
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("login", login);
		hm.put("md5_mdp", password);
		if (service.findByField(hm) != null) {
			return SUCCESS;
		} else {
			addActionError(getText("errors.login"));
			return ERROR;
		}
	}
	
	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
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