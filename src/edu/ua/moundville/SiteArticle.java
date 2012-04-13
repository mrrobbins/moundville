package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import edu.ua.moundville.DBHandler.DBResult;

public class SiteArticle extends Article implements DBResult {
	
	private static final String TAG = "ArtifactArticle";
	private String siteID = "";
	private String siteTitle = "";
	private String siteBody = "";
	protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
        queryArgs.add(new BasicNameValuePair("case", "7"));
	    siteID = getIntent().getExtras().getString("site");
	    queryArgs.add(new BasicNameValuePair("site", siteID));
	    db.sendQuery(this, queryArgs);
	}
	
	protected void prepareContent() {
		setTitle(siteTitle);
		
		Spanned formatedText = Html.fromHtml(siteBody);
		TextView siteBody = (TextView) findViewById(R.id.article_body);
		siteBody.setBackgroundColor(Color.BLACK);
		siteBody.setTextColor(Color.WHITE);
		siteBody.setTextSize(20);
		siteBody.setText(formatedText.toString());
		
		primaryImage = (ImageView)findViewById(R.id.primary_image);
		new FetchImageTask() { 
	        protected void onPostExecute(Bitmap result) {
	            if (result != null) {
	            	primaryImage.setImageBitmap(result);
	            }
	        }
	    }.execute(URL + "/" + primaryImageSubUrl);
	    
	    addSiteFields();
	}
	
	private void addSiteFields() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.article_linear_layout);
	}
	
	private void addOptionalFields(LinearLayout layout, ArrayList<String> fields, String DbCase, String argKey) {
		for (int i=0 ; i < fields.size(); i++) {
			addButton(layout, fields.get(i), DbCase, argKey);
		}
	}
	
	private void addToArrayList(ArrayList<String> list, String item) {
		for (int i = 0; i<list.size(); i++) {
			if (list.get(i).equals(item)) {
				return;
			}
		}
		
		list.add(item);
	}
	
	protected void addButton(LinearLayout layout, final String name, final String DBCase, final String argKey) {
		Button button = new Button(this);
		button.setText(name);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent launchActivity;
	        	launchActivity = new Intent(getApplicationContext(), ListItems.class);
	        	launchActivity.putExtra("case", DBCase);
	        	if (argKey != null) {
	        		launchActivity.putExtra(argKey, name);
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

	public void receiveResult(JSONArray jArray) {
		if (jArray == null) {
			//Handle Failure
		} else {
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					
					siteTitle = obj.getString("ak_Site_SiteName");
					siteBody = obj.getString("Site_Body");
					
					primaryImageSubUrl = obj.getString("Img_Image");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		prepareContent();
	}
}
