package UtilFiles;


import java.io.File;
import java.util.Date;

import org.springframework.ui.context.Theme;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	
	static ExtentReports reports;
	public static String screenshotFolderPath;
	
	public static ExtentReports getReports() {
	
		if(reports == null) {
			reports = new ExtentReports();
			// init the report folder
			Date d = new Date();
			System.out.println(d.toString().replaceAll(":", "-"));
			String reportsFolder=d.toString().replaceAll(":", "-") +"//screenshots";
			reports.setSystemInfo("Environment",ReportInput.reportEntryData().get("Environment"));
			reports.setSystemInfo("BaseURL",ReportInput.reportEntryData().get("BaseURI"));
			screenshotFolderPath = System.getProperty("user.dir") +"//reports//"+reportsFolder;
			String reportFolderPath = System.getProperty("user.dir") +"//reports//"+d.toString().replaceAll(":", "-");
			System.out.println(screenshotFolderPath);
			File f = new File(screenshotFolderPath);
			f.mkdirs();// create dynamic report folder name + screenshots
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFolderPath);
			sparkReporter.config().setReportName("Production Regression Testing");
			sparkReporter.config().setDocumentTitle("Automation Reports");
			sparkReporter.loadXMLConfig("./src/test/resources/extent-config.xml");
			//sparkReporter.config().setTheme(Theme.);
			sparkReporter.config().setEncoding("utf-8");
			
			reports.attachReporter(sparkReporter);
		}
		
		return reports;
		
	}

}
