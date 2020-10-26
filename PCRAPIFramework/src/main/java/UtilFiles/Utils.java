package UtilFiles;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.apache.http.client.methods.RequestBuilder;
import org.mozilla.javascript.ast.NewExpression;
import org.testng.Reporter;

import PayLoads.PayLoad;
import apiBuilder.PostAPIBuilder;
import apiConfig.APIPath;
import apiConfig.RequestConfigs;
import apiSession.APISession;
import constants.APIResources;
import constants.Constant;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	RequestSpecification req;
	ResponseSpecification  res;
	Response response;
	APIResources resourceAPI;
	RequestSpecBuilder builder;
	
	public  RequestSpecification requestSpec() {
		
		 req = new RequestSpecBuilder()
					.setBaseUri(Constant.mapBaseURI)
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			getExecutionSession().setRequestSpecification(req);
			return req;
			
		}
	
	public  ResponseSpecification responseSpec() {
			
		 res =new ResponseSpecBuilder()
					.expectContentType(ContentType.JSON).build();
		 getExecutionSession().setResponseSpecification(res);
			return res;
			
		}


	public void doLogin() {
		SessionFilter loginsession;
		getExecutionSession().logChildNode("Preforming Login Action");
		try {
			
			 loginsession =  new SessionFilter();
			Response res = given().log().all().spec(new RequestConfigs().requestJiraSpec()).
					body(new PostAPIBuilder().loginRequestBody()).
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
	
	public String getJsonValue(Response response, String key) {
		
		String res =  response.asString();
		JsonPath  js =  new JsonPath(res);
		return js.get(key).toString();	
		
	}
	public String getapiResource(String resource) {
		resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		String apiReso = resourceAPI.getResource();
		return resourceAPI.getResource();
	}

public void  doPostRequest(String resource, String payLoad) {
	String httpMethod = "";
	
	try {
		//String apiReso =  getapiResource(resource);
		response = given().log().all().
				//spec(getExecutionSession().getUtils().requestJiraSpec()).
				spec(new RequestConfigs().requestJiraSpec()).
				body(payLoad).
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



public APISession getExecutionSession() {
	return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
	
}

public Response doGetRequestWithQueryParam(String res, Map<String, String> params) {

	Response response = given()
							.queryParams(params)
							.when()
							.get(res);
	


	return response;

}

public Response doGetRequestWithPathParam(String apiResource, Object object) {

	String apiRes = getapiResource(apiResource);
	Response response =  given().log().all().
										spec(new RequestConfigs().requestJiraSpec()).
										pathParam("value", object.toString()).
										filter(getExecutionSession().getLoginsession())
										.when()
										.get(apiRes);
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

}
