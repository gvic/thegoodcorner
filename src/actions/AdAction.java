package actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import core.SimpleMail;
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

public class AdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
//	public static final String UL_DIR = 
//		ServletActionContext.getServletContext().getRealPath(ServletActionContext.getServletContext().getContextPath())+ "/uploads/";
	public static final String UL_DIR = "uploads/images_annonces/";
	
	@Inject
	AdService service;
	@Inject
	UserService uService;

	private Annonce adBean;
	private User userBean;
	private String confirmPassword;

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

	public String input() throws Exception {
		return INPUT;
	}

	public void validate() {
		if (userBean != null) {
			if (userBean.getLogin().equals(""))
				addFieldError("userBean.login", getText("errors.required"));
			if (userBean.getLogin().length() < 4)
				addFieldError("userBean.login", getText("errors.login.tooshort"));
			if (userBean.getMd5_mdp().equals(""))
				addFieldError("userBean.md5_mdp", getText("errors.required"));
			if (userBean.getMd5_mdp().length() < 6)
				addFieldError("userBean.md5_mdp", getText("errors.password.tooshort"));
			if (confirmPassword.equals(""))
				addFieldError("confirmPassword", getText("errors.required"));
			if (!confirmPassword.equals(userBean.getMd5_mdp()))
				addFieldError("confirmPassword", getText("errors.confirmPassword"));
			if (userBean.getEmail().equals(""))
				addFieldError("userBean.email", getText("errors.required"));
			System.out.println(userBean.toString());
			HashMap<String, Object> mhm = new HashMap<String, Object>();
			mhm.put("login", userBean.getLogin());
			if (!userBean.getLogin().equals("")
					&& uService.getByField(mhm) != null)
				addFieldError("userBean.login", getText("errors.login.used"));
			mhm.clear();
			mhm.put("email", userBean.getEmail());
			if (!userBean.getEmail().equals("")
					&& uService.getByField(mhm) != null)
				addFieldError("userBean.email", getText("errors.email.used"));
		}
	}

	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			// Not logged in
			if (uService.saveOne(userBean) != null) {
				addActionMessage(userBean.getLogin() + " "
						+ getText("now.signup"));
				System.out.println("userbean : " + userBean.toString());
				// The userBean variable isn't updated (with its ID)
				Map<String, Object> hm = new HashMap<String, Object>();
				hm.put("login", userBean.getLogin());
				// That's why we have to retrieve it from DB with it's login
				userBean = uService.getByField(hm);
			} else {
				addActionError(getText("signup.impossible"));
				return ERROR;
			}
		} else {
			userBean = user;
		}
		adBean.setUser(userBean);
		adBean.setRegion(service.getOne(Region.class, regionId));
		adBean.setDepartement(service.getOne(Departement.class,
				departementId));
		adBean.setDate_de_publication(new Date());
		adBean.setCategorie(service.getOne(Categorie.class,
				categorieId));
		adBean.setCommunautes(service.getByIds(Communaute.class,
				communitiesId));
		adBean.setValidee(false);
		MultiPartRequestWrapper multipartRequest = ((MultiPartRequestWrapper) ServletActionContext
				.getRequest());
		adBean.setImgPaths(storeImages(multipartRequest));
		// ANNONCE-IMAGEPATH relationship problem
		// annonce_id isn't store in the imagePaths
		service.save(adBean,userBean);
		addActionMessage(getText("ad.sent"));
		return SUCCESS;
	}

	public Set<ImagePath> storeImages(MultiPartRequestWrapper multipartRequest)
	throws Exception {
		// Upload d'image que pour les user enregistrés
		long userId = userBean.getId();
		File fs[] = multipartRequest.getFiles("uploads");
		Set<ImagePath> ret = null;
		if (fs != null) {
		
			String[] ct = multipartRequest.getContentTypes("uploads");
			Set<ImagePath> sip = new HashSet<ImagePath>();
			for (int i = 0; i < fs.length; i++) {
				String outputFormat = ct[i].split("/")[1];
				String fileName = userId + "_" + adBean.getId() + "_" + i;
				String imagePath = AdAction.UL_DIR+ fileName + "." + outputFormat;
				String thumbImagePath = AdAction.UL_DIR + fileName + "_thumb."
						+ outputFormat;
				ImagePath ip = new ImagePath();
				ip.setPath(imagePath);
				service.save(ip);
				ip = service.merge(ip); // Merge to retrieve saved instance?
				System.out.println("== Is the ImagePath Well Saved?? ==");
				System.out.println(ip.toString());
				sip.add(ip);
				System.out.println(ip.getId());
				File finalFile = new File(imagePath);
				FileUtils.copyFile(fs[i], finalFile);
		
				ThumbNail2 tng = new ThumbNail2();
				tng.createThumbnail(imagePath, thumbImagePath, 300, 200);
			}
			ret = sip;
		}
		return ret;
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

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

}
