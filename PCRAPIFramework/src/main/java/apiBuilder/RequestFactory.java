package apiBuilder;
import java.util.Hashtable;

import org.testng.Reporter;

import PayLoads.PayLoad;
import UtilFiles.Utils;
import apiSession.APISession;
import io.restassured.response.Response;

public class RequestFactory {
	Utils utils = new Utils();
	
	public void createNewJira(Hashtable<String, String> data,String uri) {
		
		System.out.println("------------------------");
		//String uri ="createJiraAPI";
		String dataSet = PayLoad.issueCreation();
		utils.doPostRequest(uri, dataSet);	
	}
	
	public void addAttachment(String uri,String pathParam) {
		
		utils.addAttachmentPostRequest(uri,pathParam);	
	}
public Response getJiraDetails(Object object) {
		String apiResource ="getIssueAPI";
		Response response = utils.doGetRequestWithPathParam(apiResource, object);
	
		return response;	
	}
public void getAllJiraDetails(String uri) {
	System.out.println("------------------------");
	//String uri ="createJiraAPI";
	String dataSet = PayLoad.searchAllIssueOfProject();
	utils.doPostRequest(uri, dataSet);
	
}
public void createComment(String uri, String dataSet) {
	
	System.out.println("------------------------");
	
	 utils.doPostRequest(uri, dataSet);

	
}
public APISession getExecutionSession() {
	return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
	
}
}
