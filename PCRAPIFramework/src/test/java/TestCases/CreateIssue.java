package TestCases;
import java.util.ArrayList;
import java.util.Hashtable;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;

import PayLoads.PayLoad;
import UtilFiles.DataUtil;
import apiConfig.APIPath;
import baseTest.BaseTest;
import testDataProvider.TestDataProvider;




public class CreateIssue extends BaseTest{
		ArrayList<String> list =  new ArrayList<String>();
	
	@Test(priority = 1, dataProviderClass = TestDataProvider.class , dataProvider = "getData" )
	
	public void testCreateIssue(ITestContext con,Hashtable<String,String> data) {
		
		
		session.logChildNode(data.toString());
		if(!new DataUtil().isRunnable(getClass().getSimpleName(), session.getGenericXLSReader())) {
			// skip in extent rep
			  session.skipTest("Skipping the test as Runmode was NO");
			//skip - testng
			 throw new SkipException("Skipping the test as Runmode was NO");
		}
		
			//session.getUtils().doLogin();
			session.Request().doLogin();
			System.out.println("------------------------------------");
			//session.getRequestFactory().createNewJira(data,APIPath.CREATEISSUE_POST);
			session.Request().doPostRequest(APIPath.CREATEISSUE_POST, PayLoad.issueCreation());
			response =  session.getResponse().then().extract().response();
			session.getValidation().responseCodeValiddation(response, 201);
			
			session.childlogMessage("Jira Created Response Header" + response.getHeaders().toString());
			session.childlogMessage("Jira Created Response Header" + response.getBody().toString());
			session.childlogJSON("Jira Created Response", response.asString());
			session.childlogMessage("The Jira Number is "+session.getUtils().getJsonValue(response, "id")+" and the Key is "
			+session.getUtils().getJsonValue(response, "key"));
			con.setAttribute("key", session.getUtils().getJsonValue(response, "key"));
			con.setAttribute("id", session.getUtils().getJsonValue(response, "id"));
			list.add(session.getUtils().getJsonValue(response, "key"));
			session.end();
	}
	

	@Test (priority = 2,dependsOnMethods = { "testCreateIssue" })
	public void testGetIssue(ITestContext con) {
		for (int i =0;i<list.size();i++) {
			
			session.childlogMessage("Execution of "+new Exception().getStackTrace()[0].getMethodName()+" Started");
			
			response = session.getRequestFactory().
					//getJiraDetails(con.getAttribute("id")).
					getJiraDetails(list.get(i))
					.then().log().all().
					extract().response();
			
			session.getValidation().validateStatusCode(response,200);
			session.childlogJSON("Jira Created Response", response.asString());
			session.end();		
		}
		
	}
}

	
	

