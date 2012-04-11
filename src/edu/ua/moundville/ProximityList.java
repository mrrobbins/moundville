package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import edu.ua.moundville.DBHandler.DBResult;

public class ProximityList extends ListActivity implements DBResult, LocationListener {
	
	private static final String DBCASE = "1";
	private static final long MIN_UPDATE_TIME = 5000;
	private static final float MIN_UPDATE_DISTANCE = 10;
	private static Location location = null;
	protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();
	protected final ArrayList<String> listItems = new ArrayList<String>();
	protected final ArrayList<String> listLinks = new ArrayList<String>();
	protected static DBHandler db = new DBHandler();
	private static final String TAG = "ProximityList";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setTitle("Near By");
	    
	    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    if (location == null) {
	    	location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    }
	    if (location == null) {
	    	// ToDo: error
	    	return;
	    }
	    
	    setupQuery();
	    
	    db.sendQuery(this, queryArgs);
	    
	    getListView().setOnItemClickListener(new OnItemClickListener() {
	    	 
	    	Intent launchActivity;
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	        	if (2 <= DBCase && DBCase <= 5) {
//	        		launchActivity.putExtra("artifact", listLinks.get(position));
//	            	launchActivity = new Intent(getApplicationContext(), ArtifactArticle.class);
//	        	} else {
//	        		launchActivity.putExtra("site", listLinks.get(position));
//	            	launchActivity = new Intent(getApplicationContext(), SiteArticle.class);
//	        	}
//	            startActivity(launchActivity);
	        }
	    });
	    
	    locationManager.requestLocationUpdates(
	    		LocationManager.GPS_PROVIDER, MIN_UPDATE_TIME, MIN_UPDATE_DISTANCE, this);
	}
	
	private void setupQuery() {
	    queryArgs.clear();
	    queryArgs.add(new BasicNameValuePair("case", DBCASE));
	    queryArgs.add(new BasicNameValuePair("lat", String.valueOf(location.getLatitude())));
	    queryArgs.add(new BasicNameValuePair("lon", String.valueOf(location.getLongitude())));
	}
	
	@Override
	public void onLocationChanged(Location newLocation) {
		location = newLocation;
		setupQuery();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveResult(JSONArray jArray) {
		if (jArray == null) {
			listItems.add("I failed :(");
		} else {
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					listItems.add(obj.getString("ak_Art_Title"));
					listLinks.add(obj.getString("pk_Art_ArtID"));
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
