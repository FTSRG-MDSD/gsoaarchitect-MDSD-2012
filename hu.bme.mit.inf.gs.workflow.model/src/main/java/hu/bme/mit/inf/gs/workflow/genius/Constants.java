package hu.bme.mit.inf.gs.workflow.genius;

public class Constants
{
	// work item constants
	public static String address = "http://rudolfking.dyndns.org:8001/";
	public static String useraddress = "http://szarnyasg.sch.bme.hu:8080/UserManagerComponent/";
	public static String userloginname = "JeremyMiles";
	
	// drools rules constants
	public static Double priceLimit = 8.0; // exclusive
	public static Integer purchaseLimit = 4; // inclusive
	public static Integer categoryHitLimit = 4; // inclusive
	public static Integer creatorHitLimit = 6; // inclusive
	public static Integer maxOfferableApps = 10; // exclusive

	public static Integer maxOfferFromCategory = 5; // inclusive
	public static Integer maxOfferFromCreator = 5; // inclusive
	public static Integer maxOfferFromPopularity = 10; // inclusive
}
