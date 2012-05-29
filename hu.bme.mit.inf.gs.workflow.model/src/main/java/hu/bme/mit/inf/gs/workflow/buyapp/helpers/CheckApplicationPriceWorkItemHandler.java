
package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CheckApplicationPriceWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		System.out.println("Executing appprice");
		String address = (String) wi.getParameter("URL");
		Integer applicationID = (Integer) wi.getParameter("ApplicationID");
		
		ClientResponse response = null;
		try {
			Client client = Client.create();
			System.out.println("Request: " + address + "application/?id=" + applicationID);
			WebResource webResource = client.resource(address + "application/?id=" + applicationID);
			response = webResource.get(ClientResponse.class);
			String ret = response.getEntity(String.class);
			System.out.println("Response: (" + response.getStatus() + ") " + ret );
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			
			ApplicationBoundaryEntity entity = ApplicationBoundaryEntity.readFromXML(ret);

			Map<String, Object> results = new HashMap<String, Object>();
			results.put("price", entity.getLastAcceptedMetadata().getPrice());
			results.put("result", response.getStatus());
			results.put("creator", entity.getCreatorName());
			System.out.println("Price is: " + entity.getLastAcceptedMetadata().getPrice());
			System.out.println(entity.getLastAcceptedMetadata());
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("price", -1);
			results.put("creator", "unknown");
			if (response == null)
				results.put("result", -1);
			else
				results.put("result", response.getStatus());
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
