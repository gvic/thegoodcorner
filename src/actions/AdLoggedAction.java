package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import core.ThumbNail2;
import dao.AdService;
import dao.UserService;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.ImagePath;
import entities.Region;
import entities.User;

public class AdLoggedAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String UL_DIR = "uploads/";

	@Inject
	AdService service;
	@Inject
	UserService uService;

	private Annonce adBean;
	private User userBean;

	private long regionId;
	private long departementId;
	private long categorieId;
	private Set<Long> communitiesId;

	private List<Region> regions;
	private List<Departement> departements;
	private List<Communaute> communities;
	private List<Categorie> categories;

	private List<File> uploads = new ArrayList<File>();
	private List<String> uploadFileNames = new ArrayList<String>();
	private List<String> uploadContentTypes = new ArrayList<String>();

	public String execute() throws Exception {
		System.out.println("=== execute() method called ===");

		Map<String, Object> session = ActionContext.getContext().getSession();
		Object userIdO = session.get("userId");
		if (userIdO != null) {
			long userId = (Long) userIdO;
			userBean = uService.getOne(userId);
			adBean.setUser(userBean);
			adBean.setRegion(service.getOne(Region.class, regionId));
			adBean.setCategorie(service.getOne(Categorie.class, categorieId));
			adBean.setCommunautes(service.getByIds(Communaute.class, communitiesId));
			MultiPartRequestWrapper multipartRequest = ((MultiPartRequestWrapper) ServletActionContext
					.getRequest());
			if (multipartRequest != null) {
				//store(multipartRequest);
				if (service.saveOne(adBean) != null) {
					addActionMessage(getText("ad.sent"));
					return SUCCESS;
				} else {
					addActionMessage(getText("ad.save.impossible"));
					return ERROR;
				}
			} else {
				addActionError(getText("add.ad.impossible"));
				return ERROR;
			}
		} else {
			addActionError(getText("error.notloggedin"));
			return ERROR;
		}
	}
	
	private void store(MultiPartRequestWrapper multipartRequest)
			throws Exception {
		// Upload d'image que pour les user enregistrés
		long userId = userBean.getId();
		File fs[] = multipartRequest.getFiles("upload");
		String[] ct = multipartRequest.getContentTypes("upload");
		Set<ImagePath> sip = new HashSet<ImagePath>();
		for (int i = 0; i < fs.length; i++) {
			String outputFormat = ct[i].split("/")[1];
			String fileName = userId + "_" + adBean.getId() + "_" + i;
			String imagePath = UL_DIR + fileName + "." + outputFormat;
			String thumbImagePath = UL_DIR + fileName + "_thumb." + outputFormat;
			ImagePath ip = new ImagePath();
			ip.setPath(imagePath);
			sip.add(ip);
			File finalFile = new File(imagePath);
			FileUtils.copyFile(fs[i], finalFile);

			ThumbNail2 tng = new ThumbNail2();
			tng.createThumbnail(imagePath, thumbImagePath, 300, 200);
		}
		adBean.setImgPaths(sip);
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

	public Annonce getAdBean() {
		return adBean;
	}

	public void setAdBean(Annonce adBean) {
		this.adBean = adBean;
	}

	public List<Region> getRegions() {
		return service.getRegions();
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public long getCategorieId() {
		return categorieId;
	}

	public void setCommunitiesId(Set<Long> communitiesId) {
		this.communitiesId = communitiesId;
	}

	public Set<Long> getCommunitiesId() {
		return communitiesId;
	}

	public List<File> getUpload() {
		return this.uploads;
	}

	public void setUpload(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadFileName() {
		return this.uploadFileNames;
	}

	public void setUploadFileName(List<String> uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	public List<String> getUploadContentType() {
		return this.uploadContentTypes;
	}

	public void setUploadContentType(List<String> contentTypes) {
		this.uploadContentTypes = contentTypes;
	}

	public void setDepartementId(long departementId) {
		this.departementId = departementId;
	}

	public long getDepartementId() {
		return departementId;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

	public List<Departement> getDepartements() {
		return service.getDepartements();
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public User getUserBean() {
		return userBean;
	}
}
