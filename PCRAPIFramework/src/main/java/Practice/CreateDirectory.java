package Practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectory {

	public static void main(String[] args) {
		
		
	

		        String dir = "C:\\Directory2\\Sub2\\Sub-Sub2";
		        
		        File files = new File(dir);
		        if (!files.exists()) { 
		        	if (files.mkdirs()) {
		        		
		        		System.out.println("Multiple directories are created!");
		        } else {
		        		System.out.println("Failed to create multiple directories!");
		        }
		        }

		        try {

		            Path path = Paths.get(dir);

		            Files.createDirectories(path);

		            System.out.println("Directory is created!");

		            //Files.createDirectory(path);

		        } catch (IOException e) {
		            System.err.println("Failed to create directory!" + e.getMessage());
		        }

		    }
		
	
}
