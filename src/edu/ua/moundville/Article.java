package edu.ua.moundville;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import edu.ua.moundville.DBHandler.DBResult;

public abstract class Article extends Activity implements DBResult {

	protected final String URL = "http://betatesting.as.ua.edu/mapexperience/images";
	protected String primaryImageSubUrl;
	protected ImageView primaryImage;
	protected DBHandler db = new DBHandler();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.article);
	}
	
	protected abstract void prepareContent();
	
	protected void displayImage() {
		primaryImage = (ImageView)findViewById(R.id.primary_image);
		/* Check for a valid image URL. If not, hide the ImageView */
		if (primaryImageSubUrl == null || primaryImageSubUrl.matches("^(null)?$")) {
			primaryImage.setVisibility(View.GONE);	
		} else {
			primaryImage = (ImageView)findViewById(R.id.primary_image);
			new FetchImageTask() { 
				protected void onPostExecute(Bitmap result) {
					if (result != null) {
						primaryImage.setImageBitmap(result);
					}
				}
			}.execute(URL + "/" + primaryImageSubUrl);
		}
	}
	
	protected abstract void addFieldToLayout(LinearLayout layout, String text);
	
	protected class FetchImageTask extends AsyncTask<String, Integer, Bitmap> {
	    @Override
	    protected Bitmap doInBackground(String... arg0) {
	    	Bitmap b = null;
	    	try {
				 b = BitmapFactory.decodeStream((InputStream) new URL(arg0[0]).getContent());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
	        return b;
	    }	
	}
}
