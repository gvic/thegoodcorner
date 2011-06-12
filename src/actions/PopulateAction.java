package actions;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("denomination", comBean.getDenomination());
		if(service.getByField(m) != null)
			addFieldError("comBean.denomination",getText("errors.populate.communaute.exists"));
	}

	public String processDatas() {
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
