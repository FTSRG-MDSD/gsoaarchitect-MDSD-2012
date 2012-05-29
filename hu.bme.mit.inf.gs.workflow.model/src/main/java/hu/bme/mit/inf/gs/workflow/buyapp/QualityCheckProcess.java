package hu.bme.mit.inf.gs.workflow.buyapp;


import hu.bme.mit.inf.gs.workflow.buyapp.helpers.AcceptanceDecisionWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.AppDataLoaderWorkItemHandler;
import hu.bme.mit.inf.gs.workflow.buyapp.helpers.CodeVerifierWorkItemHandler;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.workitem.wsht.WSHumanTaskHandler;

public class QualityCheckProcess {
	public static void startQualityCheckProcess(String appRepo, String codeVerifier, boolean isNewVersion, int appID) throws Exception {
		KnowledgeBase kbase = readKnowledgeBase();
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		System.out.println("Starting qualityCheck...");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applicationID", appID);
		params.put("appRepositoryURI", appRepo + "/");
		params.put("codeVerifierURI", codeVerifier + "/");
		params.put("isNewVersion", isNewVersion);
		params.put("isNewMetadata", !isNewVersion);
		ksession.getWorkItemManager().registerWorkItemHandler("Human Task", new WSHumanTaskHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("AppDataLoader", new AppDataLoaderWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("CodeVerifier", new CodeVerifierWorkItemHandler());
		ksession.getWorkItemManager().registerWorkItemHandler("AcceptanceDecision", new AcceptanceDecisionWorkItemHandler());
		
		ksession.startProcess("hu.bme.mit.inf.gs.workflow.qualitychk", params);

	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("qualitychk.bpmn"), ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
}
