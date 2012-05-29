package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement
@XmlSeeAlso(CreditTransactionEntity.class)
public class CreditTransactionCollection {

	private List<CreditTransactionEntity> creditTransactionEntitites = new ArrayList<CreditTransactionEntity>();

	@XmlElement
	public List<CreditTransactionEntity> getCreditTransactionEntitites() {
		return creditTransactionEntitites;
	}

	public void setCreditTransactionEntitites(
			List<CreditTransactionEntity> creditTransactionEntitites) {
		this.creditTransactionEntitites = creditTransactionEntitites;
	}
	
	
	
	
}
