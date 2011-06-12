package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.PopulateService;
import entities.Communaute;

public class PopulateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject
	private PopulateService service;

	private Communaute comBean;

	public void validate() {
		if (comBean != null) {
			if(service.exists(comBean.getDenomination()).size() > 0)
					addFieldError("comBean.denomination",getText("errors.populate.communaute.exists"));
		}
	}

	public String processDatas() {
		System.out.println(service.exists(comBean.getDenomination()).toString());
		System.out.println((service.exists(comBean.getDenomination()).size()));

		service.save(comBean);
		return SUCCESS;
	}

	public Communaute getComBean() {
		return comBean;
	}

	public void setComBean(Communaute comBean) {
		this.comBean = comBean;
	}

	

}
