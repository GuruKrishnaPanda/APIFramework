package apiVerifications;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import apiSession.APISession;
import io.restassured.response.Response;



public class APIVarificationValidation {
	
	SoftAssert softAssert = new SoftAssert();
	Boolean stopExecution;

	public  void  validateStatusCode(Response response, int i) {
			int actualStatuscode = response.getStatusCode();
			if(actualStatuscode!=i) {
				
				fail("Expected Status Code is "+i+" but Actual Status Code is "+actualStatuscode);
				
			}		
	}
	
	public void responseCodeValiddation(Response response, int statusCode) {

		try {
			int actualStatuscode = response.getStatusCode();
				if(actualStatuscode==statusCode) {
				
				logPass("Expected Status Code is "+statusCode+" but Actual Status Code is "+actualStatuscode);
				}else
					fail("Expected Status Code is "+statusCode+" but Actual Status Code is "+actualStatuscode);
	
		} catch (AssertionError e) {
			fail(e.fillInStackTrace().toString());
			fail("Expected status code is :: " + statusCode + " , insted of getting :: " + response.getStatusCode());
		} catch (Exception e) {
			fail(e.fillInStackTrace().toString());
		}
	}

	public  void responseKeyValidationfromArray(Response response, String key) {
		try {
			JSONArray array = new JSONArray(response.getBody().asString());
			for(int i=0; i<array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
					System.out.println(obj.get(key));
					logPass("Validetd values are  " + obj.get(key));
				
			}
		} catch (Exception e) {
			fail(e.fillInStackTrace().toString());
		}
	}
	
	
	public  void responseKeyValidationFromJsonObject(Response response, String key) {
		try {
			JSONObject json = new JSONObject(response.getBody().asString());
			if(json.has(key) && json.get(key)!= null) {
				logPass("Sucessfully validated value of " + key + " It is " + json.get(key));
			}else {
				fail("Key is not availble");
			}
		} catch (Exception e) {
			fail( e.fillInStackTrace().toString());
		}
	}
	
	
	public  void responseTimeValidation(Response response) {
		try {
			long time=response.time();
			log("Api response time is :: " + time);
		} catch (Exception e) {
			fail( e.fillInStackTrace().toString());
		}
	}
	

	
	public APISession getSession() {
		return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
	}
	
	
	public Boolean isStopExecution() {
		return stopExecution;
	}

	public void setStopExecution(Boolean stopExecution) {
		this.stopExecution = stopExecution;
	}

	public void assertAll() {
		softAssert.assertAll();
		
	}
	public SoftAssert getSoftAssert() {
		return softAssert;
	}
	public void setSoftAssert(SoftAssert softAssert) {
		this.softAssert = softAssert;
	}
	public void log(String message) {
		getSession().childlogMessage(message);
	}
	public void logPass(String message) {
		getSession().childPassTest(message);
	}
	public void fail(String message) {
		// fail in extent reports
				getSession().failTest(message);
				// fail in testng
				softAssert.fail(message);
		
		/*
		if(isStopExecution()) {
			assertAll();
		}*/
	}
	public void childlogMessage(String message) {
		getSession().logMessage(message);
	}
	public void logJSON(String message,String payload ) {
		getSession().logJSON(message, payload);
		
	}


	

}
