/***
 * Copyright (c) 2011 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package edu.ua.moundville;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
import com.readystatesoftware.mapviewballoons.BalloonOverlayView;


public class CustomItemizedOverlay<Item extends OverlayItem> extends BalloonItemizedOverlay<CustomOverlayItem> {

	private ArrayList<CustomOverlayItem> m_overlays = new ArrayList<CustomOverlayItem>();
	private Context c;
	private static final String FORMAT_SITE = "site:\\d+";
	private static final String FORMAT_ARTIFACT = "artifact:\\d+";
	
	public CustomItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenter(defaultMarker), mapView);
		c = mapView.getContext();
	}

	public void addOverlay(CustomOverlayItem overlay) {
	    m_overlays.add(overlay);
	    populate();
	}

	@Override
	protected CustomOverlayItem createItem(int i) {
		return m_overlays.get(i);
	}

	@Override
	public int size() {
		return m_overlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, CustomOverlayItem item) {
		final String itemID = item.getID();
		Toast.makeText(c, "Idenifier for Record " + itemID,
				Toast.LENGTH_LONG).show();
		
		Context outerContext = item.getContext();
		Intent launchArticle;
		if (itemID.matches(FORMAT_SITE)) {
			
			launchArticle = new Intent(outerContext, SiteArticle.class);
			launchArticle.putExtra("site", itemID.split(":")[1]);
			outerContext.startActivity(launchArticle);
			
		} else if (itemID.matches(FORMAT_ARTIFACT)) {
			launchArticle = new Intent(outerContext, ArtifactArticle.class);
			launchArticle.putExtra("artifact", itemID.split(":")[1]);
			outerContext.startActivity(launchArticle);
		} else {
			//error
		}
		return true;
	}

	@Override
	protected BalloonOverlayView<CustomOverlayItem> createBalloonOverlayView() {
		// use our custom balloon view with our custom overlay item type:
		return new CustomBalloonOverlayView<CustomOverlayItem>(getMapView().getContext(), getBalloonBottomOffset());
	}
	@Override
    public void draw(final Canvas canvas, final MapView mapView, final boolean shadow) {
        // Don't draw the shadow layer
        if (!shadow) {
            super.draw(canvas, mapView, shadow);
        }
    }
}
