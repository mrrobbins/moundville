package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;

public class MoundvilleActivity extends Activity {
	private final String TAG = "MoundvilleActivity";
	
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
    		Intent launchProximityList = new Intent(this, ListItems.class);
    		launchProximityList.putExtra("case", 3);
    		launchProximityList.putExtra("site", "2");
    		startActivity(launchProximityList);
    	} else if (view == findViewById(R.id.launch_barcode_scanner)) {
    		Log.d(TAG, "Clicked barcode_scanner");
    		Intent launchBarcodeScanner = new Intent(this, BarcodeScanner.class);
    		startActivity(launchBarcodeScanner);
    	}
    }
}
