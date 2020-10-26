import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;

public class testingFile {

	public static void main(String[] args) {
			Faker f = new Faker();
			String d = f.regexify("([A-Z]){4}([A-Z]){2}([0-9A-Z]){2}([0-9A-Z]{3}+)");
			//String d =  f.regexify("[a-zA-Z0-9]{6}");
			// d =  f.regexify("\\d{5}(?:[-\\s]\\d{4})?$");
			 String p =  f.regexify("([0-9A-Z]){2}([0-9A-Z]{3}?)");
			System.out.println(p);
			
			//FakeValuesService fakevalueservice = new FakeValuesService(arg0, arg1)
	}

}
