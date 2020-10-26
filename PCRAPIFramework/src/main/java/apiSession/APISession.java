package apiSession;


import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cedarsoftware.util.io.JsonWriter;

import UtilFiles.ExtentManager;
import UtilFiles.GenericXLSXReader;
import UtilFiles.Utils;
import apiBuilder.RequestFactory;
import apiBuilder.RestAssuredExtentions;
import apiVerifications.APIVarificationValidation;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class APISession {
	ExtentReports reports;
	ExtentTest logger;
	ExtentTest childlog;
	boolean executeListner;
	APIVarificationValidation validate;
	Utils util;
	RequestSpecification requestSpecification;
	ResponseSpecification  responseSpecification;
	Response response;
	SessionFilter loginsession;
	RequestFactory reqFactory;
	GenericXLSXReader xlsx;
	String environment;
	RestAssuredExtentions request;
	
	//SoftAssert softAssert ;

	
	public APISession() {
		System.out.println("----------Creating the Object of API Session-------------");
		validate =  new APIVarificationValidation();
		xlsx =  new GenericXLSXReader(System.getProperty("user.dir")+"\\input\\ExecutionSheet.xlsx");
		setEnvironment(xlsx.getCellData("Configuration", 4, 6));
	}
	
	public void init(String currentTestcase) {
		System.out.println("getting session");
		 
		if(Reporter.getCurrentTestResult().getTestContext().getAttribute("session") == null) {
			 Reporter.getCurrentTestResult().getTestContext().setAttribute("session", this);
			 reports=ExtentManager.getReports();
			 logger = reports.createTest(currentTestcase);
			 logMessage("Processing execution in "+getEnvironment()+" environment");
			 
		}
			 //getUtils().getSession(); 
			 //softAssert = new SoftAssert();
			 	
	}
public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

public APIVarificationValidation getValidation() {
		
		return validate;
	}

public RestAssuredExtentions Request() {
	
	return new RestAssuredExtentions();
}
public GenericXLSXReader getGenericXLSReader() {
	
	return xlsx;
}


public Utils getUtils() {
	
	return new Utils();
}
public RequestFactory getRequestFactory() {
	reqFactory= new RequestFactory();
	return reqFactory;
}

	public void end() {
		getValidation().assertAll();
	}

	public RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}


	public void setRequestSpecification(RequestSpecification requestSpecification) {
		System.out.println("Setting the RequestSpecification");
		this.requestSpecification = requestSpecification;
	}


	public ResponseSpecification getResponseSpecification() {
		return responseSpecification;
	}


	public void setResponseSpecification(ResponseSpecification responseSpecification) {
		this.responseSpecification = responseSpecification;
	}


	public Response getResponse() {
		return response;
	}


	public void setResponse(Response response) {
		this.response = response;
	}
	public SessionFilter getLoginsession() {
		return loginsession;
	}

	public void setLoginsession(SessionFilter loginsession) {
		this.loginsession = loginsession;
	}

/****************Reporting function*****************/
	public void logJSON(String message,String payload) {
		
		logger.log(Status.INFO,"<pre>" + message +" : " + JsonWriter.formatJson(payload) + "</pre>");
	}
	public void logMessage(String message) {
		
		logger.log(Status.INFO,message);
	}
public void logPass(String message) {
		
		logger.log(Status.PASS,message);
	}
	public void generateReports() {
		if(reports !=null)
			reports.flush();
	}
	public void failTest(String failureMessage) {
		//fail in extent
		System.out.println("Failing "+failureMessage);
		childfailTest(failureMessage);
	}
	public void stopExecution(String failureMessage) {
		//fail in extent
		System.out.println("Failing "+failureMessage);
		//childfailTest(failureMessage);
		getValidation().fail(failureMessage);
		end();
		
		
	}
	public void skipTest(String message) {
		childlog.log(Status.SKIP, message);
	}


public ExtentTest createChildNode(String string) {
	childlog =  logger.createNode(string);
	return childlog;
}
public void logChildNode(String MSG) {
	childlog.log(Status.INFO, MSG);
}
public void childPassTest(String MSG) {
	childlog.log(Status.PASS, MSG);
}
public void childlogJSON(String message,String payload) {
	
	childlog.log(Status.INFO,"<pre>" + message +" : " + JsonWriter.formatJson(payload) + "</pre>");
}
public void childlogMessage(String message) {
	
	childlog.log(Status.INFO,message);
}
public void childfailTest(String failureMessage) {
	System.out.println("Failing "+failureMessage);
	childlog.log(Status.FAIL, failureMessage);
	
}
}
