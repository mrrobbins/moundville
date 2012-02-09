package edu.ua.moundville;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class PlaceMap extends MapActivity {
    private final static GeoPoint MOUNDVILLE_LOCATION_CENTER = new GeoPoint(33005263, -87631438);
	private MapView mapView;
	private MapController mapController;
    private ItemOverlay primaryOverlay;
    List<Overlay> overlays;
    Drawable mapMarker;
	
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
        
        /* adding overlays */
        overlays = mapView.getOverlays();
        mapMarker = this.getResources().getDrawable(R.drawable.map_marker_green);
        primaryOverlay = new ItemOverlay(mapMarker);
        OverlayItem i = new OverlayItem(MOUNDVILLE_LOCATION_CENTER, "", "");
        primaryOverlay.addItem(i);
        
        overlays.add(primaryOverlay);
    }
    
    @Override
    protected boolean isRouteDisplayed() {
    	return false;
    }
}
