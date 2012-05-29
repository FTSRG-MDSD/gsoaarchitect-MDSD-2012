package hu.bme.mit.inf.gs.workflow.genius.helpers;


public class PopularityOfApps
{
	private Integer applicationID;
	private Integer purchaseCount;
	
	public Integer getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	public Integer getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	private PopularityOfApps()
	{
		applicationID = -1;
		purchaseCount = -1;
	}
	/*public PopularityOfApps(Integer applicationid) DEPRECATED
	{
		this.applicationID = applicationid;
		this.purchaseCount = GeniusEvaluationProcess.R.nextInt(10)+1;
	}*/
	public PopularityOfApps(Integer applicationid, int purchasecount)
	{
		this.applicationID = applicationid;
		this.purchaseCount = purchasecount;
	}
}
