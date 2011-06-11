package actions;

import java.util.List;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import dao.AdServiceImpl;
import entities.Annonce;

public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = -2767668745208342409L;	
	@Inject AdServiceImpl service;
	private String text;
	private List<Annonce> results;

	/**
	 * Method used to search a product.
	 * @return Action result
	 */
	public String search(){
		results = service.search(text);
		System.out.println(results.toString());
		return SUCCESS;
	}
	
	public String input(){
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

}
