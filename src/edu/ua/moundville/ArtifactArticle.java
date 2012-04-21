package edu.ua.moundville;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
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

public class ArtifactArticle extends Article implements DBResult {
	
	private static final String TAG = "ArtifactArticle";
	private String artifactID;
	private String artifactTitle;
	private String artifactBody;
	private final String URL = "http://betatesting.as.ua.edu/mapexperience/images";
	private String[] timePeriod = new String[2];
	private ArrayList<String> categories= new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	protected ArrayList<NameValuePair> queryArgs = new ArrayList<NameValuePair>();
	protected static DBHandler db = new DBHandler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
	    
        queryArgs.add(new BasicNameValuePair("case", "6"));
	    artifactID = getIntent().getExtras().getString("artifact");
	    queryArgs.add(new BasicNameValuePair("art", artifactID));
	    db.sendQuery(this, queryArgs);
	}
	
	protected void prepareContent() {
		setTitle(artifactTitle);
		
		Spanned formatedText = Html.fromHtml(artifactBody);
		TextView articleBody = (TextView) findViewById(R.id.article_body);
		articleBody.setBackgroundColor(Color.BLACK);
		articleBody.setTextColor(Color.WHITE);
		articleBody.setTextSize(20);
		articleBody.setText(formatedText.toString());
		
		displayImage();
	    
	    addArticleFields();
	}
	
	private void addArticleFields() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.article_linear_layout);
		addFieldToLayout(layout, "Time Period");
		addButton(layout, timePeriod[1], 5, "timepd");
		if(categories.size() != 0){
		addFieldToLayout(layout, "Categories");
		}
		addOptionalFields(layout, categories, 4, "cat");
		if(tags.size() !=0){
		addFieldToLayout(layout, "Styles");
		}
		addOptionalFields(layout, tags, 2, "style");
	}
	
	private void addOptionalFields(LinearLayout layout, ArrayList<String> fields, Integer DbCase, String argKey) {
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
	
	protected void addButton(LinearLayout layout, final String name, final Integer DBCase, final String argKey) {
		Button button = (Button) getLayoutInflater().inflate(R.layout.article_button, null);
		button.setText(name);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent launchActivity;
	        	launchActivity = new Intent(v.getContext(), ListItems.class);
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

	public void receiveResult(JSONArray jArray, ArrayList<NameValuePair> params) {
		if (jArray == null) {
			Toast.makeText(this, "Error retrieving data...", Toast.LENGTH_LONG).show();
			finish();
		} else {
			Log.d(TAG, jArray.toString());

			for (int i=0; i<jArray.length(); i++) {
				JSONObject obj = null;
				try {
					obj = (JSONObject) jArray.get(i);
					
					artifactTitle = obj.getString("ak_Art_Title");
					if(obj.getString("Art_Body").matches("^(null)?$")){
						artifactBody = "";
					}
					else{
						artifactBody = obj.getString("Art_Body");
					}
					timePeriod[0] = obj.getString("pk_Time_TimeID");
					timePeriod[1] = obj.getString("ak_Time_TimePeriod");
					
					primaryImageSubUrl = obj.getString("Img_Image");
					
					if(!obj.getString("ak_Cat_Name").matches("^(null)?$")){
					addToArrayList(categories, obj.getString("ak_Cat_Name"));
					}
					if(!obj.getString("ak_Tag_Name").matches("^(null)?$")){
					addToArrayList(tags, obj.getString("ak_Tag_Name"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		prepareContent();
	}
}
