
package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;

import com.readystatesoftware.mapviewballoons.*;

import edu.ua.moundville.DBHandler.DBResult;

public class SiteMap extends PlaceMap implements DBResult {
	private final static GeoPoint MOUNDVILLE_SITES = new GeoPoint(33005263, -87631438);
	CustomItemizedOverlay<CustomOverlayItem> itemOverlay;
    private static Drawable mapMarker;
    protected static DBHandler db = new DBHandler();
    protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();
    protected ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
    
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        mapMarker = this.getResources().getDrawable(R.drawable.pin);
        itemOverlay = new CustomItemizedOverlay<CustomOverlayItem>(mapMarker,mapView);
        
        getSites();
	}
	
	protected void populateMap() {
		
		/* check or app will crash */
		if (items != null && items.size() != 0) {
			
			for (int i=0; i<items.size(); i++) {
				ArrayList<String> record = new ArrayList<String>();
				
				record = items.get(i);
				
				itemOverlay.addOverlay(new CustomOverlayItem(this, (new GeoPoint((int) (Double.parseDouble(record.get(3))*1e6),(int) (Double.parseDouble(record.get(4))*1e6))) , record.get(1), record.get(2),URL + record.get(5), record.get(0)));
			}
			
			mapOverlays.add(itemOverlay);
		}
		
		redrawMap();
	}
	
	private void getSites() {
		queryArgs.add(new BasicNameValuePair("case","8"));
		db.sendQuery(this, queryArgs);
	}

	public void receiveResult(JSONArray jArray) {
		if (jArray == null) {
			items = null;
		} else {

			Log.d(TAG, jArray.toString());
			
			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				
				try {
					obj = (JSONObject) jArray.get(i);
					ArrayList<String> record = new ArrayList<String>();
					
					record.add("site:"+obj.getString("pk_Site_SiteID"));
					record.add(obj.getString("ak_Site_SiteName"));
					record.add(obj.getString("Site_Body2"));
					record.add(obj.getString("Site_Latitude"));
					record.add(obj.getString("Site_Longitude"));
					record.add(obj.getString("Img_ImageThumb"));
					
					items.add(record);

				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		}
		populateMap();
	}
}
