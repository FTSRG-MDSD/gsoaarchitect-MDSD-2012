
// This entity is for Java.
package hu.bme.mit.inf.gs.workflow.genius.helpers;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class RoleEntity {
	
		
	@Id
	
	private java.lang.String roleName;
	
	
		
    @XmlElement
	public java.lang.String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(java.lang.String arg) {
		roleName = arg;
	}

	
}
