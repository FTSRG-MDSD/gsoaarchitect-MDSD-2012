
// Generated file
// for UserManager component.
// This component is for JEE.

package hu.bme.mit.inf.gs.AppStore.UserManager;

import hu.bme.mit.inf.gs.AppStore.UserManager.model.RoleEntity;
import hu.bme.mit.inf.gs.AppStore.UserManager.model.UserEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class UserManagerComponent implements IUserManagerComponent {
		private final String PERSISTENCE_UNIT_NAME = "hu.bme.mit.inf.gs.AppStore.UserManager.PersistenceUnit";	

        @GET
        @Path("ue")
        @Produces("application/xml")
        public UserEntity ue() {
        	UserEntity ue = new UserEntity();
        	ue.setAge(12);
        	AddUser(ue);
        	return ue;
        }
		
		public UserEntity GetUserObject(java.lang.String loginName) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {
        		Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.loginName = :loginName");
        		q.setParameter("loginName", loginName);
    			
    			List<Object> resultList = q.getResultList();

	    		if (resultList.size() == 0) {
	    			return null; // sets status code to 204 No Content
	    		} else {
	    			return (UserEntity)(resultList.get(0));
	    		}
    		} finally {
        		em.close();
    		}
        }
        public String ModifyUser(java.lang.String loginName, UserEntity newUser) {
            DeleteUser(loginName);
            return AddUser(newUser);                
        }
        public String AddUser(UserEntity user) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        	EntityManager em = factory.createEntityManager();
        	
        	try {
	        	// running merge operation (insert or update) in a transaction
	    		em.getTransaction().begin();
	    		em.merge(user);
	    		em.getTransaction().commit();

	    		return user.getLoginName() + " added successfully.";
        	} catch (Exception e) {
        		return e.toString(); // TODO: create more catch branches for fun
        	} finally {
	    		em.close();        		
        	}
        }
        public String DeleteUser(java.lang.String loginName) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {        		
        		Query q = em.createQuery("SELECT u FROM UserEntity u WHERE u.loginName = :loginName");
        		q.setParameter("loginName", loginName);

    			List<Object> resultList = q.getResultList();
    			
    			if (resultList.size() == 0) {
	    			return "User " + loginName + " not found.";
	    		} else {
	    			System.out.println("Login name: " + ((UserEntity)(resultList.get(0))).getLoginName() );
	    			em.getTransaction().begin();
	    			em.remove(resultList.get(0));
	    			em.getTransaction().commit();
	    			return "User " + loginName + " deleted successfully.";
	    		}
    		} finally {
        		em.close();
    		}
        }
        
		public String GetOperatorCreditAccountID() {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {        		
        		Query q = em.createQuery("SELECT u FROM UserEntity u");
    			List<Object> resultList = q.getResultList();
    			for (Object o : resultList) {
					UserEntity u = (UserEntity)o;
					
					// iterate through roles to find the operator
					List<RoleEntity> roles = u.getRoles();
					for (RoleEntity roleEntity : roles) {
						if (roleEntity.getRoleName().equals("operator")) {
							return u.getLoginName();							
						}
					}
				}
    			return null; // sets status code to 204 No Content
    			
    		} finally {
        		em.close();
    		}			
    	}

		public String hello() {
			return "hello";
		}

}

