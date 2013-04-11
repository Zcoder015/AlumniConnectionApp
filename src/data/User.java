package data;

public class User {

	public boolean admin = false;
	public String email = ""; 
	public boolean faculty = false; 
	public String id = ""; 
	public String name = ""; 
	
	//Constructor
	public User(){
		
	}//end of constructor
	
	public boolean getAdmin(){
		return this.admin; 
	}
	
	public void setAdmin(boolean admin){
		this.admin = admin; 
	}
	
	public String getEmail(){
		return this.email; 
	}
	
	public void setEmail(String email){
		this.email = email; 
	}
	
	public boolean getFaculty(){
		return this.faculty; 
	}
	
	public void setFaculty(boolean faculty){
		this.faculty = faculty; 
	}
	
	public String getId(){
		return this.id; 
	}
	
	public void setId(String id){
		this.id = id; 
	}
	
	public String getName(){
		return this.name; 
	}
	
	public void setName(String name){
		this.name = name; 
	}
	
	
	
}//end of class
