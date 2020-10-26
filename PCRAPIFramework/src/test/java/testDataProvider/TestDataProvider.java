package testDataProvider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import UtilFiles.DataUtil;
import UtilFiles.GenericXLSXReader;



public class TestDataProvider {

	
	
	@DataProvider
	public static Object[][] getData(Method m){		
		System.out.println("Getting data for "+ m.getName());
		Object[][] data= new DataUtil().getData(m.getName(), new GenericXLSXReader(System.getProperty("user.dir")+"//input//ExecutionSheet.xlsx"));
		System.out.println("Got data for "+ m.getName());
		return data;
	}
	

	
	
}
