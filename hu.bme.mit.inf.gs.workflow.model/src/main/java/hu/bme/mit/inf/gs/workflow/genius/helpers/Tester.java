package hu.bme.mit.inf.gs.workflow.genius.helpers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Tester {
	public static void main(String[] args) throws Exception {
		Client client = Client.create();
		WebResource webresource = client.resource("http://darvidani.dyndns.org:8001/application/?id=1");
		String ret = webresource.get(String.class);
		System.out.println(ret);
		ApplicationBoundaryEntity entity = ApplicationBoundaryEntity.readFromXML(ret);
		System.out.println(entity.getCreatorName());
		System.out.println(entity.getApplicationID());
		System.out.println(entity.getLastAcceptedMetadata().getPrice());
		
	}
}
