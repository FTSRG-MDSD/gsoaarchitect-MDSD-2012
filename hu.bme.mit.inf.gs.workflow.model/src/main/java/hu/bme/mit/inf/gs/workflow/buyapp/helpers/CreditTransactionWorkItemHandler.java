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

public class CreditTransactionWorkItemHandler implements WorkItemHandler {

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem wi, WorkItemManager wim) {
		String address = (String) wi.getParameter("URL");
		String buyerName = (String) wi.getParameter("buyerName");
		Integer price = (Integer) wi.getParameter("price");
		String isCompensation = (String) wi.getParameter("isCompensation");
		System.out.println("Transaction handling: ");
		ClientResponse response = null;
		try {
			Client client = Client.create();
			CreditTransactionEntity transaction;
			if (isCompensation.equalsIgnoreCase("false")) {
				transaction = new CreditTransactionEntity(buyerName, (new Integer(price)).doubleValue(), "Buying an application");
			} else {
				transaction = new CreditTransactionEntity(buyerName, (new Integer((price * -1))).doubleValue(), "Buying an application");
			}
			
			
			JAXBContext context = JAXBContext.newInstance(CreditTransactionEntity.class);
			Marshaller marshaller = context.createMarshaller();
			String transactionXML;
			StringWriter sw = new StringWriter();
			System.out.println(transaction);
			marshaller.marshal(transaction, sw);
			transactionXML = sw.toString();
			System.out.println(transactionXML);
			WebResource webResource = client.resource(address + "transaction");
			response = webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, transactionXML);
			String ret = response.getEntity(String.class);
			System.out.println(ret);
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			
			Map<String, Object> results = new HashMap<String, Object>();
			results.put("result", response.getStatus());
			wim.completeWorkItem(wi.getId(), results);

		} catch (Exception e) {
			System.out.println("failed: " + e.getMessage());
			Map<String, Object> results = new HashMap<String, Object>();
			if (response != null)
				results.put("result", response.getStatus());
			else
				results.put("result", 1);
			wim.completeWorkItem(wi.getId(), results);
		}
		
	}

}

