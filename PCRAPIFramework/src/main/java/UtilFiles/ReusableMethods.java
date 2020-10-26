package UtilFiles;



import io.restassured.path.json.JsonPath;



public class ReusableMethods {

	public static JsonPath rawToJson(String response) {
		JsonPath js = new JsonPath(response);
		return js;	
	}
	public static void getSessionId() {
		
	}
	public static void getDataFromEnv(String env,String parameter) {
		FileandEnv.envAndFile(env).get(parameter);
		
	}
	
	
	

}
