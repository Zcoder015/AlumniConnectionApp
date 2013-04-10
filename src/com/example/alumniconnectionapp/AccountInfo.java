package com.example.alumniconnectionapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountInfo extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		
		TextView tv = new TextView(this); 
		tv.setText("Account info tag"); 
		setContentView(tv); 
	}

}//end of class 
