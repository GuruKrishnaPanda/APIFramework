package apiConfig;

public class APIPath {
	
		//GET
		public  final String GET_LIST_OF_POSTS ="posts";
		public  final String GET_SINGLE_POST="posts/";
		
		//POST
		public static final String CREATEISSUE_POST="/rest/api/2/issue";
		public static final  String LOGIN_POST="/rest/auth/1/session";
		public static final String SEARCHISSUE_ALL_POST="/rest/api/2/search";
		public static final String ADDATTACHMENT = "/rest/api/2/issue/{KEY_VALUE}/attachments";
		
		//update post 
		public  final String UPDATE_POST="posts";
	
	
}