package edu.ua.moundville;

import java.util.List;

import android.graphics.drawable.Drawable;
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
        
        /* get the controller to set custom pan and zoom */
        mapController = mapView.getController();
        mapController.animateTo(MOUNDVILLE_LOCATION_CENTER);
        mapController.setZoom(17);
        
        final Button btnMyLoc = (Button)this.findViewById(R.id.btn_myLocation);
        final ToggleButton toggleLayer = (ToggleButton)this.findViewById(R.id.btn_layer_toggle);
        
        /* adding overlays */
        mapOverlays = mapView.getOverlays();
        Drawable mapMarker = this.getResources().getDrawable(R.drawable.map_marker_green);
        poiOverlay = new ItemOverlay(mapMarker);
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_LOCATION_CENTER, "", ""));
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MOUND_A, "",""));
        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MUSEUM, "",""));
        mapOverlays.add(poiOverlay);
        
        locationOverlay = new MyLocationOverlay(this, mapView) {
        	
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
        	
        };
        
        locationOverlay.enableMyLocation();
//        locationOverlay.runOnFirstFix(new Runnable() {
//        	public void run() {
//        		mapController.animateTo(locationOverlay.getMyLocation());
//        	}
//        });
        
        mapOverlays.add(locationOverlay);
        
        btnMyLoc.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		//Toast.makeText(v.getContext(), "Finding your location...", Toast.LENGTH_LONG).show();
        		
        		GeoPoint myLocation = locationOverlay.getMyLocation();
        		if (myLocation == null) {
        			Toast.makeText(v.getContext(), "Location could not be found", Toast.LENGTH_SHORT).show();
        		} else if (isLocationInRange(myLocation, MOUNDVILLE_LOCATION_CENTER, 100)) {
        			mapController.animateTo(locationOverlay.getMyLocation());
        			Toast.makeText(v.getContext(), "Showing your location near Moundville...", Toast.LENGTH_LONG);
        		} else {
        			Toast.makeText(v.getContext(), "Your location is too far from Moundville...", Toast.LENGTH_LONG);
        		}
        	}
        });
        
        toggleLayer.setChecked(false);
        toggleLayer.performClick();
        toggleLayer.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		if (toggleLayer.isChecked()) {
        			mapView.setSatellite(true);
        		} else {
        			mapView.setSatellite(false);
        		}
        	}
        });
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
}
