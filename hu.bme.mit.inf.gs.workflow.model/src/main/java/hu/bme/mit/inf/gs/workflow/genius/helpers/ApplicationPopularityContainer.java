package hu.bme.mit.inf.gs.workflow.genius.helpers;

import java.util.HashSet;
import java.util.Set;

public class ApplicationPopularityContainer
{
	private Set<PopularityOfApps> appPops = new HashSet<PopularityOfApps>();
	
	public Set<PopularityOfApps> getAppPops()
	{
		return this.appPops;
	}
	public void setAppPops(Set<PopularityOfApps> allpops)
	{
		this.appPops = allpops;
	}
	
	public boolean insertPop(PopularityOfApps popofapps)
	{
		return this.appPops.add(popofapps);
	}
}
