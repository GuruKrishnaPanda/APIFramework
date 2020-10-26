package apiBuilder;

import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;

import UtilFiles.FileandEnv;
import apiSession.APISession;



public class PostAPIBuilder {
	


	
	public APISession getExecutionSession() {
		return (APISession)Reporter.getCurrentTestResult().getTestContext().getAttribute("session");
		
	}

	public Map<String, String> loginRequestBody(){
		Map<String, String> login = new HashMap<String, String>();	
		String currentEnvironment = getExecutionSession().getEnvironment();
		System.out.println(FileandEnv.envAndFile(getExecutionSession().getEnvironment()).get("username"));	
		login.put("username", FileandEnv.envAndFile(currentEnvironment).get("userName"));
		login.put("password", FileandEnv.envAndFile(currentEnvironment).get("passWord"));
		
		return login;
		
	}
}
