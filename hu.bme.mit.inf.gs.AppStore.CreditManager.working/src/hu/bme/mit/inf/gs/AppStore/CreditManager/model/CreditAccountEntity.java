
// This entity is for Java.
package hu.bme.mit.inf.gs.AppStore.CreditManager.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CreditAccountEntity {
	
		
	
	private java.util.List<CreditTransactionEntity> transactions = new java.util.ArrayList<CreditTransactionEntity>();
		
	
	
	private java.lang.String userName;
		
	@Id @GeneratedValue
	
	private int creditAccountID;
	
	
		
    @XmlElement
	@OneToMany (cascade=CascadeType.ALL)
	public java.util.List<CreditTransactionEntity> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(java.util.List<CreditTransactionEntity> arg) {
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
