package apiVerifications;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonValue;

public class DynamicJsonObject {
	
	
	public static void parseObject(JSONObject json,String key) {
		//System.out.println(json.has(key));
			System.out.println(json.get(key));
			
	}

	public static void getKey(JSONObject json,String key) {
		
		boolean isPresent = json.has(key);	
		Iterator<?> keys;
		String nextKeys;
		
		if (!isPresent) {
			keys = 	 json.keys();
			System.out.println(keys);
			while(keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {
					
					if (json.get(nextKeys) instanceof JSONObject) {
						
						if(isPresent==false) {
							getKey(json.getJSONObject(nextKeys), key);
							
						}	
					}else if(json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray =json.getJSONArray(nextKeys);
						for (int i =0;i<jsonarray.length();i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJson  =  new JSONObject(jsonarrayString);

							if(isPresent==false) {
								getKey(innerJson, key);
								
							}	
						}
					}
					
				} catch (Exception e) {
					
				}
				
			}
			
		}else {
			
			parseObject(json, key);
		
			
		}
			
		
	}
	
	public static void main(String[] args) {
		
		//String inputjson = payloads.response;
		
		JSONObject inputjsonObject  =  new JSONObject("");
		
		getKey(inputjsonObject, "created");
		
			
	}

	public static void getArrayValueFromObject(JSONObject inputjsonObject, String string) {
		JSONArray arrObj = inputjsonObject.getJSONArray(string);
        System.out.println("\nDirect Reports:");
        for(Object value : arrObj){
            System.out.println(value.toString());
        }
	}
        public static void getArrayValueFromArray(JSONArray inputjsonObject) {
        	JSONArray json = new JSONArray(inputjsonObject);
            System.out.println("\nDirect Reports:");
            for(Object value : json){
                System.out.println(value.toString());
            }
		
	}

}
