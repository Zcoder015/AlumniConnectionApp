package com.example.alumniconnectionapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UserDetail extends Activity {

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		
		TextView tv = new TextView(this);
		tv.setText("Users profile info"); 
		setContentView(tv); 
	}
	
}//end of class 
