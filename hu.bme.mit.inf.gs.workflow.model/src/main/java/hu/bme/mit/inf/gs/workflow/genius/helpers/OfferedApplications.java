package hu.bme.mit.inf.gs.workflow.genius.helpers;

import java.util.HashSet;
import java.util.Set;

public class OfferedApplications
{
	private Set<ApplicationBoundaryEntity> offeredApps = new HashSet<ApplicationBoundaryEntity>();
	
	private Integer offeredBasedOnCategory=0;	//0
	private Integer offeredBasedOnCreator=0;	//1
	private Integer offeredBasedOnPopularity=0;	//2
	public Integer getOfferedBasedOnCategory() {
		return offeredBasedOnCategory;
	}
	public void setOfferedBasedOnCategory(Integer offeredBasedOnCategory) {
		this.offeredBasedOnCategory = offeredBasedOnCategory;
	}
	public Integer getOfferedBasedOnCreator() {
		return offeredBasedOnCreator;
	}
	public void setOfferedBasedOnCreator(Integer offeredBasedOnCreator) {
		this.offeredBasedOnCreator = offeredBasedOnCreator;
	}
	public Integer getOfferedBasedOnPopularity() {
		return offeredBasedOnPopularity;
	}
	public void setOfferedBasedOnPopularity(Integer offeredBasedOnPopularity) {
		this.offeredBasedOnPopularity = offeredBasedOnPopularity;
	}

	public Set<ApplicationBoundaryEntity> getOfferedApps()
	{
		return this.offeredApps;
	}
	public void setOfferedApps(Set<ApplicationBoundaryEntity> offeredapps)
	{
		this.offeredApps = offeredapps;
	}
	public boolean offer(ApplicationBoundaryEntity appToOffer, Integer mode)
	{
		if (mode == 0)
			offeredBasedOnCategory++;
		else if (mode == 1)
			offeredBasedOnCreator++;
		else if (mode == 2)
			offeredBasedOnPopularity++;
		return offeredApps.add(appToOffer);
	}
	public boolean unoffer(ApplicationBoundaryEntity appToUnOffer)
	{
		return offeredApps.remove(appToUnOffer);
	}
}
