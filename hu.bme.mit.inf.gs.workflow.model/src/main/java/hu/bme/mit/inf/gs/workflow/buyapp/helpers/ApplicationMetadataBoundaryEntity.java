package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository", name="ApplicationMetadataBoundaryEntity")
public class ApplicationMetadataBoundaryEntity {

	private Integer acceptanceResult;
	private Integer applicationMetadataID;
	private Integer price;
	private String timestamp;
	private String name;
		
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getAcceptanceResult() {
		return acceptanceResult;
	}
	public void setAcceptanceResult(Integer acceptanceResult) {
		this.acceptanceResult = acceptanceResult;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getApplicationMetadataID() {
		return applicationMetadataID;
	}
	public void setApplicationMetadataID(Integer applicationMetadataID) {
		this.applicationMetadataID = applicationMetadataID;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getAgeRestriction() {
		return price;
	}
	public void setAgeRestriction(Integer ageRestriction) {
		this.ageRestriction = ageRestriction;
	}
	
	public static ApplicationMetadataBoundaryEntity readFromXML(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ApplicationMetadataBoundaryEntity.class);
		Unmarshaller un = context.createUnmarshaller();
		return (ApplicationMetadataBoundaryEntity)un.unmarshal(new BufferedReader(new StringReader(xml)));
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public String getName() {
		return name;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public String getTimestamp() {
		return timestamp;
	}
	@Override
	public String toString() {
		return "ApplicationMetadataBoundaryEntity [acceptanceResult="
				+ acceptanceResult + ", applicationMetadataID="
				+ applicationMetadataID + ", price=" + price + ", timestamp="
				+ timestamp + ", name=" + name + "]";
	}
	
	
	
}
