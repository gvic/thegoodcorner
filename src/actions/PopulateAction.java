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

	@Override
	public void validate() {
		super.validate();
		if (comBean != null) {
			if (comBean.getDenomination() == null) {
				addActionError(getText("errors.required"));
			}else{
				if(service.exists(comBean.getDenomination()).size() > 0)
					addActionError(getText("errors.populate.communaute.exists"));
			}
			if (comBean.getDescription() == null) {
				addActionError(getText("errors.required"));
			}
		}
	}

	public String input() throws Exception {

		return super.input();
	}

	public String processDatas() {
		System.out.println(service.exists(comBean.getDenomination()).toString());
		System.out.println((service.exists(comBean.getDenomination()).size()));

//		if (service.exists(comBean.getDenomination()).size() > 0) {
//			addActionError(getText("errors.populate.communaute.exists"));
//			return ERROR;
//		} else {
			service.save(comBean);
			return SUCCESS;
//		}
	}

	public Communaute getComBean() {
		return comBean;
	}

	public void setComBean(Communaute comBean) {
		this.comBean = comBean;
	}

	

}
