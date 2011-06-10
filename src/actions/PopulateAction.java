package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.PopulateService;
import entities.Communaute;

public class PopulateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject
	private PopulateService service;

	private Communaute communaute;

	@Override
	public void validate() {
		super.validate();
		if (communaute != null) {
			if (communaute.getDenomination() == null) {
				addActionError(getText("errors.populate.communaute.denomination"));
			}
			if (communaute.getDescription() == null) {
				addActionError(getText("errors.populate.communaute.description"));
			}
		}
	}

	public String input() throws Exception {

		return super.input();
	}

	public String processDatas() {

		if (service.exists(communaute.getDenomination())) {
			return ERROR;
		} else {
			service.save(communaute);
			return SUCCESS;
		}
	}

	public Communaute getCommunaute() {
		return communaute;
	}

	public void setCommunaute(Communaute communaute) {
		this.communaute = communaute;
	}

	public Communaute getComBean() {
		return communaute;
	}

	public void setComBean(Communaute comBean) {
		this.communaute = comBean;
	}

}
