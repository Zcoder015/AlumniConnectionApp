package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsersFetcher {
	
	private ArrayList<User> userList; 
	
	//Constructor
	public UsersFetcher(){
		
	}
	
	private ArrayList<User> parseUsers(String resp){
		try {
			JSONArray products = new JSONArray(resp);

			for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				boolean admin = product.getBoolean("admin");
				String email = product.getString("email");
				boolean faculty = product.getBoolean("faculty");
				int id = product.getInt("id"); 
				String name = product.getString("name");

				User user = new User();
				user.setAdmin(admin);
				user.setEmail(email);
				user.setFaculty(faculty);
				user.setId(new Integer(id).toString());
				user.setName(name);

				userList.add(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userList;
	}//end of parseUsers
	
	public ArrayList<User> getUserFromFile(String fileName) {
		long startTime = System.currentTimeMillis();

		try {
			// Read contents of the JSON file into a JSON string
			BufferedReader reader = new BufferedReader( new FileReader (fileName));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }

			// Parse the JSON string
			userList = parseUsers(stringBuilder.toString());
		} catch (Exception e) {
			//Log.e(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG, e);
		}
		long duration = System.currentTimeMillis() - startTime;
		//Log.v(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
		//		+ " send request and parse reviews duration - " + duration);
		return userList;
	}

}//end of class 
