package actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import core.annonceComparator;
import dao.AdService;
import entities.Annonce;
import entities.Categorie;
import entities.Communaute;
import entities.Departement;
import entities.Region;
import entities.User;

public class ListAdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Inject
	AdService service;

	// Your result List
	private List<Annonce> gridModel;
	// All results List
	private List<Annonce> myAnnonces;

	// Search annonces by :
	private long userId = -1;
	private long regionId = -1;
	private long departId = -1;
	private int codePostal;
	private long categorieId = -1;
	private List<Departement> departements;
	private List<Region> regions;
	private List<Categorie> categories;
	private List<Communaute> communities;
	private long communauteId = -1;

	// get how many rows we want to have into the grid - rowNum attribute in the
	// grid
	private Integer rows = 0;
	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;
	// sorting order - asc or desc
	private String sord;
	// get index row - i.e. user click to sort.
	private String sidx;
	// Search Field
	private String searchField;
	// The Search String
	private String searchString;
	// he Search Operation
	// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;
	// Your Total Pages
	private Integer total = 0;
	// total nb of rows in the whole list
	private Integer totalrows = 0;
	// All Record
	private Integer records = 0;
	// Load all data at once. By default grid sets this to false
	private boolean loadonce = false;
	
	@Override
	public String input() throws Exception {
		return super.input();
	}

	public String execute() {
		System.out.println("=== method execute() from ListAdAction called ===");

		myAnnonces = getMyAnnonces();

		if (getSord() != null && getSidx() != null && !getSord().equals("")
				&& !getSidx().equals("")) {
			Collections.sort(myAnnonces, new annonceComparator(getSord(),
					getSidx()));
		}

		setRecords(getMyAnnonces().size());
		if (getTotalrows() != 0) {
			setRows(getTotalrows());
		}

		int to = (getRows() * getPage());
		int from = to - getRows();
		if (to > getRecords())
			to = getRecords();
		System.out.println("to :" + to + ", from :" + from + ", total :"
				+ total);

		if (loadonce) {
			setGridModel(myAnnonces);
		} else {
			// if (searchString != null && searchOper != null) {
			// int id = Integer.parseInt(searchString);
			// if (searchOper.equalsIgnoreCase("eq")) {
			// List<Annonce> cList = new ArrayList<Annonce>();
			// cList.add(myAnnonces.get(id));
			// setGridModel(cList);
			// } else if (searchOper.equalsIgnoreCase("ne")) {
			// setGridModel(service.findNotById(myAnnonces, id, from, to));
			// } else if (searchOper.equalsIgnoreCase("lt")) {
			// setGridModel(service.findLesserAsId(myAnnonces, id, from, to));
			// } else if (searchOper.equalsIgnoreCase("gt")) {
			// setGridModel(service.findGreaterAsId(myAnnonces, id, from, to));
			// }
			// } else {
			setGridModel(myAnnonces.subList(from, to));
			// }
		}

		setTotal((int) Math.ceil((double) getRecords() / (double) getRows()));

		return SUCCESS;
	}

	public String getJSON() {
		return execute();
	}

	public void setGridModel(List<Annonce> gridModel) {
		this.gridModel = gridModel;
	}

	public List<Annonce> getGridModel() {
		return gridModel;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getRows() {
		return rows;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage() {
		return page;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String getSord() {
		return sord;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public Integer getRecords() {
		return records;
	}

	public List<Annonce> getMyAnnonces() {
Map<String, Map<String, Object>> joins = new HashMap<String, Map<String,Object>>();
		
		System.out.println("departId:"+getDepartId()
				+",regionId:"+getRegionId()
				+",userId:"+getUserId()
				+",categorieId:"+getCategorieId()
				+",communauteId:"+getCommunauteId());
		
		boolean showunvalide = false;
		
		if (getDepartId() != -1) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("id", departId);
			joins.put("departement", value);
		}
		if (getRegionId() != -1) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("id", regionId);
			joins.put("region", value);
		}
		if (getUserId() != -1) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("id", userId);
			joins.put("user", value);
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			User user = (User) session.get("user");
			if (user.getId()==userId) 
				showunvalide = true;
		}
		if (getCategorieId() != -1) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("id", categorieId);
			joins.put("categorie", value);
		}
		if (getCommunauteId() != -1) {
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("id", communauteId);
			joins.put("communautes", value);
		}

		return service.findByJointure(joins,showunvalide);
	}

	public void setMyAnnonces(List<Annonce> myAnnonces) {
		this.myAnnonces = myAnnonces;
	}

	public void setLoadonce(boolean loadonce) {
		this.loadonce = loadonce;
	}

	public boolean isLoadonce() {
		return loadonce;
	}

	public void setTotalrows(Integer totalrows) {
		this.totalrows = totalrows;
	}

	public Integer getTotalrows() {
		return totalrows;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setDepartId(long departId) {
		this.departId = departId;
	}

	public long getDepartId() {
		return departId;
	}

	public void setCategorieId(long categorieId) {
		this.categorieId = categorieId;
	}

	public long getCategorieId() {
		return categorieId;
	}

	public void setCommunauteId(long communauteId) {
		this.communauteId = communauteId;
	}

	public long getCommunauteId() {
		return communauteId;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Region> getRegions() {
		return service.getRegions();
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

}