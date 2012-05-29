package hu.bme.mit.inf.gs.workflow.genius.helpers;

import hu.bme.mit.inf.gs.workflow.genius.GeniusEvaluationProcess;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AllAndBoughtApplications
{
	private Set<ApplicationBoundaryEntity> boughtApps = new HashSet<ApplicationBoundaryEntity>();
	private Set<ApplicationBoundaryEntity> allApps = new HashSet<ApplicationBoundaryEntity>();
	
	private void makeAppDatas(Set<ApplicationMetadataBoundaryEntity> appmetadatashejj)
	{ // !!
		System.out.println("Generate apps, also randomly");
		Iterator<ApplicationMetadataBoundaryEntity> iterator = appmetadatashejj.iterator();
		for (int i = 0;i < 100;i++)
		{
			ApplicationBoundaryEntity ambe = new ApplicationBoundaryEntity();
			ambe.setApplicationID(i+1);
			ambe.setCreatorName("RUDOLF"+(char)('a'+GeniusEvaluationProcess.R.nextInt(5)));
			ambe.setLastAcceptedMetadata(iterator.next());// metadata count == app count
			ambe.setLastCommitedMetadata(ambe.getLastAcceptedMetadata());
			allApps.add(ambe);
			if (GeniusEvaluationProcess.R.nextInt(4)==0)
			{
				// néhány random megy a megvásároltak közé
				boughtApps.add(ambe);
			}
		}
		System.out.println(this.toString());
	}

	public Set<ApplicationBoundaryEntity> getBoughtApps()
	{
		return this.boughtApps;
	}
	public void setBoughtApps(Set<ApplicationBoundaryEntity> boughtapps)
	{
		this.boughtApps = boughtapps;
	}
	
	public Set<ApplicationBoundaryEntity> getAllApps()
	{
		return this.allApps;
	}
	public void setAllApps(Set<ApplicationBoundaryEntity> allapps)
	{
		this.allApps = allapps;
	}

	public boolean removeFromAllApps(ApplicationBoundaryEntity toRemove)
	{
		return allApps.remove(toRemove);
	}
	public boolean addToAllApps(ApplicationBoundaryEntity toAdd)
	{
		return allApps.add(toAdd);
	}
	
	public boolean ganyContainsBought(ApplicationBoundaryEntity abe)
	{
		//System.out.print("Lekérve: tartalmazza-e bought ezt: " + abe.toString());
		//System.out.println("bought mérete egyébként: " + this.boughtApps.size());
		for (ApplicationBoundaryEntity abeit : this.boughtApps)
		{
			if (abeit.getApplicationID() == abe.getApplicationID())
			{
				//System.out.println("tartalmazza:TRUE");
				return true;
			}
		}
		//System.out.println("nemtartalmazza:FALSE");
		return false;
	}
	
	@Override
	public String toString()
	{
		return "Bought apps:\n" + boughtApps.toString() + "\nAll Apps:\n" + allApps.toString();
	}
}
