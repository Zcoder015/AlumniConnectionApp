package data;

import java.util.Date;

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

}//end of class 
