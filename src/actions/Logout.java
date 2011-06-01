package actions;

import java.util.Map;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {

	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		session.remove("logged-in");
		return SUCCESS;
	}

}