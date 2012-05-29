package hu.bme.mit.inf.gs.workflow.genius.helpers;

import hu.bme.mit.inf.gs.workflow.genius.GeniusEvaluationProcess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ApplicationMetadataContainer
{
	private Set<ApplicationMetadataBoundaryEntity> allAppMetadatas = new HashSet<ApplicationMetadataBoundaryEntity>();
	
	private  void makeAppMetaDatas()
	{ // !!
		System.out.println("Generate app metadatas (randomly)");
		for (int i = 0;i < 100;i++)
		{
			ApplicationMetadataBoundaryEntity ambe = new ApplicationMetadataBoundaryEntity();
			ambe.setAcceptanceResult(0); // mi ez? - elfogadva?
			ambe.setAgeRestriction(GeniusEvaluationProcess.R.nextInt(25/*16*/)+3);
			ambe.setApplicationMetadataID(i+1);
			ambe.setName("TOSOTR"+(char)('a'+GeniusEvaluationProcess.R.nextInt(26))+(char)('a'+GeniusEvaluationProcess.R.nextInt(26))+(char)('a'+GeniusEvaluationProcess.R.nextInt(26)));
			ambe.setPrice((double) (GeniusEvaluationProcess.R.nextInt(200)+1));
			ArrayList<CategoryBoundaryEntity> categs = new ArrayList<CategoryBoundaryEntity>();
			CategoryBoundaryEntity cbe = new CategoryBoundaryEntity();
			cbe.setCategoryName(""+(char)('a'+GeniusEvaluationProcess.R.nextInt(6)));
			categs.add(cbe);
			ambe.setCategories(categs);
			allAppMetadatas.add(ambe);
		}
	}

	public Set<ApplicationMetadataBoundaryEntity> getAllAppMetadatas()
	{
		return this.allAppMetadatas;
	}
	public void setAllAppMetadatas(Set<ApplicationMetadataBoundaryEntity> allappmetadatas)
	{
		this.allAppMetadatas = allappmetadatas;
	}
}
