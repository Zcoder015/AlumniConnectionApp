package com.example.alumniconnectionapp;

import java.util.List;

import data.Post;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PostsAdapter extends BaseAdapter {
	
	private final Context context; 
	private List<Post> posts; 
	
	//Constructor
	public PostsAdapter(Context context, List<Post> posts){
		this.context = context; 
		this.posts = posts; 
	}
	
	public int getCount(){
		return posts.size(); 
	}
	
	public Object getItem(int position){
		return this.posts.get(position); 
	}
	
	public long getItemId(int position){
		return position; 
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
        Post post = posts.get(position);
        if (convertView == null || 
                !(convertView instanceof PostListView))
        {
            return new PostListView(context, post.content, post.postDate);
        }
        PostListView view = (PostListView)convertView;
        view.setContent(post.content);
        return view;
    }
	
	/**
	 * PostListView that adapter return as its view
	 * @author Reginald Daniel 
	 */
	
	private final class PostListView extends LinearLayout{
		
		private TextView content; 
		private TextView date; 
		
		//Constructor
		public PostListView(Context context, String passedContent, String passedDate ){
			
			super(context); 
			setOrientation(LinearLayout.VERTICAL); 
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
	        ViewGroup.LayoutParams.WRAP_CONTENT);
	        params.setMargins(5, 3, 5, 0);
	        
	        content = new TextView(context); 
            content.setText(passedContent);
            content.setTextSize(16f);
            content.setTextColor(Color.WHITE);
            addView(content, params);
            
            date = new TextView(context);
            date.setText(passedDate);
            date.setTextSize(10f);
            date.setTextColor(Color.GRAY);
            addView(date,params); 
            
		}
		
		public void setContent(String passedContent)
        {
            content.setText(passedContent);
        }
		
	}//end of PostListView

}//end of class
