package Practice;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.testng.annotations.Test;






public class practiceClass extends testBase{

	
	@Test
	public void testpracticeClass() throws FileNotFoundException  {
		
		ArrayList<String> data =  given().log().all(). spec(requestSpecification()) //.pathParam("key", "RES-197")
		  .filter(getSession())
		  //.get("/rest/api/2/issue/all").then().extract().response();
		  .get("/rest/api/2/search").then().extract().path("issues");
		 System.out.println(data.size());
		 System.out.println(data);

		/*
		 * ArrayList<String> key =
		 * given().log().all().spec(req).filter(loginsession).get("/rest/api/2/search").
		 * then().extract().path("key"); System.out.println(key.size());
		 */	
	}	
}
