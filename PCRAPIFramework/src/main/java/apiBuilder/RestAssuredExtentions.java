package apiBuilder;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;

import org.testng.Reporter;

import UtilFiles.FileandEnv;
import apiConfig.APIPath;
import apiConfig.HeaderConfigs;
import apiConfig.RequestConfigs;
import apiSession.APISession;
import constants.Constant;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtentions {
	
	public static RequestSpecification Request;
	public static Response response;
	
	public RestAssuredExtentions() {
		String BaseUri = FileandEnv.envAndFile(getExecutionSession().getEnvironment()).get("ServerUrl");
		System.out.println("BaseURI "+BaseUri);
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		
		builder.setBaseUri(BaseUri);
		builder.addHeaders(new HeaderConfigs().defaultHeaders());
		RequestSpecification requestSpec = builder.build();	
		Request =  RestAssured.given().log().all().spec(requestSpec);
		//getExecutionSession().setRequestSpecification(req);
		//return req;	
	}

	public void doLogin() {
		SessionFilter loginsession;
		getExecutionSession().logChildNode("Preforming Login Action");
		try {
			
			 loginsession =  new SessionFilter();
				Response res = /* given().log().all().spec(new RequestConfigs().requestJiraSpec()). */
					Request.body(new PostAPIBuilder().loginRequestBody()).
					filter(loginsession).when().
					post(APIPath.LOGIN_POST).
					then().log().all().
					extract().response();
			
			if(res.getStatusCode()!=200) {
				getExecutionSession().stopExecution("Unable to Log in to Application.Please check,it was expected 200 as Status code,"
						+ "but receiving  -"+res.getStatusCode());
			}else {
				getExecutionSession().setLoginsession(loginsession);
				getExecutionSession().childPassTest("Successfully Logged into System");
			}
			
		}catch(Exception e)
		{
			getExecutionSession().stopExecution("Unable to Log in due to the Error - "+e.toString());
		}	
	}
	
	public void  doPostRequest(String resource, String payLoad) {
		String httpMethod = "";
		
		try {
			//String apiReso =  getapiResource(resource);
			Response response = //spec(getExecutionSession().getUtils().requestJiraSpec()).
				//spec(new RequestConfigs().requestJiraSpec()).
			Request.body(payLoad).
				filter(getExecutionSession().getLoginsession())
				.when()
				.post(resource);
			System.out.println(response.then().extract().response().asString());
			getExecutionSession().setResponse(response);
			
		}catch(Exception e) {
			getExecutionSession().stopExecution("Connection Time Oute with error "+e.toString());
		}
		
	}
	public void  addAttachmentPostRequest(String resource,String pathValue) {
		
		try {
			//String apiReso =  getapiResource(resource);
			response = given().log().all().
					//spec(getExecutionSession().getUtils().requestJiraSpec()).
					spec(new RequestConfigs().requestAddAttachmentJiraSpec())
					.pathParam("KEY_VALUE", pathValue)
					.multiPart("file",new File("jita.txt"))
					.filter(getExecutionSession().getLoginsession())
					.when()
					.post(resource);
			
			System.out.println(response.then().extract().response().asString());
			getExecutionSession().setResponse(response);
			
		}catch(Exception e) {
			getExecutionSession().stopExecution("Connection Time Oute with error "+e.toString());
		}
		
}
	public Response doGetRequestWithQueryParam(String res, Map<String, String> params) {

		Response response = given()
								.queryParams(params)
								.when()
								.get(res);
		


		return response;

	}

	public Response doGetRequestWithPathParam(String apiResource, Object object) {

		
		Response response =  given().log().all().
											spec(new RequestConfigs().requestJiraSpec()).
											pathParam("value", object.toString()).
											filter(getExecutionSession().getLoginsession())
											.when()
											.get("");
		return response;

	}

	/**
	* 
	* @param res
	* @param headers
	* @return
	*/
	public Response doGetRequestWithHeader(String res,
		  Map<String, String> headers) {

			Response response = given()
									.headers(headers)
									.when()
									.get(res);

			return response;

	}


	/**
	* 
	* @return
	*/
	public Response doPutRequest(String res , Object body) {

		Response response = given()
								.when()
								.body(body)
								.put(res);

		return response;

	}

	/**
	* 
	* @return
	*/
	public Response doPatchRequest(String res , Object body) {

		Response response = given()
								.when()
								.body(body)
								.patch(res);

		return response;

	}

	/**
	* 
	* @return
	*/
	public Response doDeleteRequest(String res) {

		Response response = given()
									.when()
									.delete(res);

		return response;

	}

public APISession getExecutionSession() {
		return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
		
	}

}
