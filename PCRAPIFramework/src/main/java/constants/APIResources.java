package constants;

public enum APIResources {
	
	jiraBaseURI("http://localhost:8080"),
	mapBaseURI("https://rahulshettyacademy.com"),
	
	AddPlaceAPI(""),
	getPlaceAPI(""),
	deletePlaceAPI(""),
	createJiraAPI("/rest/api/2/issue"),
	getIssueAPI("/rest/api/2/issue/{value}");
	private String resource;
	
	APIResources(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		
		return resource;
		
	}
}
