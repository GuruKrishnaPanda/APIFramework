package UtilFiles;

import java.util.HashMap;
import java.util.Map;

public class ReportInput {
	
	public static Map<String, String> reportEntryData() {
		Map<String, String> reportData = new HashMap<String, String>();	
		GenericXLSXReader xlsx =new GenericXLSXReader(System.getProperty("user.dir")+"\\input\\ExecutionSheet.xlsx");
		FileandEnv.envAndFile(xlsx.getCellData("Configuration", 4, 6)).get("ServerUrl");
		reportData.put("Environment", xlsx.getCellData("Configuration", 4, 6));
		reportData.put("BaseURI", FileandEnv.envAndFile(xlsx.getCellData("Configuration", 4, 6)).get("ServerUrl"));
		return reportData;
	}
			
			

}
