package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import dao.PopulateService;

public class PopulateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject private PopulateService service;
	
	public String adminPopulate(){
		
		return SUCCESS;
	}
	
	public String addCommunaute_input() throws Exception{
		return super.input();
	}
}
