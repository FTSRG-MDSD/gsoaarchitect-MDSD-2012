package hu.bme.mit.inf.gs.workflow.genius;

import hu.bme.mit.inf.gs.workflow.genius.helpers.AllAndBoughtApplications;
import hu.bme.mit.inf.gs.workflow.genius.helpers.ApplicationBoundaryEntity;
import hu.bme.mit.inf.gs.workflow.genius.helpers.ApplicationMetadataBoundaryEntity;
import hu.bme.mit.inf.gs.workflow.genius.helpers.ApplicationMetadataContainer;
import hu.bme.mit.inf.gs.workflow.genius.helpers.ApplicationPopularityContainer;
import hu.bme.mit.inf.gs.workflow.genius.helpers.CategoryAndHit;
import hu.bme.mit.inf.gs.workflow.genius.helpers.CategoryBoundaryEntity;
import hu.bme.mit.inf.gs.workflow.genius.helpers.CreatorAndHit;
import hu.bme.mit.inf.gs.workflow.genius.helpers.OfferedApplications;
import hu.bme.mit.inf.gs.workflow.genius.helpers.PopularityOfApps;
import hu.bme.mit.inf.gs.workflow.genius.helpers.UserEntity;
import hu.bme.mit.inf.gs.workflow.genius.helpers.UserHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserIDGetterWorkIntemHandler implements WorkItemHandler
{
	@Override
	public void abortWorkItem(WorkItem wi, WorkItemManager wim)
	{
		System.out.println("Service abort ...");
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim)
	{
		Map<String, Object> results = new HashMap<String, Object>();
		System.out.println("Get data...");
		
		Set<ApplicationBoundaryEntity> apps = new HashSet<ApplicationBoundaryEntity>();
		Set<ApplicationBoundaryEntity> boughtapps = new HashSet<ApplicationBoundaryEntity>();
		Set<ApplicationMetadataBoundaryEntity> appmetas = new HashSet<ApplicationMetadataBoundaryEntity>();
		try
		{
			Client client = Client.create();
			WebResource webResource;
				webResource = client.resource(Constants.address + "applications");
			ClientResponse response = webResource.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			String ret = response.getEntity(String.class);
			ret = ret.replace("</ApplicationBoundaryEntity>", "</ApplicationBoundaryEntity>!");
			String[] splitted = ret.split("!");
			splitted[0] = splitted[0].substring(splitted[0].indexOf("<ApplicationBoundaryEntity>"));
			for (int i=0;i<splitted.length-1;i++)
			{
				ApplicationBoundaryEntity app = ApplicationBoundaryEntity.readFromXML(splitted[i].replace("<ApplicationBoundaryEntity>", "<ApplicationBoundaryEntity xmlns=\"http://schemas.datacontract.org/2004/07/AppRepository\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"));
				apps.add(app);
				if (app.getLastCommitedMetadata()!= null && app.getLastAcceptedMetadata()==null)
					appmetas.add(app.getLastCommitedMetadata());
				else
					appmetas.add(app.getLastAcceptedMetadata());
			}
			client.destroy();
			System.out.println("Got all applications, appsize: "+apps.size()+" appmetasize: "+appmetas.size());
		} catch (Exception e)
		{
			System.out.println("Error1: " + e.getMessage());
		}
		
		try
		{
			Client client = Client.create();
			WebResource webResource;
				webResource = client.resource(Constants.address + "applications/bought/"+Constants.userloginname);
			ClientResponse response = webResource.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			String ret = response.getEntity(String.class);
			ret = ret.replace("</ApplicationBoundaryEntity>", "</ApplicationBoundaryEntity>!");
			String[] splitted = ret.split("!");
			splitted[0] = splitted[0].substring(splitted[0].indexOf("<ApplicationBoundaryEntity>"));
			for (int i=0;i<splitted.length-1;i++)
			{
				ApplicationBoundaryEntity app = ApplicationBoundaryEntity.readFromXML(splitted[i].replace("<ApplicationBoundaryEntity>", "<ApplicationBoundaryEntity xmlns=\"http://schemas.datacontract.org/2004/07/AppRepository\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"));
				boughtapps.add(app);
			}
			client.destroy();
			System.out.print("Got all bought applications, size: "+boughtapps.size()+ " (");
			for (ApplicationBoundaryEntity abbee: boughtapps)
			{
				System.out.print(abbee.getApplicationID()+" ");
			}
			System.out.println(")");
		} catch (Exception e)
		{
			System.out.println("Error2: " + e.getMessage());
		}
		
		
		
		
		ApplicationMetadataContainer amc = new ApplicationMetadataContainer();
		amc.setAllAppMetadatas(appmetas);
		
		
		
		AllAndBoughtApplications aaba = new AllAndBoughtApplications();
		//aaba.makeAppDatas(amc.getAllAppMetadatas());
		aaba.setBoughtApps(boughtapps);
		aaba.setAllApps(apps);

		GeniusEvaluationProcess.ksession.insert(amc); // insert the set and its elements
		for (ApplicationMetadataBoundaryEntity amcent : amc.getAllAppMetadatas())
			GeniusEvaluationProcess.ksession.insert(amcent);
		
		GeniusEvaluationProcess.ksession.insert(aaba); // insert the set and its elements
		for (ApplicationBoundaryEntity abe : aaba.getAllApps())
			GeniusEvaluationProcess.ksession.insert(abe);
		
		OfferedApplications ofa = new OfferedApplications();
		GeniusEvaluationProcess.ksession.insert(ofa); // insert empty list into drools mist
		GeniusEvaluationProcess.offeredAppList = ofa; // set static reference
		
		ApplicationPopularityContainer apci = new ApplicationPopularityContainer();
		GeniusEvaluationProcess.ksession.insert(apci); // create popularity container (vector) and insert it
		
		
		/**/
		
		try
		{
			for (ApplicationBoundaryEntity abe : aaba.getAllApps())
			{
				Client client = Client.create();
				WebResource webResource;
					webResource = client.resource(Constants.address + "applicationsajat/?id="+abe.getApplicationID());
				ClientResponse response = webResource.get(ClientResponse.class);
				if (response.getStatus() != 200) {
					throw new Exception("Error code: " + response.getStatus());
				}
				String ret = response.getEntity(String.class);
				String intlesz = ret.substring(ret.indexOf(">")+1,ret.indexOf("<",2));
				Integer boughtCountFromWS = Integer.parseInt(intlesz);
				PopularityOfApps popofaps = new PopularityOfApps(abe.getApplicationID(),boughtCountFromWS);
				apci.insertPop(popofaps);
				GeniusEvaluationProcess.ksession.insert(popofaps);
				client.destroy();
				System.out.print(abe.getApplicationID().toString()+":"+boughtCountFromWS.toString()+" ");
			}
			System.out.println("");
		} catch (Exception e)
		{
			System.out.println("Error3: " + e.getMessage());
		}
		
		/**/
		
		UserEntity uen = new UserEntity();
		try
		{
			Client client = Client.create();
			WebResource webResource;
				webResource = client.resource(Constants.useraddress + "user/"+Constants.userloginname);
			ClientResponse response = webResource.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			String ret = response.getEntity(String.class);
			uen = UserEntity.readFromXML(ret);
			client.destroy();
			System.out.println("Got user, loginname:"+uen.getLoginName());
		} catch (Exception e)
		{
			System.out.println("Error4: " + e.getMessage());
		}
		/*uen.setApplicationAccountFID(0);
		uen.setAge(15);uen.setCreditAccountFID(0);
		uen.setEmail("pal.balazs.sandor@windowslive.com");uen.setFullName("Nem Balázs Pál");uen.setLoginName("rudolfkiraly4");
		uen.setPassword("password");
		ArrayList<RoleEntity> rls = new ArrayList<RoleEntity>(); RoleEntity rl = new RoleEntity(); rl.setRoleName("admin");
		rls.add(rl); uen.setRoles(rls);*/
		GeniusEvaluationProcess.ksession.insert(uen);

		UserHelper uh = new UserHelper();
		for (ApplicationBoundaryEntity abe : aaba.getBoughtApps())
		{
			uh.hitCreator(abe.getCreatorName());
			ApplicationMetadataBoundaryEntity ambe = abe.getLastAcceptedMetadata();
			if (ambe == null)
				ambe = abe.getLastCommitedMetadata();
			if (ambe != null)
				for (CategoryBoundaryEntity cbe : ambe.getCategories())
				{
					if (cbe.getCategoryName() != null)
						uh.hitCategory(cbe);
				}
		}
		for (CategoryAndHit cah : uh.getCategories())
			GeniusEvaluationProcess.ksession.insert(cah);
		for (CreatorAndHit cah : uh.getCreators())
			GeniusEvaluationProcess.ksession.insert(cah);
		GeniusEvaluationProcess.ksession.insert(uh);
		System.out.println(uh);
		
		results.put("offeredApps", ofa);

		wim.completeWorkItem(wi.getId(), results);
	}

}