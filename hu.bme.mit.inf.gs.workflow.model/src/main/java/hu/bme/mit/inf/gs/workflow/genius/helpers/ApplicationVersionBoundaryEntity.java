package hu.bme.mit.inf.gs.workflow.genius.helpers;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository", name="ApplicationVersionBoundaryEntity")
public class ApplicationVersionBoundaryEntity {

	private Integer acceptanceResult;
	private Integer applicationVersionID;
	private String timestamp;
	private String versionString;
	
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getAcceptanceResult() {
		return acceptanceResult;
	}
	public void setAcceptanceResult(Integer acceptanceResult) {
		this.acceptanceResult = acceptanceResult;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getApplicationVersionID() {
		return applicationVersionID;
	}
	public void setApplicationVersionID(Integer applicationVersionID) {
		this.applicationVersionID = applicationVersionID;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public String getVersionString() {
		return versionString;
	}
	public void setVersionString(String versionString) {
		this.versionString = versionString;
	}
	
	public static ApplicationVersionBoundaryEntity readFromXML(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ApplicationVersionBoundaryEntity.class);
		Unmarshaller un = context.createUnmarshaller();
		return (ApplicationVersionBoundaryEntity)un.unmarshal(new BufferedReader(new StringReader(xml)));
	}
	
	
}
