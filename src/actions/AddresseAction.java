package actions;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserService;
import entities.Adresse;

public class AddresseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject
	UserService service;

	private Adresse addresseBean;

	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
		// Validate SignUp form
		if (addresseBean != null) {
			System.out.println(addresseBean.toString());
		}
	}

	public String addAddresse() {
		System.out.println("=== addAddresse() method called ===");
		if (addresseBean != null) {
			if (service.save(addresseBean) != null) {
				return SUCCESS;
			} else {
				addActionError(getText("signup.impossible"));
				return ERROR;
			}
		} else{
			return INPUT;
		}
	}

	public Adresse getAdresseBean() {
		return addresseBean;
	}

	public void setAdresseBean(Adresse adresseBean) {
		this.addresseBean = adresseBean;
	}

}
