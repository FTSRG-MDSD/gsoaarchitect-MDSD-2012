package uploader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import customerBehaviour.App;
import customerBehaviour.Customer;
import customerBehaviour.CustomerBehaviour;
import customerBehaviour.CustomerBehaviourPackage;
import customerBehaviour.Developer;
import customerBehaviour.Purchase;

public class Uploader {
	private static String ADDRESS_USERMANAGER = "http://szarnyasg.sch.bme.hu:8080/UserManagerComponent/";
	private static String ADDRESS_CREDITMANAGER = "http://szarnyasg.sch.bme.hu:8080/CreditManagerComponent/";
	private static String ADDRESS_APPREPO = "http://localhost:8001/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("SAMPLE DATA UPLOADER STARTED.");
		
		Map<App, Integer> appIDs = new HashMap<App, Integer>();

		CustomerBehaviour cb = loadModel();
		 for (Customer c : cb.getCustomers()) {
		 AddUser(c);
		 }

		for (Developer d : cb.getDevelopers()) {
			AddUser(d.getName(), d.getName(), new Date());
			for (App app : d.getAuthoredApps()) {
				int id = AddApplication(app, d.getName());
				appIDs.put(app, id);
			}
		}

		for (Customer c : cb.getCustomers()) {
			for (Purchase p : c.getPurchases()) {
				BuyApplication(appIDs.get(p.getApp()), GetUserLoginName(c));
			}
		}
		
