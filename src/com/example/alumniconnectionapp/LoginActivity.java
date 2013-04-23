package com.example.alumniconnectionapp;

import java.util.HashMap;

import network.HTTPRequestHelper;

import org.apache.http.client.ResponseHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class LoginActivity extends Activity {
	private ViewFlipper flipper; 
	private EditText email; 
	private EditText password; 
	private EditText serverurl; 
	private Button signIn; 
	private ProgressDialog pd; 
	
	Preferences myprefs = null;
	AlertDialog.Builder adb;
	private static final String CLASSTAG = LoginActivity.class.getSimpleName(); 
	
	private final Handler handler = new Handler() {

		@Override
		public void handleMessage(final Message msg) {
			pd.dismiss();
			String bundleResult = msg.getData().getString("RESPONSE");

			// Invalid login
			// Make sure the create action in the sessions controller on the
			// server is updated.
			if (bundleResult.startsWith("Invalid")
					|| bundleResult.startsWith("Error")) {
				LoginActivity.this.myprefs.setValid(false);
				Toast.makeText(LoginActivity.this, "Invalid. Log in again.",
						Toast.LENGTH_SHORT).show();
			} else { // A successful response to either a login or a logout request
				// save off values
				if (!LoginActivity.this.myprefs.isValid()){
					//ShowSettings.this.myprefs.setServer(serverurl.getText()
						//	.toString());
					LoginActivity.this.myprefs.setUserName(email.getText()
							.toString());
					LoginActivity.this.myprefs.setPassword(password.getText()
							.toString());
					LoginActivity.this.myprefs.setValid(true);
					LoginActivity.this.myprefs.save();
					signIn.setText("Log Out");

				}
				else{
				//	LoginActivity.this.myprefs.setServer(serverurl.getText()
					//		.toString());
					LoginActivity.this.myprefs.setUserName("Unknown");
					LoginActivity.this.myprefs.setPassword("Unknown");
					LoginActivity.this.myprefs.setValid(false);
					LoginActivity.this.myprefs.save();
					signIn.setText("Log In");
				}
				// we're done!
				finish();	
			}
		}
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		this.myprefs = new Preferences(getApplicationContext()); 
		
		this.adb = new AlertDialog.Builder(this); 
		
		signIn = (Button)findViewById(R.id.sign_in_button); 
		
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
				try {
					if (LoginActivity.this.signIn.getText().toString()
							.equals("Log In")) { // Log in

						// get the string and do something with it.

						serverurl = (EditText) findViewById(R.id.server_url);
						if (serverurl.getText().length() == 0) {

							AlertDialog ad = LoginActivity.this.adb.create();
							ad.setMessage("Please Enter The URL of The Server");
							ad.show();
							return;
						} 

						email = (EditText) findViewById(R.id.email);
						if (email.getText().length() == 0) {
							AlertDialog ad = LoginActivity.this.adb.create();
							ad.setMessage("Please Enter Email");
							ad.show();
							return;
						}

						password = (EditText) findViewById(R.id.password);
						if (password.getText().length() == 0) {
							AlertDialog ad = LoginActivity.this.adb.create();
							ad.setMessage("Please Enter The Password");
							ad.show();
							return;
						}

						performRequest("http://10.0.82.105:3000/signin.json", "POST", email.getText()
								.toString(), password.getText().toString());
						
						loadMainPage();
					} else { // Log out
						serverurl = (EditText) findViewById(R.id.server_url);
						if (serverurl.getText().length() == 0) {

							AlertDialog ad = LoginActivity.this.adb.create();
							ad.setMessage("Please Enter The URL of The Server");
							ad.show();
							return;
						} 
						performRequest("http://10.0.82.105:3000/logout.json", "DELETE",null, null);
						
						
					}

				} catch (Exception e) {
					//Log.i(LoginActivity.this.CLASSTAG, "Failed to Save Settings ["
						//	+ e.getMessage() + "]");
				}
				//loadMainPage(); 
			}

		}); 
		
	}//end of onCreate
	
	protected void onResume(){
		super.onResume(); 
		
		loadScreen(); 
	}
	
	public void loadScreen(){
		
		try {
			final EditText serverurl = (EditText) findViewById(R.id.server_url);
			final EditText email = (EditText) findViewById(R.id.email);
			final EditText password = (EditText) findViewById(R.id.password);

			serverurl.setText(this.myprefs.getServer());
			email.setText(this.myprefs.getUserName());
			password.setText(this.myprefs.getPassword());

			if (this.myprefs.isValid())
				this.signIn.setText("Log Out");
			else
				this.signIn.setText("Log In");
		} catch (Exception e) {

		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	public void loadMainPage(){
		Intent intent = new Intent(this,MainActivity.class); 
		startActivity(intent); 
		
	}
	
	private void performRequest(final String url, final String method,
			final String user, final String pass) {

		Log.d(Constants.LOGTAG, " " + LoginActivity.CLASSTAG + " request url - "
				+ url);
		Log.d(Constants.LOGTAG, " " + LoginActivity.CLASSTAG
				+ " request method - " + method);
		Log.d(Constants.LOGTAG, " " + LoginActivity.CLASSTAG + " user - " + user);
		Log.d(Constants.LOGTAG, " " + LoginActivity.CLASSTAG + " password - "
				+ pass);  

		final HashMap<String, String> params = new HashMap<String, String>();
		if ((user != null) && (pass != null)) {
			params.put("name", user);
			params.put("password", pass);
		}

		final ResponseHandler<String> responseHandler = HTTPRequestHelper
				.getResponseHandlerInstance(this.handler);

		this.pd = ProgressDialog.show(this, "working . . .",
				"performing HTTP " + method + " request");

		// do the HTTP dance in a separate thread (the responseHandler will fire
		// when complete)
		new Thread() {

			@Override
			public void run() {
				HTTPRequestHelper helper = new HTTPRequestHelper(
						responseHandler);
				if (method.equals("POST")) {
					helper.performPost(HTTPRequestHelper.MIME_TEXT_PLAIN, url,
							null, null, null, params);
				} else 
				if (method.equals("DELETE")){
					helper.performDelete(HTTPRequestHelper.MIME_TEXT_PLAIN, url, null, null, null, null);
				}
				else{
					Message msg = handler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putString("RESPONSE", "ERROR - see logcat");
					msg.setData(bundle);
					handler.sendMessage(msg);
				//	Log.w(Constants.LOGTAG, " " + CLASSTAG
					//		+ " has to be a POST method");
				}
			}
		}.start();
	}

}//end of LoginActivity
