package com.example.alumniconnectionapp;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import data.User;

public class UsersAdapter extends BaseAdapter {
	
	private final Context context; 
	private List<User> userList; 
	
	//Constructor
	public UsersAdapter(Context context, List<User> userList){
		this.context = context; 
		this.userList = userList; 
	}
	
	public int getCount(){
		return userList.size(); 
	}
	
	public Object getItem(int position) {
        return this.userList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = userList.get(position);
        if (convertView == null || 
                !(convertView instanceof UserListView))
        {
            return new UserListView(context, user.name);
        }
        UserListView view = (UserListView)convertView;
        view.setName(user.name);
        return view;
    }
    
    /**
     * UserListView that adapter returns as its view item per row.
     * 
     * @author Reginald Daniel 
     */
    private final class UserListView extends LinearLayout {

        private TextView name;

        public UserListView(Context context, String userName) {

            super(context);
            setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 3, 5, 0);

            name = new TextView(context);
            name.setText(userName);
            name.setTextSize(16f);
            name.setTextColor(Color.RED);
            addView(name, params);
        }
        
        public void setName(String userName)
        {
            name.setText(userName);
        }
        
    }
	
	

}//end of class 
