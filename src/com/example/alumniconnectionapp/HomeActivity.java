package com.example.alumniconnectionapp;

import java.util.List;

import data.Post;
import data.PostFetcher;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends ListActivity {
	
	private TextView empty; 
	private PostsAdapter adapter;
	private ProgressDialog pd; 
	List<Post> posts; 
	private static final int MENU_ADD_POST = Menu.FIRST;
	
 /*	private final Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            pd.dismiss();
            if ((posts == null) || (posts.size() == 0)) {
                empty.setText("No Data");
            } else {
                adapter = new PostsAdapter(HomeActivity.this, posts);
                setListAdapter(adapter);
            }
        }
    };   */ 
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_home); 
		
		empty = (TextView)findViewById(R.id.empty2); 
		
		//set list properties
		final ListView listview = getListView(); 
		listview.setItemsCanFocus(false); 
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
		listview.setEmptyView(this.empty); 
		
		
	}
	
	public void onResume(){
		super.onResume(); 
		
		loadPosts(); 
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id){
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu); 
		
		menu.add(0, HomeActivity.MENU_ADD_POST, 0, R.string.menu_add_post).setIcon(
	            android.R.drawable.ic_menu_add); 
		
		return true; 
	}
	
	private void loadPosts(){
		
	//	pd = ProgressDialog.show(this, "", "Loading...", true, false);
		final PostFetcher pf = new PostFetcher(); 
		
		posts = pf.getPostFromFile("/data/data/com.example.alumniconnectionapp/files/UserPosts.json");
		adapter = new PostsAdapter(HomeActivity.this, posts); 
		setListAdapter(adapter); 
		
		
	/*	new Thread() {
            @Override
            public void run() {
            	//Retrieve and parse the data from json file
        	//	posts = pf.getPostFromFile("/data/data/com.example.alumniconnectionapp/files/UserPosts.json"); 
                handler.sendEmptyMessage(0);
            }
        }.start(); */ 
	} 

}//end of class 
