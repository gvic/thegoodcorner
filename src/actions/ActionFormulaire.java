package actions;

import com.opensymphony.xwork2.ActionSupport;

public class ActionFormulaire extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String titre, description;
	private float prix;

	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public float getPrix() {
		return prix;
	}
}