		System.out.println("SAMPLE DATA UPLOADER FINISHED.");
	}

	private static CustomerBehaviour loadModel() {
		// Initialize the model
		CustomerBehaviourPackage.eINSTANCE.eClass();

		// Register the XMI resource factory for the .customerbehaviour
		// extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("customerbehaviour", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Get the resource
		Resource resource = resSet.getResource(
				URI.createURI("instance/genius.customerbehaviour"), true);
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		CustomerBehaviour cb = (CustomerBehaviour) resource.getContents()
				.get(0);
		return cb;
	}

	private static void AddUser(Customer customer) {
		AddUser(GetUserLoginName(customer), customer.getFirstName() + " "
				+ customer.getLastName(), customer.getDateOfBirth());
	}

	private static String GetUserLoginName(Customer customer) {
		String loginName = customer.getFirstName() + customer.getLastName();
		return loginName.replace(' ', '_');
	}

	private static void AddUser(String loginName, String fullName,
			Date birthDate) {
		String customerLoginName = loginName.replace(' ', '_'); // customer.getFirstName()
																// +
																// customer.getLastName();
		System.out.println("Adding customer: " + customerLoginName);
		int appAccountId = -1;
		int creditAccountId = -1;

		try {
			//
			// creating application account for customer
			//
			Client client = Client.create();
			String address = ADDRESS_APPREPO + "applicationaccount/"
					+ customerLoginName;
			System.out.println("  Invoking: " + address);
			WebResource webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			ClientResponse response = webResource.queryParams(queryParams).put(
					ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("WS Invocation error. Error code: "
						+ response.getStatus());
			}
			String ret = response.getEntity(String.class);
			Pattern regex = Pattern.compile(">(.*?)</int>", Pattern.DOTALL);
			Matcher matcher = regex.matcher(ret);
			if (matcher.find()) {
				appAccountId = Integer.parseInt(matcher.group(1));
			} else {
				throw new Exception("Unable to parse returned value: " + ret);
			}
			client.destroy();

			//
			// creating credit account for customer
			//
			client = Client.create();
			address = ADDRESS_CREDITMANAGER + "creditaccount/"
					+ customerLoginName;
			System.out.println("  Invoking: " + address);
			webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			response = webResource.queryParams(queryParams).post(
					ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("WS itt Invocation error. Error code: "
						+ response.getStatus());
			}
			ret = response.getEntity(String.class);
			regex = Pattern.compile("<creditAccountID>(.*?)</creditAccountID>",
					Pattern.DOTALL);
			matcher = regex.matcher(ret);
			if (matcher.find()) {
				creditAccountId = Integer.parseInt(matcher.group(1));
			} else {
				throw new Exception("Unable to parse returned value: " + ret);
			}
			client.destroy();

			//
			// creating user for customer
			//
			UserEntity user = new UserEntity();
			user.setLoginName(customerLoginName);
			user.setPassword("jelszo123");
			user.setEmail("");
			ArrayList<RoleEntity> roles = new ArrayList<RoleEntity>();
			RoleEntity userRole = new RoleEntity();
			userRole.setRoleName("user");
			roles.add(userRole);
			user.setRoles(roles);
			user.setCreditAccountFID(creditAccountId);
			user.setApplicationAccountFID(appAccountId);
			user.setFullName(fullName);
			
			Calendar c = Calendar.getInstance();
			int now = c.get(Calendar.YEAR);
			c.setTime(birthDate);
			int year = c.get(Calendar.YEAR);
			user.setAge(now - year);

			client = Client.create();
			address = ADDRESS_USERMANAGER + "user";
			System.out.println("  Invoking: " + address);
			webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			JAXBContext context = JAXBContext.newInstance(UserEntity.class);
			Marshaller m = context.createMarshaller();
			String xml;
			StringWriter sw = new StringWriter();
			m.marshal(user, sw);
			xml = sw.toString();
			xml = xml
					.replaceFirst(
							"<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>",
							"");

			response = webResource.type(MediaType.APPLICATION_XML).post(
					ClientResponse.class, xml);
			if (response.getStatus() != 200) {
				throw new Exception("WS Invocation error. Error code: "
						+ response.getStatus());
			}
			ret = response.getEntity(String.class);
			System.out.println("Returned: " + ret);
			client.destroy();

		} catch (Exception e) {
			// TODO: change this dirty solution
			e.printStackTrace();
		}
	}

	private static int AddApplication(App application, String authorUsername) {
		authorUsername = authorUsername.replace(' ', '_');
		int appId = -1;
		try {
			//
			// creating application
			//
			Client client = Client.create();
			String address = ADDRESS_APPREPO + "application/" + authorUsername;
			System.out.println("  Invoking: " + address);
			WebResource webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			ClientResponse response = webResource.queryParams(queryParams).put(
					ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("WS Invocation error. Error code: "
						+ response.getStatus());
			}
			String ret = response.getEntity(String.class);
			Pattern regex = Pattern.compile(">(.*?)</int>", Pattern.DOTALL);
			Matcher matcher = regex.matcher(ret);
			if (matcher.find()) {
				appId = Integer.parseInt(matcher.group(1));
			} else {
				throw new Exception("Unable to parse returned value: " + ret);
			}
			client.destroy();

			//
			// adding application metadata
			//
			client = Client.create();
			address = ADDRESS_APPREPO + "application/metadata/?id=" + appId;
			System.out.println("  Invoking: " + address);
			webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			JAXBContext context = JAXBContext
					.newInstance(ApplicationMetadataBoundaryEntity.class);
			Marshaller m = context.createMarshaller();
			String xml;
			StringWriter sw = new StringWriter();
			m.marshal(GetMetadataForApp(application), sw);
			xml = sw.toString();

			// gány replace debug
			if (!xml.contains("<CategoryBoundaryEntity>"))
			{
				xml = xml.replace("<categories>", "<categories><CategoryBoundaryEntity>");
				xml = xml.replace("</categories>", "</CategoryBoundaryEntity></categories>");
				System.out.println("CategoryBoundaryEntity missing XML tag hacked!");
			}
			// gány replace debug vége
			
			response = webResource.type(MediaType.APPLICATION_XML).put(
					ClientResponse.class, xml);
			if (response.getStatus() != 200) {
				throw new Exception("WS Invocation error. Error code: "
						+ response.getStatus());
			}
			client.destroy();

			//
			// accepting metadata
			//
			// application/metadata/acceptance/?id=1&acceptance=1
			client = Client.create();
			address = ADDRESS_APPREPO + "application/metadata/acceptance/?id="
					+ appId + "&acceptance=1";
			System.out.println("  Invoking: " + address);
			webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			response = webResource.queryParams(queryParams).put(
					ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("WS Invocation error. Error code: "
						+ response.getStatus());
			}
			System.out.println("Application added: " + application.getTitle()
					+ " by " + authorUsername);
		} catch (Exception ex) {
			// TODO: change this dirty solution
			ex.printStackTrace();
		}
		return appId;
	}

	private static ApplicationMetadataBoundaryEntity GetMetadataForApp(App app) {
		ApplicationMetadataBoundaryEntity ret = new ApplicationMetadataBoundaryEntity();
		ret.setAcceptanceResult(0);
		ret.setName(app.getTitle());
		ret.setPrice(app.getCurrentPrice());
		CategoryBoundaryEntity categ = new CategoryBoundaryEntity();
		categ.setCategoryName(app.getCategory().getName());
		ArrayList<CategoryBoundaryEntity> categs = new ArrayList<CategoryBoundaryEntity>();
		categs.add(categ);
		ret.setCategories(categs);
		ret.setAgeRestriction(app.getAgeRestriction());
		return ret;
	}

	private static void BuyApplication(int appId, String buyerLoginName) {
		try {
			Client client = Client.create();
			String address = ADDRESS_APPREPO + "buy/" + buyerLoginName
					+ "/?id=" + appId;
			System.out.println("  Invoking: " + address);
			WebResource webResource = client.resource(address);

			// The next two line is needed, because Jersey's Client
			// implementation has a few bugs...
			MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
			queryParams.add("targetID", "12345");

			ClientResponse response = webResource.queryParams(queryParams).put(
					ClientResponse.class, "");
			if (response.getStatus() != 200) {
				throw new Exception("Error code: " + response.getStatus());
			}
			System.out.println("Application bought: appid=" + appId
					+ ", buyer: " + buyerLoginName);
		} catch (Exception e) {
			// TODO: change this dirty solution
			e.printStackTrace();
		}
	}
}
