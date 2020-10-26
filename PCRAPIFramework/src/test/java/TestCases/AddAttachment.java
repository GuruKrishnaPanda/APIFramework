package TestCases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.Test;

import UtilFiles.DataUtil;
import apiConfig.APIPath;
import apiVerifications.DynamicJsonObject;
import baseTest.BaseTest;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import testDataProvider.TestDataProvider;

public class AddAttachment extends BaseTest{
	
	
@Test(priority = 1, dataProviderClass = TestDataProvider.class , dataProvider = "getData" )
public void testAddAttachment(ITestContext con,Hashtable<String,String> data) {
	
	
	session.logChildNode(data.toString());
	if(!new DataUtil().isRunnable(getClass().getSimpleName(), session.getGenericXLSReader())) {
		// skip in extent rep
		  session.skipTest("Skipping the test as Runmode was NO");
		//skip - testng
		 throw new SkipException("Skipping the test as Runmode was NO");
	}
	
		session.getUtils().doLogin();	
		System.out.println("------------------------------------");
		session.getRequestFactory().addAttachment(APIPath.ADDATTACHMENT,data.get("IssueNumber"));
		response =  session.getResponse().then().extract().response();
		session.getValidation().responseCodeValiddation(response, 200);
		session.childlogJSON("FileUploadResponse -",response.getBody().asString() );
		System.out.println(response.getBody().path("filename"));
		session.end();
}
}
