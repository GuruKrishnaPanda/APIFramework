package mockService;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestPro {
	
	
	  @Test 
	  public void test001() {
	  
	  RestAssured.baseURI="https://private-d97b9b-testapi4484.apiary-mock.com";
	  RestAssured.basePath="/search"; given().log().all().get(); String key =
	  given().log().all().get().then().extract().path("issues[0].key");
	  System.out.println("The key for First Issues is "+key);
	  
	  }
	  
	  @Test public void test002() {
	  
	  RestAssured.baseURI="https://private-d97b9b-testapi4484.apiary-mock.com";
	  RestAssured.basePath="/search"; given().log().all().get(); HashMap<String,
	  String> map =
	  given().log().all().get().then().extract().path("issues[0].fields.project");
	  System.out.println("The values in projects are  "+map);
	  
	  }
	  
	  @Test public void test003() {
	  
	  RestAssured.baseURI="https://private-d97b9b-testapi4484.apiary-mock.com";
	  RestAssured.basePath="/search"; given().log().all().get(); int i =
	  given().log().all().get().then().extract().path("issues.size");
	  System.out.println("The size of Issue is   "+i);
	  
	  }
	 
	@Test
	public void test004() {
		
		RestAssured.baseURI="https://private-d97b9b-testapi4484.apiary-mock.com";
		RestAssured.basePath="/search";
		given().log().all().get();
		List<HashMap<String,Object>> list = given().log().all().get().then().extract().path("issues.findAll{it.key='TES-24'}");
		System.out.println("The size of Issue is   "+list.size());
		
	}
	@Test
	public void test005() {
		
		RestAssured.baseURI="https://private-d97b9b-testapi4484.apiary-mock.com";
		RestAssured.basePath="/search";
		given().log().all().get();
		String id = given().log().all().get().then().extract().path("issues[0].fields.issuetype.id");
		System.out.println("The id  is   "+id);
		
	}
	

}
