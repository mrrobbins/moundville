package edu.ua.moundville;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewer extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.image_viewer);
	    ImageView imageView = (ImageView)findViewById(R.id.image_view);
	    String imageFilePath = getIntent().getExtras().getString("image");
		Bitmap image = BitmapFactory.decodeFile(imageFilePath);
	    
	    if (image.getHeight() > image.getWidth()) {
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    } else {
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    }
	    imageView.setImageBitmap(image);
	}

}
