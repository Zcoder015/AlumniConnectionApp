package com.example.alumniconnectionapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

public class LoginActivity extends Activity {
	private ViewFlipper flipper; 
	private EditText email; 
	private EditText password; 
	private Button signIn; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Set flipper
		flipper = ((ViewFlipper) this.findViewById(R.id.flipper));
		flipper.setInAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_in));
		flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
				R.anim.push_left_out));
		flipper.startFlipping();
		
		email = (EditText)findViewById(R.id.email); 
		password = (EditText)findViewById(R.id.password); 
		signIn = (Button)findViewById(R.id.sign_in_button); 
		
		
		//set listener for sign in button
		signIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadHomePage(); 
			}

		}); 
		
	}//end of onCreate
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	public void loadHomePage(){
		Intent intent = new Intent(this,HomePage.class); 
		startActivity(intent); 
		
	}

}//end of LoginActivity
