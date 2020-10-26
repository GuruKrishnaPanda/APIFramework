package Practice;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import PayLoads.PayLoad;
import constants.Constant;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testBase {

	
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log ;
		
			 log =new PrintStream(new FileOutputStream("logging.txt"));
		
		RequestSpecification req =
				new RequestSpecBuilder()
				.setBaseUri(Constant.localJiraBaseURI)
				.addHeader("Content-Type", "application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();	
		return req;
		
	}
	
	
	public SessionFilter getSession() {
		SessionFilter loginsession =  new SessionFilter();
		try {
			Response res = given().log().all().spec(requestSpecification()).
			body(PayLoad.jiraSession()).
			filter(loginsession).when().
			post("/rest/auth/1/session").then().extract().response();
			System.out.println(res.getSessionId());
			System.out.println(res.getStatusCode());
			
			return loginsession;
			
		}catch(Exception e)
		{
			return loginsession;
		}
		
		
	}
	
}
