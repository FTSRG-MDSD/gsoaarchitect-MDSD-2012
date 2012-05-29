// Generated file
// for CreditManager component.
// This component is for JEE.

package hu.bme.mit.inf.gs.AppStore.CreditManager;

import hu.bme.mit.inf.gs.AppStore.CreditManager.model.CreditAccountEntity;
import hu.bme.mit.inf.gs.AppStore.CreditManager.model.CreditTransactionEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

public interface ICreditManagerComponent {
	/**
	 * 
	 * 
	 * @param CreditTransactionEntity
	 *            transaction
	 * @return String
	 */
	@POST
	@Path("transaction")
	@Consumes("application/xml")
	@Produces("text/plain")
	String AddTransaction(CreditTransactionEntity transaction);

	/**
	 * 
	 * 
	 * @param Integer
	 *            transactionID
	 * @return String
	 */
	@DELETE
	@Path("transaction/{transactionID}")
	@Consumes("text/plain")
	@Produces("text/plain")
	String DeleteTransaction(@PathParam("transactionID") int transactionID);

	/**
	 * 
	 * 
	 * @param String
	 *            userLoginName
	 * @return CreditAccountEntity
	 */

	@POST
	@Path("creditaccount/{userLoginName}")
	@Consumes("text/plain")
	@Produces("application/xml")
	CreditAccountEntity CreateCreditAccount(
			@PathParam("userLoginName") java.lang.String userLoginName);

	/**
	 * 
	 * 
	 * @param String
	 *            userLoginName
	 * @return String
	 */
	@DELETE
	@Path("creditaccount/{userLoginName}")
	@Consumes("text/plain")
	@Produces("text/plain")
	String DeleteCreditAccount(
			@PathParam("userLoginName") java.lang.String userLoginName);

	/**
	 * 
	 * 
	 * @param String
	 *            userLoginName
	 * @return double
	 */
	@GET
	@Path("creditaccount/amount/{userLoginName}")
	@Consumes("text/plain")
	@Produces("text/plain")
	String GetCreditAmount(
			@PathParam("userLoginName") java.lang.String userLoginName);

	/**
	 * 
	 * 
	 * @param String
	 *            userLoginName
	 * @return CreditAccountEntity
	 */
	@GET
	@Path("creditaccount/{userLoginName}")
	@Consumes("text/plain")
	@Produces("application/xml")
	CreditAccountEntity GetCreditAccount(
			@PathParam("userLoginName") java.lang.String userLoginName);

	/**
	 * Adds multiple credit transactions.
	 * 
	 * @param NOT_USED
	 *            -- there is no platform independent name for collections
	 *            transactions
	 * @return String
	 */
	@POST
	@Path("transactions/multiple")
	@Consumes("application/xml")
	@Produces("text/plain")
	String AddTransactions(
			java.util.ArrayList<CreditTransactionEntity> transactions);

	@GET
	@Path("hello")
	@Consumes("text/plain")
	@Produces("text/plain")
	String hello();
}
