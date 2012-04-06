package edu.ua.moundville;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class SiteMap extends PlaceMap {
	private final static GeoPoint MOUNDVILLE_SITES = new GeoPoint(33005263, -87631438);
	CustomItemizedOverlay<CustomOverlayItem> itemOverlay;
    private static Drawable mapMarker;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        Drawable mapMarker = this.getResources().getDrawable(R.drawable.pin);
        itemOverlay = new CustomItemizedOverlay<CustomOverlayItem>(mapMarker,mapView);
       
        
        itemOverlay.addOverlay(new CustomOverlayItem(MOUNDVILLE_LOCATION_CENTER, "Moundville", "The Geographical Center of the Park",""));
        itemOverlay.addOverlay(new CustomOverlayItem(MOUNDVILLE_MOUND_A, "Mound A","The second largest mound in the park",""));
        itemOverlay.addOverlay(new CustomOverlayItem(MOUNDVILLE_MUSEUM, "MOundville Museum","A collection of pottery awaits.",""));
        
        mapOverlays.add(itemOverlay);
        
        populateMap();
	}
	
	protected void populateMap() {
		int items = 0;
		
		
		/* check or app will crash */
		if (items != 0) {
			mapOverlays.add(itemOverlay);
		}
	}
	
	private void getSites() {
	
	}
}
        /* adding overlays */
//        Drawable mapMarker = this.getResources().getDrawable(R.drawable.pin);
//        poiOverlay = new ItemOverlay(mapMarker);
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_LOCATION_CENTER, "", ""));
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MOUND_A, "",""));
//        poiOverlay.addItem(new OverlayItem(MOUNDVILLE_MUSEUM, "",""));
//        mapOverlays.add(poiOverlay);