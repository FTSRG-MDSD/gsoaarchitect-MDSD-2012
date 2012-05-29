package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository", name="ApplicationBoundaryEntity")
public class ApplicationBoundaryEntity {

	private Integer applicationID;
	private String creatorName;
	private ApplicationMetadataBoundaryEntity lastAcceptedMetadata;
	private ApplicationMetadataBoundaryEntity lastCommitedMetadata;
	
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public Integer getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public ApplicationMetadataBoundaryEntity getLastAcceptedMetadata() {
		return lastAcceptedMetadata;
	}
	public void setLastAcceptedMetadata(ApplicationMetadataBoundaryEntity lastAcceptedMetadata) {
		this.lastAcceptedMetadata = lastAcceptedMetadata;
	}
	@XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public ApplicationMetadataBoundaryEntity getLastCommitedMetadata() {
		return lastCommitedMetadata;
	}
	public void setLastCommitedMetadata(ApplicationMetadataBoundaryEntity lastCommitedMetadata) {
		this.lastCommitedMetadata = lastCommitedMetadata;
	}
	public static ApplicationBoundaryEntity readFromXML(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(ApplicationBoundaryEntity.class);
		Unmarshaller un = context.createUnmarshaller();
		return (ApplicationBoundaryEntity)un.unmarshal(new BufferedReader(new StringReader(xml)));
	}
	
	
}
