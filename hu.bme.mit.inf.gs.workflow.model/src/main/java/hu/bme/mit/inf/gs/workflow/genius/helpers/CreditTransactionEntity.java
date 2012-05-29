// This entity is for Java.
package hu.bme.mit.inf.gs.workflow.genius.helpers;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CreditTransactionEntity {

	@Id
	@GeneratedValue
	private int creditTransactionID;

	private double timestamp;

	private double amount;

	private java.lang.String description;

	@XmlElement
	public int getCreditTransactionID() {
		return creditTransactionID;
	}

	public void setCreditTransactionID(int arg) {
		creditTransactionID = arg;
	}

	@XmlElement
	public double getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(double arg) {
		timestamp = arg;
	}

	@XmlElement
	public double getAmount() {
		return amount;
	}

	public void setAmount(double arg) {
		amount = arg;
	}

	@XmlElement
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String arg) {
		description = arg;
	}

}
