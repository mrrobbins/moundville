package edu.ua.moundville;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PlaceMap extends MapActivity {
	private final static String TAG = "PlaceMap";
    private final MapActivity mapActivity = this;
    private final static GeoPoint MOUNDVILLE_LOCATION_CENTER = new GeoPoint(33005263, -87631438);
    private final static GeoPoint THE_BLUFF = new GeoPoint(3322029,-87527894);
    private final static GeoPoint MOUNDVILLE_MOUND_A = new GeoPoint(33006034,-87631124);
    private final static GeoPoint MOUNDVILLE_MUSEUM = new GeoPoint(33006176,-87634921);
    
	private MapView mapView;
	private MapController mapController;
	
    private ItemOverlay poiOverlay;
    private MyLocationOverlay locationOverlay;
    private List<Overlay> mapOverlays;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_map);
        
        /*	Capture the LinearLayout and MapView through their layout resources. */
        mapView = (MapView) findViewById(R.id.mapview);
        /* 	Then get the ZoomControls from the MapView */
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        mapView.setSaveEnabled(true);
        mapView.setWillNotCacheDrawing(false);
        
        /* get the controller to set custom pan and zoom */
        mapController = mapView.getController();
        mapController.animateTo(MOUNDVILLE_LOCATION_CENTER);
        mapController.setZoom(17);
        
        final ToggleButton toggleLocation = (ToggleButton)this.findViewById(R.id.toggle_location);
        final ToggleButton toggleSatellite = (ToggleButton)this.findViewById(R.id.toggle_satellite);
        
        /* adding overlays */
        mapOverlays = mapView.getOverlays();
        Drawable mapMarker = this.getResources().getDrawable(R.drawable.map_marker_green);
        poiOverlay = new ItemOverlay(mapMarker);
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_LOCATION_CENTER, "", ""));
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MOUND_A, "",""));
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MUSEUM, "",""));
        mapOverlays.add(poiOverlay);
        
        locationOverlay = new MyLocationOverlay(this, mapView); 
        
//      {
//        	@Override
//        	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
//        		
//        		GeoPoint myLocation = locationOverlay.getMyLocation();
//        	
//        		// Check if location is valid to draw
//        		if (myLocation != null) {
//
//					Point mapLocation = mapView.getProjection().toPixels(myLocation, null);
//
//					BitmapDrawable locationIndicator = (BitmapDrawable) mapActivity.getResources().getDrawable(R.drawable.moundville_launcher);
//
//					locationIndicator.setAntiAlias(true);
//					// Convert dp to pixel
//					int dimPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
//					dimPx /= 2;
//					locationIndicator.setBounds(mapLocation.x - dimPx, mapLocation.y - dimPx, mapLocation.x + dimPx, mapLocation.y + dimPx);
//					locationIndicator.draw(canvas);
//        		}
//        		
//        		return false;
//        	}
        	
//      };
        
        
        mapOverlays.add(locationOverlay);
       
        toggleLocation.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		if (toggleLocation.isChecked()) {
        			locationOverlay.enableMyLocation();
        			Toast.makeText(v.getContext(), "Finding your location...", Toast.LENGTH_SHORT).show();
        			locationOverlay.runOnFirstFix(new Runnable(){
        				public void run() {
        					Runnable action = null;
        					GeoPoint userLocation = locationOverlay.getMyLocation();

        					if (userLocation == null) {
        						action = new Runnable() {
        							public void run() {
        								Toast.makeText(findViewById(R.id.mapview).getContext(), "Your location could not be determined.", Toast.LENGTH_LONG).show();
        								toggleLocation.performClick();
        							}
        						};

        					} else if (isLocationInRange(userLocation, MOUNDVILLE_LOCATION_CENTER, 100)) {
        						mapController.setZoom(17);
        					} else {
        						action = new Runnable() {
        							public void run() {
        								Toast.makeText(findViewById(R.id.mapview).getContext(), "You're location cannot be displayed.\n   You are too far from Moundville.", Toast.LENGTH_LONG).show();
        								toggleLocation.performClick();
        							}
        						};
        					}
        					if (action != null) runOnUiThread(action);
        				}
        			});
        		} else {
        			locationOverlay.disableMyLocation();
        		}
        	}
        });
        
        toggleSatellite.setChecked(true);
        toggleSatellite.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		if (toggleSatellite.isChecked()) {
        			mapView.setSatellite(true);
        		} else {
        			mapView.setSatellite(false);
        		}
        	} });
    }
    
    private static boolean isLocationInRange(GeoPoint x, GeoPoint y, double distance) {
    	double microDegreeDistance = Math.sqrt(Math.pow(x.getLatitudeE6()-y.getLatitudeE6(),2) + Math.pow(x.getLongitudeE6()-y.getLongitudeE6(),2));
    	// divide micro-degrees by meters per micro-degree
    	double meterDistance = microDegreeDistance / 0.1113196;
    	Log.d(TAG, String.format("meterDistance = %f", meterDistance));
    	return meterDistance < distance ? true : false;
    }
        

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	protected void onPause() {
		super.onPause();
	}
}
