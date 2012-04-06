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
    protected ArrayList<String> record = new ArrayList<String>();
    private GeoPoint RecordLoc = new GeoPoint(0, 0);
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        Drawable mapMarker = this.getResources().getDrawable(R.drawable.pin);
        itemOverlay = new CustomItemizedOverlay<CustomOverlayItem>(mapMarker,mapView);
        
        items = null;
       
        getSites();
        
        
        populateMap();
	}
	
	protected void populateMap() {
		
		
		/* check or app will crash */
		if (items != null) {
			
			for (int i=0; i<items.size(); i++) {
				record = items.get(i);
				
				RecordLoc.equals(new GeoPoint(Integer.parseInt(record.get(3)),Integer.parseInt(record.get(4))));
					
				itemOverlay.addOverlay(new CustomOverlayItem( RecordLoc , record.get(1), record.get(2),record.get(5), record.get(0)));
				
			}
			
			mapOverlays.add(itemOverlay);
		}
		
		else{
			
		}
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
					
					record.add(obj.getString("pk_Site_siteID"));
					record.add(obj.getString("ak_Site_SiteName"));
					record.add(obj.getString("Site_Body2"));
					record.add(obj.getString("Site_Latitude"));
					record.add(obj.getString("Site_Longitude"));
					record.add(obj.getString("Img_Image"));
					
					items.add(record);
					record = null;
					

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
        /* adding overlays */
//        Drawable mapMarker = this.getResources().getDrawable(R.drawable.pin);
//        poiOverlay = new ItemOverlay(mapMarker);
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_LOCATION_CENTER, "", ""));
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MOUND_A, "",""));
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MUSEUM, "",""));
//        mapOverlays.add(poiOverlay);