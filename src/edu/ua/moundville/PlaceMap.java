package edu.ua.moundville;

import java.util.List;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public abstract class PlaceMap extends MapActivity {

	protected final String URL = "http://betatesting.as.ua.edu/mapexperience/images/";
	protected final static String TAG = "PlaceMap";
    protected final MapActivity mapActivity = this;
    protected final static GeoPoint MOUNDVILLE_LOCATION_CENTER = new GeoPoint(33005263, -87631438);
    protected static boolean satelliteState;
    protected static boolean locationState;
    protected static boolean firstTimeIn;
    protected static boolean systemResuming;
    
	protected MapView mapView;
	protected MapController mapController;
    protected ToggleButton toggleLocation;
    protected ToggleButton toggleSatellite;
	
    protected MyLocationOverlay locationOverlay;
    protected List<Overlay> mapOverlays;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	    setContentView(R.layout.place_map);
        toggleLocation = (ToggleButton)this.findViewById(R.id.toggle_location);
        toggleSatellite = (ToggleButton)this.findViewById(R.id.toggle_satellite);
        /*	Capture the LinearLayout and MapView through their layout resources. */
        mapView = (MapView) findViewById(R.id.mapview);
        /* 	Then get the ZoomControls from the MapView */
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(false);
        mapView.setSaveEnabled(true);
        mapView.setWillNotCacheDrawing(false);
        
        /* get the controller to set custom pan and zoom */
        mapController = mapView.getController();
        mapController.animateTo(MOUNDVILLE_LOCATION_CENTER);
        mapController.setZoom(17);
        
        
        /* adding overlays */
        mapOverlays = mapView.getOverlays();
        locationOverlay = new MyLocationOverlay(this, mapView); 
        mapOverlays.add(locationOverlay);
        
        firstTimeIn = false;
        systemResuming = false;
        toggleLocation.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		if (toggleLocation.isChecked()) {
        			locationOverlay.enableMyLocation();
        			if (firstTimeIn == true || !systemResuming) {
        				Toast.makeText(v.getContext(), "Finding your location...", Toast.LENGTH_SHORT).show();
        				Toast.makeText(v.getContext(), "You may need to zoom out to see your location displayed.", Toast.LENGTH_LONG).show();
        			}
        			locationOverlay.runOnFirstFix(new Runnable(){
        				public void run() {
        					Runnable action = null;
        					GeoPoint userPoint = locationOverlay.getMyLocation();

        					if (userPoint == null) {
        						action = new Runnable() {
        							public void run() {
        								Toast.makeText(findViewById(R.id.mapview).getContext(), "Your location could not be determined.", Toast.LENGTH_LONG).show();
        								toggleLocation.performClick();
        							}
        						};

//        					} else if (!isLocationInRange(userPoint, MOUNDVILLE_LOCATION_CENTER, 3000)) {
//        						action = new Runnable() {
//        							public void run() {
//        								Toast.makeText(findViewById(R.id.mapview).getContext(), "You're location cannot be displayed.\n   You are too far from Moundville.", Toast.LENGTH_LONG).show();
//        								toggleLocation.performClick();
//        							}
//        						};
//        					} else {
//        						mapController.animateTo(userPoint);
        					}
        					if (action != null) {
        						runOnUiThread(action);
        					}
        				}
        			});
        		} else {
        			locationOverlay.disableMyLocation();
        		}
        	}
        });
        
        toggleSatellite.setChecked(false);
        toggleSatellite.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		if (toggleSatellite.isChecked()) {
        			mapView.setSatellite(true);
        			Runnable action = new Runnable() {
						@Override
						public void run() {
							Toast.makeText(findViewById(R.id.mapview).getContext(), "Map may load slower in Satellite view", Toast.LENGTH_LONG).show();
						}
					};
					if (firstTimeIn == true || !systemResuming) {
						runOnUiThread(action);
					}
        		} else {
        			mapView.setSatellite(false);
        		}
        	} });
        firstTimeIn = false;
    }
    
    abstract protected void populateMap();
    
    protected void redrawMap() {
    	mapView.invalidate();
    }
    
    public static boolean isLocationInRange(GeoPoint point1, GeoPoint point2, float distance) {
    	Location location1 = new Location("MyLocationOverlay");
    	location1.setLatitude(point1.getLatitudeE6() / 1E6d);
    	location1.setLongitude(point1.getLongitudeE6() / 1E6d);
    	
    	Location location2 = new Location("MyLocationOverlay");
    	location2.setLatitude(point2.getLatitudeE6() / 1E6d);
    	location2.setLongitude(point2.getLongitudeE6() / 1E6d);
        
    	float meterDistance = location1.distanceTo(location2);
    	float[] result = new float[1];
    	Location.distanceBetween(
    			location1.getLatitude(),
    			location1.getLongitude(),
    			location2.getLatitude(),
    			location2.getLongitude(), 
    			result);
    	meterDistance = result[0];
    	
    	Log.d(TAG, "meterDistance: " + String.valueOf(meterDistance));
    	Log.d(TAG, "loc1: " + String.valueOf(location1.getLatitude()) + ", " + String.valueOf(location1.getLongitude()));
    	Log.d(TAG, "loc1: " + String.valueOf(location2.getLatitude()) + ", " + String.valueOf(location2.getLongitude()));
    	
    	return meterDistance <= distance ? true : false;
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
    
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public static boolean isRoute() {
		return true;
	}
	
	protected void onPause() {
		super.onPause();
		if (toggleSatellite.isChecked() == true) {
			satelliteState = true;
		} else {
			satelliteState = false;
		}
		if (toggleLocation.isChecked() == true) {
			locationState = true;
		} else {
			locationState = false;
		}
	}
	protected void onResume() {
		super.onResume();
		systemResuming = true;
		if (satelliteState == true && !toggleSatellite.isChecked()) {
			toggleSatellite.performClick();
		}
		if (locationState == true && !toggleLocation.isChecked()) {
			toggleLocation.performClick();
		}
		systemResuming = false;
	}
}
