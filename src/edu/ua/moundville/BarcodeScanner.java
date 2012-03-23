package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
			String content = scanResult.getContents();
			Log.d("BarcodeMessage", content);
		} else {
			Log.d("BarcodeMessage", "is null!");
		}
		//Prevents activity screen from appearing on back
		finish();
	}
}
