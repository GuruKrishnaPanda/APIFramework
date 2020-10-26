package apiConfig;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigs {
	
	public HeaderConfigs() {
		
	}
	
	public Map<String, String> defaultHeaders(){
		Map<String, String> defalutHeaders = new HashMap<String, String>();
		defalutHeaders.put("Content-Type", "application/json");
		
		return defalutHeaders;
		
	}
	public Map<String, String> attachmentHeaders(){
		Map<String, String> attachmentHeaders = new HashMap<String, String>();
		attachmentHeaders.put("Content-Type", "multipart/form-data");
		attachmentHeaders.put("X-Atlassian-Token", "no-check");
		return attachmentHeaders;
		
	}
	
	public Map<String, String> headersWithToken(){
		Map<String, String> defalutHeaders = new HashMap<String, String>();
		defalutHeaders.put("Content-Type", "application/json");
		defalutHeaders.put("Acess_Token", "sdjhvbshjdvbjhsdvbhjsdvbljhdsbv");
		defalutHeaders.put("jwt_token", "sdjhvbshjdvbjhsdvbhjsdvbljhdsbv");
		defalutHeaders.put("Tenet_user", "test");
		
		return defalutHeaders;
		
	}
	
//	public Map<String, String> headersWithToken(){
//		Map<String, String> defalutHeaders = new HashMap<String, String>();
//		defalutHeaders.put("Content-Type", "application/json");
//		defalutHeaders.put("Acess_Token", "sdjhvbshjdvbjhsdvbhjsdvbljhdsbv");
//		defalutHeaders.put("jwt_token", "sdjhvbshjdvbjhsdvbhjsdvbljhdsbv");
//		defalutHeaders.put("Tenet_user", "test");
//		
//		return defalutHeaders;
//		
//	}
	
	

}
