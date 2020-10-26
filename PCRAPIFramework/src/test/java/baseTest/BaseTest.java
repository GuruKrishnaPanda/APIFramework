package baseTest;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



import UtilFiles.GenericXLSXReader;
import UtilFiles.TestDataBuilder;
import apiSession.APISession;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseTest {
	public APISession session;
	public String currentTestcase=null;
	public  RequestSpecification req;
	public ResponseSpecification  res;
	public Response response;
	public TestDataBuilder data =  new TestDataBuilder();
	public String testName=null;
	//public GenericXLSXReader xls = new GenericXLSXReader(System.getProperty("user.dir")+"//input//Data.xlsx");
/*
@BeforeMethod
public void init(ITestResult result) {
	System.out.println("@BeforeMethod");
	testName = result.getMethod().getMethodName().toUpperCase();
	System.out.println(testName);
	session= new APISession();
	session.init(testName);

}*/
	@BeforeMethod
	public void init(ITestResult result) {
		System.out.println("@BeforeMethod");
		testName = result.getMethod().getMethodName().toUpperCase();
		System.out.println(testName);
		session.createChildNode(testName);
		
		
	}

	@BeforeClass
	public void init() {
		System.out.println("@BeforeClass");
		session= new APISession();	
		testName = this.getClass().getSimpleName();
		session.init(testName);
	}
	
	
@AfterMethod
public void quit() {
	//session.getCon().quit();

}
@AfterClass
public void quit1() {
	//session.getCon().quit();
	session.generateReports();
}

}
