package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;

public class AccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	
	private String name, firstname, email, login;
	
	public String showAccount(){
		int id = 1;
		entities.User u = service.getOne(id) ;
		setName(u.getNom());
		firstname = u.getPrenom();
		email = u.getEmail();
		setLogin(u.getLogin());
		
		return SUCCESS;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
}
