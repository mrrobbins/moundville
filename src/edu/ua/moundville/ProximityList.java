package edu.ua.moundville;

import android.os.Bundle;

import com.google.android.maps.GeoPoint;

public class ProximityList extends ListItems {
	
	private static final String query = "Select items near me";
	private static GeoPoint location;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	}
	
	/* check if near moundville */
	private boolean isLocationApplicable() {
		return false;
	}
	
	private int getLongitude() {
		return 0;
	}
	
	private int getLatitude() {
		return 0;
	}
}
