package TestCases;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import POJO.AddLocationResponse;
import PayLoads.PayLoad;
import baseTest.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssuredAddLocationSecond extends BaseTest {
	

	@Test(priority=1)
	public void testAddLocation(ITestContext con) {
		
		
		session.childlogMessage("Execution of "+new Exception().getStackTrace()[0].getMethodName()+" Started");
		System.out.println(new Exception().getStackTrace()[0].getMethodName());
		RequestSpecification req = given().log().all().spec(session.getUtils().requestSpec()).
		body(data.addPlacePayload());
		Response res = req.log().all().when().post("/maps/api/place/add/json");
		res.then().log().all().spec(session.getUtils().responseSpec()).extract().response();
		AddLocationResponse alr = res.as(AddLocationResponse.class);
		String response = res.asString();
		//JsonWriter.formatJson(response);
		session.childlogJSON("Add Location Response is ",response);
		con.setAttribute("place_id", alr.getPlace_id());
		session.end();
		
	}
	@Test(priority=2)
	public void testDeletLocation(ITestContext con) {
		
		
		session.childlogMessage("Execution of "+new Exception().getStackTrace()[0].getMethodName()+" Started");
		session.childlogMessage("Trying to delete the location with the Place Id "+con.getAttribute("place_id").toString());
		RequestSpecification req = given().log().all().spec(session.getUtils().requestSpec()).
		body(PayLoad.deletePlacePayload(con.getAttribute("place_id").toString()));
		Response res = req.when().log().all().post("/maps/api/place/delete/json");
		Response res1 = res.then().log().all().spec(session.getUtils().responseSpec()).extract().response();
		String response = res1.asString();
		//JsonWriter.formatJson(response);
		session.childlogJSON("Delete Location Response is ",response);
		session.end();
		
	}
	

}
