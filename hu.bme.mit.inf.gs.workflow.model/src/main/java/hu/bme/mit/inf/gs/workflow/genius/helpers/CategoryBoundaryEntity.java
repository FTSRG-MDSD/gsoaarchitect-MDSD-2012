// This entity is for Java.
package hu.bme.mit.inf.gs.workflow.genius.helpers;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
@XmlRootElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")//, name="CategoryBoundaryEntity")
public class CategoryBoundaryEntity {
	@Id
	private java.lang.String categoryName;
	
    @XmlElement(namespace="http://schemas.datacontract.org/2004/07/AppRepository")
	public java.lang.String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(java.lang.String arg) {
		categoryName = arg;
	}
	
	@Override
	public String toString()
	{
		return this.categoryName;
	}
}
