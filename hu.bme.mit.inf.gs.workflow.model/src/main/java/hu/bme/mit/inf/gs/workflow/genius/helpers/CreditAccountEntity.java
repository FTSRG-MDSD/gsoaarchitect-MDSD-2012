// This entity is for Java.
package hu.bme.mit.inf.gs.workflow.genius.helpers;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CreditAccountEntity {

	@OneToMany
	private java.util.ArrayList<CreditTransactionEntity> transactions = new java.util.ArrayList<CreditTransactionEntity>();

	private java.lang.String userName;

	@Id
	@GeneratedValue
	private int creditAccountID;

	@XmlElement
	public java.util.ArrayList<CreditTransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(java.util.ArrayList<CreditTransactionEntity> arg) {
		transactions = arg;
	}

	@XmlElement
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String arg) {
		userName = arg;
	}

	@XmlElement
	public int getCreditAccountID() {
		return creditAccountID;
	}

	public void setCreditAccountID(int arg) {
		creditAccountID = arg;
	}

}
