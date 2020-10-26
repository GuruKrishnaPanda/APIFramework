package test;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import PayLoads.PayLoad;
import UtilFiles.ReusableMethods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class testAddBook_reject {

@Test()

public void addBook(ITestContext context)

{
	RestAssured.baseURI="http://216.10.245.166";

	String resp=given().

			header("Content-Type","application/json").

			body(PayLoad.Addbook()).log().all().
			when().
			post("/Library/Addbook.php").
			then().
			extract().response().asString();//assertThat().statusCode(200).
	JsonPath js= ReusableMethods.rawToJson(resp);
	context.setAttribute("CustID", js.get("ID"));
	
	System.out.println(resp);
	System.out.println(context.getAttribute("CustID"));

}

}

