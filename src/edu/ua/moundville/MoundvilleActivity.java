package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MoundvilleActivity extends Activity {
	private static final String TAG = "MoundvilleActivity";
	private static final String MOUNDVILLE_WEBSITE = "http://moundville.ua.edu/";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moundville_activity);
    }
    
    public void buttonHandler(View view) {
    	if (view == findViewById(R.id.launch_map)) {
    		Log.d(TAG, "Clicked launch_map");
    		Intent launchMap = new Intent(this, SiteMap.class);
    		startActivity(launchMap);
    	} else if (view == findViewById(R.id.launch_proximity_list)) {
    		Log.d(TAG, "Clicked proximity_list");
    		Intent launchProximityList = new Intent(this, ProximityList.class);
    		startActivity(launchProximityList);
    	} else if (view == findViewById(R.id.launch_barcode_scanner)) {
    		Log.d(TAG, "Clicked barcode_scanner");
    		Intent launchBarcodeScanner = new Intent(this, BarcodeScanner.class);
    		startActivity(launchBarcodeScanner);
    	} else if (view == findViewById(R.id.launch_about_info)) {
    		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MOUNDVILLE_WEBSITE));
    		startActivity(myIntent);
    	}
    }
} 