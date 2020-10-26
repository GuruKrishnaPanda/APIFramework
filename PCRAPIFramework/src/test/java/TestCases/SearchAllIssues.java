package TestCases;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;

import UtilFiles.DataUtil;
import apiConfig.APIPath;
import apiVerifications.DynamicJsonObject;
import baseTest.BaseTest;






public class SearchAllIssues extends BaseTest{

	
@Test

public void testCreateIssue(ITestContext con) {

	if(!new DataUtil().isRunnable(getClass().getSimpleName(), session.getGenericXLSReader())) {
		// skip in extent rep
		  session.skipTest("Skipping the test as Runmode was NO");
		//skip - testng
		 throw new SkipException("Skipping the test as Runmode was NO");
	}

	session.logChildNode(data.toString());
		session.getUtils().doLogin();
	
		session.getRequestFactory().getAllJiraDetails(APIPath.SEARCHISSUE_ALL_POST);
		response =  session.getResponse().then().extract().response();
		session.getValidation().responseCodeValiddation(response, 200);
	
		JSONObject inputjsonObject  =  new JSONObject(response.getBody().asString());
		DynamicJsonObject.getArrayValueFromObject(inputjsonObject,"issues");
		
		DynamicJsonObject.getKey(inputjsonObject, "key");
		ArrayList<String> issuesdata = response.getBody().path("issues");
		session.childPassTest("Expected 100 issues  and actual is also "+issuesdata.size()+" Issues");
		System.out.println(issuesdata);	
		session.end();
}

}
