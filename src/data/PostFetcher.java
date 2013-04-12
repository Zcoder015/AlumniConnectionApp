package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostFetcher {
	
	private ArrayList<Post> posts; 
	
	//Constructor
	public PostFetcher(){
		posts = new ArrayList<Post>(); 
	}
	
	private ArrayList<Post> parsePosts(String resp){
		try {
			JSONArray products = new JSONArray(resp);

			for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				String date = product.getString("created_at"); 
				String content = product.getString("content");
				String id = product.getString("id");
				String user_id = product.getString("user_id");
				
				//Formats date string
				String dateString = date; 
				Scanner scanner = new Scanner(dateString).useDelimiter("-");
				String year = scanner.next();
				String month = scanner.next(); 
				String day = scanner.next(); 
				
				//Extract day
				Scanner scan2 = new Scanner(day).useDelimiter("T"); 
				String dayWithoutTime = scan2.next(); 

				Post post = new Post();
				post.setPostDate("Posted on " + month + "-" + dayWithoutTime + "-" + year); 
				post.setContent(content);
				post.setId(id);
				post.setUserId(user_id);

				posts.add(post);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return posts;
	}//end of parsePosts
	
	public ArrayList<Post> getPostFromFile(String fileName) {
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
			posts = parsePosts(stringBuilder.toString());
		} catch (Exception e) {
			//Log.e(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG, e);
		}
		long duration = System.currentTimeMillis() - startTime;
		//Log.v(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
		//		+ " send request and parse reviews duration - " + duration);
		return posts;
	}

}//end of class 
