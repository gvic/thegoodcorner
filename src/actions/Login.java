package actions;

import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserService;


public class Login extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject
	private UserService service;
	private String username;
	private String password;
	
//	public Login() {
//		super();
//		service = new UserServiceImpl();
//	}
//	
	public Login(UserService service) {
		super();
		this.service = service;
	}

	public String execute() throws Exception {
		System.out.println("Validating login");

		if (getUsername() != null && getPassword() != null) {

			System.out.println("================"+service.toString()+"====================");			
			if (service.findByUserNameAndPassword(getUsername(), getPassword()) != null) {
				Map session = ActionContext.getContext().getSession();
				session.put("logged-in", "true");
				return SUCCESS;

			} else
				return LOGIN;

		} else
			return LOGIN;
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