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
	
//	public String execute() throws Exception {
//	    return SUCCESS;
//	}
	
//	public String login() throws Exception {
//		System.out.println("=== login() method called ===");
//		if (username.equals("") && password.equals("")) {
//			if (service.findByUserNameAndPassword(getUsername(), getPassword()) != null) {
//				System.out.println("=== User found! ===");
//				Map session = ActionContext.getContext().getSession();
//				session.put("logged-in", "true");
//				return SUCCESS;
//			} else {
//				return LOGIN;
//			}
//		} else {
//			return INPUT;
//		}
//	}

	public String signUp() throws Exception {
		System.out.println("=== signUp() method called ===");
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

}