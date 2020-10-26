package UtilFiles;

import java.util.ArrayList;
import java.util.List;

import POJO.AddLocationRequest;
import POJO.Location;

public class TestDataBuilder {
	
	
	public AddLocationRequest addPlacePayload() {
		AddLocationRequest al = new AddLocationRequest();
		al.setAccuracy(50);
		al.setAddress("29, side layout, cohen 09");
		al.setLanguage("French-IN");
		al.setName("Food Junction");
		al.setPhone_number("(+91) 983 893 3937");
		al.setWebsite("http://google.com");
		List<String> myList =  new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		al.setTypes(myList);
		Location loc =  new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		al.setLocation(loc);
		return al;	
	}

}
