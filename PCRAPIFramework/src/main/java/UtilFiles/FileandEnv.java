package UtilFiles;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {
	public static Map<String, String> fileandenv = new HashMap<String, String>();
	public static Properties proMain =  new Properties();
	public static Properties propPreSet =  new Properties();
	
	public static Map<String, String> envAndFile(String env){
		
		String environment = env;
		try {
			
			if(environment.equalsIgnoreCase("Dev")){
				
				FileInputStream fisDev =  new FileInputStream(System.getProperty("user.dir")+"/input/dev.properties");
				proMain.load(fisDev);
				fileandenv.put("ServerUrl", proMain.getProperty("ServerUrl"));
				fileandenv.put("portNo", proMain.getProperty("portNo"));
				fileandenv.put("userName", proMain.getProperty("userName"));
				fileandenv.put("passWord", proMain.getProperty("passWord"));
				
			}else if (environment.equalsIgnoreCase("QA")) {
				
				FileInputStream fisQA =  new FileInputStream(System.getProperty("user.dir")+"/input/qa.properties");
				proMain.load(fisQA);
				fileandenv.put("ServerUrl", proMain.getProperty("ServerUrl"));
				fileandenv.put("portNo", proMain.getProperty("portNo"));
				fileandenv.put("userName", proMain.getProperty("userName"));
				fileandenv.put("passWord", proMain.getProperty("passWord"));
			}else if (environment.equalsIgnoreCase("qstating")) {
				
				FileInputStream fisStaging =  new FileInputStream(System.getProperty("user.dir")+"/input/staging.properties");
				proMain.load(fisStaging);
				fileandenv.put("ServerUrl", proMain.getProperty("ServerUrl"));
				fileandenv.put("portNo", proMain.getProperty("portNo"));
				fileandenv.put("userName", proMain.getProperty("userName"));
				fileandenv.put("passWord", proMain.getProperty("passWord"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return fileandenv;
	}
	
	public static Map<String, String> getConfigReader(String env){
		if (fileandenv == null)
			fileandenv = envAndFile(env);
		
		return fileandenv;
	}

}
