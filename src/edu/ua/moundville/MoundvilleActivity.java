package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    		Intent launchMap = new Intent(this, PlaceMap.class);
    		startActivity(launchMap);
    	}
    }
}
