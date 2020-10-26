package apiBuilder;

import static io.restassured.RestAssured.given;

import org.testng.Reporter;

import PayLoads.PayLoad;
import apiSession.APISession;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient {
	
	
	
	public APISession getExecutionSession() {
		return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
		
	}
	
	 
		public Response doGetRequest(String requestPath) {
			
			return 	given()
			   		   .when()
			   		   .get(requestPath);
		}
		public Response doPostRequest(String uri) {
			
			return given().
					header("Content-Type","application/json").
					body(PayLoad.jiraSession()).log().all().
					when().
					post(uri);
		}
}
