package PayLoads;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

public class PayLoad {
	public static Faker faker = new Faker();
	public static String Addbook() {
		
		String addbook_pl="{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\"bcdgk005\",\r\n" + 
				"\"aisle\":\"22723005\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
		
		return addbook_pl;	
	}
	public static String jiraSession() {
		
		String jiraSession_pl="{ \"username\": \"kunusmiles\", \"password\": \"nikkumania\" }";
		
		return jiraSession_pl;	
	}
	
	public static String issueCreation() {
		Integer number = faker.number().numberBetween(1, 9999);
		
		String issuecreate_pl="{\r\n" + 
				"    \"fields\" : {\r\n" + 
				"        \"project\":\r\n" + 
				"        {\r\n" + 
				"            \"key\": \"RES\"\r\n" + 
				"        },\r\n" + 
				"        \"summary\":\"issue creating "+number+"\",\r\n" + 
				"        \"description\":\"description of the issue "+number+"\",\r\n" + 
				"        \"issuetype\":{\r\n" + 
				"            \"name\":\"Bug\"\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		return issuecreate_pl;
	}
	public static String addComment(){
		
		String comment_pl="{\r\n" + 
				"    \"body\": \"My comment Final.\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return comment_pl;
	}
	
	public static String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
	
	public static String searchAllIssueOfProject() {
		String search_pl = "{\r\n" + 
				"    \"jql\": \"project = RES\",\r\n" + 
				"    \"startAt\": 0,\r\n" + 
				"    \"maxResults\": 100,\r\n" + 
				"    \"fields\": [\r\n" + 
				"        \"summary\",\r\n" + 
				"        \"status\",\r\n" + 
				"        \"assignee\"\r\n" + 
				"    ]\r\n" + 
				"}";
		return search_pl;
			
	}
	
}
