package com.example.alumniconnectionapp;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.navtabs); 
		
		Resources resources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		//Users tab
		Intent intent = new Intent().setClass(this,UsersActivity.class); 
		TabSpec tabSpecUsers = tabHost.newTabSpec("Tab1").setIndicator("Users", resources.getDrawable(R.drawable.ic_menu_allfriends)).setContent(intent); 
		
		//Home tab
		Intent intent2 = new Intent().setClass(this, HomeActivity.class);
		TabSpec tabSpecHome = tabHost.newTabSpec("Tab2").setIndicator("Home",resources.getDrawable(R.drawable.ic_menu_home)).setContent(intent2); 
		
		//Account info 
		Intent intent3 = new Intent().setClass(this, AccountInfo.class);
		TabSpec tabSpecInfo = tabHost.newTabSpec("Tab3").setIndicator("Account Info", resources.getDrawable(R.drawable.ic_menu_preferences)).setContent(intent3); 
		
		//Add tabs
		tabHost.addTab(tabSpecHome); 
		tabHost.addTab(tabSpecUsers);
		tabHost.addTab(tabSpecInfo); 
		
		//set default tab to Home
		tabHost.setCurrentTab(0); 
		
	}//end of onCreate

}//end of class 
