package core;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewBuilder {
	
	ArrayList<String> css_files;
	ArrayList<String> js_files;
		
	public ViewBuilder(String page) {
		css_files = new ArrayList<String>();
		js_files = new ArrayList<String>();
		
		if (page.equals("index.jsp")) {
			// CSS
			css_files.add("css/css.css");
			css_files.add("http://fonts.googleapis.com/css?family=Muli:light,regular|Raleway:100");
			// JS
			js_files.add("http://code.jquery.com/jquery-1.6.1.min.js");
			js_files.add("js/jquery.scrollTo-1.4.2-min.js");
			js_files.add("js/index.init.js");
		}
	}
	
	public String getCSS() {
		String resultat = "";
		Iterator<String> ite = css_files.iterator();
		
		if (ite != null) {
			while (ite.hasNext()) {
				String next = ite.next();
				if (next != null) {
					resultat +=  "<link href=\""+next+"\" rel=\"stylesheet\" type=\"text/css\" />";
				}
			}
		}
		
		return resultat;
	}
	
	public String getJS() {
		String resultat = "";
		Iterator<String> ite = js_files.iterator();
		
		if (ite != null) {
			while (ite.hasNext()) {
				String next = ite.next();
				if (next != null) {
					resultat +=  "<script type=\"text/javascript\" src=\""+next+"\"></script>";
				}
			}
		}
		
		return resultat;

	}
}
