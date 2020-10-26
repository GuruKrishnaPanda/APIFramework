import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import PayLoads.PayLoad;
import UtilFiles.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Jira {
	
	
	@Test(priority=1)

	public void jiraSession(ITestContext context)
	{
		RestAssured.baseURI="http://localhost:8080";

		String resp=given().

				header("Content-Type","application/json").

				body(PayLoad.jiraSession()).log().all().
				when().
				post("/rest/auth/1/session").
				then().
				extract().response().asString();//assertThat().statusCode(200).
		JsonPath js= ReusableMethods.rawToJson(resp);
		context.setAttribute("sessionID", js.get("session.value"));
		
		System.out.println(resp);
		System.out.println(context.getAttribute("sessionID"));
	}
	@Test(priority=2)

	public void getData(ITestContext context)
	{
		//RestAssured.baseURI="http://localhost:8080";
		String sessionid1 = (String)context.getAttribute("sessionID");
		String resp=given().

				header("Content-Type","application/json").
				header("cookie","JSESSIONID="+sessionid1).

				body(PayLoad.issueCreation()).log().all().
				when().
				post("/rest/api/2/issue").
				then().
				extract().response().asString();//assertThat().statusCode(200).
		JsonPath js= ReusableMethods.rawToJson(resp);
		System.out.println(resp);
		System.out.println(js.getString("id"));
		System.out.println(js.getString("key"));
		System.out.println(js.getString("self"));
		String data = "";
	}
	
}
