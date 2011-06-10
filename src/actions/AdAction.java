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
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

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
	private static final String OUTPUT_FORMAT = "jpeg";

	@Inject
	AdService service;
	
	private Annonce adBean;
	
	private long regionId;
	private long categorieId;
	private Set<Long> communitiesId;
	
	private List<Region> regions;
//	private String region;
//	private List<Departement> departements;

	private List<Communaute> communities;

	private List<Categorie> categories;

    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();


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

    public String upload() throws Exception {
        System.out.println("\n\n upload1");
        System.out.println("files:");
        for (File u: uploads) {
            System.out.println("*** "+u+"\t"+u.length());
        }
        System.out.println("filenames:");
        for (String n: uploadFileNames) {
            System.out.println("*** "+n);
        }
        System.out.println("content types:");
        for (String c: uploadContentTypes) {
            System.out.println("*** "+c);
        }
        System.out.println("\n\n");
        return SUCCESS;
    }

	/***
	 * FAIRE ENVOI DE MAIL ET REDIMENSIONNEMENT DE L'IMAGE.... .....
	 */
//	public String processDatas() {
//		if (geographicAreas == null)
//			System.out.println("==========nulllllll========");
//		
//
//		try {
//
//			String fullFileName = "/home/victorinox/lib/jboss-6.0.0.Final/server/default/deploy/uploads/";
//			fullFileName += "_" + title + "_" + fileCaption;
//
//			File theFile = new File(fullFileName);
//
//			FileUtils.copyFile(file, theFile);
//
//			BufferedImage img = ImageIO.read(theFile);
//			int scaleX = (int) (img.getWidth() * 0.5);
//			int scaleY = (int) (img.getHeight() * 0.5);
//
//			Image newImg = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
//			FileImageOutputStream fios = new FileImageOutputStream(new File(fullFileName + "_thumb"));
//			ImageIO.write(toBufferedImage(newImg), OUTPUT_FORMAT, fios);
//
//		} catch (Exception e) {
//
//			addActionError(e.getMessage());
//
//			return INPUT;
//
//		}
//
//		return SUCCESS;
//	}

	public String submit() throws Exception {
		System.out.println("=== submit() method called ===");
		
        System.out.println("\n\n upload1");
        System.out.println("files:");
        for (File u: uploads) {
            System.out.println("*** "+u+"\t"+u.length());
        }
        System.out.println("filenames:");
        for (String n: uploadFileNames) {
            System.out.println("*** "+n);
        }
        System.out.println("content types:");
        for (String c: uploadContentTypes) {
            System.out.println("*** "+c);
        }
        System.out.println("\n\n");
		
		adBean.setRegion(service.getOne(Region.class, regionId));
		adBean.setCategorie(service.getOne(Categorie.class, categorieId));
		adBean.setCommunautes(service.get(Communaute.class, communitiesId));
		if (service.saveOne(adBean) != null) {
			addActionMessage(getText("ad.sent"));
			return SUCCESS;
		} else {
			addActionError(getText("add.ad.impossible"));
			return ERROR;
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
		return service.getRegions();
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
//
//	public List<Departement> getDepartements() {
//		return departements;
//	}
//
//	public void setDepartements(List<Departement> departements) {
//		this.departements = departements;
//	}

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
	
}
