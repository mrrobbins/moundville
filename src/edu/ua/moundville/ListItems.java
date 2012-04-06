package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import edu.ua.moundville.DBHandler.DBResult;

public class ListItems extends ListActivity implements DBResult {
	
	private static final String TAG = "ListItems";
	protected String selectQuery;
	protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();
	protected final ArrayList<String> items = new ArrayList<String>();
	protected final ArrayList<String> links = new ArrayList<String>();
	protected static DBHandler db = new DBHandler();
	protected int DBCase; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setupQuery();
	    
	    db.sendQuery(this, queryArgs);
	    
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	 
	    	Intent launchActivity;
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	if (DBCase == 5555) {
	            	launchActivity = new Intent(getApplicationContext(), ArtifactArticle.class);
	        	} else if (DBCase == 6666) {
	            	launchActivity = new Intent(getApplicationContext(), SiteArticle.class);
	        	}
	            startActivity(launchActivity);
	        }
	    });
	}
	
	private void setupQuery() {
		
	    DBCase = getIntent().getExtras().getInt("case");
	    switch (DBCase) {
	    	case 8: break;
	    	default: break;
	    }
	    
		queryArgs.add(new BasicNameValuePair("case", "8"));
	}
	
	protected void getItems() {
		
	}
	
	protected void launchArticle() {
		
	}
	
	public void receiveResult(JSONArray jArray) {
		
		if (jArray == null) {
			items.add("I failed :(");
		} else {

			Log.d(TAG, jArray.toString());


			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					items.add(obj.getString("ak_Site_SiteName"));

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	    
	    prepareList();
	}

	private void prepareList() {
	    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
	}
}
