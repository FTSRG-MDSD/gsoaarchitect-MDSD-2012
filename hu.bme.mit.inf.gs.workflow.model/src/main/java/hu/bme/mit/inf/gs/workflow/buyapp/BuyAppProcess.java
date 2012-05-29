package hu.bme.mit.inf.gs.workflow.buyapp;


import hu.bme.mit.inf.gs.workflow.buyapp.helpers.AddApplicationToUserWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.CheckApplicationPriceWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.CreditTransactionWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.CreditTransactionsWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.GetOperatorUsernameWorkItemHandler;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class BuyAppProcess {
	public static void startProcess(String buyerName, Integer appID, String appRepoURI, String CreditManager, String UserManager) throws Exception {
		KnowledgeBase kbase = readKnowledgeBase();
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		ksession.getWorkItemManager().registerWorkItemHandler("CheckApplicationPrice", new CheckApplicationPriceWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("AddApplicationToUser", new AddApplicationToUserWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("CreditTransactionCollection", new CreditTransactionsWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("CreditTransaction", new CreditTransactionWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("GetOperator", new GetOperatorUsernameWorkItemHandler());
		System.out.println("Starting buyapp...");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("buyerName", buyerName);
		params.put("applicationID", appID);
		params.put("appRepositoryURI", appRepoURI + "/");
		params.put("creditManagerURI", CreditManager + "/");
		params.put("userManagerURI", UserManager + "/");
		
		ksession.startProcess("hu.bme.mit.inf.gs.workflow.buyapp", params);

	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("buyapp.bpmn"), ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
}