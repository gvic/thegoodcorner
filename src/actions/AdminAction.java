package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdminService;

public class AdminAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Inject AdminService service;

}
