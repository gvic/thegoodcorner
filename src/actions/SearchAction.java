package actions;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdServiceImpl;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.User;

public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = -2767668745208342409L;
	@Inject
	AdServiceImpl service;
	private String text;
	private List<Annonce> results;
	private long regionIdKey = -1;
	private long departementIdKey = -1;
	private int codePostal;
	private long categorieId = -1;
	private List<Communaute> communities;
	private List<Categorie> categories;
	private String keywords;

	/**
	 * Method used to search a product.
	 * 
	 * @return Action result
	 */
	public String search() {
		results = service.search(text);
		System.out.println(results.toString());
		return SUCCESS;
	}

	public String input() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User userBean = (User) session.get("user");
		// Compulsary for the Double Select
		if (userBean != null) {
			System.out.println(userBean);
			if (userBean.getRegion() != null)
				regionIdKey = userBean.getRegion().getId();
			if (userBean.getDepartement() != null)
				departementIdKey = userBean.getDepartement().getId();
			if (userBean.getCodePostal() != 0) {
				codePostal = userBean.getCodePostal();
			}
		}
		return INPUT;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Annonce> getResults() {
		return results;
	}

	public void setResults(List<Annonce> results) {
		this.results = results;
	}

	public long getRegionIdKey() {
		return regionIdKey;
	}

	public void setRegionIdKey(long regionIdKey) {
		this.regionIdKey = regionIdKey;
	}

	public long getDepartementIdKey() {
		return departementIdKey;
	}

	public void setDepartementIdKey(long departementIdKey) {
		this.departementIdKey = departementIdKey;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public long getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public void setCommunities(List<Communaute> communautes) {
		this.communities = communautes;
	}

	public List<Communaute> getCommunities() {
		return service.getCommunautes();
	}

	public List<Categorie> getCategories() {
		return service.getCategories();
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getKeywords() {
		return keywords;
	}
}
