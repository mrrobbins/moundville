package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

import edu.ua.moundville.DBHandler.DBResult;

public class ArtifactMap extends PlaceMap implements DBResult {
	private final static GeoPoint MOUNDVILLE_SITES = new GeoPoint(33005263, -87631438);
	private String siteID;
	private String DBCase;
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
        
        getArtifacts();
	}
	
	protected void populateMap() {
		
		/* check or app will crash */
		if (items != null && items.size() != 0) {
			
			for (int i=0; i<items.size(); i++) {
				ArrayList<String> record = new ArrayList<String>();
				
				record = items.get(i);
				
				itemOverlay.addOverlay(new CustomOverlayItem(
						this, 
						(new GeoPoint((int) (Double.parseDouble(record.get(3))*1e6),(int) (Double.parseDouble(record.get(4))*1e6))), 
						record.get(1), 
						record.get(2),
						URL + record.get(5),
						record.get(0)));
			}
			
			mapOverlays.add(itemOverlay);
		}
		
		redrawMap();
	}
	
	private void getArtifacts() {
        DBCase = getIntent().getExtras().getString("case");
        queryArgs.add(new BasicNameValuePair("case", DBCase));
        
	    siteID = getIntent().getExtras().getString("site");
	    queryArgs.add(new BasicNameValuePair("site", siteID));
	    
	    db.sendQuery(this, queryArgs);
	}

	public void receiveResult(JSONArray jArray) {
		try {
			if (jArray == null) {
				items = null;
			}
			else if (((JSONObject)jArray.get(0)).getString("pk_Art_ArtID").equals("null")) {
				Toast.makeText(this, "No artifacts for this site", Toast.LENGTH_LONG).show();	
				finish();
			}

			else {
				Log.d(TAG, jArray.toString());

				for (int i=0; i<jArray.length(); i++) {
					JSONObject obj = null;

					obj = (JSONObject) jArray.get(i);
					ArrayList<String> record = new ArrayList<String>();

					record.add("artifact:"+obj.getString("pk_Art_ArtID"));
					record.add(obj.getString("ak_Art_Title"));
					record.add(obj.getString("Art_Body2"));
					record.add(obj.getString("Art_Latitude"));
					record.add(obj.getString("Art_Longitude"));
					record.add(obj.getString("Img_ImageThumb"));

					items.add(record);

				}
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
		populateMap();
	}
}
