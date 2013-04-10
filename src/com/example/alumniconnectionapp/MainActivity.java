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
		TabSpec tabSpecUsers = tabHost.newTabSpec("Tab1").setIndicator("Users").setContent(intent); 
		
		//Home tab
		Intent intent2 = new Intent().setClass(this, HomeActivity.class);
		TabSpec tabSpecHome = tabHost.newTabSpec("Tab2").setIndicator("Home").setContent(intent2); 
		
		//Add tabs
		tabHost.addTab(tabSpecUsers); 
		tabHost.addTab(tabSpecHome);
		
		//set default tab
		tabHost.setCurrentTab(1); 
		
	}//end of onCreate

}//end of class 
