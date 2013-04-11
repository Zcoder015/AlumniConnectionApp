package com.example.alumniconnectionapp;

import java.util.List;

import data.User;
import data.UsersFetcher;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class UsersActivity extends ListActivity {
	
	private TextView empty; 
	private UsersAdapter adapter; 
	private ProgressDialog pd; 
	List<User> usersList; 
	
	 private final Handler handler = new Handler() {
	        @Override
	        public void handleMessage(final Message msg) {
	            pd.dismiss();
	            if ((usersList == null) || (usersList.size() == 0)) {
	                empty.setText("No Data");
	            } else {
	                adapter = new UsersAdapter(UsersActivity.this, usersList);
	                setListAdapter(adapter);
	            }
	        }
	    };   
	
	
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
		
		loadUsers(); 
	}
	
	//Shows individual users information when clicked
	protected void onListItemClick(ListView l, View v, int position, long id){
		
	}
	
	private void loadUsers(){
		
		pd = ProgressDialog.show(this, "", "Loading...", true, false);
		final UsersFetcher uf = new UsersFetcher(); 
		
		
		new Thread() {
            @Override
            public void run() {
            	//Retrieve and parse the data from json file
        		usersList = uf.getUserFromFile("/data/data/com.example.alumniconnectionapp/files/users.json"); 
                handler.sendEmptyMessage(0);
            }
        }.start();
	}

}//end of class
