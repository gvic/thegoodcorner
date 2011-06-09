package actions;

import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.User;

public class AccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	
	private User userBean;
	
//	private String name, firstname, email, login;
//	
//	public String showAccount() throws Exception{
//		long id = 16;
//		entities.User u = service.getOne(id) ;
//		setName(u.getNom());
//		firstname = u.getPrenom();
//		email = u.getEmail();
//		setLogin(u.getLogin());
//		
//		return super.input();
//	}
//	
	public String input() throws Exception {
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userId = session.get("userId");
		if (userId != null) {
			long id = (Long) userId;
			userBean = service.getOne(id) ;
			return INPUT;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}
	
	public void validate() {
		System.out.println("=== validate() method called ===");
		System.out.println("UserId : "+userBean.getId());
		addActionError("test error");
	}
	
	public String updateAccount(){
		System.out.println("=== updateAccount() method called ===");
		System.out.println("UserId : "+userBean.getId());
		service.updateOne(userBean);
		addActionMessage(getText("account.update.success"));
		return SUCCESS;
	}

//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//	public String getLogin() {
//		return login;
//	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public User getUserBean() {
		return userBean;
	}
}
