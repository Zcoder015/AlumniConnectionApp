package com.example.alumniconnectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	
    private SharedPreferences prefs = null;
    private Editor editor = null;
    private String username= "Unknown";
    private String password= "Unknown";
    private String serverurl = "http://10.0.82.105:3000";
    private boolean valid = false;

    public Preferences(Context context) {
        this.prefs = context.getSharedPreferences("PREFS_PRIVATE", Context.MODE_PRIVATE);
        this.editor = this.prefs.edit();
    }

    public String getValue(String key, String defaultvalue) {
        if (this.prefs == null) {
            return "Unknown";
        }

        return this.prefs.getString(key, defaultvalue);
    }

    public void setValue(String key, String value) {
        if (this.editor == null) {
            return;
        }

        this.editor.putString(key, value);

    }

    public String getUserName() {
        if (this.prefs == null) {
            return "Unknown";
        }

        this.username = this.prefs.getString("username", "Unknown");
        return this.username;
    }
    
    public String getPassword() {
        if (this.prefs == null) {
            return "Unknown";
        }

        this.password = this.prefs.getString("password", "Unknown");
        return this.password;
    }

    public String getServer() {
        if (this.prefs == null) {
            return "http://10.1.74.50:3000";
        }

        this.serverurl = this.prefs.getString("serverurl", "http://10.0.82.105:3000");
        return this.serverurl;
    }

    public void setUserName(String newusername) {
        if (this.editor == null) {
            return;
        }

        this.editor.putString("username", newusername);
    }
    
    public void setPassword(String newpassword) {
        if (this.editor == null) {
            return;
        }

        this.editor.putString("password", newpassword);
    }
    
    public void setServer(String serverurl) {
        if (this.editor == null) {
            return;
        }
        this.editor.putString("serverurl", serverurl);
    }

    public void setValid(boolean v) {
        if (this.editor == null) {
            return;
        }
        this.editor.putBoolean("valid", v);
    }
    
    public boolean isValid() {
        if (this.prefs == null) {
            return false;
        }

        this.valid = this.prefs.getBoolean("valid", false);
        return this.valid;
    }
    
    public void save() {
        if (this.editor == null) {
            return;
        }
        this.editor.commit();
    }

}//end of class
