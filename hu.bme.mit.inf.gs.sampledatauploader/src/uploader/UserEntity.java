
// This entity is for Java.
package uploader;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class UserEntity {
	
		
	@Id
	
	private java.lang.String loginName;
		
	
	
	private java.lang.String password;
		
	
	
	private java.lang.String email;
		
	
	@OneToMany
	private java.util.ArrayList<RoleEntity> roles = new java.util.ArrayList<RoleEntity>();
		
	
	
	private int creditAccountFID;
		
	
	
	private int applicationAccountFID;
		
	
	
	private java.lang.String fullName;
		
	
	
	private int age;
	
	
		
    @XmlElement
	public java.lang.String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(java.lang.String arg) {
		loginName = arg;
	}

	
		
    @XmlElement
	public java.lang.String getPassword() {
		return password;
	}
	
	public void setPassword(java.lang.String arg) {
		password = arg;
	}

	
		
    @XmlElement
	public java.lang.String getEmail() {
		return email;
	}
	
	public void setEmail(java.lang.String arg) {
		email = arg;
	}

	
		
    @XmlElement
	public java.util.ArrayList<RoleEntity> getRoles() {
		return roles;
	}
	
	public void setRoles(java.util.ArrayList<RoleEntity> arg) {
		roles = arg;
	}

	
		
    @XmlElement
	public int getCreditAccountFID() {
		return creditAccountFID;
	}
	
	public void setCreditAccountFID(int arg) {
		creditAccountFID = arg;
	}

	
		
    @XmlElement
	public int getApplicationAccountFID() {
		return applicationAccountFID;
	}
	
	public void setApplicationAccountFID(int arg) {
		applicationAccountFID = arg;
	}

	
		
    @XmlElement
	public java.lang.String getFullName() {
		return fullName;
	}
	
	public void setFullName(java.lang.String arg) {
		fullName = arg;
	}

	
		
    @XmlElement
	public int getAge() {
		return age;
	}
	
	public void setAge(int arg) {
		age = arg;
	}

	
}
