
// Generated file
// for UserManager component.
// This component is for JEE.

package hu.bme.mit.inf.gs.AppStore.UserManager;

import hu.bme.mit.inf.gs.AppStore.UserManager.model.UserEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface IUserManagerComponent {
        /**
         *  
         *
         * @param String loginName
         * @return UserEntity
         */
        @GET
        @Path("user/{loginName}")
        @Consumes("text/plain")
        @Produces("application/xml")
        UserEntity GetUserObject(@PathParam("loginName") java.lang.String loginName);        
        /**
         *  
         *
         * @param String loginName
         * @param UserEntity newUser
         * @return String
         */
        @POST
        @Path("user/modify/{loginName}")
        @Consumes("application/xml")
        @Produces("text/plain")
        String ModifyUser(@PathParam("loginName") java.lang.String loginName, UserEntity newUser);        
        /**
         *  
         *
         * @param UserEntity user
         * @return String
         */
        @POST
        @Path("user")
        @Consumes("application/xml")
        @Produces("text/plain")
        String AddUser(UserEntity user);        
        /**
         *  
         *
         * @param String loginName
         * @return String
         */
        @DELETE
        @Path("user/{loginName}")
        @Consumes("text/plain")
        @Produces("text/plain")
        String DeleteUser(@PathParam("loginName") java.lang.String loginName);        
		/**
		 *  
		 *
	
		 * @return String
		 */
		@GET
		@Path("operator")
		String GetOperatorCreditAccountID();
		
		@GET
		@Path("hello")
        @Consumes("text/plain")
        @Produces("text/plain")
		String hello();
}

