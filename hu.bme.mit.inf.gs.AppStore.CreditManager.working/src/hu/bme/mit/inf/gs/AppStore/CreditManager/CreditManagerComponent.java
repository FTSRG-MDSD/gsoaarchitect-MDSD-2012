
// Generated file
// for CreditManager component.
// This component is for JEE.

package hu.bme.mit.inf.gs.AppStore.CreditManager;

import hu.bme.mit.inf.gs.AppStore.CreditManager.model.CreditAccountEntity;
import hu.bme.mit.inf.gs.AppStore.CreditManager.model.CreditTransactionEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.ws.http.HTTPException;

@Path("/")
public class CreditManagerComponent implements ICreditManagerComponent {
		private final String PERSISTENCE_UNIT_NAME = "hu.bme.mit.inf.gs.AppStore.CreditManager.PersistenceUnit";	
		
        @GET
        @Path("cte")
        @Produces("application/xml")
        public CreditTransactionEntity cte() {
        	CreditTransactionEntity cte = new CreditTransactionEntity();
        	cte.setAmount(12.3);
        	cte.setCreditTransactionID(12);
        	cte.setDescription("desc");
        	cte.setTimestamp(98.7);
        	return cte;
        }
        
        @GET
        @Path("cae")
        @Produces("application/xml")
        public CreditAccountEntity cae() {
        	CreditAccountEntity cae = new CreditAccountEntity();
        	cae.setCreditAccountID(1);
        	
        	ArrayList<CreditTransactionEntity> al = new ArrayList<CreditTransactionEntity>();
        	al.add(cte());
        	al.add(cte());
        	
        	cae.setTransactions(al);
        	cae.setUserName("name");
        	
        	return cae;
        }
        
        @GET
        @Path("ctes")
        @Produces("application/xml")
        public List<CreditTransactionEntity> ctes() {
        	List<CreditTransactionEntity> ctes = new ArrayList<CreditTransactionEntity>();
        	ctes.add(cte());
        	ctes.add(cte());
        	return ctes;
        }        
        
        ///////////////////////////////////////////////// >% ////////////////////
        
		public String AddTransaction(CreditTransactionEntity transaction) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        	EntityManager em = factory.createEntityManager();

