package Practice;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ExtractionBetterWay extends testBase{
	
	
	@Test
	public void testpracticeClass() throws FileNotFoundException  {
		
		
		Response res =  given().log().all(). spec(requestSpecification()) //.pathParam("key", "RES-197")
		  .filter(getSession())
		  //.get("/rest/api/2/issue/all").then().extract().response();
		  .get("/rest/api/2/search");
		JsonPath extractor =  res.jsonPath();
		

		
	}	

}