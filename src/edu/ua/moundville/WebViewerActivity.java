package edu.ua.moundville;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewerActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.simple_web_viewer);
	    
        Intent launchingIntent = getIntent();
        String content = launchingIntent.getData().toString();
 
        WebView viewer = (WebView) findViewById(R.id.simple_web_viewer);
        viewer.loadUrl(content);	
	}

}
