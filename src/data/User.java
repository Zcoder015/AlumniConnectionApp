package data;

public class User {

	private String admin;
	private String email; 
	private String faculty; 
	private String id; 
	private String name; 
	
	//Constructor
	public User(){
		
	}//end of constructor
	
	public String getAdmin(){
		return this.admin; 
	}
	
	public void setAdmin(String admin){
		this.admin = admin; 
	}
	
	public String getEmail(){
		return this.email; 
	}
	
	public void setEmail(String email){
		this.email = email; 
	}
	
	public String getFaculty(){
		return this.faculty; 
	}
	
	public void setFaculty(String faculty){
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
