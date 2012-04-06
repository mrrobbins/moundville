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
	protected final ArrayList<String> listItems = new ArrayList<String>();
	protected final ArrayList<String> listLinks = new ArrayList<String>();
	protected static DBHandler db = new DBHandler();
	protected int DBCase = -1; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setupQuery();
	    
	    db.sendQuery(this, queryArgs);
	    
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	 
	    	Intent launchActivity;
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	if (2 <= DBCase && DBCase <= 5) {
	        		launchActivity.putExtra("artifact", listLinks.get(position));
	            	launchActivity = new Intent(getApplicationContext(), ArtifactArticle.class);
	        	} else {
	        		launchActivity.putExtra("site", listLinks.get(position));
	            	launchActivity = new Intent(getApplicationContext(), SiteArticle.class);
	        	}
	            startActivity(launchActivity);
	        }
	    });
	}
	
	private void setupQuery() {
	    DBCase = getIntent().getExtras().getInt("case");
	    if (DBCase <= 0 ) {
	    	/* TODO error */
	    }
	    queryArgs.add(new BasicNameValuePair("case", String.valueOf(DBCase)));
	    switch (DBCase) {
	    	case 2:
	    		String styleName = getIntent().getExtras().getString("style");
	    		queryArgs.add(new BasicNameValuePair("style", styleName));
	    		break;
	    	case 3: 
	    		String siteID = getIntent().getExtras().getString("site");
	    		queryArgs.add(new BasicNameValuePair("site", siteID));
	    		break;
		    case 4: 
	    		String catName = getIntent().getExtras().getString("cat");
	    		queryArgs.add(new BasicNameValuePair("cat", catName));
	    		break;
		    case 5:
	    		String timepd = getIntent().getExtras().getString("timepd");
	    		queryArgs.add(new BasicNameValuePair("timepd", timepd));
	    		break;
	    	// No case matched
	    	/* TODO return error code or display error toast before returning */
	    	default: finish();
	    }
	}
	
	protected void launchArticle() {
		
	}
	
	public void receiveResult(JSONArray jArray) {
		
		if (jArray == null) {
			listItems.add("I failed :(");
		} else {
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					switch (DBCase) {
					case 2:
						listItems.add(obj.getString("ak_Tag_Name"));
						listLinks.add(obj.getString("pk_Tag_TagID"));
						break;
					case 3: 
						listItems.add(obj.getString("ak_Site_SiteName"));
						listLinks.add(obj.getString("pk_Site_SiteID"));
						break;
					case 4: 
						listItems.add(obj.getString("ak_Cat_Name"));
						listLinks.add(obj.getString("pk_Cat_CatID"));
						break;
					case 5:
						listItems.add(obj.getString("ak_Time_TimeID"));
						listLinks.add(obj.getString("pk_Time_TimePeriod"));
						break;
						// No case matched
						/* TODO return error code or display error toast before returning */
					default: 
						Log.e(TAG, "Failed to parse return json");
						finish();
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	    
	    prepareList();
	}

	private void prepareList() {
	    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems));
	}
}
