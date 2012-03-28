package edu.ua.moundville;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class SiteMap extends PlaceMap {
	private final static GeoPoint MOUNDVILLE_SITES = new GeoPoint(33005263, -87631438);
	private ItemOverlay itemOverlay;
    private static Drawable mapMarker;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        mapMarker = this.getResources().getDrawable(R.drawable.pin);
        itemOverlay = new ItemOverlay(mapMarker);
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