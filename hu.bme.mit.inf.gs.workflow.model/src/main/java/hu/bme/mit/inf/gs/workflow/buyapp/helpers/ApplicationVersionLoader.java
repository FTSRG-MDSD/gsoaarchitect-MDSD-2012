package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class ApplicationVersionLoader implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		try {
			/*System.out.println(address + "buy/" + buyerName + "/?id=" + applicationID);
			WebResource webResource = client.resource(address + "buy/" + buyerName + "/?id=" + applicationID);*/
			// The next two line is needed, because Jersey's Client implementation has a few bugs...
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");
			//ClientResponse response = webResource.queryParams(queryParams).put(ClientResponse.class, "");
			//if (response.getStatus() != 200) {
			//	throw new Exception("Error code: " + response.getStatus());
			//}
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", 1);
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", -1);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
