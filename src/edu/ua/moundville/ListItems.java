package edu.ua.moundville;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class ListItems extends ListActivity {
	
	protected String selectQuery;
	protected ArrayList<String> items;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.tut_titles, R.layout.simple_textview));
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	 
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	final String[] links = getResources().getStringArray(R.array.tut_links);
	        	String content = links[position];
	            Intent showContent = new Intent(getApplicationContext(), WebViewerActivity.class);
	            showContent.setData(Uri.parse(content));
	            startActivity(showContent);
	        }
	    });
	}
	
	protected void getItems() {
		
	}
	
	protected void launchArticle() {
		
	}
}
