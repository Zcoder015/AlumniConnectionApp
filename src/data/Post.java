package data;

import java.util.Date;

import android.os.Bundle;

public class Post {
	
	public String content = ""; 
	public String id = "";
	public String user_id;
	public String postDate; 
	
	//Constructor
	public Post(){
		
	}
	
	public String getContent(){
		return this.content; 
	}
	
	public void setContent(String content){
		this.content = content; 
	}
	
	public String getId(){
		return this.id; 
	}
	
	public void setId(String id){
		this.id = id; 
	}
	
	public String getUserId(){
		return this.user_id; 
	}
	
	public void setUserId(String user_id){
		this.user_id = user_id; 
	}
	
	public String getPostDate(){
		return this.postDate; 
	}
	
	public void setPostDate(String date){
		this.postDate = date; 
	}
	
	public Bundle toBundle(){
		Bundle b = new Bundle(); 
		b.putString("content", this.content); 
		b.putString("id", this.id); 
		b.putString("user_id", this.user_id); 
		b.putString("postDate", this.postDate); 
		
		return b; 
	}
	
	public static Post fromBundle(Bundle b){
		Post post = new Post(); 
		post.setContent(b.getString("content")); 
		post.setId("id"); 
		post.setUserId("user_id"); 
		post.setPostDate("postDate"); 
		
		return post; 
	}

}//end of class 
