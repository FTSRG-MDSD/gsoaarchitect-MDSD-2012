package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetOperatorUsernameWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		System.out.println("Getting the operator's creditaccount");
		String address = (String) wi.getParameter("URL");
		ClientResponse response = null;
		try {
			Client client = Client.create();
			System.out.println(address + "operator");
			WebResource webResource = client.resource(address + "operator");
			response = webResource.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", response.getStatus());
			String ret = response.getEntity(String.class);
			System.out.println("operator: " + ret);
			results.put("operator", ret);
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("Failed (" + e.getMessage() + ")");
			Map<String, Object> results = new HashMap<String, Object>();
			if (response != null)
				results.put("result", response.getStatus());
			else
				results.put("result", 500);
			results.put("operator", -1);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}

