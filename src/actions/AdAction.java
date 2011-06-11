package actions;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import core.ThumbNail2;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import dao.AdService;
import dao.UserService;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;
import entities.User;

public class AdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String UL_DIR = "uploads/";

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

	// Server-side validation
	public void validate() {
		System.out.println("=== validate() method called ===");
		// Validate SignUp form
		if (userBean != null) {
			System.out.println(userBean.toString());
			HashMap<String, Object> mhm = new HashMap<String, Object>();
			mhm.put("login", userBean.getLogin());
			if (!userBean.getLogin().equals("")
					&& uService.findByField(mhm) != null) {
				addFieldError("userBean.login", getText("username.used"));
			}
			mhm.clear();
			mhm.put("email", userBean.getEmail());
			if (!userBean.getEmail().equals("")
					&& uService.findByField(mhm) != null) {
				addFieldError("userBean.email", getText("email.used"));
			}
		}
	}

	public String submit() throws Exception {

		if (uService.saveOne(userBean) != null) {
			addActionMessage(userBean.getLogin() + " " + getText("now.signup"));
			System.out.println("=== submit() method called ===");

			adBean.setRegion(service.getOne(Region.class, regionId));
			adBean.setCategorie(service.getOne(Categorie.class, categorieId));
			adBean.setCommunautes(service.get(Communaute.class, communitiesId));
			if (service.saveOne(adBean) != null) {
				MultiPartRequestWrapper multipartRequest = ((MultiPartRequestWrapper) ServletActionContext
						.getRequest());
				if (multipartRequest != null)
					store(multipartRequest);
				addActionMessage(getText("ad.sent"));
				return SUCCESS;
			} else {
				addActionError(getText("add.ad.impossible"));
				return ERROR;
			}
		} else {
			addActionError(getText("signup.impossible"));
			return ERROR;
		}

	}

	private void store(MultiPartRequestWrapper multipartRequest)
			throws Exception {
		// Upload d'image que pour les user enregistrés
		long userId = userBean.getId(); 
		File fs[] = multipartRequest.getFiles("upload");
		String[] ct = multipartRequest.getContentTypes("upload");
		for (int i = 0; i < fs.length; i++) {
			String outputFormat = ct[i].split("/")[1];
			String fileName = userId + "_"+adBean.getId()+"_"+i;
			// adBean.addImgPath(UL_DIR+fileName);
			String fullName = UL_DIR + fileName + "." + outputFormat;
			String fullNameThumb = UL_DIR + fileName + "_thumb." + outputFormat;
			File finalFile = new File(fullName);
			FileUtils.copyFile(fs[i], finalFile);

			ThumbNail2 tng = new ThumbNail2();
			tng.createThumbnail(fullName, fullNameThumb, 300, 200);

		}
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

	// This method returns a buffered image with the contents of an image
	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see Determining If an Image Has Transparent Pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	// This method returns true if the specified image has transparent pixels
	public static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}

		// Get the image's color model
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
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
