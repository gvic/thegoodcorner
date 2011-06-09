package actions;

import com.opensymphony.xwork2.ActionSupport;

public class ShowAdAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private long adId;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public void setAdId(long adId) {
		this.adId = adId;
	}

	public long getAdId() {
		return adId;
	}

}
