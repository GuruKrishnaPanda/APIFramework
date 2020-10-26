package apiConfig;



import org.testng.Reporter;

import UtilFiles.FileandEnv;
import UtilFiles.ReusableMethods;
import apiSession.APISession;
import constants.Constant;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class RequestConfigs {
	
	
	
	public  RequestSpecification requestJiraSpec() {
		
		
		String BaseUri = FileandEnv.envAndFile(getExecutionSession().getEnvironment()).get("ServerUrl");
		System.out.println("BaseURI "+BaseUri);
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(BaseUri)
				.addHeaders(new HeaderConfigs().defaultHeaders())
				.build();		
		getExecutionSession().setRequestSpecification(req);
		return req;	
	}
	public  RequestSpecification requestAddAttachmentJiraSpec() {
		
		
		String BaseUri = FileandEnv.envAndFile(getExecutionSession().getEnvironment()).get("ServerUrl");
		System.out.println("BaseURI "+BaseUri);
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(BaseUri)
				.addHeaders(new HeaderConfigs().attachmentHeaders())
				.build();		
		getExecutionSession().setRequestSpecification(req);
		return req;	
	}
	
	public APISession getExecutionSession() {
		return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
		
	}

}
