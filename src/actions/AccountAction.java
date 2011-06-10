package actions;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.User;

public class AccountAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject UserService service;
	
	private String name, firstname, email, login, phone, mobile;
	
	// Server-side validation
	public void validate() {
		
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			System.out.println("=== validate() method called ===");
			
			String oldEmail = service.getOne((Long) userIdO).getEmail();
			
			HashMap<String,Object> mhm = new HashMap<String,Object>();
			mhm.put("email", email);
			if ( !email.equals("") && service.findByField(mhm)!=null && !email.equals(oldEmail) ) {
				addFieldError("email", getText("email.used"));
			}
		}
		else{
			addActionError(getText("error.notloggedin"));
		}
			
	}
	
	public String input() throws Exception {
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			User userBean = service.getOne(userId);
			name = userBean.getNom();
			firstname = userBean.getPrenom();
			email = userBean.getEmail();
			login = userBean.getLogin();
			phone = userBean.getTelephoneFixe();
			mobile = userBean.getTelephonePortable();
			return INPUT;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}	
	public String updateAccount(){
		Map<String,Object>  session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			
			User userBean = service.getOne(userId);
			
			userBean.setNom(name);
			userBean.setPrenom(firstname);
			userBean.setEmail(email);
			userBean.setTelephoneFixe(phone);
			userBean.setTelephonePortable(mobile);
			
			service.updateOne(userBean);
			addActionMessage(getText("account.update.success"));
			// Because the field is disabled it is not processed...
			login = userBean.getLogin();
			return SUCCESS;
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
