package actions;

import java.util.Map;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

import dao.UserService;
import dao.UserServiceImpl;


public class Login extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private UserService service;
	
	private String username;
	private String password;
	
	public Login() {
		super();
		try {
			InitialContext ctx = new InitialContext();
			service = (UserService) ctx.lookup("ejbremote:dao.UserService");
			System.out.println(service.toString());  
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public String execute() throws Exception {
//		System.out.println("Validating login");
//
//		if (getUsername() != null && getPassword() != null) {
//
//			System.out.println("================"+service.toString()+"====================");			
//			if (service.findByUserNameAndPassword(getUsername(), getPassword()) != null) {
//				Map session = ActionContext.getContext().getSession();
//				session.put("logged-in", "true");
//				return SUCCESS;
//
//			} else
//				return LOGIN;
//
//		} else
//			return LOGIN;
//	}

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