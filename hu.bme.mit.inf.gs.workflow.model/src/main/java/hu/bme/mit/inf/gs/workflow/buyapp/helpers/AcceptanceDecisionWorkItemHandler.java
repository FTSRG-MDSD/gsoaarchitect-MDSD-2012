package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class AcceptanceDecisionWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		String address = (String) wi.getParameter("URL");
		Integer dataID = (Integer) wi.getParameter("ID");
		String type = (String) wi.getParameter("Type");
		int autoResult = (Integer) wi.getParameter("Auto");
		int humanResult = Integer.parseInt((String) wi.getParameter("Human"));
		int choice = 0;
		try {
			if (autoResult == 2 || humanResult == 2) { //Denied
				choice = 2;
			} else if (autoResult == 3 || humanResult == 3) { //Unknown, do nothing
				wim.completeWorkItem(wi.getId(), null);
			} else { //accepted
				choice = 1;
			}
			Client client = Client.create();
			WebResource webResource = null;
			if (type.equalsIgnoreCase("metadata")) {
				 webResource = client.resource(address + "application/metadata/acceptance/?id=" + dataID + "&acceptance=" + choice);
			} else {
				webResource = client.resource(address + "application/version/acceptance/?id=" + dataID + "&acceptance=" + choice);
			}
			// The next two line is needed, because Jersey's Client implementation has a few bugs...
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");
			ClientResponse response = webResource.queryParams(queryParams).put(ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", choice);
			System.out.println("Acceptance decision is: " + choice);
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("Acceptance decision failed: " + e.getMessage());
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", -1);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
