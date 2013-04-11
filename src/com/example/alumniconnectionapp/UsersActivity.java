package com.example.alumniconnectionapp;

import java.util.List;

import data.User;
import data.UsersFetcher;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class UsersActivity extends ListActivity {
	
	private TextView empty; 
	private UsersAdapter adapter; 
	List<User> usersList; 
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		
		this.setContentView(R.layout.activity_users); 
		
		this.empty = (TextView)findViewById(R.id.empty); 
		
		//set list properties
		final ListView listview = getListView(); 
		listview.setItemsCanFocus(false); 
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
		listview.setEmptyView(this.empty); 
	}
	
	public void onResume(){
		super.onResume(); 
		
		UsersFetcher uf = new UsersFetcher(); 
		
		//Retrieve and parse the data from json file
		this.usersList = uf.getUserFromFile("/data/data/com.example.alumniconnectionapp/files/users.json"); 
		adapter = new UsersAdapter(UsersActivity.this, usersList); 
		setListAdapter(adapter); 
	}

}//end of class
