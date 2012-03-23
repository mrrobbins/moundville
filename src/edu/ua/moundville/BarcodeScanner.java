package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanner extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
    	IntentIntegrator integrator = new IntentIntegrator(BarcodeScanner.this);
    	integrator.initiateScan();
	
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
		// handle scan result
		}
		// else continue with any other code you need in the method
	}

}
