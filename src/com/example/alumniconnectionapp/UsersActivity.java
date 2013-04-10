package com.example.alumniconnectionapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class UsersActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		TextView textView = new TextView(this);
		textView.setText("Reggie was here"); 
		setContentView(textView); 
	}

}//end of class
