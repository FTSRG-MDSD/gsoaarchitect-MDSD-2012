package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CreditTransactionEntity {
	
	private double amount;
	private int creditTransactionID;
	private String description;
	private double timestamp;
	private String userName;
	
	@XmlElement
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@XmlElement
	public int getCreditTransactionID() {
		return creditTransactionID;
	}
	public void setCreditTransactionID(int creditTransactionID) {
		this.creditTransactionID = creditTransactionID;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public double getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(double timestamp) {
		this.timestamp = timestamp;
	}
	@XmlElement
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public CreditTransactionEntity(String username, Double amount, String description) {
		this.description = description;
		this.userName = username;
		this.amount = amount;
		this.creditTransactionID = 0;
		this.timestamp = 100.1;
	}
	@Override
	public String toString() {
		return "CreditTransactionEntity [amount=" + amount
				+ ", creditTransactionID=" + creditTransactionID
				+ ", description=" + description + ", timestamp=" + timestamp
				+ ", username=" + userName + "]";
	}
	
	public CreditTransactionEntity() {}
	
	
	
	

}
