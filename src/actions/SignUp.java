package actions;

import com.opensymphony.xwork2.ActionSupport;


import dao.UserService;
import dao.UserServiceImpl;

public class SignUp extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private UserService service;
	private String username;
	private String password;

	public SignUp() {
		super();
		service = new UserServiceImpl();
	}
	
//	public String execute() throws Exception {
//		System.out.println("Validating login");
//		if (username != null && password != null){
//			if (service.createUser(username, password)) {
//				return SUCCESS;
//			} else {
//				return ERROR;
//			}	
//		} else{
//			return INPUT;
//		}
//		
//	}

	

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
