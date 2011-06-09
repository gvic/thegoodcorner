package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import dao.AdService;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;

public class AdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Inject
	AdService service;

	private Annonce annonce;
	private String title;
	private String description;
	private int price;
	private List<String> geographicAreas;
	private Set<String> categories;
	private List<Communaute> communautes;
	private String geographicArea;
	private File image;// The actual file
	private String uploadContentType; // The content type of the file
	private String uploadFileName; // The uploaded file name
	private String fileCaption;// The caption of the file entered by use

	public String input() throws Exception {
		List<Region> regions = service.getRegions();
		List<Categorie> master_cat = service.getMasterCategory();
		List<Categorie> fst_level_cat = service.getFirstLevelCategories();
		Iterator<Categorie> it_cat = fst_level_cat.iterator();
		categories = new HashSet<String>();
		categories.add(master_cat.get(0).getNom());
		while (it_cat.hasNext()) {
			Categorie p = it_cat.next();
			categories.add(p.getNom());
			for(Categorie son : service.getSonsCategories(p.getId())){
				categories.add(son.getNom());
			}
		}

		setCommunautes(service.getCommunautes());
		geographicAreas = new ArrayList<String>();
		Region reg = null;
		Iterator<Region> it_reg = regions.iterator();
		while (it_reg.hasNext()) {
			reg = it_reg.next();
			List<Departement> matched_dep = reg.getDepartements();
			geographicAreas.add(reg.getNom());
			Iterator<Departement> it = matched_dep.iterator();
			while (it.hasNext()) {
				geographicAreas.add("- " + it.next().getNom());
			}
		}

		/***
		 * FAIRE ENVOI DE MAIL ET REDIMENSIONNEMENT DE L'IMAGE.... .....
		 */

		return super.input();
		// if (communautes != null)
		// System.out.println("==== communaute:" + communautes.toString() +
		// " ====");
		// System.out.println("==== area:" + geographicArea + " ====");
		// System.out.println("==== title:" + title + " ====");
		// System.out.println("==== desc:" + description + " ====");
		// System.out.println("==== price:" + price + " ====");
		//
		// if (communautes != null && geographicArea != null
		// && title != null
		// && description != null) {
		//
		// Set<Communaute> sc = new HashSet<Communaute>();
		// Iterator<Communaute> it = communautes.iterator();
		// while (it.hasNext()) {
		// sc.add(it.next());
		// }
		// annonce.setCommunautes(sc);
		// service.save(annonce);
		// System.out.println("=== ad saved ===");
		// return SUCCESS;
		//
		// } else {
		//
		//
		// }
	}

	public List<String> getGeographicAreas() {
		return geographicAreas;
	}

	public void setGeographicAreas(List<String> geographicAreas) {
		this.geographicAreas = geographicAreas;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	public void setCommunautes(List<Communaute> communautes) {
		this.communautes = communautes;
	}

	public List<Communaute> getCommunautes() {
		return communautes;
	}

	public String getGeographicArea() {
		return geographicArea;
	}

	public void setGeographicArea(String geographicArea) {
		this.geographicArea = geographicArea;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titre) {
		this.title = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}

}
