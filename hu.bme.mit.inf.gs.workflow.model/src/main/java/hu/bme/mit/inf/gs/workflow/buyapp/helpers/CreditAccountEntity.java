package hu.bme.mit.inf.gs.workflow.buyapp.helpers;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="creditAccountEntity")
public class CreditAccountEntity {

		private Integer creditAccountID;
		private String userName;
		
		
		public Integer getCreditAccountID() {
			return creditAccountID;
		}
		public void setCreditAccountID(Integer creditAccountID) {
			this.creditAccountID = creditAccountID;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public static CreditAccountEntity readFromXML(String xml) throws JAXBException {
			JAXBContext context = JAXBContext.newInstance(CreditAccountEntity.class);
			Unmarshaller un = context.createUnmarshaller();
			return (CreditAccountEntity)un.unmarshal(new BufferedReader(new StringReader(xml)));
		}
		
		
	
}
