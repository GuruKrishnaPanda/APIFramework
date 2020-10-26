package TestCases;

import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;

import POJO.AddLocationResponse;
import UtilFiles.Utils;
import baseTest.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AssuredAddLocation extends BaseTest{
	
	
	@Test
	public void testAddLocation() {
		
		//session.init(this.getClass().getSimpleName());
		session.childlogMessage("Execution of "+new Exception().getStackTrace()[0].getMethodName()+" Started");
		RequestSpecification req = given().spec(session.getUtils().requestSpec()).
		body(data.addPlacePayload());
	
		System.out.println("Body Created");
		Response res = req.when().post("/maps/api/place/add/json");
		
		res.then().spec(session.getUtils().responseSpec()).extract().response();
		AddLocationResponse alr = res.as(AddLocationResponse.class);
		String response = res.asString();
		//JsonWriter.formatJson(response);
		session.childlogJSON("Add Location Response is ",response);
		
	}

}
