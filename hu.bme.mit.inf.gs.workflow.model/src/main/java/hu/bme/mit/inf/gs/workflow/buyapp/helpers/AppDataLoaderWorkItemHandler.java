package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.HashMap;
import java.util.Map;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class AppDataLoaderWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		String address = (String) wi.getParameter("URL");
		Integer applicationID = (Integer) wi.getParameter("ApplicationID");
		String type = (String) wi.getParameter("type");
		try {
			Client client = Client.create();
			WebResource webResource;
			if (type.equalsIgnoreCase("version")) {
				webResource = client.resource(address + "application/version/?id=" + applicationID);
			} else {
				webResource = client.resource(address + "application/metadata/?id=" + applicationID);
			}
			ClientResponse response = webResource.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			Map<String, Object> results = new HashMap<String, Object>();
			String ret = response.getEntity(String.class);
			if (type.equalsIgnoreCase("metadata")) {
				ApplicationMetadataBoundaryEntity meta = ApplicationMetadataBoundaryEntity.readFromXML(ret);
				if (meta.getAcceptanceResult() != 3 && meta.getAcceptanceResult() != 0) {
					System.out.println("Already decided...");
					results.put("appDataXML", "");
				} else {
					results.put("appDataXML", ret);
					results.put("ver", meta.getTimestamp());
					results.put("name", meta.getName());
					results.put("ID", meta.getApplicationMetadataID());
					System.out.println("App data load done");
				}
			} else {
				ApplicationVersionBoundaryEntity ver = ApplicationVersionBoundaryEntity.readFromXML(ret);
				if (ver.getAcceptanceResult() != 3 && ver.getAcceptanceResult() != 0) {
					System.out.println("Already decided...");
					results.put("appDataXML", "");
				} else {
					results.put("appDataXML", ret);
					results.put("ver", ver.getTimestamp());
					results.put("ID", ver.getApplicationVersionID());
					System.out.println("App data load done");
				}
			}
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("appDataXML", "");
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
