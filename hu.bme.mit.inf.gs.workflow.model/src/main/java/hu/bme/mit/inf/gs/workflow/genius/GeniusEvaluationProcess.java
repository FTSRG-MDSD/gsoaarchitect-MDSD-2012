package hu.bme.mit.inf.gs.workflow.genius;

import hu.bme.mit.inf.gs.workflow.genius.helpers.ApplicationBoundaryEntity;
import hu.bme.mit.inf.gs.workflow.genius.helpers.OfferedApplications;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class GeniusEvaluationProcess
{
	//public static ArrayList<ApplicationMetadataBoundaryEntity> allAppMetadatas = new ArrayList<ApplicationMetadataBoundaryEntity>();
	public static final Random R = new Random();
	public static StatefulKnowledgeSession ksession = null;
	public static OfferedApplications offeredAppList = null;
	
	public static void main(String[] args) throws Exception
	{
		KnowledgeBase kbase = readKnowledgeBase();
		ksession = kbase.newStatefulKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "logs/genius01");
		
		ksession.getWorkItemManager().registerWorkItemHandler("UserIdGetter", new UserIDGetterWorkIntemHandler());

		Map<String, Object> context = new HashMap<String, Object>();
		System.out.println("Start bpmn process!");
		ksession.startProcess("hu.bme.mit.inf.gs.workflow.geniusbpmn", context);
		System.out.println("Fire!");
		ksession.fireAllRules();	// !!
		System.out.println("Rules fired! Offered apps (and their metadata):");
		for (ApplicationBoundaryEntity abe : offeredAppList.getOfferedApps())
		{
			System.out.print("offered:" + abe.getLastAcceptedMetadata().toString());
		}
		logger.close();
	}
	
	private static KnowledgeBase readKnowledgeBase() throws Exception
	{
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("droolshak.bpmn"), ResourceType.BPMN2);
        kbuilder.add(ResourceFactory.newClassPathResource("geniusrules.drl"), ResourceType.DRL);	// !!
		return kbuilder.newKnowledgeBase();
	}
}
