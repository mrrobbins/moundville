package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import edu.ua.moundville.DBHandler.DBResult;

public class SiteArticle extends Article implements DBResult {
	
	private static final String TAG = "SiteArticle";
	private String siteID = "";
	private String siteTitle = "";
	private String siteBody = "";
	protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();

	protected ArrayList<NameValuePair> queryArgs2 = new ArrayList<NameValuePair>();
	protected DBHandler db2 = new DBHandler();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
        queryArgs.add(new BasicNameValuePair("case", "7"));
	    siteID = getIntent().getExtras().getString("site");
	    queryArgs.add(new BasicNameValuePair("site", siteID));
	    db.sendQuery(this, queryArgs);
	    
	    queryArgs2.add(new BasicNameValuePair("case", "9"));
	    queryArgs2.add(new BasicNameValuePair("site", siteID));
	    
	    db2.sendQuery( this, queryArgs2);
		
	}
	
	protected void prepareContent() {
		setTitle(siteTitle);
		
		Spanned formatedText = Html.fromHtml(siteBody);
		TextView siteBody = (TextView) findViewById(R.id.article_body);
		siteBody.setBackgroundColor(Color.BLACK);
		siteBody.setTextColor(Color.WHITE);
		siteBody.setTextSize(20);
		siteBody.setText(formatedText.toString());
		
		displayImage();
		
	}
	
	private void addSiteFields() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.article_linear_layout);
		addButton(layout, "View map of artifacts", "3", "site", siteID);
	}
	
	private void addToastButton(){
		Button button = new Button(this);
		button.setText("View map of artifacts");
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(SiteArticle.this, "No artifacts for this site", Toast.LENGTH_LONG).show();	
			}
		});

		LinearLayout layout = (LinearLayout) findViewById(R.id.article_linear_layout);
		layout.addView(button);
	}
	
	protected void addButton(LinearLayout layout, final String name, final String DBCase, final String argKey, final String argValue) {
		Button button = new Button(this);
		button.setText(name);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent launchActivity;
	        	launchActivity = new Intent(SiteArticle.this, ArtifactMap.class);
	        	launchActivity.putExtra("case", DBCase);
	        	if (argKey != null) {
	        		launchActivity.putExtra(argKey, argValue);
	        	}
	        	
	        	startActivity(launchActivity);
			}
		});
		layout.addView(button);
	}
	
	protected void addFieldToLayout(LinearLayout layout, String text) {
		TextView textView = new TextView(this);
		textView.setText(text);
		textView.setGravity(Gravity.CENTER_HORIZONTAL);
		textView.setBackgroundColor(Color.rgb(139, 69, 19));
		layout.addView(textView);
	}

	public void receiveResult(JSONArray jArray, ArrayList<NameValuePair> params) {
		if (jArray == null) {
			//Handle Failure
		} else {
			if(params.get(0).getValue().equals("7")){
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					
					siteTitle = obj.getString("ak_Site_SiteName");
					
					if(obj.getString("Site_Body").matches("^(null)?$")){
						siteBody = "";
					}
					else{
						siteBody = obj.getString("Site_Body");
					}
					
					primaryImageSubUrl = obj.getString("Img_Image");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			else if (params.get(0).getValue().equals("9")){
				try{
					JSONObject obj = (JSONObject) jArray.getJSONObject(0);
					if(obj.getString("COUNT(*)").equals("0")){
						addToastButton();
					}
					else{
						addSiteFields();
					}
						
				}
				catch (JSONException e){
					e.printStackTrace();
				}
				
			}
		}
		
		prepareContent();
	}

}
