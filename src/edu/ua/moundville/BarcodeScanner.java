package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScanner extends Activity {
	private final static String TAG = "BarcodeScanner";
	private static IntentResult scanResult = null;
	private static String scanContent = null;
	private static final String BARCODE_FORMAT = "[AS]:[0-9]+";

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
    	IntentIntegrator integrator = new IntentIntegrator(BarcodeScanner.this);
    	integrator.initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null && (scanContent = scanResult.getContents()) != null) {
			Log.d(TAG, scanContent);
			if (validContent(scanContent)) {
				
			}
		} else {
			Log.d(TAG, "Message is null!");
		}
		//Prevents activity screen from appearing on Android.back()
		finish();
	}
	
	private boolean validContent(String scanContent) {
		return scanContent.matches(BARCODE_FORMAT); 
	}
}