        	try {    		
	        	Query q = em.createQuery("SELECT ca FROM CreditAccountEntity ca WHERE ca.userName = :userName");
        		q.setParameter("userName", transaction.getUserName());
        		CreditAccountEntity ca = (CreditAccountEntity) q.getSingleResult();
        		
        		if (transaction.getCreditTransactionID() == 0) {
            		Query r = em.createQuery("SELECT MAX(ct.creditTransactionID) FROM CreditTransactionEntity ct");
        			
        			// generating new unique ID
        			Integer ID = (Integer) r.getSingleResult();
        			
        			if (ID == null) {
        				ID = 1;
        			} else {
        				ID++;
        			}
        			
        			transaction.setCreditTransactionID(ID);
        		}
        		
	        	// running merge operation (insert or update) in a transaction
	    		em.getTransaction().begin();
	    		em.merge(transaction);
	    		ca.getTransactions().add(transaction);
	    		em.getTransaction().commit();

	    		return "Transaction #" + transaction.getCreditTransactionID() + " added successfully.";
        	} finally {
        		em.close();
        	}
        }
        public String DeleteTransaction(int transactionID) {
       		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {        		
        		Query q = em.createQuery("SELECT ct FROM CreditTransactionEntity ct WHERE ct.creditTransactionID = :transactionID");
        		q.setParameter("transactionID", transactionID);

    			List<Object> resultList = q.getResultList();
    			
    			if (resultList.size() == 0) {
	    			return "Credit transaction #" + transactionID + " not found.";
	    		} else {
	    			CreditTransactionEntity cte = (CreditTransactionEntity)(resultList.get(0));

	    			// getting transaction's CreditAccountEntity
	        		Query r = em.createQuery("SELECT ca FROM CreditAccountEntity ca WHERE ca.userName = :userName");
	        		r.setParameter("userName", cte.getUserName());
	    			CreditAccountEntity cae = (CreditAccountEntity)(r.getSingleResult());
	    		
	    			em.getTransaction().begin();
	        		cae.getTransactions().remove(cte); // delete from the user's transaction list	    			
	    			em.remove(cte);
	    			em.getTransaction().commit();
	    			return "Credit transaction #" + transactionID + " deleted successfully.";
	    		}
    		} finally {
        		em.close();
    		}
        }
        
        ///////////////////////////////////////////////// >% /////////////////////////////////////////////////
        
        public CreditAccountEntity CreateCreditAccount(java.lang.String userLoginName) {
			// return existing CreditAccountEntity 
        	CreditAccountEntity oldCAE = GetCreditAccount(userLoginName); 
	        if (oldCAE != null) return oldCAE;
			
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        	EntityManager em = factory.createEntityManager();
        	
        	try {    		
        		Query q = em.createQuery("SELECT MAX(ct.creditAccountID) FROM CreditAccountEntity ct");
    			
    			// generating new unique ID
    			Integer maxID = (Integer) q.getSingleResult();

    			if (maxID == null) {
    				maxID = 1;
    			} else {
    				maxID++;
    			}
        		
    			CreditAccountEntity cae = new CreditAccountEntity();
	        	cae.setUserName(userLoginName);
	        	cae.setTransactions(new ArrayList<CreditTransactionEntity>());	        	
	        	cae.setCreditAccountID(maxID);
	        	
	        	// running merge operation (insert or update) in a transaction
	    		em.getTransaction().begin();
	    		em.merge(cae);
	    		em.getTransaction().commit();
	    		
	    		return cae;
        	}  finally {
	    		em.close();
        	}
        }
    	public String DeleteCreditAccount(java.lang.String userLoginName) {
       		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {        		
        		Query q = em.createQuery("SELECT ca FROM CreditAccountEntity ca WHERE ca.userName = :userName");
        		q.setParameter("userName", userLoginName);

        		List<Object> resultList = q.getResultList();
    			
    			if (resultList.size() == 0) {
    				throw new HTTPException(404);
	    			//return "Credit account for user " + userLoginName + " not found.";	    			
	    		} else {
	    			em.getTransaction().begin();
	    			em.remove(resultList.get(0));
	    			em.getTransaction().commit();
	    			return "Credit account for user " + userLoginName + " deleted successfully.";
	    		}
    		} finally {
        		em.close();
    		}
        }
        public String GetCreditAmount(java.lang.String userLoginName) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {
    			Query q = em.createQuery("SELECT ca FROM CreditAccountEntity ca WHERE ca.userName = :userLoginName");
        		q.setParameter("userLoginName", userLoginName);
    			
        		CreditAccountEntity ca = (CreditAccountEntity)(q.getSingleResult());
        		if (ca == null) {
        			return "ca is null";
        		}
        		
    			Double sum = 0.0;
    			for (CreditTransactionEntity ct: ca.getTransactions()) {
					sum += ct.getAmount();
				}
    		
    			sum = Math.round(sum * 100)/100.0;
    			return sum.toString();
    		} catch (NoResultException e) {
    			return e.toString();
    		}
			finally {
				em.close();
    		}
        }
        public CreditAccountEntity GetCreditAccount(java.lang.String userLoginName) {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    		EntityManager em = factory.createEntityManager();
    		
    		try {
        		Query q = em.createQuery("SELECT ca FROM CreditAccountEntity ca WHERE ca.userName = :userLoginName");
        		q.setParameter("userLoginName", userLoginName);
        		
        		List<Object> resultList = q.getResultList();
        		if (resultList.size() == 0) return null;
        		
        		CreditAccountEntity cae = (CreditAccountEntity)(resultList.get(0));        		
        		return cae;    			
    		} finally {
        		em.close();
    		}
        }
        public String AddTransactions(java.util.ArrayList<CreditTransactionEntity> transactions) {
        	for (CreditTransactionEntity creditTransactionEntity : transactions) {
				AddTransaction(creditTransactionEntity);
			}
        	return transactions.size() + " transactions added."; // TODO: exception handling
        }
        
        @GET
        @Path("alltransactions")
        @Produces("application/xml")
        @Consumes("text/plain")
        public java.util.List<CreditTransactionEntity> GetTransactions() {
        	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        	EntityManager em = factory.createEntityManager();
    		
        	List<CreditTransactionEntity> ret = new ArrayList<CreditTransactionEntity>();
        	
        	Query q = em.createQuery("SELECT ct FROM CreditTransactionEntity ct");        	
        	List<Object> resultList = q.getResultList();
        	for (Object object : resultList) {
				ret.add((CreditTransactionEntity)object);
			}
			
			em.close();
			return ret;
        }

		public String hello() {
			return "hello";
		}
}

