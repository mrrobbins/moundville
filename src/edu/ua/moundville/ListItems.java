package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.commons.lang.WordUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	protected String title;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setCase();
	    
	    setupQuery();
	    
	    db.sendQuery(this, queryArgs);
	    
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	 
	    	Intent launchActivity;
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        	if (2 <= DBCase && DBCase <= 5) {
	            	launchActivity = new Intent(ListItems.this, ArtifactArticle.class);
	        		launchActivity.putExtra("artifact", listLinks.get(position));
	        	} else {
	            	launchActivity = new Intent(ListItems.this, SiteArticle.class);
	        		launchActivity.putExtra("site", listLinks.get(position));
	        	}
	            startActivity(launchActivity);
	        }
	    });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.default_menu, menu);
	    return true;
	}    
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, MoundvilleActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}    
	
	private void setCase() {
	    DBCase = getIntent().getExtras().getInt("case");
	    if (DBCase <= 0 ) {
	    	/* TODO error */
	    }
	    switch(DBCase) {
	    case 2:
	    	title="Style: " + WordUtils.capitalize(getIntent().getExtras().getString("style"));
	    	break;
	    case 3:
	    	title="Artifacts";
	    	break;
	    case 4:
	    	title="Category: " + WordUtils.capitalize(getIntent().getExtras().getString("cat"));
	    	break;
	    case 5:
	    	title="Time Period: " + WordUtils.capitalize(getIntent().getExtras().getString("timepd"));
	    	break;
	    case 8:
	    	title="All Sites";
	    	break;
	    default:
	    	title="List";
	    	break;
	    }
	    
	    setTitle(title);
	}
	
	private void setupQuery() {
	    queryArgs.add(new BasicNameValuePair("case", String.valueOf(DBCase)));
	    
	    switch (DBCase) {
	    	case 2:
	    		String styleName = getIntent().getExtras().getString("style");
	    		queryArgs.add(new BasicNameValuePair("style", styleName));
	    		break;
	    	case 3: 
	    		String siteID = getIntent().getExtras().getString("site");
	    		queryArgs.add(new BasicNameValuePair("site", siteID));
	    		Log.d(TAG, "siteID = " + siteID);
	    		break;
		    case 4: 
	    		String catName = getIntent().getExtras().getString("cat");
	    		queryArgs.add(new BasicNameValuePair("cat", catName));
	    		break;
		    case 5:
	    		String timepd = getIntent().getExtras().getString("timepd");
	    		queryArgs.add(new BasicNameValuePair("timepd", timepd));
	    		break;
		    case 8:
		    	break;
	    	// No case matched
	    	/* TODO return error code or display error toast before returning */
		    /* not working */
	    	default: finish();
	    }
	}
	
	public void receiveResult(JSONArray jArray, ArrayList<NameValuePair> params) {
		
		if (jArray == null) {
			listItems.add("Error retrieving data...");
		} else {
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					switch (DBCase) {
					case 2:
					case 3: 
					case 4: 
					case 5:
						listItems.add(obj.getString("ak_Art_Title"));
						listLinks.add(obj.getString("pk_Art_ArtID"));
						break;
					case 8:
						listItems.add(obj.getString("ak_Site_SiteName"));
						listLinks.add(obj.getString("pk_Site_SiteID"));
						break;
					/* No case matched. Should not happen. */
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
