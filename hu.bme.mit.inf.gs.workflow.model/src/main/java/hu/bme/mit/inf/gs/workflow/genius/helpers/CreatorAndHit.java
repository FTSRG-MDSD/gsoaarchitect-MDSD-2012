package hu.bme.mit.inf.gs.workflow.genius.helpers;

public class CreatorAndHit
{
	private String creatorName;
	private Integer hitCount;
	
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public Integer getHitCount() {
		return hitCount;
	}
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
	
	private CreatorAndHit(){}
	
	public CreatorAndHit(String creaname, Integer hitco)
	{
		this.creatorName = creaname;
		this.hitCount = hitco;
	}
	
	@Override
	public String toString()
	{
		return creatorName+"("+hitCount+")";
	}
}
