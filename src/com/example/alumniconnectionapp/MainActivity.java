package com.example.alumniconnectionapp;

import java.io.FileOutputStream;

import network.HTTPRequestHelper;

import org.apache.http.client.ResponseHandler;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
	private static final String CLASSTAG = MainActivity.class.getSimpleName(); 
	Preferences prefs = null; 
	
	ProgressDialog pd; 
	
	private final Handler progresshandler = new Handler() {

		 @Override
		 public void handleMessage(final Message msg) {
				pd.dismiss();
				if (msg.what == 2)
					return;

				String bundleResult = msg.getData().getString("RESPONSE");

				try {
					FileOutputStream fos = getApplication().getApplicationContext()
							.openFileOutput("UserPosts.json", Context.MODE_PRIVATE);
					fos.write(bundleResult.getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					Log.d("Alumni", "Exception: " + e.getMessage());
					Message m = new Message();
					m.what = 2; // error occured
					m.obj = ("Caught an error retrieving catalog data: " + e
							.getMessage());
					MainActivity.this.progresshandler.sendMessage(m);
				}

		/*		final TabHost tabHost = getTabHost();
				Resources resources = getResources();
				
				//Home Tab 
				tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Home", resources.getDrawable(R.drawable.ic_menu_home)) 
						.setContent(new Intent(MainActivity.this, HomeActivity.class)));
				
				//Users Tab 
				tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Users", resources.getDrawable(R.drawable.ic_menu_allfriends))
						.setContent(new Intent(MainActivity.this, UsersActivity.class)));

				tabHost.addTab(tabHost
						.newTabSpec("Tab3")
						.setIndicator("Account Info", resources.getDrawable(R.drawable.ic_menu_preferences))
						.setContent(new Intent(MainActivity.this, AccountInfo.class)));
				
				//set default tab to Home
				tabHost.setCurrentTab(0); */ 
			}
		};
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.navtabs); 
		
		this.prefs = new Preferences(getApplicationContext()); 
		this.pd = ProgressDialog.show(this, "", "Loading...", true, false); 
		
		final ResponseHandler<String> responseHandler = HTTPRequestHelper
				.getResponseHandlerInstance(this.progresshandler); 
		
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
		TabSpec tabSpecInfo = tabHost.newTabSpec("Tab3").setIndicator("Work").setContent(intent3); 
		
		//Add tabs
		tabHost.addTab(tabSpecHome); 
		tabHost.addTab(tabSpecUsers);
		tabHost.addTab(tabSpecInfo); 
		
		//set default tab to Home
		tabHost.setCurrentTab(0);  
		
		// do the HTTP dance in a separate thread (the responseHandler will fire
				// when complete)
				new Thread() {

					@Override
					public void run() {
						HTTPRequestHelper helper = new HTTPRequestHelper(
								responseHandler);
						helper.performGet(MainActivity.this.prefs.getServer()
								+ "/users/1.json", null, null, null);
					}
				}.start(); 
		
	}//end of onCreate

}//end of class 
