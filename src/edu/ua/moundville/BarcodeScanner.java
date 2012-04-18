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
	private static final String BARCODE_FORMAT_SITE = "Site:[0-9]+";
	private static final String BARCODE_FORMAT_ARTIFACT = "Artifact:[0-9]+";
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
			
			Intent launchArticle = null;
			if (scanContent.matches(BARCODE_FORMAT_ARTIFACT)) {
				
				launchArticle = new Intent(this, ArtifactArticle.class);
				launchArticle.putExtra("artifact",scanContent.split(":")[1]);
				startActivity(launchArticle);
				
			}
			else if (scanContent.matches(BARCODE_FORMAT_SITE)){


				launchArticle = new Intent(this, SiteArticle.class);
				launchArticle = new Intent(this, SiteArticle.class);
				launchArticle.putExtra("site",scanContent.split(":")[1]);
				startActivity(launchArticle);
			}
		} else {
			Log.d(TAG, "Message is null!");
		}
		//Prevents activity screen from appearing on Android.back()
		finish();
	}
	 
	
}
