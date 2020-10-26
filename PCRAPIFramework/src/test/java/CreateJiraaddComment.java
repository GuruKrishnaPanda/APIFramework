import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cedarsoftware.util.io.JsonWriter;

import PayLoads.PayLoad;
import UtilFiles.ExtentManager;
import UtilFiles.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateJiraaddComment {
	
	
	@Test
	public void createJiraAddComment() {
		ExtentReports reports;
		ExtentTest test;
		reports=ExtentManager.getReports();
		test = reports.createTest("createJiraAddComment");
		test.log(Status.INFO,"Session Creation Started");
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session = new SessionFilter();
		Response res = given().
				header("Content-Type","application/json").
				body(PayLoad.jiraSession()).log().all().
				filter(session).when().
				post("/rest/auth/1/session").
				then().log().all().
				extract().response();//assertThat().statusCode(200).
		test.log(Status.INFO,"<pre>" + "Session Response: " + JsonWriter.formatJson(res.asString()) + "</pre>");
		test.log(Status.INFO,"Session Creation Ended");
//addComment
		test.log(Status.INFO,"Add Comment Started");
		String commentResponse = given().pathParam("key", "10107").
		header("Content-Type","application/json").
		body(PayLoad.addComment()).log().all().
		filter(session).when().
		post("/rest/api/2/issue/{key}/comment").
		then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath commentpath = ReusableMethods.rawToJson(commentResponse);
		int commentID= commentpath.getInt("id");
		System.out.println(commentResponse);
		test.log(Status.INFO,"<pre>" + "Add Comment Response: " + JsonWriter.formatJson(res.asString()) + "</pre>");
		test.log(Status.INFO,"Add Comment Ended");
		//extract().response();//assertThat().statusCode(200).
//Add Attachment
		
		given().pathParam("key", "10107").header("X-Atlassian-Token", "no-check").filter(session).
		header("Content-Type","multipart/form-data").
		multiPart("file",new File("jita.txt")).when().
		post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
// Get the details
		test.log(Status.INFO,"Get Comment Started");
		String response = given().pathParam("key", "10107").
				queryParam("fields", "comment").
		header("Content-Type","application/json").
		log().all().
		filter(session).when().
		get("/rest/api/2/issue/{key}").
		then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = ReusableMethods.rawToJson(response);
		System.out.println("----------------"+response);
		//JsonPath js1 = new JsonPath(response);
		test.log(Status.INFO,"Get Comment Ended");
		test.log(Status.INFO,"Get Comment Ended");
		JsonWriter.formatJson(response);
		
		test.log(Status.INFO,"<pre>" + "Formatted JSON: " + JsonWriter.formatJson(response) + "</pre>");
		reports.flush();
		int commentSize = js.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentSize;i++) {
			
			int  id = js.getInt("fields.comment.comments["+i+"].id");
			System.out.println("The Id is "+id);
			String  idbody = js.get("fields.comment.comments["+i+"].body");
			System.out.println(idbody);
			if(commentID==id) {
				
				
			}
		}

		
	}

}
