package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CodeVerifierWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		String address = (String) wi.getParameter("URL");
		String type = (String) wi.getParameter("type");
		String toCheck = (String) wi.getParameter("toCheck");
		try {
			System.out.println("Verifying code");
			Client client = Client.create();
			WebResource webResource;
			if (type.equalsIgnoreCase("version")) {
				webResource = client.resource(address + "code");
			} else {
				webResource = client.resource(address + "metadata");
			}
			System.out.println(toCheck.replaceFirst("AppRepository", "Server"));
			ClientResponse response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, toCheck.replaceFirst("AppRepository", "Server"));
			String ret = response.getEntity(String.class);
			
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			
			System.out.println(ret);
			Integer retValue = Integer.parseInt(ret.replaceFirst("<int xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">", "").replaceFirst("</int>", ""));
			System.out.println(retValue);
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", retValue);
			
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", 3);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
