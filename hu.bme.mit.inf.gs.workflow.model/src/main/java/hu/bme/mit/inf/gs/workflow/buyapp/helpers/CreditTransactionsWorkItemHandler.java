package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.drools.process.instance.WorkItemHandler;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CreditTransactionsWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		System.out.println("Transaction set handling");
		String address = (String) wi.getParameter("URL");
		String operatorName = (String) wi.getParameter("operatorName");
		String developerName = (String) wi.getParameter("creatorName");
		Integer price = (Integer) wi.getParameter("price");
		String isCompensation = (String) wi.getParameter("isCompensation");
		ClientResponse response = null;
		try {
			CreditTransactionEntity tran1 = null;
			CreditTransactionEntity tran2 = null;
			if (isCompensation.equalsIgnoreCase("false")) {
				tran1 = new CreditTransactionEntity(operatorName, price * 0.3, "30% from application price");
				tran2 = new CreditTransactionEntity(developerName, price * 0.7, "70% from application price");
			} else {
				tran1 = new CreditTransactionEntity(operatorName, price * 0.3 * -1, "COMPENSATION: 30% from application price");
				tran2 = new CreditTransactionEntity(developerName, price * 0.7 * -1, "COMPENSATION: 70% from application price");
			}
			
			
			JAXBContext context = JAXBContext.newInstance(CreditTransactionEntity.class);
			Marshaller m = context.createMarshaller();
			String xml;
			StringWriter sw = new StringWriter();
			m.marshal(tran1, sw);
			xml = "<creditTransactionEntities>";
			xml = xml.concat(sw.toString().replaceFirst("<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>", ""));
			sw = new StringWriter();
			m.marshal(tran2, sw);
			xml = xml.concat(sw.toString().replaceFirst("<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>", ""));
			xml = xml.concat("</creditTransactionEntities>");
			
			Client client = Client.create();
			WebResource webResource = client.resource(address + "transactions/multiple");
			response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, xml);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", response.getStatus());
			System.out.println("Process succesfully ended!");
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("failed: " + e.getMessage());
			Map<String, Object> results = new HashMap<String, Object>();
			if (response != null)
				results.put("result", response.getStatus());
			else
				results.put("result", 500);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}
