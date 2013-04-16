package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.alumniconnectionapp.Constants;

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
	
	private void persist(String productsInJson) {
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter(Constants.USER_POSTS_JSON_FILE));
			writer.write( productsInJson );

		}
		catch ( IOException e)
		{
			//Log.d(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
				//	+ "Failed to write out file " + e.getMessage());
		}
		finally
		{
			try
			{
				if ( writer != null)
					writer.flush();
					writer.close( );
			}
			catch ( IOException e)
			{
			}
	     }
	}//end of persist
	
	public void replace(String fileName, Post post){
		long startTime = System.currentTimeMillis();
		BufferedReader reader  = null;

		try {
			// Read contents of the JSON file into a JSON string
			reader = new BufferedReader( new FileReader (fileName));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }

		    JSONArray products = new JSONArray(stringBuilder.toString());
		    for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				if (product.getInt("id") == Integer.parseInt(post.getId())){
						product.put("content", post.getContent());
						product.put("id", post.getId());
						product.put("user_id", post.getUserId());
						product.put("postDate", post.getPostDate());
				}
		    }

		    persist(products.toString());

		} catch (Exception e) {
			//Log.e(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG, e);
		}
		finally
		{
			try
			{
				if ( reader != null)
					reader.close( );
			}
			catch ( IOException e)
			{
			}
	     }
		long duration = System.currentTimeMillis() - startTime;
		//Log.v(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
		//		+ " send request and parse reviews duration - " + duration);
	}//end of replace  
	
	public void delete(String fileName, Post post){
		long startTime = System.currentTimeMillis();
		BufferedReader reader  = null;

		try {
			// Read contents of the JSON file into a JSON string
			reader = new BufferedReader( new FileReader (fileName));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }

		    JSONArray products = new JSONArray(stringBuilder.toString());
		    JSONArray new_products = new JSONArray();
		    for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				if (product.getInt("id") != Integer.parseInt(post.getId())){
						new_products.put(product);
				}
		    }

		    persist(new_products.toString());

		} catch (Exception e) {
			//Log.e(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG, e);
		}
		finally
		{
			try
			{
				if ( reader != null)
					reader.close( );
			}
			catch ( IOException e)
			{
			}
	     }
		long duration = System.currentTimeMillis() - startTime;
		//Log.v(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
			//	+ " send request and parse reviews duration - " + duration);
	}//end of delete
	
	public void create(String fileName, Post post){
		long startTime = System.currentTimeMillis();
		BufferedReader reader  = null;

		try {
			// Read contents of the JSON file into a JSON string
			reader = new BufferedReader( new FileReader (fileName));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }

		    JSONArray products = new JSONArray(stringBuilder.toString());
			int max_id = 0;
			for (int i = 0; i < products.length(); i++) {
				JSONObject product = products.getJSONObject(i);
				if (product.getInt("id") > max_id)
					max_id = product.getInt("id");
			}
			JSONObject obj = new JSONObject();
			obj.put("id", max_id+1);
			obj.put("content", post.getContent());
			obj.put("user_id", post.getUserId());
			obj.put("postDate", post.getPostDate());
			products.put(obj);

		    persist(products.toString());

		} catch (Exception e) {
			//Log.e(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG, e);
		}
		finally
		{
			try
			{
				if ( reader != null)
					reader.close( );
			}
			catch ( IOException e)
			{
			}
	     }
		long duration = System.currentTimeMillis() - startTime;
		//Log.v(Constants.LOGTAG, " " + CatalogFetcher.CLASSTAG
		//		+ " send request and parse reviews duration - " + duration);
	}

}//end of class 
