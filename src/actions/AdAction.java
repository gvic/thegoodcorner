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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;

import dao.AdService;
import entities.Annonce;
import entities.Communaute;
import entities.Departement;
import entities.Region;

public class AdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String OUTPUT_FORMAT = "jpeg";

	@Inject
	AdService service;
	
	private Annonce adBean;

	private List<String> geographicAreas;
	
	private List<Region> regions;
	private List<Departement> departements;

	private List<Communaute> communities;

	private List<String> categories;

	private String title;
	private String description;
	private int price;
	private File file;// The actual file
	private String uploadContentType; // The content type of the file
	private String uploadFileName; // The uploaded file name
	private String fileCaption;// The caption of the file entered by use

	// Méthode d'entrée sur la page du formulaire de
	// soumission d'annonce
	public String input() throws Exception {
		// Dans le cas ou l'user a un compte
		geographicAreas = new ArrayList<String>();
		geographicAreas = service.getAreas();
		
		regions = service.getRegions();
		departements = service.getDepartements();

		categories = new ArrayList<String>();
		categories = service.getOrderedCategories();

		setCommunities(service.getCommunautes());

		// Dans le cas ou l'user n'a pas de compte

		return super.input();

	}

	@Override
	public void validate() {
		
	}

	/***
	 * FAIRE ENVOI DE MAIL ET REDIMENSIONNEMENT DE L'IMAGE.... .....
	 */
	public String processDatas() {
		if (geographicAreas == null)
			System.out.println("==========nulllllll========");
		

		try {

			String fullFileName = "/home/victorinox/lib/jboss-6.0.0.Final/server/default/deploy/uploads/";
			fullFileName += "_" + title + "_" + fileCaption;

			File theFile = new File(fullFileName);

			FileUtils.copyFile(file, theFile);

			BufferedImage img = ImageIO.read(theFile);
			int scaleX = (int) (img.getWidth() * 0.5);
			int scaleY = (int) (img.getHeight() * 0.5);

			Image newImg = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
			FileImageOutputStream fios = new FileImageOutputStream(new File(fullFileName + "_thumb"));
			ImageIO.write(toBufferedImage(newImg), OUTPUT_FORMAT, fios);

		} catch (Exception e) {

			addActionError(e.getMessage());

			return INPUT;

		}

		return SUCCESS;
	}

	public List<String> getGeographicAreas() {
		return geographicAreas;
	}

	public void setGeographicAreas(List<String> geographicAreas) {
		this.geographicAreas = geographicAreas;
	}

	public void setCommunities(List<Communaute> communautes) {
		this.communities = communautes;
	}

	public List<Communaute> getCommunities() {
		return communities;
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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	// This method returns a buffered image with the contents of an image
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	    }

	    // This code ensures that all the pixels in the image are loaded
	    image = new ImageIcon(image).getImage();

	    // Determine if the image has transparent pixels; for this method's
	    // implementation, see Determining If an Image Has Transparent Pixels
	    boolean hasAlpha = hasAlpha(image);

	    // Create a buffered image with a format that's compatible with the screen
	    BufferedImage bimage = null;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.OPAQUE;
	        if (hasAlpha) {
	            transparency = Transparency.BITMASK;
	        }

	        // Create the buffered image
	        GraphicsDevice gs = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gs.getDefaultConfiguration();
	        bimage = gc.createCompatibleImage(
	            image.getWidth(null), image.getHeight(null), transparency);
	    } catch (HeadlessException e) {
	        // The system does not have a screen
	    }

	    if (bimage == null) {
	        // Create a buffered image using the default color model
	        int type = BufferedImage.TYPE_INT_RGB;
	        if (hasAlpha) {
	            type = BufferedImage.TYPE_INT_ARGB;
	        }
	        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
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
	        BufferedImage bimage = (BufferedImage)image;
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

	public Annonce getAdBean() {
		return adBean;
	}

	public void setAdBean(Annonce adBean) {
		this.adBean = adBean;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}
	
}
