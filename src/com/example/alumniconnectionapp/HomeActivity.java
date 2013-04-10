package com.example.alumniconnectionapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState); 
		TextView textView = new TextView(this);
		textView.setText("Welcome Home!!"); 
		setContentView(textView); 
	}

}//end of class 
